package com.ahead.web.interceptor;

import java.util.Map;

import com.ahead.domain.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class PrivilegeInterceptor extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		//1、获得session
		Map<String, Object> session = ActionContext.getContext().getSession();
		//2、从session中获取user对象
		User user = (User) session.get("user");
		//3、判断对象是否为空
		if(user != null){
			//不为空就放行
			return invocation.invoke();
		}else{
			//为空就重定向到登录界面
			return "toLogin";
		}
	}

}
