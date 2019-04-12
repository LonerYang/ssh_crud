package com.ahead.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.ahead.dao.BaseDao;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	
	
	private Class clazz;
	
	public BaseDaoImpl(){
		//获得当前类带有泛型的父类 (因为这行代码是在该类的子类运行的，所以运行时获取父类就是当前类)
		ParameterizedType superClass = (ParameterizedType) this.getClass().getGenericSuperclass();
		//获得运行时类上的泛型类型(获得真实类型参数)
		clazz = (Class) superClass.getActualTypeArguments()[0];
	}
	
	@Override
	public void save(T t) {
		getHibernateTemplate().save(t);
	}
	
	public void saveOrUpdate(T t){
		getHibernateTemplate().saveOrUpdate(t);
	}

	@Override
	public void delete(T t) {
		getHibernateTemplate().delete(t);
	}

	@Override
	public void delete(Serializable id) {
		T t = this.getById(id);
		getHibernateTemplate().delete(t);
	}

	@Override
	public void update(T t) {
		getHibernateTemplate().update(t);
	}

	@Override
	public T getById(Serializable id) {
		return (T) getHibernateTemplate().get(clazz, id);
	}

	@Override
	public Integer getTotalCount(DetachedCriteria dc) {
		dc.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) getHibernateTemplate().findByCriteria(dc);
		dc.setProjection(null);
		if(list != null && list.size() != 0){
			return list.get(0).intValue();
		}else{
			return null;
		}
	}

	@Override
	public List<T> getPageList(DetachedCriteria dc, int start, int pageSize) {
		List<T> list = (List<T>) getHibernateTemplate().findByCriteria(dc, start, pageSize);
		return list;
	}

}
