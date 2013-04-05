package br.absolut.persistencia.movimento.venda;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import br.absolut.persistencia.cliente.Cliente;
import br.absolut.persistencia.movimento.Movimento;
import br.absolut.persistencia.movimento.pagamento.Pagamento;
import br.absolut.persistencia.usuario.Usuario;

public class Venda implements Serializable {

	private static final long serialVersionUID = -160132140006083613L;
	private Long cod;
	private Date dtVenda;
	private Cliente cliente;
	private double subTotal;
	private double desconto;
	private double total;
	private Long qtdProdutos;
	private Pagamento pagamento;
	private Usuario usuario;
	private List<Movimento> movimentos;

	public Long getCod() {
		return cod;
	}

	public void setCod(Long cod) {
		this.cod = cod;
	}

	public Date getDtVenda() {
		return dtVenda;
	}

	public void setDtVenda(Date dtVenda) {
		this.dtVenda = dtVenda;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public double getDesconto() {
		return desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Long getQtdProdutos() {
		return qtdProdutos;
	}

	public void setQtdProdutos(Long qtdProdutos) {
		this.qtdProdutos = qtdProdutos;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Movimento> getMovimentos() {
		return movimentos;
	}

	public void setMovimentos(List<Movimento> movimentos) {
		this.movimentos = movimentos;
	}

}
