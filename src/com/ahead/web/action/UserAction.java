package com.ahead.web.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ahead.domain.Customer;
import com.ahead.domain.User;

import com.ahead.service.UserService;
import com.ahead.utils.PageBean;
import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
@Scope("prototype")
@Controller("userAction")
public class UserAction extends ActionSupport implements ModelDriven<User>{
	@Resource(name="userService")
	private UserService userService;
	
	User u = new User();
	private Integer page;
	private Integer rows;
	
	public String list() throws Exception {
		// 1、将获取过来的参数传到service层
		// 为了代码的复用性，所以这里选择用离线Criteria
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		
		PageBean pageBean = userService.getPageBean(dc, page, rows);
		// 2、返回pageBean对象并存入request域然后转发到jsp页面
		ActionContext.getContext().put("pageBean", pageBean);
		
		//因为easyUI需要接收的json格式为
		//{"total":xx,"rows",[]} total:总条数  rows：每行显示的数据
		//所有用map来进行封装
		Map map = new HashMap();
		map.put("total", pageBean.getTotalCount());
		map.put("rows", pageBean.getList());
		
		String json = JSON.toJSONString(map);
		ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().write(json);
		return null;
	}

	public String login() throws Exception{
		User user = userService.getByUserCode(u);
		
		ActionContext.getContext().getSession().put("user", user);
		return "toHome";
	}
	
	public String delete() throws Exception{
		userService.deleteById(u.getUser_id());
		return null;
	}
	
	public String toEdit() throws Exception{
		
		//调用service查询对象
		User user = userService.getById(u.getUser_id());

		user.setUser_password(""); //设置前台不回显密码
		String json = JSON.toJSONString(user);
		
		ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().write(json);
		
		return null;
	}
	
	public String regist() throws Exception{
		
		try {
			//1、调用service进行保存
			userService.saveUser(u);
		} catch (Exception e) {
			System.out.println("抛出异常==>>用户名已经存在");
			ActionContext.getContext().put("error", e.getMessage());
			return "errorRegist";
		}
		//2、重定向到登录页面
		return "toLogin";
	}
	
	@Override
	public User getModel() {
		return u;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}
	
	
}
