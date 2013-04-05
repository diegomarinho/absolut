package br.absolut.persistencia.unidadefederativa;

import java.io.Serializable;

public class UnidadeFederativa implements Serializable {

	private static final long serialVersionUID = -2405576108581912722L;
	private Long codigo;
	private String nome;
	private String sigla;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
}
