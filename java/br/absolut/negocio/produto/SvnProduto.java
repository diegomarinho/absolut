package br.absolut.negocio.produto;

import java.util.List;

import br.absolut.persistencia.produto.Produto;


public interface SvnProduto {
	public Produto inclui(Produto produto);
	public Produto altera(Produto produto);
	public List<Produto> recuperaTodos();
	public List<Produto> consultaPorParametros(DtoParametroConsulta parametros);
	public List<Produto> recuperaProdutosComBaixoEstoque();
	public Produto recuperaPorId(Long codigo);	
	public void exclui(Long codigo);
	public void excluiLogicamente(Long codigo);
}
