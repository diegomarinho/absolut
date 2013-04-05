package br.absolut.negocio.movimento.estoque;

import java.util.List;

import br.absolut.persistencia.movimento.estoque.DaoEstoque;
import br.absolut.persistencia.movimento.estoque.Estoque;

public class SvnEstoqueImp implements SvnEstoque {
	private DaoEstoque daoEstoque;
	
	public SvnEstoqueImp() {}

	public Estoque altera(Estoque estoque) {
		return this.daoEstoque.update(estoque);
	}

	public void exclui(Long codigo) {
		Estoque estoque = this.daoEstoque.findById(codigo);
		
		if(estoque != null)
			this.daoEstoque.delete(estoque);		
	}

	public Estoque inclui(Estoque estoque) {
		return this.daoEstoque.save(estoque);
	}

	public Estoque recuperaPorId(Long codigo) {
		return this.daoEstoque.findById(codigo);
	}

	public List<Estoque> recuperaTodos() {
		return this.daoEstoque.listAll();
	}

	public void setDaoEstoque(DaoEstoque daoEstoque) {
		this.daoEstoque = daoEstoque;
	}

}
