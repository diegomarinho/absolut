package br.absolut.apresentacao.mantemcliente;

import java.io.Serializable;

public class DtoCliente implements Serializable {

	private static final long serialVersionUID = 4240118342094879544L;
	private Long cod;
	private String cpfCnpj;
	private String identidade;
	private String nome;
	private String endereco;
	private String bairro;
	private String cidade;
	private String estado;
	private String cep;
	private String telefone;
	private String celular;
	private String dtInclusao;
	private String dtExclusao;

	public Long getCod() {
		return cod;
	}

	public void setCod(Long cod) {
		this.cod = cod;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getIdentidade() {
		return identidade;
	}

	public void setIdentidade(String identidade) {
		this.identidade = identidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
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

	public String getDtInclusao() {
		return dtInclusao;
	}

	public void setDtInclusao(String dtInclusao) {
		this.dtInclusao = dtInclusao;
	}

	public String getDtExclusao() {
		return dtExclusao;
	}

	public void setDtExclusao(String dtExclusao) {
		this.dtExclusao = dtExclusao;
	}
}
