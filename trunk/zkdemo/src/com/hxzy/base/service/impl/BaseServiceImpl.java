/**
 * 
 */
package com.hxzy.base.service.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hxzy.base.dao.BaseDao;
import com.hxzy.base.service.BaseService;
import com.hxzy.base.util.Pagination;



/**
 * @author xiacc
 *
 * √Ë ˆ£∫
 */
public abstract class BaseServiceImpl<T, V extends BaseDao<T>> implements BaseService<T, V>{
	
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	protected abstract V getDao();

	/* (non-Javadoc)
	 * @see base.service.BaseService#createCriteria()
	 */
	public Criteria createCriteria() {
		return getDao().createCriteria();
	}

	/* (non-Javadoc)
	 * @see base.service.BaseService#createCriteria(org.hibernate.criterion.Criterion[])
	 */
	public Criteria createCriteria(Criterion... criterions) {
		return getDao().createCriteria(criterions);
	}

	/* (non-Javadoc)
	 * @see base.service.BaseService#createCriteria(java.lang.String, boolean, org.hibernate.criterion.Criterion[])
	 */
	public Criteria createCriteria(String orderBy, boolean isAsc, Criterion... criterions) {
		return getDao().createCriteria(orderBy, isAsc, criterions);
	}

	/* (non-Javadoc)
	 * @see base.service.BaseService#createQuery(java.lang.String, java.lang.Object[])
	 */
	public Query createQuery(String hql, Object... values) {
		return getDao().createQuery(hql, values);
	}

	/* (non-Javadoc)
	 * @see base.service.BaseService#delete(java.lang.Long)
	 */
	public void delete(Long id) {
		getDao().delete(id);
	}

	/* (non-Javadoc)
	 * @see base.service.BaseService#delete(java.lang.Object)
	 */
	public void delete(T persistentInstance) {
		getDao().delete(persistentInstance);
	}

	/* (non-Javadoc)
	 * @see base.service.BaseService#find(java.lang.String, java.lang.Object)
	 */
	public List<T> find(String hql, Object[] values) {
		return getDao().find(hql, values);
	}

	/* (non-Javadoc)
	 * @see base.service.BaseService#findByCriteria(org.hibernate.Criteria)
	 */
	public List<T> findByCriteria(Criteria criteria) {
		return getDao().findByCriteria(criteria);
	}

	/* (non-Javadoc)
	 * @see base.service.BaseService#findByCriteria(org.hibernate.criterion.DetachedCriteria)
	 */
	public List<T> findByCriteria(DetachedCriteria detachedCriteria) {
		return getDao().findByCriteria(detachedCriteria);
	}

	/* (non-Javadoc)
	 * @see base.service.BaseService#findByExample(java.lang.Object)
	 */
	public List<T> findByExample(T instance) {
		return getDao().findByExample(instance);
	}

	/* (non-Javadoc)
	 * @see base.service.BaseService#findById(java.lang.Long)
	 */
	public T findById(Long id) {
		return getDao().findById(id);
	}

	/* (non-Javadoc)
	 * @see base.service.BaseService#findByProperty(java.lang.String, java.lang.Object)
	 */
	public List<T> findByProperty(String propertyName, Object value) {
		return getDao().findByProperty(propertyName, value);
	}

	/* (non-Javadoc)
	 * @see base.service.BaseService#findPageByCriteria(org.hibernate.Criteria, int, int)
	 */
	public Pagination findPageByCriteria(Criteria criteria, int pageSize, int pageNo) {
		return getDao().findPageByCriteria(criteria, pageSize, pageNo);
	}

	/* (non-Javadoc)
	 * @see base.service.BaseService#findPageByCriteria(org.hibernate.Criteria, int)
	 */
	public Pagination findPageByCriteria(Criteria criteria, int pageNo) {
		return getDao().findPageByCriteria(criteria, pageNo);
	}

	/* (non-Javadoc)
	 * @see base.service.BaseService#findPageByCriteria(org.hibernate.Criteria)
	 */
	public Pagination findPageByCriteria(Criteria criteria) {
		return getDao().findPageByCriteria(criteria);
	}

	/* (non-Javadoc)
	 * @see base.service.BaseService#findPageByCriteria(org.hibernate.criterion.DetachedCriteria, int, int)
	 */
	public Pagination findPageByCriteria(DetachedCriteria detachedCriteria, int pageSize,
			int pageNo) {
		return getDao().findPageByCriteria(detachedCriteria, pageSize, pageNo);
	}

	/* (non-Javadoc)
	 * @see base.service.BaseService#findPageByCriteria(org.hibernate.criterion.DetachedCriteria, int)
	 */
	public Pagination findPageByCriteria(DetachedCriteria detachedCriteria, int pageNo) {
		return getDao().findPageByCriteria(detachedCriteria, pageNo);
	}

	/* (non-Javadoc)
	 * @see base.service.BaseService#findPageByCriteria(org.hibernate.criterion.DetachedCriteria)
	 */
	public Pagination findPageByCriteria(DetachedCriteria detachedCriteria) {
		return getDao().findPageByCriteria(detachedCriteria);
	}

	/* (non-Javadoc)
	 * @see base.service.BaseService#findPageByQuery(java.lang.String, int, int, java.lang.Object[])
	 */
	public Pagination findPageByQuery(String hql, int pageSize, int pageNo,
			Object... values) {
		return getDao().findPageByQuery(hql, pageSize, pageNo, values);
	}

	/* (non-Javadoc)
	 * @see base.service.BaseService#findPageByQuery(java.lang.String, int, java.lang.Object[])
	 */
	public Pagination findPageByQuery(String hql, int pageNo, Object... values) {
		return getDao().findPageByQuery(hql, pageNo, values);
	}

	/* (non-Javadoc)
	 * @see base.service.BaseService#findPageByQuery(java.lang.String, java.lang.Object[])
	 */
	public Pagination findPageByQuery(String hql, Object... values) {
		return getDao().findPageByQuery(hql, values);
	}

	/* (non-Javadoc)
	 * @see base.service.BaseService#getCountByCriteria(org.hibernate.Criteria)
	 */
	public int getCountByCriteria(Criteria criteria) {
		return getDao().getCountByCriteria(criteria);
	}

	/* (non-Javadoc)
	 * @see base.service.BaseService#getCountByCriteria(org.hibernate.criterion.DetachedCriteria)
	 */
	public int getCountByCriteria(DetachedCriteria detachedCriteria) {
		return getDao().getCountByCriteria(detachedCriteria);
	}

	/* (non-Javadoc)
	 * @see base.service.BaseService#getCountByQuery(java.lang.String, java.lang.Object[])
	 */
	public int getCountByQuery(String hql, Object... values) {
		return getDao().getCountByQuery(hql, values);
	}

	/* (non-Javadoc)
	 * @see base.service.BaseService#loadAll()
	 */
	public List<T> loadAll() {
		return getDao().loadAll();
	}

	/* (non-Javadoc)
	 * @see base.service.BaseService#save(java.lang.Object)
	 */
	public Long save(T transientInstance) {
		return getDao().save(transientInstance);
	}

	/* (non-Javadoc)
	 * @see base.service.BaseService#update(java.lang.Object)
	 */
	public void update(T transientInstance) {
		getDao().update(transientInstance);
	}
	
	
	
}
