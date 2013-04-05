package br.absolut.apresentacao.mantemfornecedor;

import java.io.Serializable;

public class DtoConsultaFornecedor implements Serializable {

	private static final long serialVersionUID = -5448150588361260262L;
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
