package br.absolut.persistencia.movimento.compra;

import java.util.Date;
import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;

import br.absolut.persistencia.generico.DaoGenericoImp;
import br.absolut.persistencia.movimento.pagamento.Pagamento;
import br.absolut.util.function.Funcoes;

public class DaoCompraImp extends DaoGenericoImp<Compra, Long> implements
		DaoCompra {

	public List<Compra> recuperaComprasPorParametro(List<Object[]> listaParam) {
		int cont = 0;
		Criterion[] criterion = new Criterion[listaParam.size()];

		for (Object[] param : listaParam) {
			criterion[cont] = Expression.eq(param[0].toString(), param[1]);

			cont++;
		}

		return findByCrieria(criterion);
	}

	@SuppressWarnings("unchecked")
	public List<Compra> recuperaComprasPorPeriodo(Date dtInicial, Date dtFinal) {
		String hql = "from Compra"
				+ " where dtCompra between '"
				+ Funcoes.utilDateToSqlDate(dtInicial)
				+ "' and '"
				+ Funcoes.utilDateToSqlDate(dtFinal)
				+ "' order by dtCompra";
		
		return (List<Compra>) this.getHibernateTemplate().find(hql);
	}

	/* (non-Javadoc)
	 * @see br.absolut.persistencia.movimento.compra.DaoCompra#recuperaComprasParceladas()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Compra> recuperaComprasParceladas() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Compra.class);
		criteria.setFetchMode("pagamento", FetchMode.JOIN);
		
		criteria.add(Expression.ne("pagamento.cod", Pagamento.COD_PAGAMENTO_A_VISTA));
		
		return getHibernateTemplate().findByCriteria(criteria);
	}

}
