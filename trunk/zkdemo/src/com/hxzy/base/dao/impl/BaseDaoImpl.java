/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Mar 18, 2010</p>
 * <p>更新：</p>
 */
package com.hxzy.base.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hxzy.base.dao.BaseDao;
import com.hxzy.base.util.GenericsUtils;
import com.hxzy.base.util.Pagination;

/**
 * @author xiacc
 * 
 * 描述：
 */
public abstract class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	private Class<T> pojoClass;

	public BaseDaoImpl() {
		this.pojoClass = GenericsUtils.getSuperClassGenricType(getClass());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see base.dao.BaseDao#createCriteria()
	 */
	public Criteria createCriteria() {
		return getSession().createCriteria(getPojoClass());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see base.dao.BaseDao#createCriteria(org.hibernate.criterion.Criterion[])
	 */
	public Criteria createCriteria(Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(getPojoClass());
		for (Criterion c : criterions)
			criteria.add(c);
		return criteria;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see base.dao.BaseDao#createCriteria(java.lang.String, boolean,
	 *      org.hibernate.criterion.Criterion[])
	 */
	public Criteria createCriteria(String orderBy, boolean isAsc, Criterion... criterions) {
		Criteria criteria = createCriteria(criterions);
		if (isAsc)
			criteria.addOrder(Order.asc(orderBy));
		else
			criteria.addOrder(Order.desc(orderBy));
		return criteria;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see base.dao.BaseDao#createQuery(java.lang.String, java.lang.Object[])
	 */
	public Query createQuery(String hql, Object... values) {
		Query query = getSession().createQuery(hql);
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		return query;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see base.dao.BaseDao#delete(java.lang.Long)
	 */
	public void delete(Long id) {
		T instance = findById(id);
		if (instance != null)
			getHibernateTemplate().delete(instance);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see base.dao.BaseDao#delete(java.lang.Object)
	 */
	public void delete(T persistentInstance) {
		getHibernateTemplate().delete(persistentInstance);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see base.dao.BaseDao#find(java.lang.String, java.lang.Object)
	 */
	public List find(String hql, Object[] values) {
		return getHibernateTemplate().find(hql, values);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see base.dao.BaseDao#findByCriteria(org.hibernate.Criteria)
	 */
	public List<T> findByCriteria(Criteria criteria) {
		List list = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return transformResults(list);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see base.dao.BaseDao#findByCriteria(org.hibernate.criterion.DetachedCriteria)
	 */
	@SuppressWarnings( { "unchecked", "deprecation" })
	public List<T> findByCriteria(final DetachedCriteria detachedCriteria) {
		return (List<T>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Criteria criteria = detachedCriteria.setResultTransformer(
						Criteria.DISTINCT_ROOT_ENTITY).getExecutableCriteria(session);
				List list = criteria.list();
				return transformResults(list);
			}
		}, true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see base.dao.BaseDao#findByExample(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByExample(T instance) {
		List<T> results = (List<T>) getHibernateTemplate().findByExample(instance);
		return results;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see base.dao.BaseDao#findById(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	public T findById(Long id) {
		return (T) getHibernateTemplate().get(getPojoClassName(), id);
	}

	public T loadById(Long id) {
		return (T) getHibernateTemplate().load(getPojoClassName(), id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see base.dao.BaseDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByProperty(String propertyName, Object value) {
		String queryString = "from " + getPojoClassName() + " as model where model." + propertyName
				+ "= ?";
		return (List<T>) getHibernateTemplate().find(queryString, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see base.dao.BaseDao#findPageByCriteria(org.hibernate.Criteria, int,
	 *      int)
	 */
	public Pagination findPageByCriteria(Criteria criteria, int pageSize, int pageNo) {
		int totalCount = getCountByCriteria(criteria);
		criteria.setProjection(null);
		Pagination p = new Pagination(pageNo, pageSize, totalCount);

		if (totalCount < 1) {
			p.setList(new ArrayList());
			return p;
		}

		List items = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).setFirstResult(
				p.getFirstResult()).setMaxResults(p.getPageSize()).list();
		items = transformResults(items);

		p.setList(items);

		return p;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see base.dao.BaseDao#findPageByCriteria(org.hibernate.Criteria)
	 */
	public Pagination findPageByCriteria(Criteria criteria) {
		return findPageByCriteria(criteria, 10, 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see base.dao.BaseDao#findPageByCriteria(org.hibernate.Criteria, int)
	 */
	public Pagination findPageByCriteria(Criteria criteria, int pageNo) {
		return findPageByCriteria(criteria, pageNo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see base.dao.BaseDao#findPageByCriteria(org.hibernate.criterion.DetachedCriteria,
	 *      int, int)
	 */
	@SuppressWarnings("deprecation")
	public Pagination findPageByCriteria(final DetachedCriteria detachedCriteria,
			final int pageSize, final int pageNo) {
		return (Pagination) getHibernateTemplate().execute(new HibernateCallback() {
			@SuppressWarnings("unchecked")
			public Object doInHibernate(Session session) throws HibernateException {
				Criteria criteria = detachedCriteria.getExecutableCriteria(session);
				int totalCount = ((Integer) criteria.setProjection(Projections.rowCount())
						.uniqueResult()).intValue();
				criteria.setProjection(null);

				Pagination p = new Pagination(pageNo, pageSize, totalCount);

				if (totalCount < 1) {
					p.setList(new ArrayList());
					return p;
				}

				List items = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
						.setFirstResult(p.getFirstResult()).setMaxResults(p.getPageSize()).list();
				items = transformResults(items);

				p.setList(items);
				return p;
			}
		}, true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see base.dao.BaseDao#findPageByCriteria(org.hibernate.criterion.DetachedCriteria)
	 */
	public Pagination findPageByCriteria(DetachedCriteria detachedCriteria) {
		return findPageByCriteria(detachedCriteria, 10, 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see base.dao.BaseDao#findPageByCriteria(org.hibernate.criterion.DetachedCriteria,
	 *      int)
	 */
	public Pagination findPageByCriteria(DetachedCriteria detachedCriteria, int pageNo) {
		return findPageByCriteria(detachedCriteria, 10, pageNo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see base.dao.BaseDao#findPageByQuery(java.lang.String, int, int,
	 *      java.lang.Object[])
	 */
	public Pagination findPageByQuery(String hql, int pageSize, int pageNo, Object... values) {
		int totalCount = getCountByQuery(hql, values);

		Pagination p = new Pagination(pageNo, pageSize, totalCount);

		if (totalCount < 1) {
			p.setList(new ArrayList());
			return p;
		}

		Query query = createQuery(hql, values);
		List items = query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).setFirstResult(
				p.getFirstResult()).setMaxResults(p.getPageSize()).list();

		p.setList(items);
		return p;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see base.dao.BaseDao#findPageByQuery(java.lang.String,
	 *      java.lang.Object[])
	 */
	public Pagination findPageByQuery(String hql, Object... values) {
		return findPageByQuery(hql, 10, 1, values);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see base.dao.BaseDao#findPageByQuery(java.lang.String, int,
	 *      java.lang.Object[])
	 */
	public Pagination findPageByQuery(String hql, int pageNo, Object... values) {
		return findPageByQuery(hql, 10, pageNo, values);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see base.dao.BaseDao#getCountByCriteria(org.hibernate.Criteria)
	 */
	public int getCountByCriteria(Criteria criteria) {
		Integer count = (Integer) criteria.setProjection(Projections.rowCount()).uniqueResult();
		return count.intValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see base.dao.BaseDao#getCountByCriteria(org.hibernate.criterion.DetachedCriteria)
	 */
	@SuppressWarnings("deprecation")
	public int getCountByCriteria(final DetachedCriteria detachedCriteria) {
		Integer count = (Integer) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Criteria criteria = detachedCriteria.getExecutableCriteria(session);
				return criteria.setProjection(Projections.rowCount()).uniqueResult();
			}
		}, true);
		return count.intValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see base.dao.BaseDao#getCountByQuery(java.lang.String,
	 *      java.lang.Object[])
	 */
	public int getCountByQuery(String hql, Object... values) {
		String countQueryString = " select count (*) " + removeSelect(removeOrders(hql));
		List countlist = getHibernateTemplate().find(countQueryString, values);
		return (Integer) countlist.get(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see base.dao.BaseDao#getPojoClass()
	 */
	public Class<T> getPojoClass() {
		return this.pojoClass;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see base.dao.BaseDao#getPojoClassName()
	 */
	public String getPojoClassName() {
		return getPojoClass().getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see base.dao.BaseDao#loadAll()
	 */
	public List<T> loadAll() {		
		return (List<T>) getHibernateTemplate().loadAll(getPojoClass());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see base.dao.BaseDao#save(java.lang.Object)
	 */
	public Long save(T transientInstance) {
		return (Long) getHibernateTemplate().save(transientInstance);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see base.dao.BaseDao#update(java.lang.Object)
	 */
	public void update(T transientInstance) {
		getHibernateTemplate().update(transientInstance);
	}

	/**
	 * 将联合查询的结果内容从Map或者Object[]转换为实体类型，如果没有转换必要则直接返回
	 */
	private List transformResults(List items) {
		if (items.size() > 0) {
			if (items.get(0) instanceof Map) {
				ArrayList list = new ArrayList(items.size());
				for (int i = 0; i < items.size(); i++) {
					Map map = (Map) items.get(i);
					list.add(map.get(CriteriaSpecification.ROOT_ALIAS));
				}
				return list;
			} else if (items.get(0) instanceof Object[]) {
				ArrayList list = new ArrayList(items.size());
				int pos = 0;
				for (int i = 0; i < ((Object[]) items.get(0)).length; i++) {
					if (((Object[]) items.get(0))[i] != null
							&& ((Object[]) items.get(0))[i].getClass() == getPojoClass()) {
						pos = i;
						break;
					}
				}
				for (int i = 0; i < items.size(); i++) {
					list.add(((Object[]) items.get(i))[pos]);
				}
				return list;
			} else
				return items;
		} else
			return items;
	}

	/**
	 * 方法取自SpringSide. 去除hql的orderby子句
	 */
	private static String removeOrders(String hql) {
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}

	/**
	 * 方法取自SpringSide. 去除hql的select子句，未考虑union的情况
	 */
	private static String removeSelect(String hql) {
		int beginPos = hql.toLowerCase().indexOf("from");
		return hql.substring(beginPos);
	}

	/**
	 * 设置 pojoClass
	 */
	public void setPojoClass(Class<T> pojoClass) {
		this.pojoClass = pojoClass;
	}
	
	/**
	 * 描述：执行更新sql语句
	 * @param sql
	 * @return
	 */
	public Integer updateByHql(final String sql){
		
		return (Integer) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(sql);
				return query.executeUpdate();
			}
			
		});
	}
	
	/**
	 * 描述：执行查询sql语句
	 * @param sql
	 * @return
	 */
	public List queryByHql(final String sql){
		return (List) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(sql);
				return query.list();
			}
			
		});
	}

}
