package com.ahead.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.ahead.dao.UserDao;
import com.ahead.domain.User;
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Resource(name="sessionFactory")
	public void setSF(SessionFactory sf){
		super.setSessionFactory(sf);
	}
	
	public User getByUserCode(String user_code){
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		dc.add(Restrictions.eq("user_code", user_code));
		List<User> list = (List<User>) getHibernateTemplate().findByCriteria(dc);
		if(list != null && list.size() != 0){
			return list.get(0);
		}else{
			return null;
		}
	}

}
