package br.absolut.persistencia.imagem;

import java.io.Serializable;
import java.sql.Blob;

public class Imagem implements Serializable {

	private static final long serialVersionUID = 5237807280524543639L;
	private Long cod;
	private String nome;
	private Long tamanho;
	private Blob arquivo;

	public Long getCod() {
		return cod;
	}

	public void setCod(Long cod) {
		this.cod = cod;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getTamanho() {
		return tamanho;
	}

	public void setTamanho(Long tamanho) {
		this.tamanho = tamanho;
	}

	public Blob getArquivo() {
		return arquivo;
	}

	public void setArquivo(Blob arquivo) {
		this.arquivo = arquivo;
	}
}
