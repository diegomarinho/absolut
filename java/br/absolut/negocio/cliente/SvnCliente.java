package br.absolut.negocio.cliente;

import java.util.List;

import br.absolut.persistencia.cliente.Cliente;

public interface SvnCliente {
	public Cliente inclui(Cliente cliente);
	public Cliente altera(Cliente cliente);
	public List<Cliente> recuperaTodos();
	public List<Cliente> consultaPorParametros(DtoParametroConsulta parametros);
	public Cliente recuperaPorId(Long codigo);	
	public void exclui(Long codigo);
	public void excluiLogicamente(Long codigo);
}
