package br.absolut.negocio.produto;

import java.util.List;

import br.absolut.persistencia.produto.DaoTipoProduto;
import br.absolut.persistencia.produto.TipoProduto;

public class SvnTipoProdutoImp implements SvnTipoProduto {
	private DaoTipoProduto daoTipoProduto;
	
	public SvnTipoProdutoImp() {}

	public TipoProduto altera(TipoProduto tipoProduto) {
		return this.daoTipoProduto.update(tipoProduto);
	}

	public void exclui(Long codigo) {
		TipoProduto tipoProduto = this.daoTipoProduto.findById(codigo);
		
		if(tipoProduto != null)
			this.daoTipoProduto.delete(tipoProduto);
	}

	public TipoProduto inclui(TipoProduto tipoProduto) {
		return this.daoTipoProduto.save(tipoProduto);
	}

	public TipoProduto recuperaPorId(Long codigo) {
		return this.daoTipoProduto.findById(codigo);
	}

	public List<TipoProduto> recuperaTodos() {
		return this.daoTipoProduto.listAll();
	}

	public void setDaoTipoProduto(DaoTipoProduto daoTipoProduto) {
		this.daoTipoProduto = daoTipoProduto;
	}

}
