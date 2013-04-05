package br.absolut.persistencia.produto;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Expression;

import br.absolut.persistencia.generico.DaoGenericoImp;

public class DaoProdutoImp extends DaoGenericoImp<Produto, Long> implements DaoProduto {

	@Override
	public List<Produto> recuperaProdutosPorParametro(List<Object[]> listaParam) {
		int cont = 0;
		Criterion[] criterion = new Criterion[listaParam.size() + 1];

		for (Object[] param : listaParam) {
			if (param[0].equals("nome") || param[0].equals("descricao")) {
				criterion[cont] = Expression.like(param[0].toString(), param[1]);
			} else {
				criterion[cont] = Expression.eq(param[0].toString(), param[1]);
			}
			cont++;
		}

		criterion[cont] = Expression.isNull("dtExclusao");

		return findByCrieria(criterion);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Produto> recuperaProdutosComBaixoEstoque() {
		String hql = "from Produto where saldoAtual <= 5 and dtExclusao is null order by saldoAtual";

		return this.getHibernateTemplate().find(hql);
	}

}
