package br.absolut.persistencia.pagina;

import java.io.Serializable;

import br.absolut.persistencia.acesso.Acesso;

public class Pagina implements Serializable {

	private static final long serialVersionUID = 7930547493060120908L;
	private Long cod;
	private String nomePagina;
	private String enderecoCompleto;
	private String extensao;
	private String descricao;
	private Acesso acesso;

	public Long getCod() {
		return cod;
	}

	public void setCod(Long cod) {
		this.cod = cod;
	}

	public String getNomePagina() {
		return nomePagina;
	}

	public void setNomePagina(String nomePagina) {
		this.nomePagina = nomePagina;
	}

	public String getEnderecoCompleto() {
		return enderecoCompleto;
	}

	public void setEnderecoCompleto(String enderecoCompleto) {
		this.enderecoCompleto = enderecoCompleto;
	}

	public String getExtensao() {
		return extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Acesso getAcesso() {
		return acesso;
	}

	public void setAcesso(Acesso acesso) {
		this.acesso = acesso;
	}
}
