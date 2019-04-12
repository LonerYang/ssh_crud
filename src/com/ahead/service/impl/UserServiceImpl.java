package com.ahead.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ahead.dao.UserDao;
import com.ahead.domain.Customer;
import com.ahead.domain.User;
import com.ahead.service.UserService;
import com.ahead.utils.MD5Utils;
import com.ahead.utils.PageBean;

//@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=true)
@Service("userService")
@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
public class UserServiceImpl implements UserService{
	@Resource(name="userDao")
	UserDao ud;
	
	@Override
	public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {
		//1、查询总条数
		Integer totalCount = ud.getTotalCount(dc);
		//2、创建pageBean对象
		PageBean pageBean = new PageBean(currentPage, pageSize, totalCount);
		//3、查询每页的数据
		List<User> list = ud.getPageList(dc,pageBean.getStart(),pageBean.getPageSize());
		pageBean.setList(list);
		return pageBean;
	}
	
	@Override
	public User getByUserCode(User u) {
		//1、通过账号获得user
		User existU = ud.getByUserCode(u.getUser_code());
		//如果user为null就抛出账号不存在异常
		if(existU == null){
			throw new RuntimeException("账号不存在");
		}else{
			if(!MD5Utils.md5(u.getUser_password()).equals(existU.getUser_password())){
				throw new RuntimeException("密码错误");
			}
		}
		//如果不为null就对比密码，密码错误就抛出密码错误异常
		
		return existU;
	}

	@Override
//	@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveUser(User u) {
		//如果是前台是保存的话就没有id，就进行下面的逻辑判断
		if (u.getUser_id() == null) {
			//1、去数据库中查对象
			User existU = ud.getByUserCode(u.getUser_code());
			if (existU != null) { //数据库中存在该账号
				//2、如果返回的对象不为空就抛出异常
				throw new RuntimeException("用户已存在");
			} 
		}
		// 3、如果为空说明数据库中不存在然后进行保存
		// 使用MD5进行密码加密
		u.setUser_password(MD5Utils.md5(u.getUser_password()));
		ud.saveOrUpdate(u);
		
	}

	public void setUd(UserDao ud) {
		this.ud = ud;
	}

	@Override
	public User getById(Long user_id) {
		return ud.getById(user_id);
	}

	@Override
	public void deleteById(Long user_id) {
		ud.delete(user_id);
	}
	
	
	

}
