package br.absolut.persistencia.fornecedor;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Expression;

import br.absolut.persistencia.generico.DaoGenericoImp;

public class DaoFornecedorImp extends DaoGenericoImp<Fornecedor, Long> implements DaoFornecedor {

	@Override
	public List<Fornecedor> recuperaFornecedoresPorParametro(List<String[]> listaParam) {
		int cont = 0;
		Criterion[] criterion = new Criterion[listaParam.size() + 1];

		for (String[] param : listaParam) {
			if (param[0].equals("nome") || param[0].equals("descricao")) {
				criterion[cont] = Expression.like(param[0], param[1]);
			} else {
				criterion[cont] = Expression.eq(param[0], param[1]);
			}

			cont++;
		}

		criterion[cont] = Expression.isNull("dtExclusao");

		return findByCrieria(criterion);
	}

}
