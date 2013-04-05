package br.absolut.apresentacao;

import java.io.Serializable;


public class DtoUsuario implements Serializable{
	private static final long serialVersionUID = -1800014992662094069L;
	private Long cod;            
	private Long acesso;
	private String nome;           
	private String login;
	
	public Long getCod() {
		return cod;
	}
	public void setCod(Long cod) {
		this.cod = cod;
	}
	public Long getAcesso() {
		return acesso;
	}
	public void setAcesso(Long acesso) {
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
}
