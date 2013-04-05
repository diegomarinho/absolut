package br.absolut.apresentacao.mantemusuario;

import java.io.Serializable;

public class DtoAlteraSenha implements Serializable {

	private static final long serialVersionUID = -2747780868092020088L;
	private String senhaAtual;
	private String novaSenha;
	private String reNovaSenha;

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getReNovaSenha() {
		return reNovaSenha;
	}

	public void setReNovaSenha(String reNovaSenha) {
		this.reNovaSenha = reNovaSenha;
	}
}
