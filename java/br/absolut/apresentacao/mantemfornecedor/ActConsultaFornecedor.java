package br.absolut.apresentacao.mantemfornecedor;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import br.absolut.apresentacao.seguranca.Seguranca;

public class ActConsultaFornecedor extends Seguranca {
	private static final long serialVersionUID = -3370801257592868321L;
	private CtlMantemFornecedor ctlFornecedor;
	private DtoConsultaFornecedor dtoConsulta = new DtoConsultaFornecedor();
	private List<DtoResultadoConsulta> listaResultado = new ArrayList<DtoResultadoConsulta>();
	private boolean seMostraResultado;
	private String codFornecedor;
	private int scrollerPage;

	public ActConsultaFornecedor() throws Exception {
		super(Seguranca.PERFIL_ACESSO_FUNCIONARIO);

	}

	public String alterar() throws Exception {
		if ((codFornecedor != null) && !codFornecedor.isEmpty()) {
			return "atualizaFornecedor";
		}

		return "";
	}

	public String atualizaResultado() throws Exception {
		listaResultado = ctlFornecedor.consultaFornecedor(dtoConsulta);
		if (listaResultado.isEmpty()) {
			seMostraResultado = false;
		}

		return "";
	}

	public String consultar() throws Exception {
		List<DtoResultadoConsulta> lista = ctlFornecedor
				.consultaFornecedor(dtoConsulta);

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
		if ((codFornecedor != null) && !codFornecedor.isEmpty()) {
			ctlFornecedor.excluiFornecedor(new Long(codFornecedor));
			codFornecedor = null;
			addMsgInfo("Fornecedor excluído com sucesso!");
		}
	}

	public String getCodFornecedor() {
		return codFornecedor;
	}

	public CtlMantemFornecedor getCtlFornecedor() {
		return ctlFornecedor;
	}

	public DtoConsultaFornecedor getDtoConsulta() {
		return dtoConsulta;
	}

	public List<DtoResultadoConsulta> getListaResultado() {
		return listaResultado;
	}

	public int getScrollerPage() {
		return scrollerPage;
	}

	public boolean isSeMostraResultado() {
		return seMostraResultado;
	}

	public void limparConsulta(ActionEvent evt) {
		if (dtoConsulta != null) {
			dtoConsulta.setCnpj("");
			dtoConsulta.setNome("");
		} else {
			dtoConsulta = new DtoConsultaFornecedor();
		}

		this.seMostraResultado = false;
	}

	public void setCodFornecedor(String codFornecedor) {
		this.codFornecedor = codFornecedor;
	}

	public void setCtlFornecedor(CtlMantemFornecedor ctlFornecedor) {
		this.ctlFornecedor = ctlFornecedor;
	}

	public void setDtoConsulta(DtoConsultaFornecedor dtoConsulta) {
		this.dtoConsulta = dtoConsulta;
	}

	public void setListaResultado(List<DtoResultadoConsulta> listaResultado) {
		this.listaResultado = listaResultado;
	}

	public void setScrollerPage(int scrollerPage) {
		this.scrollerPage = scrollerPage;
	}

	public void setSeMostraResultado(boolean seMostraResultado) {
		this.seMostraResultado = seMostraResultado;
	}
}
