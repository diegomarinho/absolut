package br.absolut.persistencia.movimento;

import java.io.Serializable;
import java.util.Date;

import br.absolut.persistencia.produto.Produto;

public class Movimento implements Serializable {

	private static final long serialVersionUID = -1768076253543593285L;
	private Long cod;
	private TipoMovimento tipoMovimento;
	private Long codCompra;
	private Long codVenda;
	private Produto produto;
	private Date dtMovimento;
	private Long qtdMovimento;
	private double valorCompra;
	private double valorVenda;
	private double valorTotal;

	public Long getCod() {
		return cod;
	}

	public void setCod(Long cod) {
		this.cod = cod;
	}

	public TipoMovimento getTipoMovimento() {
		return tipoMovimento;
	}

	public void setTipoMovimento(TipoMovimento tipoMovimento) {
		this.tipoMovimento = tipoMovimento;
	}

	public Long getCodCompra() {
		return codCompra;
	}

	public void setCodCompra(Long compra) {
		this.codCompra = compra;
	}

	public Long getCodVenda() {
		return codVenda;
	}

	public void setCodVenda(Long venda) {
		this.codVenda = venda;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Date getDtMovimento() {
		return dtMovimento;
	}

	public void setDtMovimento(Date dtMovimento) {
		this.dtMovimento = dtMovimento;
	}

	public Long getQtdMovimento() {
		return qtdMovimento;
	}

	public void setQtdMovimento(Long qtdMovimento) {
		this.qtdMovimento = qtdMovimento;
	}

	public double getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(double valorCompra) {
		this.valorCompra = valorCompra;
	}

	public double getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(double valorVenda) {
		this.valorVenda = valorVenda;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
}
