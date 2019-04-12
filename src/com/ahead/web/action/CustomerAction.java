package com.ahead.web.action;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ahead.domain.Customer;
import com.ahead.service.CustomerService;
import com.ahead.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
@Controller("customerAction")
@Scope("prototype")
public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{
	
	@Resource(name="customerService")
	private CustomerService cs;
	
	//这里写一个对应提交过来的一样的键值，就会把提交过来的文件自动封装到File对象中
	private File photo;
	//在键值后面加上固定的FileName后缀就会把文件名称封装到属性中
	private String photoFileName;
	//在键值后面加上固定的ContentType后缀就会把文件的MIME类型封装到属性中
	private String photoContentType;
	
	
	Customer customer = new Customer();
	
	private Integer currentPage;
	private Integer pageSize;
	
	public String list() throws Exception {
		// 1、将获取过来的参数传到service层
		// 为了代码的复用性，所以这里选择用离线Criteria
		DetachedCriteria dc = DetachedCriteria.forClass(Customer.class);
		if (customer.getCust_name() != null) {
			dc.add(Restrictions.like("cust_name", "%" + customer.getCust_name() + "%"));
		}
		PageBean pageBean = cs.getPageBean(dc, currentPage, pageSize);
		// 2、返回pageBean对象并存入request域然后转发到jsp页面
		ActionContext.getContext().put("pageBean", pageBean);
		return "list";
		
	}

	public String add()  {
//		System.out.println("文件MIME类型为"+photoContentType);
//		System.out.println("文件名称为"+photoFileName);
		//把获取过来的文件剪切到指定目录下
		if(photo != null){
			photo.renameTo(new File("B:/"+photoFileName)); 
		}
		//1、调用service
		cs.saveOrUpdate(customer);
		//2、重定向到Action_list
		return "toList";
	}
	
	public String industryCount() throws Exception{
		List<Object[]> list = cs.getIndustryCount();
		ActionContext.getContext().put("list", list);
		return "industryCount";
	}
	
	
	public String toEdit() throws Exception{
		//1、调用service根据id查找对象
		Customer c = cs.getById(customer.getCust_id());
		//2、把查到的对象放入到request域中
		ActionContext.getContext().put("customer", c);
		return "edit";
	}
	
	@Override
	public Customer getModel() {
		return customer;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public void setCs(CustomerService cs) {
		this.cs = cs;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	
	public File getPhoto() {
		return photo;
	}

	public void setPhoto(File photo) {
		this.photo = photo;
	}

	public String getPhotoFileName() {
		return photoFileName;
	}

	public void setPhotoFileName(String photoFileName) {
		this.photoFileName = photoFileName;
	}

	public String getPhotoContentType() {
		return photoContentType;
	}

	public void setPhotoContentType(String photoContentType) {
		this.photoContentType = photoContentType;
	}
}
