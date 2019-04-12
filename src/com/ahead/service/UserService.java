package com.ahead.service;

import org.hibernate.criterion.DetachedCriteria;

import com.ahead.domain.User;
import com.ahead.utils.PageBean;

public interface UserService {
	//获取对象
	User getByUserCode(User u);
	//注册用户
	void saveUser(User u);
	PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize);
	//根据id查询对象
	User getById(Long user_id);
	//根据id删除对象
	void deleteById(Long user_id);
}
