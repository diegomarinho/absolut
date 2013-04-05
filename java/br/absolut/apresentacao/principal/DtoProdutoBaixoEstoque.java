package br.absolut.apresentacao.principal;

import java.io.Serializable;

public class DtoProdutoBaixoEstoque implements Serializable {

	private static final long serialVersionUID = 2440540881854592853L;
	private String dtUltimaAlteracao;
	private String produto;
	private String codigoBarra;
	private String qtdEstoque;

	public String getDtUltimaAlteracao() {
		return dtUltimaAlteracao;
	}

	public void setDtUltimaAlteracao(String dtUltimaAlteracao) {
		this.dtUltimaAlteracao = dtUltimaAlteracao;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public String getCodigoBarra() {
		return codigoBarra;
	}

	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}

	public String getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(String qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}
}
