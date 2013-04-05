package br.absolut.persistencia.movimento.compra;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import br.absolut.persistencia.fornecedor.Fornecedor;
import br.absolut.persistencia.movimento.Movimento;
import br.absolut.persistencia.movimento.pagamento.Pagamento;
import br.absolut.persistencia.usuario.Usuario;

public class Compra implements Serializable {

	private static final long serialVersionUID = 4686084855374816564L;
	private Long cod;
	private Fornecedor fornecedor;
	private Usuario usuario;
	private Date dtCompra;
	private double total;
	private Long qtdProduto;
	private Pagamento pagamento;
	private String notaFiscal;
	private List<Movimento> movimentos;

	public Long getCod() {
		return cod;
	}

	public void setCod(Long cod) {
		this.cod = cod;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getDtCompra() {
		return dtCompra;
	}

	public void setDtCompra(Date dtCompra) {
		this.dtCompra = dtCompra;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Long getQtdProduto() {
		return qtdProduto;
	}

	public void setQtdProduto(Long qtdProduto) {
		this.qtdProduto = qtdProduto;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public String getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(String notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public List<Movimento> getMovimentos() {
		return movimentos;
	}

	public void setMovimentos(List<Movimento> movimentos) {
		this.movimentos = movimentos;
	}
}
