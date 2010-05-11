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
	 * 加载所有的对象
	 */
	public List<T> loadAll();

	/** */
	/**
	 * 根据hql查询
	 *
	 * @param values 可变参数
	 */
	public List find(String hql, Object[] values);

	/** */
	/**
	 * 根据条件加载对象
	 * 
	 * @param criteria Criteria实例
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(final Criteria criteria);

	/** */
	/**
	 * 根据条件加载对象
	 * @param detachedCriteria DetachedCriteria实例
	 */
	@SuppressWarnings( { "unchecked", "deprecation" })
	public List<T> findByCriteria(final DetachedCriteria detachedCriteria);

	/** */
	/**
	 * 根据给定的实例查找对象
	 */
	public List<T> findByExample(T instance);

	/** */
	/**
	 * 根据ID查找对象
	 */
	public T findById(Long id);
	
	
	/**
	 * 描述：根据id加载对象
	 * @param id
	 * @return
	 */
	public T loadById(Long id);

	/** */
	/**
	 * 根据某个具体属性进行查找
	 */
	public List<T> findByProperty(String propertyName, Object value);

	/** */
	/**
	 * 新建对象实例化
	 */
	public Long save(T transientInstance);

	/** */
	/**
	 * 更新已存在的对象
	 */
	public void update(T transientInstance);

	/** */
	/**
	 * 删除指定ID的对象
	 */
	public void delete(Long id);

	/** */
	/**
	 * 删除指定对象
	 */
	public void delete(T persistentInstance);

	//分页
	/** */
	/**
	 * 根据Criteria加载分页，指定页大小和起始位置
	 */
	public Pagination findPageByCriteria(final Criteria criteria,
			final int pageSize, final int pageNo);

	/** */
	/**
	 * 根据Criteria加载分页，默认页大小，从第0条开始
	 */
	public Pagination findPageByCriteria(final Criteria criteria);

	/** */
	/**
	 * 根据Criteria加载分页，默认页大小，从第pageNo条开始
	 */
	public Pagination findPageByCriteria(final Criteria criteria,
			final int pageNo);

	/** */
	/**
	 * 根据Criteria统计总数
	 */
	public int getCountByCriteria(final Criteria criteria);

	/** */
	/**
	 * 根据DetachedCriteria加载分页，指定页大小和起始位置
	 */
	@SuppressWarnings("deprecation")
	public Pagination findPageByCriteria(
			final DetachedCriteria detachedCriteria, final int pageSize,
			final int pageNo);

	/** */
	/**
	 * 根据DetachedCriteria加载分页，默认页大小，从第0条开始
	 */
	public Pagination findPageByCriteria(
			final DetachedCriteria detachedCriteria);

	/** */
	/**
	 * 根据DetachedCriteria加载分页，默认页大小，从第pageNo条开始
	 */
	public Pagination findPageByCriteria(
			final DetachedCriteria detachedCriteria, final int pageNo);

	/** */
	/**
	 * 根据DetachedCriteria统计总数
	 */
	@SuppressWarnings("deprecation")
	public int getCountByCriteria(final DetachedCriteria detachedCriteria);

	/** */
	/**
	 * 根据hql加载分页，指定页大小和起始位置
	 */
	public Pagination findPageByQuery(final String hql,
			final int pageSize, final int pageNo, Object... values);

	/** */
	/**
	 * 根据hql加载分页，默认页大小，从第0条开始
	 */
	public Pagination findPageByQuery(final String hql, Object... values);

	/** */
	/**
	 * 根据hql加载分页，默认页大小，从第pageNo条开始
	 */
	public Pagination findPageByQuery(final String hql,
			final int pageNo, Object... values);

	/** */
	/**
	 * 根据hql统计总数
	 */
	public int getCountByQuery(final String hql, Object... values);

	
	/**
	 * 方法描述：创建Criteria对象
	 * @return Criteria对象
	 */
	public Criteria createCriteria();
	
	/** */
	/**
	 * 创建Criteria对象
	 *
	 * @param criterions 可变的Restrictions条件列表
	 */
	public Criteria createCriteria(Criterion... criterions);

	/** */
	/**
	 * 创建Criteria对象，带排序字段与升降序字段
	 */
	public Criteria createCriteria(String orderBy, boolean isAsc,
			Criterion... criterions);

	/** */
	/**
	 * 方法取自SpringSide.
	 * 创建Query对象. 对于需要first,max,fetchsize,cache,cacheRegion等诸多设置的函数,可以在返回Query后自行设置.
	 * 留意可以连续设置,如下：
	 * <pre>
	 * dao.getQuery(hql).setMaxResult(100).setCacheable(true).list();
	 * </pre>
	 * 调用方式如下：
	 * <pre>
	 *        dao.createQuery(hql)
	 *        dao.createQuery(hql,arg0);
	 *        dao.createQuery(hql,arg0,arg1);
	 *        dao.createQuery(hql,new Object[arg0,arg1,arg2])
	 * </pre>
	 *
	 * @param values 可变参数.
	 */
	public Query createQuery(String hql, Object... values);
	
	/**
	 * 描述：执行更新sql
	 * @param sql
	 * @return
	 */
	public Integer updateByHql(String sql);
	
	/**
	 * 描述：执行查询sql
	 * @param sql
	 * @return
	 */
	public List queryByHql(String sql);

}
