package br.absolut.apresentacao.mantemproduto;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import br.absolut.apresentacao.seguranca.Seguranca;

public class ActConsultaProduto extends Seguranca {

	private static final long serialVersionUID = -9198469563895496428L;
	private CtlMantemProduto ctlProduto;
	private DtoConsultaProduto dtoConsulta = new DtoConsultaProduto();
	private List<DtoResultadoConsulta> listaResultado = new ArrayList<DtoResultadoConsulta>();
	private List<SelectItem> listaTipoProduto;
	private boolean seMostraResultado;
	private String codProduto;
	private int scrollerPage;

	public ActConsultaProduto() throws Exception {
		super(Seguranca.PERFIL_ACESSO_FUNCIONARIO);

	}

	public String alterar() throws Exception {
		if ((codProduto != null) && !codProduto.isEmpty()) {
			return "atualizaProduto";
		}

		return "";
	}

	public String atualizaResultado() throws Exception {
		listaResultado = ctlProduto.consultaProduto(dtoConsulta);
		if (listaResultado.isEmpty()) {
			seMostraResultado = false;
		}

		return "";
	}

	public String consultar() throws Exception {
		List<DtoResultadoConsulta> lista = ctlProduto
				.consultaProduto(dtoConsulta);

		if ((lista != null) && !lista.isEmpty()) {
			seMostraResultado = true;
			listaResultado = lista;
		} else {
			listaResultado.clear();
			seMostraResultado = false;
			addMsgAlerta("Nenhum resultado encontrado!");
		}
		return "";
	}

	public void excluir(ActionEvent evt) {
		if ((codProduto != null) && !codProduto.isEmpty()) {
			ctlProduto.excluiProduto(new Long(codProduto));
			codProduto = null;
			addMsgInfo("Produto excluído com sucesso!");
		}
	}

	public String getCodProduto() {
		return codProduto;
	}

	public CtlMantemProduto getCtlProduto() {
		return ctlProduto;
	}

	public DtoConsultaProduto getDtoConsulta() {
		return dtoConsulta;
	}

	public List<DtoResultadoConsulta> getListaResultado() {
		return listaResultado;
	}

	public List<SelectItem> getListaTipoProduto() {
		if ((listaTipoProduto == null) || listaTipoProduto.isEmpty()) {
			listaTipoProduto = ctlProduto.recuperaListaTipoProduto();
		}

		return listaTipoProduto;
	}

	public int getScrollerPage() {
		return scrollerPage;
	}

	public boolean isSeMostraResultado() {
		return seMostraResultado;
	}

	public void limparConsulta(ActionEvent evt) {
		if (dtoConsulta != null) {
			dtoConsulta.setCodBarra("");
			dtoConsulta.setCodTipoProduto(null);
			dtoConsulta.setDescricao("");
		} else {
			dtoConsulta = new DtoConsultaProduto();
		}

		this.seMostraResultado = false;
	}

	public void setCodProduto(String codProduto) {
		this.codProduto = codProduto;
	}

	public void setCtlProduto(CtlMantemProduto ctlProduto) {
		this.ctlProduto = ctlProduto;
	}

	public void setDtoConsulta(DtoConsultaProduto dtoConsulta) {
		this.dtoConsulta = dtoConsulta;
	}

	public void setListaResultado(List<DtoResultadoConsulta> listaResultado) {
		this.listaResultado = listaResultado;
	}

	public void setListaTipoProduto(List<SelectItem> listaTipoProduto) {
		this.listaTipoProduto = listaTipoProduto;
	}

	public void setScrollerPage(int scrollerPage) {
		this.scrollerPage = scrollerPage;
	}

	public void setSeMostraResultado(boolean seMostraResultado) {
		this.seMostraResultado = seMostraResultado;
	}

}
