package br.absolut.negocio.fornecedor;

import java.io.Serializable;

public class DtoParametroConsulta implements Serializable {

	private static final long serialVersionUID = 6178922891921603109L;
	private String cnpj;
	private String nome;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
