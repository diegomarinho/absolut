package br.absolut.apresentacao.compra;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class DtoCompra implements Serializable{
	private static final long serialVersionUID = 8975014394594458652L;
	private Long cod;           
	private String codFornecedor;
	private Long codUsuario;   
	private Date dtCompra;     
	private String total;
	private String qtdProduto;   
	private String codPagamento;
	private String notaFiscal;
	private List<DtoItem> listaItens;
	
	public Long getCod() {
		return cod;
	}
	public void setCod(Long cod) {
		this.cod = cod;
	}
	public String getCodFornecedor() {
		return codFornecedor;
	}
	public void setCodFornecedor(String codFornecedor) {
		this.codFornecedor = codFornecedor;
	}
	public Long getCodUsuario() {
		return codUsuario;
	}
	public void setCodUsuario(Long codUsuario) {
		this.codUsuario = codUsuario;
	}
	public Date getDtCompra() {
		return dtCompra;
	}
	public void setDtCompra(Date dtCompra) {
		this.dtCompra = dtCompra;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getQtdProduto() {
		return qtdProduto;
	}
	public void setQtdProduto(String qtdProduto) {
		this.qtdProduto = qtdProduto;
	}
	public String getCodPagamento() {
		return codPagamento;
	}
	public void setCodPagamento(String codPagamento) {
		this.codPagamento = codPagamento;
	}
	public String getNotaFiscal() {
		return notaFiscal;
	}
	public void setNotaFiscal(String notaFiscal) {
		this.notaFiscal = notaFiscal;
	}
	public List<DtoItem> getListaItens() {
		return listaItens;
	}
	public void setListaItens(List<DtoItem> listaItens) {
		this.listaItens = listaItens;
	}
}
