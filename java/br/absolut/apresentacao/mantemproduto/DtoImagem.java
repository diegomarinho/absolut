package br.absolut.apresentacao.mantemproduto;

import java.io.File;
import java.io.Serializable;

public class DtoImagem implements Serializable {

	private static final long serialVersionUID = 213987559003443875L;
	private String nome;
	private Long tamanho;
	private File arquivo;

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

	public File getArquivo() {
		return arquivo;
	}

	public void setArquivo(File arquivo) {
		this.arquivo = arquivo;
	}
}
