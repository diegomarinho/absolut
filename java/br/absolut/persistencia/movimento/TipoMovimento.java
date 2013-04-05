package br.absolut.persistencia.movimento;

import java.io.Serializable;

public class TipoMovimento implements Serializable {

	private static final long serialVersionUID = 5763324334378698379L;
	public static final Long MOVIMENTO_ENTRADA = new Long(1);
	public static final Long MOVIMENTO_SAIDA = new Long(2);
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
