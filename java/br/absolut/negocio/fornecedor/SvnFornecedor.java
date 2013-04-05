package br.absolut.negocio.fornecedor;

import java.util.List;

import br.absolut.persistencia.fornecedor.Fornecedor;



public interface SvnFornecedor {
	public Fornecedor inclui(Fornecedor fornecedor);
	public Fornecedor altera(Fornecedor fornecedor);
	public List<Fornecedor> recuperaTodos();
	public List<Fornecedor> consultaPorParametros(DtoParametroConsulta parametros);
	public Fornecedor recuperaPorId(Long codigo);	
	public void exclui(Long codigo);
	public void excluiLogicamente(Long codigo);
}
