package br.absolut.apresentacao.relatorio;

import java.io.Serializable;
import java.util.List;

import br.absolut.apresentacao.compra.DtoItem;

public class DtoRelatorioVendaPeriodo implements Serializable {

	private static final long serialVersionUID = -6151939520390366547L;
	private String dtInicio;
	private String dtFinal;
	private String subTotal;
	private String desconto;
	private String total;
	private List<DtoItem> listaItem;

	public String getDtInicio() {
		return dtInicio;
	}

	public void setDtInicio(String dtInicio) {
		this.dtInicio = dtInicio;
	}

	public String getDtFinal() {
		return dtFinal;
	}

	public void setDtFinal(String dtFinal) {
		this.dtFinal = dtFinal;
	}

	public String getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
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

	public List<DtoItem> getListaItem() {
		return listaItem;
	}

	public void setListaItem(List<DtoItem> listaItem) {
		this.listaItem = listaItem;
	}
}
