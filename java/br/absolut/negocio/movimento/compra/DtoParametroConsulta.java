package br.absolut.negocio.movimento.compra;

import java.io.Serializable;
import java.util.Date;

public class DtoParametroConsulta implements Serializable {

	private static final long serialVersionUID = -7800500508521865683L;
	private Long cod;
	private Long codUsuario;
	private Date dtCompra;
	private Long codFornecedor;
	private Long notaFiscal;

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

	public Date getDtCompra() {
		return dtCompra;
	}

	public void setDtCompra(Date dtCompra) {
		this.dtCompra = dtCompra;
	}

	public Long getCodFornecedor() {
		return codFornecedor;
	}

	public void setCodFornecedor(Long codFornecedor) {
		this.codFornecedor = codFornecedor;
	}

	public Long getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(Long notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

}
