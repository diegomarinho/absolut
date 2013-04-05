package br.absolut.negocio.produto;

import java.io.Serializable;

public class DtoParametroConsulta implements Serializable {

	private static final long serialVersionUID = -5525286623339109545L;
	private String codBarra;
	private Long codTipoProduto;
	private String descricao;
	private String fabricante;

	public String getCodBarra() {
		return codBarra;
	}

	public void setCodBarra(String codBarra) {
		this.codBarra = codBarra;
	}

	public Long getCodTipoProduto() {
		return codTipoProduto;
	}

	public void setCodTipoProduto(Long codTipoProduto) {
		this.codTipoProduto = codTipoProduto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
}
