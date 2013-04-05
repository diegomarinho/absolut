package br.absolut.apresentacao.compra;

import java.io.Serializable;

public class DtoItem implements Serializable {

	private static final long serialVersionUID = -8104265565058243543L;
	private boolean selecionado;
	private Long codVenda;
	private Long codCompra;
	private Long codItem;
	private String codBarra;
	private String fabricante;
	private String fornecedor;
	private Long codTipo;
	private String descrTipo;
	private String descricao;
	private String valCompra;
	private String valVenda;
	private String quantidade;
	private String total;
	private String estoque;
	private String cliente;
	private String data;

	public boolean isSelecionado() {
		return selecionado;
	}

	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
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

	public String getValCompra() {
		return valCompra;
	}

	public void setValCompra(String valCompra) {
		this.valCompra = valCompra;
	}

	public String getValVenda() {
		return valVenda;
	}

	public void setValVenda(String valVenda) {
		this.valVenda = valVenda;
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

	public Long getCodItem() {
		return codItem;
	}

	public void setCodItem(Long codItem) {
		this.codItem = codItem;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public Long getCodTipo() {
		return codTipo;
	}

	public void setCodTipo(Long codTipo) {
		this.codTipo = codTipo;
	}

	public String getDescrTipo() {
		return descrTipo;
	}

	public void setDescrTipo(String descrTipo) {
		this.descrTipo = descrTipo;
	}

	public String getEstoque() {
		return estoque;
	}

	public void setEstoque(String estoque) {
		this.estoque = estoque;
	}

	public Long getCodVenda() {
		return codVenda;
	}

	public void setCodVenda(Long codVenda) {
		this.codVenda = codVenda;
	}

	public Long getCodCompra() {
		return codCompra;
	}

	public void setCodCompra(Long codCompra) {
		this.codCompra = codCompra;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
