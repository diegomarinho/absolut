package br.absolut.persistencia.movimento.venda;

import java.util.Date;

public class DtoHistoricoVenda {
	private Date data;
	private Long quantidade;
	private double total;
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Long getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}	
}
