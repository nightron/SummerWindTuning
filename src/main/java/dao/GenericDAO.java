package dao;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface GenericDAO<T> {

	public T create(T t);

	public void delete(Object id);

	public T find(Object id);

	public T update(T t);

}
