package br.absolut.apresentacao.login;

import br.absolut.apresentacao.DtoUsuario;
import br.absolut.apresentacao.mantemusuario.CtlMantemUsuario;
import br.absolut.apresentacao.seguranca.Seguranca;

public class ActLogin extends Seguranca {
	private static final long serialVersionUID = 1035491150805550859L;
	private CtlMantemUsuario ctlUsuario;
	private String login;
	private String senha;

	public ActLogin() throws Exception {
		super(Seguranca.PERFIL_ACESSO_PUBLICO);

	}

	public String efetuaLogin() throws Exception {
		DtoUsuario dtoUsuario = ctlUsuario.verificaLoginSenha(login, senha);
		if(dtoUsuario != null) {
			this.getHttpServletRequest().getSession().setAttribute("dtoUsuario", dtoUsuario);
			this.getHttpServletResponse().sendRedirect("/absolut/web/apresentacao/principal/principal.faces");
		} else {
			this.addMsgErro("Login e/ou Senha inválidos!");
		}
		return "";
	}
	
	public String efetuaLogout() throws Exception {
		if(this.getHttpServletRequest().getSession() != null)
			this.getHttpServletRequest().getSession().invalidate();

		return "login";
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setCtlUsuario(CtlMantemUsuario ctlUsuario) {
		this.ctlUsuario = ctlUsuario;
	}

	

}
