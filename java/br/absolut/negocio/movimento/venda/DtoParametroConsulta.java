package br.absolut.negocio.movimento.venda;

import java.io.Serializable;
import java.util.Date;

public class DtoParametroConsulta implements Serializable {

	private static final long serialVersionUID = -6038302066761653550L;
	private Long cod;
	private Long codUsuario;
	private Date dtVenda;
	private Long codCliente;

	public Long getCod() {
		return cod;
	}

	public void setCod(Long cod) {
		this.cod = cod;
	}

	public Long getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(Long codUsuario) {
		this.codUsuario = codUsuario;
	}

	public Date getDtVenda() {
		return dtVenda;
	}

	public void setDtVenda(Date dtVenda) {
		this.dtVenda = dtVenda;
	}

	public Long getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(Long codCliente) {
		this.codCliente = codCliente;
	}
}
