package br.absolut.persistencia.fornecedor;

import java.util.List;

import br.absolut.persistencia.generico.DaoGenerico;

public interface DaoFornecedor extends DaoGenerico<Fornecedor, Long> {
	public List<Fornecedor> recuperaFornecedoresPorParametro(List<String[]> listaParam);
}
