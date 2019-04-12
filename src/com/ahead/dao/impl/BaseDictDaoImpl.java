package com.ahead.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ahead.dao.BaseDictDao;
import com.ahead.domain.BaseDict;
//这个就相当于<bean name="baseDictDao"></bean>
@Repository("baseDictDao")
public class BaseDictDaoImpl extends BaseDaoImpl<BaseDict> implements BaseDictDao {
	
	@Resource(name="sessionFactory")
	public void setSF(SessionFactory sf){
		super.setSessionFactory(sf);
	}
	
	@Override
	public List<BaseDict> getListByTypeCode(String dict_type_code) {
		DetachedCriteria dc = DetachedCriteria.forClass(BaseDict.class);
		dc.add(Restrictions.eq("dict_type_code", dict_type_code));
		List<BaseDict> list = (List<BaseDict>) getHibernateTemplate().findByCriteria(dc);
		return list;
	}


}
