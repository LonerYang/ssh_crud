package com.ahead.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.ahead.dao.LinkManDao;
import com.ahead.domain.LinkMan;
@Repository("linkManDao")
public class LinkManDaoImpl extends BaseDaoImpl<LinkMan> implements LinkManDao{

	@Resource(name="sessionFactory")
	public void setSF(SessionFactory sf){
		super.setSessionFactory(sf);
	}
}
