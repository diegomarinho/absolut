package br.absolut.negocio.cliente;

import java.io.Serializable;

public class DtoParametroConsulta implements Serializable {

	private static final long serialVersionUID = 464484131538141609L;
	private String cpfCnpj;
	private String nome;
	private String rg;

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}
}
