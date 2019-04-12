package com.ahead.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.ahead.domain.Customer;

public interface CustomerDao extends BaseDao<Customer>{

	//查询客户数量统计
	List<Object[]> getIndustryCount();
}
