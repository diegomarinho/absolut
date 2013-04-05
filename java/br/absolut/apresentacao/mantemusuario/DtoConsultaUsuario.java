package br.absolut.apresentacao.mantemusuario;

import java.io.Serializable;

public class DtoConsultaUsuario implements Serializable {

	private static final long serialVersionUID = -3152489972786314896L;
	private boolean selecionado;
	private Long cod;
	private String nome;
	private String login;
	private String acesso;

	public boolean isSelecionado() {
		return selecionado;
	}

	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}

	public Long getCod() {
		return cod;
	}

	public void setCod(Long cod) {
		this.cod = cod;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getAcesso() {
		return acesso;
	}

	public void setAcesso(String acesso) {
		this.acesso = acesso;
	}
}
