package com.ahead.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ahead.dao.CustomerDao;
import com.ahead.domain.Customer;
import com.ahead.service.CustomerService;
import com.ahead.utils.PageBean;
@Service("customerService")
@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
public class CustomerServiceImpl implements CustomerService {
	@Resource(name="customerDao")
	private CustomerDao cd;
	
	@Override
	public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {
		//1、查询总条数
		Integer totalCount = cd.getTotalCount(dc);
		//2、创建pageBean对象
		PageBean pageBean = new PageBean(currentPage, pageSize, totalCount);
		//3、查询每页的数据
		List<Customer> list = cd.getPageList(dc,pageBean.getStart(),pageBean.getPageSize());
		pageBean.setList(list);
		return pageBean;
	}

	public void setCd(CustomerDao cd) {
		this.cd = cd;
	}

	@Override
	public void save(Customer customer) {
		//1、维护Customer和BaseDict的关系，但是由于一请求过来
		//struts2就会帮我们初始化BaseDict对象并赋值dict_id，所以此时该对象就是游离状态的
		//不需要手动维护关系 
		//(hibernate中只要是游离或者持久化状态的，只要两张表有关联，查其中一个就会查出所有相关联的数据)
		//2、调用dao保存对象
		cd.save(customer);
	}

	@Override
	public Customer getById(Long cust_id) {
		return cd.getById(cust_id);
	}

	@Override
	public void saveOrUpdate(Customer customer) {
		cd.saveOrUpdate(customer);
	}

	@Override
	public List<Object[]> getIndustryCount() {
		return cd.getIndustryCount();
	}
}
