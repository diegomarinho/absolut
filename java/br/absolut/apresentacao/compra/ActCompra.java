package br.absolut.apresentacao.compra;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.apache.commons.lang.StringUtils;

import br.absolut.apresentacao.seguranca.Seguranca;
import br.absolut.util.function.Funcoes;

public class ActCompra extends Seguranca {

	private static final long serialVersionUID = -3573294781265126953L;
	private CtlCompra ctlCompra;
	private DtoCompra dtoCompra = new DtoCompra();
	private DtoPesquisaItem dtoPesquisaItem = new DtoPesquisaItem();
	private List<DtoItem> listaItem = new ArrayList<DtoItem>();
	private List<SelectItem> listaFornecedor;
	private List<SelectItem> listaTipoPagamento;
	private List<SelectItem> listaProduto;
	private boolean novaCompra;

	public ActCompra() throws Exception {
		super(Seguranca.PERFIL_ACESSO_FUNCIONARIO);
		this.dtoCompra.setQtdProduto("0");
		this.dtoCompra.setTotal("0,00");

	}

	public String salvar() {
		if (verificaCamposPreenchidos()) {
			dtoCompra.setListaItens(listaItem);
			dtoCompra.setCodUsuario(this.getDtoUsuario().getCod());
			String codigoCompra = ctlCompra.salvarCompra(dtoCompra);

			if (codigoCompra != null) {
				addMsgInfo("Compra de código " + codigoCompra + " cadastrada com sucesso!");
				novaCompra = true;
			}
		}
		return "";
	}

	public String cancelar() {
		dtoCompra.setCod(null);
		dtoCompra.setCodFornecedor("");
		dtoCompra.setCodPagamento("");
		dtoCompra.setCodUsuario(null);
		dtoCompra.setDtCompra(null);
		dtoCompra.setListaItens(null);
		dtoCompra.setNotaFiscal("");
		dtoCompra.setQtdProduto("0");
		dtoCompra.setTotal("0,00");
		listaItem.clear();
		return "";
	}

	public String nova() {
		cancelar();
		novaCompra = false;

		return "";
	}

	private boolean verificaCamposPreenchidos() {
		boolean retorno = true;
		if (dtoCompra.getNotaFiscal() == null || dtoCompra.getNotaFiscal().isEmpty()) {
			addMsgErro("O campo 'Nota fiscal' é de preenchimento obrigatório");
			retorno = false;
		}
		if (dtoCompra.getDtCompra() == null || dtoCompra.getDtCompra().toString().isEmpty()) {
			addMsgErro("O campo 'Data' é de preenchimento obrigatório");
			retorno = false;
		}
		if (dtoCompra.getCodFornecedor() == null || dtoCompra.getCodFornecedor().isEmpty()) {
			addMsgErro("O campo 'Fornecedor' é de preenchimento obrigatório");
			retorno = false;
		}
		if (listaItem.isEmpty()) {
			addMsgErro("é obrigatório a inclusão de no mínimo um item");
			retorno = false;
		}
		if (dtoCompra.getCodPagamento() == null || dtoCompra.getCodPagamento().equals("0")) {
			addMsgErro("O campo 'Tipo pagamento' é de preenchimento obrigatório");
			retorno = false;
		}

		return retorno;
	}

	public String adicionaItem() {
		DtoItem item = null;
		if (validaItemAdicionado()) {
			if (dtoPesquisaItem.getCodProduto() != null && !dtoPesquisaItem.getCodProduto().isEmpty()) {
				item = ctlCompra.recuperaProduto(new Long(dtoPesquisaItem.getCodProduto()));
			}
			if (item != null) {
				item.setValCompra(dtoPesquisaItem.getValorCompra());
				item.setValVenda(dtoPesquisaItem.getValorVenda());
				item.setQuantidade(dtoPesquisaItem.getQuantidade());
				item.setTotal(dtoPesquisaItem.getValorTotal());

				listaItem.add(item);

				// altera valores totais (quantidade e total)
				this.dtoCompra.setQtdProduto(Integer.parseInt(this.dtoCompra.getQtdProduto()) + Integer.parseInt(item.getQuantidade()) + "");
				this.dtoCompra.setTotal(Funcoes.formataValor(Funcoes.stringToDouble(this.dtoCompra.getTotal())
						+ Funcoes.stringToDouble(item.getTotal())));
			}

			limpaDtoPesquisaItem();
		}

		return "";
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean validaItemAdicionado() {
		boolean validou = Boolean.TRUE;
		
		if(StringUtils.isEmpty(dtoPesquisaItem.getCodProduto())) {
			addMsgErro("O campo 'Produto' é de preenchimento obrigatório");
			validou = Boolean.FALSE;
		}
		
		if(StringUtils.isEmpty(dtoPesquisaItem.getValorCompra())) {
			addMsgErro("O campo 'Preço compra' é de preenchimento obrigatório");
			validou = Boolean.FALSE;
		}
		
		if(StringUtils.isEmpty(dtoPesquisaItem.getQuantidade())) {
			addMsgErro("O campo 'Quantidade' é de preenchimento obrigatório");
			validou = Boolean.FALSE;
		}
		
		if(StringUtils.isEmpty(dtoPesquisaItem.getValorTotal())) {
			addMsgErro("O campo 'Valor total' é de preenchimento obrigatório");
			validou = Boolean.FALSE;
		}
		
		return validou;
	}

	public String excluiItem() {
		List<DtoItem> listaItemAux = new ArrayList<DtoItem>();
		if (listaItem != null && !listaItem.isEmpty()) {
			for (DtoItem item : listaItem) {
				if (item.isSelecionado()) {
					listaItemAux.add(item);
				}
			}
		}
		for (DtoItem item : listaItemAux) {
			dtoCompra.setQtdProduto(Integer.parseInt(dtoCompra.getQtdProduto()) - Integer.parseInt(item.getQuantidade()) + "");
			dtoCompra.setTotal(Funcoes.formataValor(Funcoes.stringToDouble(dtoCompra.getTotal()) - Funcoes.stringToDouble(item.getTotal())));
			listaItem.remove(item);
		}
		return "";
	}

	private void limpaDtoPesquisaItem() {
		if (dtoPesquisaItem != null) {
			dtoPesquisaItem.setCodProduto("");
			dtoPesquisaItem.setQuantidade("");
			dtoPesquisaItem.setValorTotal("");
			dtoPesquisaItem.setValorCompra("");
			dtoPesquisaItem.setValorVenda("");
			dtoPesquisaItem.setLucro("");
		} else {
			dtoPesquisaItem = new DtoPesquisaItem();
		}

	}

	public void limpaListaFornecedor(ActionEvent evt) {
		listaFornecedor.clear();
	}

	public String calculaValorTotalItem() {
		if ((dtoPesquisaItem.getValorCompra() != null && !dtoPesquisaItem.getValorCompra().isEmpty()) && (dtoPesquisaItem.getQuantidade() != null && !dtoPesquisaItem.getQuantidade().isEmpty())) {
			double total = (Funcoes.stringToDouble(dtoPesquisaItem.getValorCompra()) * Integer.parseInt(dtoPesquisaItem.getQuantidade()));
			dtoPesquisaItem.setValorTotal(Funcoes.formataValor(total));
		}
		return "";
	}

	public String calculaLucro() {
		if ((dtoPesquisaItem.getValorCompra() != null && !dtoPesquisaItem.getValorCompra().isEmpty()) && (dtoPesquisaItem.getLucro() != null && !dtoPesquisaItem.getLucro().isEmpty())) {

			double valorCompra = Funcoes.stringToDouble(dtoPesquisaItem.getValorCompra());
			double aux = dtoPesquisaItem.getLucro() != null && !dtoPesquisaItem.getLucro().isEmpty() ? new Double(dtoPesquisaItem.getLucro()) : 0.0;
			double lucro = Funcoes.calculaLucro(aux, valorCompra);
			double valorVenda = valorCompra + lucro;

			dtoPesquisaItem.setValorVenda(Funcoes.formataValor(valorVenda));
		} else {
			dtoPesquisaItem.setValorVenda(dtoPesquisaItem.getValorCompra());
		}
		return "";
	}

	public boolean isListaItensVazia() {
		return listaItem.isEmpty();
	}

	public DtoCompra getDtoCompra() {
		return dtoCompra;
	}

	public void setDtoCompra(DtoCompra dtoCompra) {
		this.dtoCompra = dtoCompra;
	}

	public List<SelectItem> getListaFornecedor() {
		if (listaFornecedor == null || listaFornecedor.isEmpty()) {
			listaFornecedor = ctlCompra.recuperaListaFornecedor();
		}

		return listaFornecedor;
	}

	public void setListaFornecedor(List<SelectItem> listaFornecedor) {
		this.listaFornecedor = listaFornecedor;
	}

	public List<SelectItem> getListaTipoPagamento() {
		if (listaTipoPagamento == null || listaTipoPagamento.isEmpty()) {
			listaTipoPagamento = ctlCompra.recuperaListaPagamento();
		}

		return listaTipoPagamento;
	}

	public void setListaTipoPagamento(List<SelectItem> listaTipoPagamento) {
		this.listaTipoPagamento = listaTipoPagamento;
	}

	public List<SelectItem> getListaProduto() {
		if (listaProduto == null || listaProduto.isEmpty()) {
			listaProduto = ctlCompra.recuperaListaProduto();
		}

		return listaProduto;
	}

	public void setListaProduto(List<SelectItem> listaProduto) {
		this.listaProduto = listaProduto;
	}

	public List<DtoItem> getListaItem() {
		return listaItem;
	}

	public void setListaItem(List<DtoItem> listaItem) {
		this.listaItem = listaItem;
	}

	public DtoPesquisaItem getDtoPesquisaItem() {
		return dtoPesquisaItem;
	}

	public void setDtoPesquisaItem(DtoPesquisaItem dtoPesquisaItem) {
		this.dtoPesquisaItem = dtoPesquisaItem;
	}

	public boolean isNovaCompra() {
		return novaCompra;
	}

	public void setNovaCompra(boolean novaCompra) {
		this.novaCompra = novaCompra;
	}

	public void setCtlCompra(CtlCompra ctlCompra) {
		this.ctlCompra = ctlCompra;
	}

}
