package com.ahead.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface BaseDao<T> {

	void saveOrUpdate(T t);
	 
	/**
	 * 增  保存用户
	 * @param t
	 */
	void save(T t);
	
	/**
	 * 删  根据对象删除
	 * @param t
	 */
	void delete(T t);
	
	/**
	 * 删 根据id删除
	 * @param id
	 */
	void delete(Serializable id);

	/**
	 * 改  修改用户
	 * @param t
	 */
	void update(T t);
	
	/**
	 * 查 根据id查询用户
	 * @param id
	 * @return
	 */
	T getById(Serializable id);

	/**
	 * 查  根据离线Criteria对象查询条数	
	 * @param dc
	 * @return
	 */
	Integer getTotalCount(DetachedCriteria dc);

	/**
	 * 查 查询分页
	 * @param dc
	 * @param start 当前页的索引下标
	 * @param pageSize 每页的条数
	 * @return
	 */
	List<T> getPageList(DetachedCriteria dc,int start,int pageSize);
}
