package br.absolut.persistencia.produto;

import java.util.List;

import br.absolut.persistencia.generico.DaoGenerico;


public interface DaoProduto extends DaoGenerico<Produto, Long> {
	public List<Produto> recuperaProdutosPorParametro(List<Object[]> listaParam);
	public List<Produto> recuperaProdutosComBaixoEstoque();
}
