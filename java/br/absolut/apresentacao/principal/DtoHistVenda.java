package br.absolut.apresentacao.principal;

import java.io.Serializable;

public class DtoHistVenda implements Serializable {

	private static final long serialVersionUID = 6438415115239301531L;
	private String data;
	private String quantidade;
	private String total;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
}
