package br.absolut.apresentacao.mantemusuario;

import java.io.Serializable;

public class DtoIncluiUsuario implements Serializable {

	private static final long serialVersionUID = -5412598297538696028L;

	private Long cod;
	private String nome;
	private String login;
	private String senha;
	private String reSenha;
	private Long codigoAcesso;

	public String getNome() {
		return nome;
	}

	public Long getCod() {
		return cod;
	}

	public void setCod(Long cod) {
		this.cod = cod;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getReSenha() {
		return reSenha;
	}

	public void setReSenha(String reSenha) {
		this.reSenha = reSenha;
	}

	public Long getCodigoAcesso() {
		return codigoAcesso;
	}

	public void setCodigoAcesso(Long codigoAcesso) {
		this.codigoAcesso = codigoAcesso;
	}
}
