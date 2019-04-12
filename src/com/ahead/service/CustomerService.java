package com.ahead.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.ahead.domain.Customer;
import com.ahead.utils.PageBean;

public interface CustomerService {

	//获取每页的数据
	PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize);

	//执行保存用户操作
	void save(Customer customer);

	//根据id获取对象
	Customer getById(Long cust_id);

	//如果有id就是修改没有id就是保存
	void saveOrUpdate(Customer customer);
	
	//查询客户数量
	List<Object[]> getIndustryCount();

}
