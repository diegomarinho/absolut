package br.absolut.apresentacao.mantemusuario;

import java.util.List;

import javax.faces.model.SelectItem;

import br.absolut.apresentacao.seguranca.Seguranca;

public class ActAtualizaUsuario extends Seguranca {

	private static final long serialVersionUID = -7513142375755166187L;
	private CtlMantemUsuario ctlUsuario;
	private DtoIncluiUsuario dtoIncluiUsuario = new DtoIncluiUsuario();
	private boolean ehAlteracao;
	private String codUsuario;
	private List<SelectItem> listaAcesso;

	public ActAtualizaUsuario() throws Exception {
		super(Seguranca.PERFIL_ACESSO_GERENTE);
		ehAlteracao = false;

		verificaSeEhAlteracao();
	}

	public String salvar() {
		if (dtoIncluiUsuario.getCod() != null && dtoIncluiUsuario.getCod() != 0) {
			ctlUsuario.alteraUsuario(dtoIncluiUsuario);
			addMsgInfo("Usuário alterado com sucesso!");
		} else {
			if (verificaPreenchimentoCampos()) {
				ctlUsuario.incluiUsuario(dtoIncluiUsuario);
				addMsgInfo("Usuário cadastrado com sucesso!");
			}
		}
		return "";
	}

	private boolean verificaPreenchimentoCampos() {
		boolean retorno = true;

		if (!dtoIncluiUsuario.getReSenha().equals(dtoIncluiUsuario.getSenha())) {
			this.addMsgErro("Os campos 'Senha' e 'Redigite senha' devem ser iguais");
			retorno = false;
		}
		return retorno;
	}

	private void verificaSeEhAlteracao() throws Exception {
		ActConsultaUsuario actConsulta = (ActConsultaUsuario) this.getHttpServletRequest().getSession().getAttribute("actConsultaUsuario");
		if (actConsulta != null) {
			if (actConsulta.getCodUsuario() != null && !actConsulta.getCodUsuario().isEmpty()) {
				if (ctlUsuario == null) ctlUsuario = actConsulta.getCtlUsuario();

				dtoIncluiUsuario = ctlUsuario.recuperaUsuario(new Long(actConsulta.getCodUsuario()));
				codUsuario = dtoIncluiUsuario.getCod().toString();
				this.retiraObjetoSessao("actConsultaUsuario");

				ehAlteracao = true;
			}
		}
	}

	public List<SelectItem> getListaAcesso() {
		if (listaAcesso == null || listaAcesso.isEmpty()) listaAcesso = ctlUsuario.recuperaListaTipoAcesso();
		return listaAcesso;
	}

	public void setListaAcesso(List<SelectItem> listaAcesso) {
		this.listaAcesso = listaAcesso;
	}

	public DtoIncluiUsuario getDtoIncluiUsuario() {
		return dtoIncluiUsuario;
	}

	public void setDtoIncluiUsuario(DtoIncluiUsuario dtoIncluiUsuario) {
		this.dtoIncluiUsuario = dtoIncluiUsuario;
	}

	public String getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(String codUsuario) {
		this.codUsuario = codUsuario;
	}

	public boolean isEhAlteracao() {
		return ehAlteracao;
	}

	public void setEhAlteracao(boolean ehAlteracao) {
		this.ehAlteracao = ehAlteracao;
	}

	public void setCtlUsuario(CtlMantemUsuario ctlUsuario) {
		this.ctlUsuario = ctlUsuario;
	}
}
