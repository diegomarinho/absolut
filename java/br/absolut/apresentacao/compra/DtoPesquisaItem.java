package br.absolut.apresentacao.compra;

import java.io.Serializable;

public class DtoPesquisaItem implements Serializable {

	private static final long serialVersionUID = -5081816506514957640L;
	private String codProduto;
	private String valorCompra;
	private String valorVenda;
	private String quantidade;
	private String lucro;
	private String valorTotal;

	public String getCodProduto() {
		return codProduto;
	}

	public void setCodProduto(String codProduto) {
		this.codProduto = codProduto;
	}

	public String getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(String valorCompra) {
		this.valorCompra = valorCompra;
	}

	public String getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(String valorVenda) {
		this.valorVenda = valorVenda;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public String getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getLucro() {
		return lucro;
	}

	public void setLucro(String lucro) {
		this.lucro = lucro;
	}
}
