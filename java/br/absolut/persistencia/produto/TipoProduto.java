package br.absolut.persistencia.produto;

import java.io.Serializable;

public class TipoProduto implements Serializable {

	private static final long serialVersionUID = 7385418583395505827L;
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
