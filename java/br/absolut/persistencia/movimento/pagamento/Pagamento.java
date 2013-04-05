package br.absolut.persistencia.movimento.pagamento;

import java.io.Serializable;

public class Pagamento implements Serializable {

	private static final long serialVersionUID = 2925424314844887507L;
	public static final int COD_PAGAMENTO_A_VISTA = 1;
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
