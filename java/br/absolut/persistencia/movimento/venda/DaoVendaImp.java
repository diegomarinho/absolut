package br.absolut.persistencia.movimento.venda;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Expression;

import br.absolut.persistencia.generico.DaoGenericoImp;
import br.absolut.util.function.Funcoes;

public class DaoVendaImp extends DaoGenericoImp<Venda, Long> implements
		DaoVenda {

	public List<Venda> recuperaVendasPorParametro(List<Object[]> listaParam) {
		int cont = 0;
		Criterion[] criterion = new Criterion[listaParam.size()];

		for (Object[] param : listaParam) {
			criterion[cont] = Expression.eq(param[0].toString(), param[1]);
			cont++;
		}

		return findByCrieria(criterion);
	}

	@SuppressWarnings("unchecked")
	public List<DtoHistoricoVenda> recuperaHistoricoVenda(Date dtInicial,
			Date dtFinal) {
		List<DtoHistoricoVenda> listaHistoricoVenda = new ArrayList<DtoHistoricoVenda>();
		DtoHistoricoVenda dtoHistoricoVenda;
		String hql = "select dtVenda, count(cod) as qtd, sum(total) as total from Venda"
				+ " where dtVenda between '"
				+ Funcoes.utilDateToSqlDate(dtInicial)
				+ "' and '"
				+ Funcoes.utilDateToSqlDate(dtFinal)
				+ "' group by dt_venda order by dt_venda";

		List<Object[]> lista = this.getHibernateTemplate().find(hql);

		if (lista != null) {
			for (Object[] historico : lista) {
				dtoHistoricoVenda = new DtoHistoricoVenda();
				dtoHistoricoVenda.setData((Date) historico[0]);
				dtoHistoricoVenda.setQuantidade((Long) historico[1]);
				dtoHistoricoVenda.setTotal((Double) historico[2]);

				listaHistoricoVenda.add(dtoHistoricoVenda);
			}
		}

		return listaHistoricoVenda;
	}
	
	@SuppressWarnings("unchecked")
	public List<Venda> recuperaVendasPorPeriodo(Date dtInicial,
			Date dtFinal) {
		String hql = "from Venda"
				+ " where dtVenda between '"
				+ Funcoes.utilDateToSqlDate(dtInicial)
				+ "' and '"
				+ Funcoes.utilDateToSqlDate(dtFinal)
				+ "' order by dtVenda";
		
		return (List<Venda>) this.getHibernateTemplate().find(hql);
	}
}
