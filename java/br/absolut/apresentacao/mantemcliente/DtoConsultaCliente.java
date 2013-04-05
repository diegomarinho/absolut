package br.absolut.apresentacao.mantemcliente;

import java.io.Serializable;

public class DtoConsultaCliente implements Serializable {

	private static final long serialVersionUID = 8157273053611630641L;
	private String cpf;
	private String rg;
	private String nome;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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
