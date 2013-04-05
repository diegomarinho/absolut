package br.absolut.apresentacao.mantemcliente;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import br.absolut.apresentacao.seguranca.Seguranca;

public class ActConsultaCliente extends Seguranca {

	private static final long serialVersionUID = 5966558310527604239L;
	private CtlMantemCliente ctlCliente;
	private DtoConsultaCliente dtoConsulta = new DtoConsultaCliente();
	private List<DtoResultadoConsulta> listaResultado = new ArrayList<DtoResultadoConsulta>();
	private boolean seMostraResultado;
	private String codCliente;
	private int scrollerPage;

	public ActConsultaCliente() throws Exception {
		super(Seguranca.PERFIL_ACESSO_FUNCIONARIO);
	}

	public String consultar() throws Exception {
		List<DtoResultadoConsulta> lista = ctlCliente.consultaCliente(dtoConsulta);

		if (lista != null && !lista.isEmpty()) {
			seMostraResultado = true;
			listaResultado = lista;
		} else {
			listaResultado.clear();
			seMostraResultado = false;
			addMsgAlerta("Nenhum resultado encontrado!");
		}
		return "";
	}

	public String alterar() throws Exception {
		if (codCliente != null && !codCliente.isEmpty()) return "atualizaCliente";

		return "";
	}

	public void excluir(ActionEvent evt) {
		if (codCliente != null && !codCliente.isEmpty()) {
			ctlCliente.excluiCliente(new Long(codCliente));
			codCliente = null;
			addMsgInfo("Cliente excluído com sucesso!");
		}
	}

	public String getExcluir() {
		return "";
	}

	public String atualizaResultado() throws Exception {
		listaResultado = ctlCliente.consultaCliente(dtoConsulta);
		if (listaResultado.isEmpty()) seMostraResultado = false;

		return "";
	}

	public void limparConsulta(ActionEvent evt) {
		if (dtoConsulta != null) {
			dtoConsulta.setCpf("");
			dtoConsulta.setNome("");
			dtoConsulta.setRg("");
		} else {
			dtoConsulta = new DtoConsultaCliente();
		}

		this.seMostraResultado = false;
	}

	public DtoConsultaCliente getDtoConsulta() {
		return dtoConsulta;
	}

	public void setDtoConsulta(DtoConsultaCliente dtoConsulta) {
		this.dtoConsulta = dtoConsulta;
	}

	public boolean isSeMostraResultado() {
		return seMostraResultado;
	}

	public List<DtoResultadoConsulta> getListaResultado() {
		return listaResultado;
	}

	public void setListaResultado(List<DtoResultadoConsulta> listaResultado) {
		this.listaResultado = listaResultado;
	}

	public String getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}

	public int getScrollerPage() {
		return scrollerPage;
	}

	public void setScrollerPage(int scrollerPage) {
		this.scrollerPage = scrollerPage;
	}

	public CtlMantemCliente getCtlCliente() {
		return ctlCliente;
	}

	public void setCtlCliente(CtlMantemCliente ctlCliente) {
		this.ctlCliente = ctlCliente;
	}

}
