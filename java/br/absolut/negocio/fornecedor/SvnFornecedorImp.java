package br.absolut.negocio.fornecedor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.absolut.persistencia.fornecedor.DaoFornecedor;
import br.absolut.persistencia.fornecedor.Fornecedor;

public class SvnFornecedorImp implements SvnFornecedor {
	private DaoFornecedor daoFornecedor;

	public SvnFornecedorImp() {
	}

	public Fornecedor altera(Fornecedor fornecedor) {
		return this.daoFornecedor.update(fornecedor);
	}

	public List<Fornecedor> consultaPorParametros(
			DtoParametroConsulta parametros) {
		List<String[]> listaParam = new ArrayList<String[]>();
		String[] param = new String[2];

		if (parametros != null) {
			if (parametros.getCnpj() != null && !parametros.getCnpj().isEmpty()) {
				param[0] = "cnpj";
				param[1] = parametros.getCnpj();

				listaParam.add(param);
			}
			if (parametros.getNome() != null && !parametros.getNome().isEmpty()) {
				param[0] = "nome";
				param[1] = parametros.getNome() + "%";

				listaParam.add(param);
			}
		}

		return this.daoFornecedor.recuperaFornecedoresPorParametro(listaParam);
	}

	public void exclui(Long codigo) {
		Fornecedor fornecedor = this.daoFornecedor.findById(codigo);

		if (fornecedor != null)
			this.daoFornecedor.delete(fornecedor);
	}

	public void excluiLogicamente(Long codigo) {
		Fornecedor fornecedor = this.daoFornecedor.findById(codigo);

		if (fornecedor != null) {
			fornecedor.setDtExclusao(new Date());
			this.daoFornecedor.update(fornecedor);
		}
	}

	public Fornecedor inclui(Fornecedor fornecedor) {
		return this.daoFornecedor.save(fornecedor);
	}

	public Fornecedor recuperaPorId(Long codigo) {
		return this.daoFornecedor.findById(codigo);
	}

	public List<Fornecedor> recuperaTodos() {
		return this.daoFornecedor.listAll();
	}

	public void setDaoFornecedor(DaoFornecedor daoFornecedor) {
		this.daoFornecedor = daoFornecedor;
	}

}
