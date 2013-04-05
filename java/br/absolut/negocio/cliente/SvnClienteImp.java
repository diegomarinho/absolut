package br.absolut.negocio.cliente;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.absolut.persistencia.cliente.Cliente;
import br.absolut.persistencia.cliente.DaoCliente;

public class SvnClienteImp implements SvnCliente {

	private DaoCliente daoCliente;

	public SvnClienteImp() throws Exception {}

	public Cliente altera(Cliente cliente) {
		return this.daoCliente.update(cliente);
	}

	public List<Cliente> consultaPorParametros(DtoParametroConsulta parametros) {
		List<String[]> listaParam = new ArrayList<String[]>();
		String[] param;

		if (parametros != null) {
			if (parametros.getCpfCnpj() != null && !parametros.getCpfCnpj().isEmpty()) {
				param = new String[2];
				param[0] = "cpfCnpj";
				param[1] = parametros.getCpfCnpj();

				listaParam.add(param);
			}
			if (parametros.getNome() != null && !parametros.getNome().isEmpty()) {
				param = new String[2];
				param[0] = "nome";
				param[1] = "%" + parametros.getNome() + "%";

				listaParam.add(param);
			}
			if (parametros.getRg() != null && !parametros.getRg().isEmpty()) {
				param = new String[2];
				param[0] = "identidade";
				param[1] = parametros.getRg();

				listaParam.add(param);
			}
		}

		return this.daoCliente.recuperaClientesPorParametro(listaParam);
	}

	public void exclui(Long codigo) {
		Cliente cliente = this.daoCliente.findById(codigo);

		if (cliente != null) this.daoCliente.delete(cliente);
	}

	public Cliente inclui(Cliente cliente) {
		return this.daoCliente.save(cliente);
	}

	public Cliente recuperaPorId(Long codigo) {
		return this.daoCliente.findById(codigo);
	}

	public List<Cliente> recuperaTodos() {
		return this.daoCliente.listAll();
	}

	public void excluiLogicamente(Long codigo) {
		Cliente cliente = this.daoCliente.findById(codigo);

		if (cliente != null) {
			cliente.setDtExclusao(new Date());
			this.daoCliente.update(cliente);
		}

	}

	public void setDaoCliente(DaoCliente daoCliente) {
		this.daoCliente = daoCliente;
	}
}
