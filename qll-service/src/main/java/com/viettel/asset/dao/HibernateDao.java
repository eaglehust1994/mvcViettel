package com.viettel.asset.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Basic DAO operations dependent with Hibernate's specific classes
 * 
 * @see SessionFactory
 */
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class HibernateDao<E, K extends Serializable> {

	private SessionFactory sessionFactory;
	protected Class<? extends E> daoType;

	@SuppressWarnings("unchecked")
	public HibernateDao() {
		daoType = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	public Long insert(E entity) {
	 Long id = (Long)	currentSession().save(entity);
	 return id;
	}

	public void update(E entity) {
		currentSession().update(entity);
	}
	
	public void insertOrUpdate(E entity) {
		currentSession().saveOrUpdate(entity);
	}

	public void delete(E entity) {
		currentSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public E find(K key) {
		return (E) currentSession().get(daoType, key);
	}

	@SuppressWarnings("unchecked")
	public List<E> list() {
		return currentSession().createCriteria(daoType).list();
	}

	public List<E> find(String property, Object value) {
		return find(daoType, property, value);
	}

	public List<E> find(String[] arrProperty, Object[] arrValue) {
		return find(daoType, arrProperty, arrValue);
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> find(Class<? extends T> beanClass, String property, Object value) {
		return findCriteria(beanClass, property, value).list();
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> find(Class<? extends T> beanClass, String[] arrProperty, Object[] arrValue) {
		Criteria cri = currentSession().createCriteria(beanClass);
		for (int i = 0; i < arrProperty.length; i++) {
			cri.add(Restrictions.eq(arrProperty[i], arrValue[i]));
		}
		return cri.list();
	}
	
	private Criteria findCriteria(Class<?> beanClass, String property, Object value) {
		Criteria cri = currentSession().createCriteria(beanClass);
		cri.add(Restrictions.eq(property, value));
		return cri;
	}
	
	public boolean isExists(String property, Object value) {
		return isExists(daoType, property, value);
	}
	
	public boolean isExists(Class<?> beanClass, String property, Object value) {
		Criteria cri = findCriteria(beanClass, property, value);
		cri.setMaxResults(1);
		List<?> lst = cri.list();
		return !lst.isEmpty();
	}
}
