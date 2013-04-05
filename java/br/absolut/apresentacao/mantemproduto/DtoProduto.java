package br.absolut.apresentacao.mantemproduto;

import java.io.Serializable;

public class DtoProduto implements Serializable {

	private static final long serialVersionUID = -4635826699452737833L;
	private Long cod;
	private Long codTipoProduto;
	private String codBarra;
	private String descricao;
	private String fabricante;
	private String valorCompra;
	private String valorVenda;
	private Long saldoAtual;
	private DtoImagem dtoImagem;

	public Long getCod() {
		return cod;
	}

	public void setCod(Long cod) {
		this.cod = cod;
	}

	public Long getCodTipoProduto() {
		return codTipoProduto;
	}

	public void setCodTipoProduto(Long codTipoProduto) {
		this.codTipoProduto = codTipoProduto;
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

	public Long getSaldoAtual() {
		return saldoAtual;
	}

	public void setSaldoAtual(Long saldoAtual) {
		this.saldoAtual = saldoAtual;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public DtoImagem getDtoImagem() {
		return dtoImagem;
	}

	public void setDtoImagem(DtoImagem dtoImagem) {
		this.dtoImagem = dtoImagem;
	}

}
