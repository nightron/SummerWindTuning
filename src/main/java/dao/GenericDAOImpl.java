package dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


public class GenericDAOImpl<T> implements GenericDAO<T>{
	
	@PersistenceContext(unitName="persistenceUN")
	protected EntityManager entityManager;
	private Class<T> type;

	private static Logger logger = LogManager.getLogger();
    public GenericDAOImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }
    
    @Transactional(rollbackFor=Exception.class)
    public T create(final T t) {
        this.entityManager.persist(t);
        return t;
    }

    public void delete(final Object id) {
        this.entityManager.remove(this.entityManager.getReference(type, id));
    }

    public T find(final Object id) {
        return (T) this.entityManager.find(type, id);
    }

    public T update(final T t) {
        return this.entityManager.merge(t);    
    }
//
//    @Autowired
//    @Qualifier("entityManager")
//    public void setEntityManager(EntityManager entityManager){
//    	this.entityManager = entityManager;
//    }
    
}
