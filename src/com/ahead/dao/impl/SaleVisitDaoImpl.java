package com.ahead.dao.impl;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.ahead.dao.SaleVisitDao;
import com.ahead.domain.SaleVisit;
@Repository("saleVisitDao")
public class SaleVisitDaoImpl extends BaseDaoImpl<SaleVisit> implements SaleVisitDao{

	@Resource(name="sessionFactory")
	public void setSF(SessionFactory sf){
		super.setSessionFactory(sf);
	}
}
