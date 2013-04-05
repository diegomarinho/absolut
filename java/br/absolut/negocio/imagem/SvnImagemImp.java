package br.absolut.negocio.imagem;

import java.util.List;

import br.absolut.persistencia.imagem.DaoImagem;
import br.absolut.persistencia.imagem.Imagem;

public class SvnImagemImp implements SvnImagem {
	private DaoImagem daoImagem;

	public Imagem altera(Imagem imagem) {
		return this.daoImagem.update(imagem);
	}

	public void exclui(Long codigo) {
		Imagem imagem = this.daoImagem.findById(codigo);
		if(imagem != null)
			this.daoImagem.delete(imagem);
	}

	public Imagem inclui(Imagem imagem) {
		return this.daoImagem.save(imagem);
	}

	public Imagem recuperaPorId(Long codigo) {
		return this.daoImagem.findById(codigo);
	}

	public List<Imagem> recuperaTodos() {
		return this.daoImagem.listAll();
	}

	public void setDaoImagem(DaoImagem daoImagem) {
		this.daoImagem = daoImagem;
	}

}
