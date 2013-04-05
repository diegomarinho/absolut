package br.absolut.persistencia.acesso;

import java.io.Serializable;

public class Acesso implements Serializable {

	private static final long serialVersionUID = 3700041038163477087L;
	private Long cod;
	private String descricao;

	public Long getCod() {
		return cod;
	}

	public void setCod(Long cod) {
		this.cod = cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
