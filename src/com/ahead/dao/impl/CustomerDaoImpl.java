package com.ahead.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.ahead.dao.CustomerDao;
import com.ahead.domain.Customer;
/**
 * 业务方法全部由继承BaseDaoImpl而来的
 * @author 10512
 *
 */
@Repository("customerDao")
public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {

	@Resource(name="sessionFactory")
	public void setSF(SessionFactory sf){
		super.setSessionFactory(sf);
	}
	
	@Override
	public List<Object[]> getIndustryCount() {
		List<Object[]> list = getHibernateTemplate().execute(new HibernateCallback<List<Object[]>>() {
			String sql = "SELECT bd.dict_item_name,COUNT(*) total 	 "+
							"	FROM cst_customer c,base_dict bd     "+
							"	WHERE c.cust_industry=bd.dict_id     "+
							"	GROUP BY c.cust_industry             ";
			public List<Object[]> doInHibernate(Session session) throws HibernateException {
				SQLQuery query = session.createSQLQuery(sql);
				return query.list();
			}
		});
		return list;
	}
	
}
