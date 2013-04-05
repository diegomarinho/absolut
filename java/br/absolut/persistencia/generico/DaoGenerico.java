package br.absolut.persistencia.generico;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.SERIALIZABLE, readOnly = true)
public interface DaoGenerico<T, ID extends Serializable> {
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	T save(T entity);

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	T update(T entity);

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	void delete(T entity);

	List<T> listAll();

	T findById(ID id);
}
