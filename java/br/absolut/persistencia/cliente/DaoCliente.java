package br.absolut.persistencia.cliente;

import java.util.List;

import br.absolut.persistencia.generico.DaoGenerico;


public interface DaoCliente extends DaoGenerico<Cliente, Long> {
	public List<Cliente> recuperaClientesPorParametro(List<String[]> listaParam);
	
}
