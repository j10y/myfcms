/**
 * <p>项目名称：公共模块</p>
 * <p>版权所有 (c) 2009 湖北融汇信息科技有限公司</p>
 * <p>版本：1.0</p>
 * <p>日期：2009-12-16</p>
 * <p>更新：</p>
 */
package laji;

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

import base.dao.BaseDao;
import base.util.GenericsUtils;
import base.util.Pagination;

/**
 * 类名：HibernateGenericDao
 * 描述：
 * @author xiacc
 */
public abstract class BaseDaoImpl1<T> extends HibernateDaoSupport implements BaseDao<T> {
    private Class<T> pojoClass;
    
    /** *//**
     * 初始化DAO，获取POJO类型
     */
    public BaseDaoImpl1() {
//        this.pojoClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.pojoClass = GenericsUtils.getSuperClassGenricType(getClass());
    }
    
    /** *//* (non-Javadoc)
	 * @see com.ronghui.common.base.dao.impl.HibernateGenericDao2#getPojoClass()
	 */
    public Class<T> getPojoClass() {
        return this.pojoClass;
    }
    
    /** *//* (non-Javadoc)
	 * @see com.ronghui.common.base.dao.impl.HibernateGenericDao2#getPojoClassName()
	 */
    public String getPojoClassName() {
        return getPojoClass().getName();
    }

    //加载对象
    
    /** *//* (non-Javadoc)
	 * @see com.ronghui.common.base.dao.impl.HibernateGenericDao2#loadAll()
	 */
    public List<T> loadAll() {
        return (List<T>)getHibernateTemplate().loadAll(getPojoClass());
    }
    
    /** *//* (non-Javadoc)
	 * @see com.ronghui.common.base.dao.impl.HibernateGenericDao2#find(java.lang.String, java.lang.Object)
	 */
    public List find(String hql, Object values) {
        return getHibernateTemplate().find(hql, values);
    }

    /** *//* (non-Javadoc)
	 * @see com.ronghui.common.base.dao.impl.HibernateGenericDao2#findByCriteria(org.hibernate.Criteria)
	 */
    @SuppressWarnings("unchecked")
	public List<T> findByCriteria(final Criteria criteria) {
        List list = criteria.list(); 
        return transformResults(list);
    }
    
    /** *//* (non-Javadoc)
	 * @see com.ronghui.common.base.dao.impl.HibernateGenericDao2#findByCriteria(org.hibernate.criterion.DetachedCriteria)
	 */
    @SuppressWarnings({ "unchecked", "deprecation" })
	public List<T> findByCriteria(final DetachedCriteria detachedCriteria) {
        return (List<T>) getHibernateTemplate().execute(new HibernateCallback() {
               public Object doInHibernate(Session session) throws HibernateException {
                   Criteria criteria = detachedCriteria.getExecutableCriteria(session);
                   List list = criteria.list(); 
                   return transformResults(list); 
               }
           }, true);
    }
    
    /* (non-Javadoc)
	 * @see com.ronghui.common.base.dao.impl.HibernateGenericDao2#findByExample(T)
	 */
    public List<T> findByExample(T instance) {
        List<T> results = (List<T>)getHibernateTemplate().findByExample(instance);
        return results;
    }    
    
    /* (non-Javadoc)
	 * @see com.ronghui.common.base.dao.impl.HibernateGenericDao2#findById(ID)
	 */
    public T findById(Long id) {
        return (T) getHibernateTemplate().get(getPojoClassName(), id);
    }
    
    /*
	 * @see com.ronghui.common.base.dao.impl.HibernateGenericDao2#findByProperty(java.lang.String, java.lang.Object)
	 */
    public List<T> findByProperty(String propertyName, Object value) {
       String queryString = "from " + getPojoClassName() + " as model where model." 
                               + propertyName + "= ?";
       return (List<T>)getHibernateTemplate().find(queryString, value);
    }
    
    //新建、修改、删除
    
    /** *//* (non-Javadoc)
	 * @see com.ronghui.common.base.dao.impl.HibernateGenericDao2#save(T)
	 */
    public Long save(T transientInstance) {
        return (Long)getHibernateTemplate().save(transientInstance);
    }
    
    /** *//* (non-Javadoc)
	 * @see com.ronghui.common.base.dao.impl.HibernateGenericDao2#update(T)
	 */
    public void update(T transientInstance) {
        getHibernateTemplate().update(transientInstance);
    }
    
    /** *//* (non-Javadoc)
	 * @see com.ronghui.common.base.dao.impl.HibernateGenericDao2#delete(ID)
	 */
    public void delete(Long id) {
           T instance = findById(id);
           if (instance != null)
               getHibernateTemplate().delete(instance);
    }
    
    /** *//* (non-Javadoc)
	 * @see com.ronghui.common.base.dao.impl.HibernateGenericDao2#delete(T)
	 */
    public void delete(T persistentInstance) {
        getHibernateTemplate().delete(persistentInstance);
    }    
    
    //分页
    /** *//* (non-Javadoc)
	 * @see com.ronghui.common.base.dao.impl.HibernateGenericDao2#findPageByCriteria(org.hibernate.Criteria, int, int)
	 */
    public Pagination findPageByCriteria(final Criteria criteria, final int pageSize, final int startIndex) {   
        int totalCount = getCountByCriteria(criteria);   
        criteria.setProjection(null);
        List items = criteria.setFirstResult(startIndex).setMaxResults(pageSize).list();
        items = transformResults(items);
        Pagination ps = new Pagination(items, totalCount, pageSize, startIndex);
        return ps;
    }
    
    /** *//* (non-Javadoc)
	 * @see com.ronghui.common.base.dao.impl.HibernateGenericDao2#findPageByCriteria(org.hibernate.Criteria)
	 */
    public Pagination findPageByCriteria(final Criteria criteria) {   
        return findPageByCriteria(criteria, Pagination.PAGESIZE, 0);   
    }   
 
    /** *//* (non-Javadoc)
	 * @see com.ronghui.common.base.dao.impl.HibernateGenericDao2#findPageByCriteria(org.hibernate.Criteria, int)
	 */
    public Pagination findPageByCriteria(final Criteria criteria, final int startIndex) {   
        return findPageByCriteria(criteria, Pagination.PAGESIZE, startIndex);   
    }
    
    /** *//* (non-Javadoc)
	 * @see com.ronghui.common.base.dao.impl.HibernateGenericDao2#getCountByCriteria(org.hibernate.Criteria)
	 */
    public int getCountByCriteria(final Criteria criteria) {   
        Integer count = (Integer) criteria.setProjection(Projections.rowCount()).uniqueResult();    
        return count.intValue();   
    }
    
    /** *//* (non-Javadoc)
	 * @see com.ronghui.common.base.dao.impl.HibernateGenericDao2#findPageByCriteria(org.hibernate.criterion.DetachedCriteria, int, int)
	 */
    @SuppressWarnings("deprecation")
	public Pagination findPageByCriteria(final DetachedCriteria detachedCriteria, final int pageSize, final int startIndex) {   
        return (Pagination) getHibernateTemplate().execute(new HibernateCallback() {   
            @SuppressWarnings("unchecked")
			public Object doInHibernate(Session session) throws HibernateException {   
                Criteria criteria = detachedCriteria.getExecutableCriteria(session);
                int totalCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();   
                criteria.setProjection(null);
                List items = criteria.setFirstResult(startIndex).setMaxResults(pageSize).list();
                items = transformResults(items);
                Pagination ps = new Pagination(items, totalCount, pageSize, startIndex);   
                return ps;   
            }
        }, true);   
    }
    
    /** *//* (non-Javadoc)
	 * @see com.ronghui.common.base.dao.impl.HibernateGenericDao2#findPageByCriteria(org.hibernate.criterion.DetachedCriteria)
	 */
    public Pagination findPageByCriteria(final DetachedCriteria detachedCriteria) {   
        return findPageByCriteria(detachedCriteria, Pagination.PAGESIZE, 0);   
    }   
  
    /** *//* (non-Javadoc)
	 * @see com.ronghui.common.base.dao.impl.HibernateGenericDao2#findPageByCriteria(org.hibernate.criterion.DetachedCriteria, int)
	 */
    public Pagination findPageByCriteria(final DetachedCriteria detachedCriteria, final int startIndex) {   
        return findPageByCriteria(detachedCriteria, Pagination.PAGESIZE, startIndex);   
    } 
    
    /** *//* (non-Javadoc)
	 * @see com.ronghui.common.base.dao.impl.HibernateGenericDao2#getCountByCriteria(org.hibernate.criterion.DetachedCriteria)
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
    
    /** *//* (non-Javadoc)
	 * @see com.ronghui.common.base.dao.impl.HibernateGenericDao2#findPageByQuery(java.lang.String, int, int, java.lang.Object)
	 */
    public Pagination findPageByQuery(final String hql, final int pageSize, final int startIndex, Object...values) {
        int totalCount = getCountByQuery(hql, values);
        
        if (totalCount < 1)
            return new Pagination(new ArrayList(0), 0);

        Query query = createQuery(hql, values);
        List items = query.setFirstResult(startIndex).setMaxResults(pageSize).list();
        Pagination ps = new Pagination(items, totalCount, pageSize, startIndex);
        return ps;
    }
    
    /** *//* (non-Javadoc)
	 * @see com.ronghui.common.base.dao.impl.HibernateGenericDao2#findPageByQuery(java.lang.String, java.lang.Object)
	 */
    public Pagination findPageByQuery(final String hql, Object...values) {   
        return findPageByQuery(hql, Pagination.PAGESIZE, 0, values);   
    }   
 
    /** *//* (non-Javadoc)
	 * @see com.ronghui.common.base.dao.impl.HibernateGenericDao2#findPageByQuery(java.lang.String, int, java.lang.Object)
	 */
    public Pagination findPageByQuery(final String hql, final int startIndex, Object...values) {   
        return findPageByQuery(hql, Pagination.PAGESIZE, startIndex, values);  
    }
    
    /** *//* (non-Javadoc)
	 * @see com.ronghui.common.base.dao.impl.HibernateGenericDao2#getCountByQuery(java.lang.String, java.lang.Object)
	 */
    public int getCountByQuery(final String hql, Object...values) {   
        String countQueryString = " select count (*) " + removeSelect(removeOrders(hql));
        List countlist = getHibernateTemplate().find(countQueryString, values);
        return (Integer) countlist.get(0);
    }
    
    public Criteria createCriteria(){
    	return getSession().createCriteria(getPojoClass());
    }
    
    //创建Criteria和Query
    
    /** *//* (non-Javadoc)
	 * @see com.ronghui.common.base.dao.impl.HibernateGenericDao2#createCriteria(org.hibernate.criterion.Criterion)
	 */
    public Criteria createCriteria(Criterion...criterions) {
        Criteria criteria = getSession().createCriteria(getPojoClass());
        for (Criterion c : criterions)
            criteria.add(c);
        return criteria;
    }

    /** *//* (non-Javadoc)
	 * @see com.ronghui.common.base.dao.impl.HibernateGenericDao2#createCriteria(java.lang.String, boolean, org.hibernate.criterion.Criterion)
	 */
    public Criteria createCriteria(String orderBy, boolean isAsc, Criterion...criterions) {
        Criteria criteria = createCriteria(criterions);
        if (isAsc)
            criteria.addOrder(Order.asc(orderBy));
        else
            criteria.addOrder(Order.desc(orderBy));
        return criteria;
    }
    
    /** *//* (non-Javadoc)
	 * @see com.ronghui.common.base.dao.impl.HibernateGenericDao2#createQuery(java.lang.String, java.lang.Object)
	 */
    public Query createQuery(String hql, Object...values) {
        Query query = getSession().createQuery(hql);
        for (int i = 0; i < values.length; i++) {
            query.setParameter(i, values[i]);
        }
        return query;
    }
    
    /** *//**
     * 方法取自SpringSide.
     * 去除hql的select子句，未考虑union的情况
     */
    private static String removeSelect(String hql) {
        int beginPos = hql.toLowerCase().indexOf("from");
        return hql.substring(beginPos);
    }

    /** *//**
     * 方法取自SpringSide.
     * 去除hql的orderby子句
     */
    private static String removeOrders(String hql) {
        Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*",  Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(hql);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "");
        }
        m.appendTail(sb);
        return sb.toString();
    }
    
    /** *//**
     * 将联合查询的结果内容从Map或者Object[]转换为实体类型，如果没有转换必要则直接返回
     */
    private List transformResults(List items) {
        if (items.size() > 0) {
            if (items.get(0) instanceof Map) {
                ArrayList list = new ArrayList(items.size());
                for (int i = 0; i < items.size(); i++) {
                    Map map = (Map)items.get(i);
                    list.add(map.get(CriteriaSpecification.ROOT_ALIAS));
                }
                return list;
            } else if (items.get(0) instanceof Object[]) {
                ArrayList list = new ArrayList(items.size());
                int pos = 0;
                for (int i = 0; i < ((Object[])items.get(0)).length; i++) {
                    if (((Object[])items.get(0))[i].getClass() == getPojoClass()) {
                        pos = i;
                        break;
                    }
                }
                for (int i = 0; i < items.size(); i++) {
                    list.add(((Object[])items.get(i))[pos]);
                }
                return list;
            } else
                return items;
        } else
            return items;
    }
}
