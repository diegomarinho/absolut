package br.absolut.persistencia.movimento.estoque;

import java.io.Serializable;

import br.absolut.persistencia.movimento.Movimento;

public class Estoque implements Serializable {

	private static final long serialVersionUID = 1483530754529759310L;
	private Long cod;
	private Movimento movimento;
	private Long qtdSaldo;

	public Long getCod() {
		return cod;
	}

	public void setCod(Long cod) {
		this.cod = cod;
	}

	public Movimento getMovimento() {
		return movimento;
	}

	public void setMovimento(Movimento movimento) {
		this.movimento = movimento;
	}

	public Long getQtdSaldo() {
		return qtdSaldo;
	}

	public void setQtdSaldo(Long qtdSaldo) {
		this.qtdSaldo = qtdSaldo;
	}

}
