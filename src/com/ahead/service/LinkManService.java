package com.ahead.service;

import org.hibernate.criterion.DetachedCriteria;

import com.ahead.domain.LinkMan;
import com.ahead.utils.PageBean;

public interface LinkManService {

	//保存联系人
	void save(LinkMan linkMan);

	//分页
	PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize);

	//根据id查找对象
	LinkMan getById(Long lkm_id);

	//保存或修改联系人
	void saveOrUpdate(LinkMan linkMan);

}
