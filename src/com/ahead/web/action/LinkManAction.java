package com.ahead.web.action;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ahead.domain.LinkMan;
import com.ahead.service.LinkManService;
import com.ahead.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
@Controller("linkManAction")
@Scope("prototype")
public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan>{
	
	@Resource(name="linkManService")
	private LinkManService lms;
	private LinkMan linkMan = new LinkMan();
	private Integer pageSize;
	private Integer currentPage;
	
	
	public String list() throws Exception {
		DetachedCriteria dc = DetachedCriteria.forClass(LinkMan.class);
		if (StringUtils.isNotBlank(linkMan.getLkm_name())) {
			dc.add(Restrictions.like("lkm_name", "%" + linkMan.getLkm_name() + "%"));
		}
		if (linkMan.getCustomer() != null && linkMan.getCustomer().getCust_id() != null) {
			dc.add(Restrictions.eq("customer.cust_id", linkMan.getCustomer().getCust_id()));
		}
		// 1、调用service查询分页数据，返回PageBean对象
		PageBean pageBean = lms.getPageBean(dc, currentPage, pageSize);
		// 2、把pageBean存入request中
		ActionContext.getContext().put("pageBean", pageBean);
		return "list";
	}
	
	public String add() throws Exception{
		//1、把接收到的参数传到service层
		lms.saveOrUpdate(linkMan);
		//2、重定向到list方法中
		return "toList";
	}

	public String toEdit() throws Exception {

		// 1、调用service根据lkm_id查询出对象
		LinkMan lm = lms.getById(linkMan.getLkm_id());
		// 2、存到request域中
		System.out.println(lm.getCustomer());
		ActionContext.getContext().put("linkMan", lm);

		return "edit";
	}
	
	
	
	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	@Override
	public LinkMan getModel() {
		return linkMan;
	}
	
	public void setLms(LinkManService lms) {
		this.lms = lms;
	}
	
	

}
