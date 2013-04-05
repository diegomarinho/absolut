package br.absolut.negocio.movimento.estoque;

import java.util.List;

import br.absolut.persistencia.movimento.estoque.Estoque;

public interface SvnEstoque {
	public Estoque inclui(Estoque estoque);
	public Estoque altera(Estoque estoque);
	public List<Estoque> recuperaTodos();
	public Estoque recuperaPorId(Long codigo);	
	public void exclui(Long codigo);
}
