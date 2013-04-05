package br.absolut.apresentacao.mantemproduto;

import java.io.Serializable;

public class DtoResultadoConsulta implements Serializable {

	private static final long serialVersionUID = -3035533801067936434L;
	private boolean selecionado;
	private Long cod;
	private String codBarras;
	private String tipoProduto;
	private String descricao;
	private String valorCompra;
	private String valorVenda;
	private String saldoAtual;
	private String fabricante;
	private String urlImagem;

	public boolean isSelecionado() {
		return selecionado;
	}

	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}

	public Long getCod() {
		return cod;
	}

	public void setCod(Long cod) {
		this.cod = cod;
	}

	public String getCodBarras() {
		return codBarras;
	}

	public void setCodBarras(String codBarras) {
		this.codBarras = codBarras;
	}

	public String getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(String tipoProduto) {
		this.tipoProduto = tipoProduto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	public String getSaldoAtual() {
		return saldoAtual;
	}

	public void setSaldoAtual(String saldoAtual) {
		this.saldoAtual = saldoAtual;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getUrlImagem() {
		return urlImagem;
	}

	public void setUrlImagem(String urlImagem) {
		this.urlImagem = urlImagem;
	}
}
