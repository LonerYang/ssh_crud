package com.ahead.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ahead.dao.LinkManDao;
import com.ahead.domain.LinkMan;
import com.ahead.service.LinkManService;
import com.ahead.utils.PageBean;
@Service("linkManService")
@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
public class LinkManServiceImpl implements LinkManService {
	@Resource(name="linkManDao")
	private LinkManDao lmd;
	
	@Override
	public void save(LinkMan linkMan) {
		lmd.save(linkMan);
	}

	
	public void setLmd(LinkManDao lmd) {
		this.lmd = lmd;
	}


	@Override
	public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {
		//1、获得总条数
		Integer totalCount = lmd.getTotalCount(dc);
		//2、创建PageBean对象
		PageBean pageBean = new PageBean(currentPage, pageSize, totalCount);
		//3、查找每页的数据
		//必须要去pageBean对象中拿数据，因为里面进行了非空处理
		List<LinkMan> list = lmd.getPageList(dc, pageBean.getStart(), pageBean.getPageSize());
		pageBean.setList(list);
		
		return pageBean;
	}


	@Override
	public LinkMan getById(Long lkm_id) {
		return lmd.getById(lkm_id);
	}


	@Override
	public void saveOrUpdate(LinkMan linkMan) {
		lmd.saveOrUpdate(linkMan);
	}

	
}
