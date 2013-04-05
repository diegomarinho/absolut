package br.absolut.apresentacao.venda;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import br.absolut.apresentacao.compra.CtlCompra;
import br.absolut.apresentacao.compra.DtoItem;
import br.absolut.apresentacao.mantemcliente.CtlMantemCliente;
import br.absolut.apresentacao.mantemcliente.DtoCliente;
import br.absolut.apresentacao.mantemproduto.CtlMantemProduto;
import br.absolut.apresentacao.relatorio.DtoRelatorioVenda;
import br.absolut.apresentacao.seguranca.Seguranca;
import br.absolut.util.filter.report.ReportUtil;
import br.absolut.util.function.Funcoes;

public class ActVenda extends Seguranca {

	private static final long serialVersionUID = -6623826661509299336L;
	private CtlVenda ctlVenda;
	private CtlMantemCliente ctlCliente;
	private CtlMantemProduto ctlProduto;
	private CtlCompra ctlCompra;
	private DtoVenda dtoVenda = new DtoVenda();
	private DtoItem dtoItem = new DtoItem();
	private List<DtoItem> listaItemAdicionado = new ArrayList<DtoItem>();
	private List<SelectItem> listaProduto;
	private List<SelectItem> listaTipoPagamento;
	private Long codProduto;
	private Long codCliente;
	private String styleEstoque;
	private boolean podeAddItem;
	private boolean novaVenda;
	private Long codigoVenda;

	public ActVenda() throws Exception {
		super(Seguranca.PERFIL_ACESSO_FUNCIONARIO);
		this.dtoVenda.setQtdProdutos("0");
		this.dtoVenda.setSubTotal("0,00");
		this.dtoVenda.setDtPedido(new Date());
	}

	public String salvar() {
		if (verificaCamposPreenchidos()) {
			dtoVenda.setListaItem(listaItemAdicionado);
			dtoVenda.setCodUsuario(this.getDtoUsuario().getCod());
			Long codigoVenda = ctlVenda.salvarVenda(dtoVenda);

			if (codigoVenda != null) {
				this.codigoVenda = codigoVenda;
				addMsgInfo("Venda de código " + codigoVenda + " cadastrada com sucesso!");
				novaVenda = true;
			}
		}

		return "";
	}

	private boolean verificaCamposPreenchidos() {
		boolean retorno = true;
		if (dtoVenda.getDtPedido() == null || dtoVenda.getDtPedido().toString().isEmpty()) {
			addMsgErro("O campo 'Data' é de preenchimento obrigatório");
			retorno = false;
		}
		if (dtoVenda.getCodCliente() == null) {
			addMsgErro("O campo 'Cliente' é de prenchimento obrigatório");
			retorno = false;
		}
		if (listaItemAdicionado == null || listaItemAdicionado.isEmpty()) {
			addMsgErro("Ao menos um item deve ser adicionado");
			retorno = false;
		}

		if (dtoVenda.getCodPagamento() == null || dtoVenda.getCodPagamento().intValue() == 0) {
			addMsgErro("O campo 'Forma pagamento' é de preenchimento obrigatório");
			retorno = false;
		}
		return retorno;
	}

	public String adicionaItem() {
		if (dtoItem != null && dtoItem.getCodItem() != null) {
			listaItemAdicionado.add(dtoItem);

			// altera valores totais (quantidade e total)
			this.dtoVenda.setQtdProdutos(Integer.parseInt(this.dtoVenda.getQtdProdutos()) + Integer.parseInt(dtoItem.getQuantidade()) + "");
			if (dtoItem.getTotal() != null && !dtoItem.getTotal().isEmpty()) {
				this.dtoVenda.setSubTotal(Funcoes.formataValor(Funcoes.stringToDouble(this.dtoVenda.getSubTotal())
						+ Funcoes.stringToDouble(dtoItem.getTotal())));
				dtoVenda.setTotal(dtoVenda.getSubTotal());
			}

			// limpar dtoItem
			limpaDtoItem();
		}
		return "";
	}

	public String excluiItem() {
		List<DtoItem> listaItemAux = new ArrayList<DtoItem>();
		if (listaItemAdicionado != null && !listaItemAdicionado.isEmpty()) {
			for (DtoItem item : listaItemAdicionado) {
				if (item.isSelecionado()) {
					listaItemAux.add(item);
				}
			}
		}
		for (DtoItem item : listaItemAux) {
			dtoVenda.setQtdProdutos(Integer.parseInt(dtoVenda.getQtdProdutos()) - Integer.parseInt(item.getQuantidade()) + "");
			if (item.getTotal() != null && !item.getTotal().isEmpty()) {
				dtoVenda.setSubTotal(Funcoes.formataValor(Funcoes.stringToDouble(dtoVenda.getSubTotal()) - Funcoes.stringToDouble(item.getTotal())));
			}
			listaItemAdicionado.remove(item);
		}
		return "";
	}

	public boolean isListaItensVazia() {
		return listaItemAdicionado.isEmpty();
	}

	public String nova() {
		cancelar();
		novaVenda = false;
		this.dtoVenda.setDtPedido(new Date());

		return "";
	}

	private void limpaDtoItem() {
		dtoItem = new DtoItem();
	}

	public String gerarRelatorioVendaImpressao() {
		try {
			List<DtoRelatorioVenda> listaRelatorioVenda = ctlVenda.geraRelatorioVenda(codigoVenda);
			ReportUtil.gerarRelatorio("relVenda", listaRelatorioVenda);
		} catch (Exception e) {
			addMsgErro("Erro ao gerar o arquivo para impressão");
			e.printStackTrace();
		}

		return "";
	}

	public String cancelar() {
		dtoVenda.setCodCliente(null);
		dtoVenda.setCodPedido(null);
		dtoVenda.setCodUsuario(null);
		dtoVenda.setCpfCliente("");
		dtoVenda.setDesconto("");
		dtoVenda.setDtPedido(null);
		dtoVenda.setListaItem(null);
		dtoVenda.setNomeCliente("");
		dtoVenda.setNomeUsuario("");
		dtoVenda.setQtdProdutos("0");
		dtoVenda.setSubTotal("0,00");
		dtoVenda.setTotal("0,00");
		listaItemAdicionado.clear();

		return "";
	}

	public void recuperaClientePorCpf(ActionEvent evt) throws Exception {
		if (dtoVenda.getCpfCliente() != null && !dtoVenda.getCpfCliente().isEmpty()) {
			DtoCliente dtoCliente = ctlCliente.recuperaClientePorCpf(Funcoes.retiraCaracteresFormatacao(dtoVenda.getCpfCliente()));
			if (dtoCliente != null) {
				dtoVenda.setCodCliente(dtoCliente.getCod());
				dtoVenda.setNomeCliente(dtoCliente.getNome());
				dtoVenda.setCpfCliente(dtoCliente.getCpfCnpj());
			} else {
				dtoVenda.setCodCliente(null);
				dtoVenda.setNomeCliente("--- Cliente não encontrado ---");
				dtoVenda.setCpfCliente("-----------");
			}
		} else {
			dtoVenda.setCodCliente(null);
			dtoVenda.setNomeCliente("--- Cliente não encontrado ---");
			dtoVenda.setCpfCliente("-----------");
		}
	}

	public String recuperaProdutoPorCodigoBarra() {
		if (dtoItem.getCodBarra() != null && !dtoItem.getCodBarra().isEmpty()) {
			DtoItem dto = ctlProduto.recuperaProdutoPorCodigoBarra(dtoItem.getCodBarra());
			if (dto != null) {
				verificaEstoque(dto.getEstoque());
				dtoItem = dto;
			} else {
				limpaDtoItem();
			}
		}
		return "";
	}

	private void verificaEstoque(String estoque) {
		int est = Integer.parseInt(estoque);
		if (est <= 5) {
			styleEstoque = "color:red;";
		} else if (est > 5 && est <= 10) {
			styleEstoque = "color:#FFCC00;";
		} else {
			styleEstoque = "color:blue;";
		}

		if (est == 0) {
			podeAddItem = true;
		} else {
			podeAddItem = false;
		}

	}

	public String recuperaProdutoPorId() {
		if (dtoItem.getCodItem() != null && dtoItem.getCodItem() > 0) {
			DtoItem dto = ctlProduto.recuperaProdutoPorId(dtoItem.getCodItem());
			if (dto != null) {
				verificaEstoque(dto.getEstoque());
				dtoItem = dto;
			} else {
				limpaDtoItem();
			}
		}
		return "";
	}

	public String calculaValorTotalItem() {
		if ((dtoItem.getValVenda() != null && !dtoItem.getValVenda().isEmpty()) && (dtoItem.getQuantidade() != null && !dtoItem.getQuantidade().isEmpty())) {
			if (verificaQuantidadeInserida()) {
				double total = (Funcoes.stringToDouble(dtoItem.getValVenda()) * Integer.parseInt(dtoItem.getQuantidade()));
				dtoItem.setTotal(Funcoes.formataValor(total));
			}
		}
		return "";
	}

	public String calculaDesconto() {
		double desconto = 0;
		double subtotal = 0;
		double total = -1;
		if (dtoVenda.getDesconto() != null && !dtoVenda.getDesconto().isEmpty() && dtoVenda.getSubTotal() != null && !dtoVenda.getSubTotal().isEmpty()) {
			desconto = Funcoes.stringToDouble(dtoVenda.getDesconto());
			subtotal = Funcoes.stringToDouble(dtoVenda.getSubTotal());
		}
		double aux = (subtotal * desconto) / 100;

		total = subtotal - aux;

		if (total >= 0) {
			dtoVenda.setTotal(Funcoes.formataValor(total));
		} else {
			dtoVenda.setTotal(dtoVenda.getSubTotal());
		}

		return "";
	}

	private boolean verificaQuantidadeInserida() {
		int quantidade = Integer.parseInt(dtoItem.getQuantidade());
		int estoque = Integer.parseInt(dtoItem.getEstoque());
		if (quantidade > estoque) {
			this.addMsgErro("Quantidade maior que o estoque");
			dtoItem.setTotal("");
			podeAddItem = true;
			return false;
		} else {
			podeAddItem = false;
			return true;
		}
	}

	public DtoVenda getDtoVenda() {
		return dtoVenda;
	}

	public void setDtoVenda(DtoVenda dtoVenda) {
		this.dtoVenda = dtoVenda;
	}

	public Long getCodProduto() {
		return codProduto;
	}

	public void setCodProduto(Long codProduto) {
		this.codProduto = codProduto;
	}

	public Long getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(Long codCliente) {
		this.codCliente = codCliente;
	}

	public Long getCodigoVenda() {
		return codigoVenda;
	}

	public void setCodigoVenda(Long codigoVenda) {
		this.codigoVenda = codigoVenda;
	}

	public void setListaProduto(List<SelectItem> listaProduto) {
		this.listaProduto = listaProduto;
	}

	public List<SelectItem> getListaProduto() {
		if (listaProduto == null || listaProduto.isEmpty()) {
			listaProduto = ctlCompra.recuperaListaProduto();
		}
		return listaProduto;
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

	public DtoItem getDtoItem() {
		return dtoItem;
	}

	public void setDtoItem(DtoItem dtoItem) {
		this.dtoItem = dtoItem;
	}

	public List<DtoItem> getListaItemAdicionado() {
		return listaItemAdicionado;
	}

	public void setListaItemAdicionado(List<DtoItem> listaItemAdicionado) {
		this.listaItemAdicionado = listaItemAdicionado;
	}

	public String getStyleEstoque() {
		return styleEstoque;
	}

	public void setStyleEstoque(String styleEstoque) {
		this.styleEstoque = styleEstoque;
	}

	public boolean isPodeAddItem() {
		return podeAddItem;
	}

	public void setPodeAddItem(boolean podeAddItem) {
		this.podeAddItem = podeAddItem;
	}

	public boolean isNovaVenda() {
		return novaVenda;
	}

	public void setNovaVenda(boolean novaVenda) {
		this.novaVenda = novaVenda;
	}

	public void setCtlVenda(CtlVenda ctlVenda) {
		this.ctlVenda = ctlVenda;
	}

	public void setCtlCliente(CtlMantemCliente ctlCliente) {
		this.ctlCliente = ctlCliente;
	}

	public void setCtlProduto(CtlMantemProduto ctlProduto) {
		this.ctlProduto = ctlProduto;
	}

	public void setCtlCompra(CtlCompra ctlCompra) {
		this.ctlCompra = ctlCompra;
	}
}
