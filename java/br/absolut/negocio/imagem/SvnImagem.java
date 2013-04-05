package br.absolut.negocio.imagem;

import java.util.List;

import br.absolut.persistencia.imagem.Imagem;

public interface SvnImagem {
	public Imagem inclui(Imagem imagem);
	public Imagem altera(Imagem imagem);
	public List<Imagem> recuperaTodos();
	public Imagem recuperaPorId(Long codigo);
	public void exclui(Long codigo);
}
