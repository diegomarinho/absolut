package br.absolut.apresentacao.relatorio;

import java.io.Serializable;
import java.util.List;

import br.absolut.apresentacao.compra.DtoItem;

public class DtoRelatorioCompraPeriodo implements Serializable {

	private static final long serialVersionUID = 6333626185298833426L;
	private String dtInicial;
	private String dtFinal;
	private String total;
	private List<DtoItem> listaItem;

	public String getDtInicial() {
		return dtInicial;
	}

	public void setDtInicial(String dtInicial) {
		this.dtInicial = dtInicial;
	}

	public String getDtFinal() {
		return dtFinal;
	}

	public void setDtFinal(String dtFinal) {
		this.dtFinal = dtFinal;
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
