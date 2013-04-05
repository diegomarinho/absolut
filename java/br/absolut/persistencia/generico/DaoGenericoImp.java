package br.absolut.persistencia.generico;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class DaoGenericoImp<T, ID extends Serializable> extends HibernateDaoSupport implements DaoGenerico<T, ID> {

	protected static Log LOG = LogFactory.getLog(DaoGenericoImp.class);
	private final Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public DaoGenericoImp() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	@Override
	public T save(T entity) {
		try {
			this.getHibernateTemplate().save(entity);
			return entity;
		} catch (final HibernateException ex) {
			DaoGenericoImp.LOG.error(ex);
			throw convertHibernateAccessException(ex);
		}
	}

	@Override
	public T update(T entity) {
		try {
			this.getHibernateTemplate().update(entity);
			return entity;
		} catch (final HibernateException ex) {
			DaoGenericoImp.LOG.error(ex);
			throw convertHibernateAccessException(ex);
		}
	}

	@Override
	public void delete(T entity) {
		try {
			this.getHibernateTemplate().delete(entity);
		} catch (final HibernateException ex) {
			DaoGenericoImp.LOG.error(ex);
			throw convertHibernateAccessException(ex);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public T findById(ID id) {
		try {
			return (T) this.getHibernateTemplate().get(getPersistentClass(), id);
		} catch (final HibernateException ex) {
			DaoGenericoImp.LOG.error(ex);
			throw convertHibernateAccessException(ex);
		}
	}

	@SuppressWarnings("unchecked")
	protected List<T> findByCrieria(Criterion... criterion) {
		try {
			//Criteria criteria = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(getPersistentClass()); 
			DetachedCriteria criteria = DetachedCriteria.forClass(getPersistentClass());
			for (Criterion c : criterion) {
				criteria.add(c);
			}

			//return (List<T>) criteria.list();
			return getHibernateTemplate().findByCriteria(criteria);
		} catch (final HibernateException ex) {
			DaoGenericoImp.LOG.error(ex);
			throw convertHibernateAccessException(ex);
		}
	}

	@SuppressWarnings("unchecked")
	protected List<T> findByParams(String query, Map<String, Object> params) {
		try {
			return this.getHibernateTemplate().find(query, params);
		} catch (final HibernateException ex) {
			DaoGenericoImp.LOG.error(ex);
			throw convertHibernateAccessException(ex);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> listAll() {
		try {
			return this.getHibernateTemplate().loadAll(getPersistentClass());
		} catch (final HibernateException ex) {
			DaoGenericoImp.LOG.error(ex);
			throw convertHibernateAccessException(ex);
		}
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}
}
