package br.absolut.apresentacao.mantemcliente;

import java.io.Serializable;

public class DtoResultadoConsulta implements Serializable {

	private static final long serialVersionUID = 8407562890962549011L;
	private boolean selecionado;
	private Long cod;
	private String cpf;
	private String nome;
	private String dtInclusao;
	private String telefone;
	private String celular;

	public boolean isSelecionado() {
		return selecionado;
	}

	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}

	public Long getCod() {
		return cod;
	}

	public void setCod(Long cod) {
		this.cod = cod;
	}

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

	public String getDtInclusao() {
		return dtInclusao;
	}

	public void setDtInclusao(String dtInclusao) {
		this.dtInclusao = dtInclusao;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}
}
