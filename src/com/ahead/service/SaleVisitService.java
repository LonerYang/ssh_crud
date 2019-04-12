package com.ahead.service;

import org.hibernate.criterion.DetachedCriteria;

import com.ahead.domain.SaleVisit;
import com.ahead.utils.PageBean;

public interface SaleVisitService {

	//保存客户拜访记录
	void save(SaleVisit saleVisit);

	//客户拜访记录分页
	public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize);

	//查找客服拜访对象
	SaleVisit getById(String visit_id);
}
