package com.ahead.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ahead.dao.SaleVisitDao;
import com.ahead.domain.Customer;
import com.ahead.domain.SaleVisit;
import com.ahead.service.SaleVisitService;
import com.ahead.utils.PageBean;
@Service("saleVisitService")
@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
public class SaleVisitServiceImpl implements SaleVisitService {
	@Resource(name="saleVisitDao")
	private SaleVisitDao svd;
	
	@Override
	public void save(SaleVisit saleVisit) {
		svd.saveOrUpdate(saleVisit);
	}

	public void setSvd(SaleVisitDao svd) {
		this.svd = svd;
	}
	
	@Override
	public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {
		//1、查询总条数
		Integer totalCount = svd.getTotalCount(dc);
		//2、创建pageBean对象
		PageBean pageBean = new PageBean(currentPage, pageSize, totalCount);
		//3、查询每页的数据
		List<SaleVisit> list = svd.getPageList(dc,pageBean.getStart(),pageBean.getPageSize());
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public SaleVisit getById(String visit_id) {
		return svd.getById(visit_id);
	}

}
