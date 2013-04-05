package br.absolut.persistencia.produto;

import java.io.Serializable;
import java.util.Date;

public class Produto implements Serializable {

	private static final long serialVersionUID = -6785412970925741091L;
	private Long cod;
	private TipoProduto tipoProduto;
	private String codBarra;
	private String descricao;
	private String fabricante;
	private double valorCompra;
	private double valorVenda;
	private Long saldoAtual;
	private Date dtExclusao;

	public Long getCod() {
		return cod;
	}

	public void setCod(Long cod) {
		this.cod = cod;
	}

	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(TipoProduto tipoProduto) {
		this.tipoProduto = tipoProduto;
	}

	public String getCodBarra() {
		return codBarra;
	}

	public void setCodBarra(String codBarra) {
		this.codBarra = codBarra;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	public Long getSaldoAtual() {
		return saldoAtual;
	}

	public void setSaldoAtual(Long saldoAtual) {
		this.saldoAtual = saldoAtual;
	}

	public Date getDtExclusao() {
		return dtExclusao;
	}

	public void setDtExclusao(Date dtExclusao) {
		this.dtExclusao = dtExclusao;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
}
