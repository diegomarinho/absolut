package br.absolut.persistencia.usuario;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Expression;

import br.absolut.persistencia.generico.DaoGenericoImp;

public class DaoUsuarioImp extends DaoGenericoImp<Usuario, Long> implements
		DaoUsuario {

	public Usuario login(String login, String hash) {
		Criterion[] criterion = new Criterion[3];

		criterion[0] = Expression.eq("login", login);
		criterion[1] = Expression.eq("senha", hash);
		criterion[2] = Expression.isNull("dtExclusao");

		try {
			Criteria criteria = this.getSession().createCriteria(getPersistentClass());
			for (Criterion c : criterion) {
				criteria.add(c);
			}

			return (Usuario) criteria.uniqueResult();
		} catch (final HibernateException ex) {
			DaoGenericoImp.LOG.error(ex);
			throw convertHibernateAccessException(ex);
		}
	}

}
