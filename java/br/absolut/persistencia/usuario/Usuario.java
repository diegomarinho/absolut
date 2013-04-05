package br.absolut.persistencia.usuario;

import java.io.Serializable;
import java.util.Date;

import br.absolut.persistencia.acesso.Acesso;

public class Usuario implements Serializable {

	private static final long serialVersionUID = 5388444674729396576L;
	private Long cod;
	private Acesso acesso;
	private String nome;
	private String login;
	private String senha;
	private Date dtExclusao;

	public Long getCod() {
		return cod;
	}

	public void setCod(Long cod) {
		this.cod = cod;
	}

	public Acesso getAcesso() {
		return acesso;
	}

	public void setAcesso(Acesso acesso) {
		this.acesso = acesso;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDtExclusao() {
		return dtExclusao;
	}

	public void setDtExclusao(Date dtExclusao) {
		this.dtExclusao = dtExclusao;
	}

}
