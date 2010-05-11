/**
 * 
 */
package com.hxzy.base.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;

import com.hxzy.base.util.Pagination;

/**
 * @author xiacc
 *
 */
public abstract interface BaseService <T, V>{
	
	/** */
	/**
	 * �������еĶ���
	 */
	public List<T> loadAll();

	/** */
	/**
	 * ����hql��ѯ
	 *
	 * @param values �ɱ����
	 */
	public List find(String hql, Object[] values);

	/** */
	/**
	 * �����������ض���
	 * 
	 * @param criteria Criteriaʵ��
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(final Criteria criteria);

	/** */
	/**
	 * �����������ض���
	 * @param detachedCriteria DetachedCriteriaʵ��
	 */
	@SuppressWarnings( { "unchecked", "deprecation" })
	public List<T> findByCriteria(final DetachedCriteria detachedCriteria);

	/** */
	/**
	 * ���ݸ�����ʵ�����Ҷ���
	 */
	public List<T> findByExample(T instance);

	/** */
	/**
	 * ����ID���Ҷ���
	 */
	public T findById(Long id);
	
	
	/**
	 * ����������id���ض���
	 * @param id
	 * @return
	 */
	public T loadById(Long id);

	/** */
	/**
	 * ����ĳ���������Խ��в���
	 */
	public List<T> findByProperty(String propertyName, Object value);

	/** */
	/**
	 * �½�����ʵ����
	 */
	public Long save(T transientInstance);

	/** */
	/**
	 * �����Ѵ��ڵĶ���
	 */
	public void update(T transientInstance);

	/** */
	/**
	 * ɾ��ָ��ID�Ķ���
	 */
	public void delete(Long id);

	/** */
	/**
	 * ɾ��ָ������
	 */
	public void delete(T persistentInstance);

	//��ҳ
	/** */
	/**
	 * ����Criteria���ط�ҳ��ָ��ҳ��С����ʼλ��
	 */
	public Pagination findPageByCriteria(final Criteria criteria,
			final int pageSize, final int pageNo);

	/** */
	/**
	 * ����Criteria���ط�ҳ��Ĭ��ҳ��С���ӵ�0����ʼ
	 */
	public Pagination findPageByCriteria(final Criteria criteria);

	/** */
	/**
	 * ����Criteria���ط�ҳ��Ĭ��ҳ��С���ӵ�pageNo����ʼ
	 */
	public Pagination findPageByCriteria(final Criteria criteria,
			final int pageNo);

	/** */
	/**
	 * ����Criteriaͳ������
	 */
	public int getCountByCriteria(final Criteria criteria);

	/** */
	/**
	 * ����DetachedCriteria���ط�ҳ��ָ��ҳ��С����ʼλ��
	 */
	@SuppressWarnings("deprecation")
	public Pagination findPageByCriteria(
			final DetachedCriteria detachedCriteria, final int pageSize,
			final int pageNo);

	/** */
	/**
	 * ����DetachedCriteria���ط�ҳ��Ĭ��ҳ��С���ӵ�0����ʼ
	 */
	public Pagination findPageByCriteria(
			final DetachedCriteria detachedCriteria);

	/** */
	/**
	 * ����DetachedCriteria���ط�ҳ��Ĭ��ҳ��С���ӵ�pageNo����ʼ
	 */
	public Pagination findPageByCriteria(
			final DetachedCriteria detachedCriteria, final int pageNo);

	/** */
	/**
	 * ����DetachedCriteriaͳ������
	 */
	@SuppressWarnings("deprecation")
	public int getCountByCriteria(final DetachedCriteria detachedCriteria);

	/** */
	/**
	 * ����hql���ط�ҳ��ָ��ҳ��С����ʼλ��
	 */
	public Pagination findPageByQuery(final String hql,
			final int pageSize, final int pageNo, Object... values);

	/** */
	/**
	 * ����hql���ط�ҳ��Ĭ��ҳ��С���ӵ�0����ʼ
	 */
	public Pagination findPageByQuery(final String hql, Object... values);

	/** */
	/**
	 * ����hql���ط�ҳ��Ĭ��ҳ��С���ӵ�pageNo����ʼ
	 */
	public Pagination findPageByQuery(final String hql,
			final int pageNo, Object... values);

	/** */
	/**
	 * ����hqlͳ������
	 */
	public int getCountByQuery(final String hql, Object... values);

	
	/**
	 * ��������������Criteria����
	 * @return Criteria����
	 */
	public Criteria createCriteria();
	
	/** */
	/**
	 * ����Criteria����
	 *
	 * @param criterions �ɱ��Restrictions�����б�
	 */
	public Criteria createCriteria(Criterion... criterions);

	/** */
	/**
	 * ����Criteria���󣬴������ֶ����������ֶ�
	 */
	public Criteria createCriteria(String orderBy, boolean isAsc,
			Criterion... criterions);

	/** */
	/**
	 * ����ȡ��SpringSide.
	 * ����Query����. ������Ҫfirst,max,fetchsize,cache,cacheRegion��������õĺ���,�����ڷ���Query����������.
	 * ���������������,���£�
	 * <pre>
	 * dao.getQuery(hql).setMaxResult(100).setCacheable(true).list();
	 * </pre>
	 * ���÷�ʽ���£�
	 * <pre>
	 *        dao.createQuery(hql)
	 *        dao.createQuery(hql,arg0);
	 *        dao.createQuery(hql,arg0,arg1);
	 *        dao.createQuery(hql,new Object[arg0,arg1,arg2])
	 * </pre>
	 *
	 * @param values �ɱ����.
	 */
	public Query createQuery(String hql, Object... values);
	
	/**
	 * ������ִ�и���sql
	 * @param sql
	 * @return
	 */
	public Integer updateByHql(String sql);
	
	/**
	 * ������ִ�в�ѯsql
	 * @param sql
	 * @return
	 */
	public List queryByHql(String sql);

}
