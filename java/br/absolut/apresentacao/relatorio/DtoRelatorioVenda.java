package br.absolut.apresentacao.relatorio;

import java.io.Serializable;
import java.util.List;

import br.absolut.apresentacao.compra.DtoItem;

public class DtoRelatorioVenda implements Serializable {

	private static final long serialVersionUID = 6181964327903085771L;
	private String codigo;
	private String data;
	private String cliente;
	private String vendedor;
	private String subtotal;
	private String desconto;
	private String total;
	private String quantidade;
	private List<DtoItem> listaItem;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getVendedor() {
		return vendedor;
	}

	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}

	public String getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}

	public String getDesconto() {
		return desconto;
	}

	public void setDesconto(String desconto) {
		this.desconto = desconto;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public List<DtoItem> getListaItem() {
		return listaItem;
	}

	public void setListaItem(List<DtoItem> listaItem) {
		this.listaItem = listaItem;
	}
}
