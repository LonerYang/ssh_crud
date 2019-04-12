package com.ahead.web.action;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ahead.domain.Customer;
import com.ahead.domain.SaleVisit;
import com.ahead.domain.User;
import com.ahead.service.SaleVisitService;
import com.ahead.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
@Controller("saleVisitAction")
@Scope("prototype")
public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit>{
	@Resource(name="saleVisitService")
	private SaleVisitService svs;
	private SaleVisit saleVisit = new SaleVisit();
	
	private Integer currentPage;
	private Integer pageSize;
	
	public String add() throws Exception{
		//0把当前登录的用户赋值给当前的拜访记录
		User user = (User) ActionContext.getContext().getSession().get("user");
		saleVisit.setUser(user);
		//1、调用service执行保存方法
		svs.save(saleVisit);
		//2、重定向到查询客户拜访记录Action中
		return "toList";
	}

	
	public String list() throws Exception {
		// 1、将获取过来的参数传到service层
		// 为了代码的复用性，所以这里选择用离线Criteria
		DetachedCriteria dc = DetachedCriteria.forClass(SaleVisit.class);
		if (saleVisit.getCustomer() != null && saleVisit.getCustomer().getCust_id() != null) {
			dc.add(Restrictions.eq("customer.cust_id",saleVisit.getCustomer().getCust_id()));
		}
		PageBean pageBean = svs.getPageBean(dc, currentPage, pageSize);
		// 2、返回pageBean对象并存入request域然后转发到jsp页面
		ActionContext.getContext().put("pageBean", pageBean);
		return "list";
	}
	
	public String toEdit() throws Exception {
		//1、调用service查询对象
		SaleVisit sv = svs.getById(saleVisit.getVisit_id());
		//2、把获取的对象放入request域中
		ActionContext.getContext().put("saleVisit", sv);
		//3、转发到add页面
		return "edit";
	}

	
	@Override
	public SaleVisit getModel() {
		return saleVisit;
	}
	public void setSvs(SaleVisitService svs) {
		this.svs = svs;
	}


	public Integer getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}


	public Integer getPageSize() {
		return pageSize;
	}


	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
