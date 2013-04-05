package br.absolut.apresentacao.mantemproduto;

import java.io.IOException;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import br.absolut.apresentacao.seguranca.Seguranca;

public class ActAtualizaProduto extends Seguranca {
	private static final long serialVersionUID = 7731373844562678634L;
	private CtlMantemProduto ctlProduto;
	private DtoProduto dtoProduto = new DtoProduto();
	private List<SelectItem> listaTipoProduto;
	private boolean ehAlteracao;
	private String codProduto;

	public ActAtualizaProduto() throws Exception {
		super(Seguranca.PERFIL_ACESSO_FUNCIONARIO);

		verificaSeEhAlteracao();
	}

	public String salvar() throws Exception {
		// DtoImagem dtoImagem = (DtoImagem)
		// this.getHttpServletRequest().getSession().getAttribute("dtoImagem");
		// if(dtoImagem != null)
		// dtoProduto.setDtoImagem(dtoImagem);

		if (dtoProduto.getCod() != null && dtoProduto.getCod() != 0) {
			ctlProduto.alteraProduto(dtoProduto);
			addMsgInfo("Produto alterado com sucesso!");
		} else {
			ctlProduto.incluiProduto(dtoProduto);
			addMsgInfo("Produto cadastrado com sucesso!");
			limpaCampos();
		}

		this.retiraObjetoSessao("uploadItem");

		return "consultaProduto";
	}

	public void limpar(ActionEvent evt) {
		if (dtoProduto != null) {
			dtoProduto.setCodBarra("");
			dtoProduto.setCodTipoProduto(null);
			dtoProduto.setDescricao("");
			dtoProduto.setValorCompra("");
			dtoProduto.setValorVenda("");
			dtoProduto.setSaldoAtual(null);
			dtoProduto.setFabricante("");
		} else {
			dtoProduto = new DtoProduto();
		}
	}
	
	private void limpaCampos() {
		if (dtoProduto != null) {
			dtoProduto.setCodBarra("");
			dtoProduto.setCodTipoProduto(null);
			dtoProduto.setDescricao("");
			dtoProduto.setValorCompra("");
			dtoProduto.setValorVenda("");
			dtoProduto.setSaldoAtual(null);
			dtoProduto.setFabricante("");
		} else {
			dtoProduto = new DtoProduto();
		}
	}

	private void verificaSeEhAlteracao() {
		ActConsultaProduto actConsulta = (ActConsultaProduto) this
				.getHttpServletRequest().getSession().getAttribute(
						"actConsultaProduto");
		if (actConsulta != null) {
			if (actConsulta.getCodProduto() != null
					&& !actConsulta.getCodProduto().isEmpty()) {
				if (ctlProduto == null)
					ctlProduto = actConsulta.getCtlProduto();

				dtoProduto = ctlProduto.recuperaProduto(new Long(actConsulta
						.getCodProduto()));
				codProduto = dtoProduto.getCod().toString();
				retiraObjetoSessao("actConsultaProduto");

				ehAlteracao = true;
			}
		}

	}

	public void listener(UploadEvent event) throws IOException {
		UploadItem item = event.getUploadItem();

		DtoImagem dtoImagem = new DtoImagem();
		dtoImagem.setNome(item.getFileName());
		dtoImagem.setTamanho(new Long(item.getFileSize()));
		dtoImagem.setArquivo(item.getFile());

		this.getHttpServletRequest().getSession().setAttribute("dtoImagem",
				dtoImagem);
	}

	public DtoProduto getDtoProduto() {
		return dtoProduto;
	}

	public void setDtoProduto(DtoProduto dtoProduto) {
		this.dtoProduto = dtoProduto;
	}

	public List<SelectItem> getListaTipoProduto() {
		listaTipoProduto = ctlProduto.recuperaListaTipoProduto();

		return listaTipoProduto;
	}

	public void setListaTipoProduto(List<SelectItem> listaTipoProduto) {
		this.listaTipoProduto = listaTipoProduto;
	}

	public boolean isEhAlteracao() {
		return ehAlteracao;
	}

	public void setEhAlteracao(boolean ehAlteracao) {
		this.ehAlteracao = ehAlteracao;
	}

	public String getCodProduto() {
		return codProduto;
	}

	public void setCodProduto(String codProduto) {
		this.codProduto = codProduto;
	}

	public void setCtlProduto(CtlMantemProduto ctlProduto) {
		this.ctlProduto = ctlProduto;
	}
}
