package com.ahead.test;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ahead.dao.UserDao;
import com.ahead.domain.User;
import com.ahead.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class HibernateTest {
	
	@Resource(name="sessionFactory")
	private SessionFactory sf;
	@Resource(name="userDao")
	private UserDao ud;
	@Resource(name="userService")
	private UserService us;
	
	@Test
	public void fun1(){
		Configuration cf = new Configuration().configure();
		SessionFactory sf = cf.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		User u = new User();
		u.setUser_code("jack");
		u.setUser_name("杰克");
		u.setUser_password("12345");
		session.save(u);
		
		tx.commit();
		session.close();
		sf.close();
	}
	
	@Test
	public void fun2(){
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		User u = new User();
		u.setUser_code("ago");
		u.setUser_name("杨日健");
		u.setUser_password("12345");
		session.save(u);
		
		tx.commit();
		session.close();
		sf.close();
	}
	@Test
	public void fun3(){
		User user = ud.getByUserCode("jack");
		System.out.println(user);
	}
	
	@Test
	public void fun4(){
		User u = new User();
		u.setUser_name("汤姆");
		u.setUser_code("tom");
		u.setUser_password("12345");
		us.saveUser(u);
	}
}
