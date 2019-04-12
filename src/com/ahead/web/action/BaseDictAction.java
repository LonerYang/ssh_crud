package com.ahead.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ahead.domain.BaseDict;
import com.ahead.service.BaseDictService;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
@Controller("baseDictAction")
@Scope("prototype")
public class BaseDictAction extends ActionSupport {

	@Resource(name="baseDictService")
	private BaseDictService bds;
	
	private String dict_type_code;
	
	
	@Override
	public String execute() throws Exception {
		//1、调用servicer获取到数据字典集合
		List<BaseDict> list = bds.getListByTypeCode(dict_type_code);
		//2、把集合转成json字符串
		String json = JSONArray.fromObject(list).toString();
		//3、把json字符串返回给浏览器
		ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().write(json);
		
		return null; //返回null就是告诉struts2不需要处理我这个结果
	}

	public void setDict_type_code(String dict_type_code) {
		this.dict_type_code = dict_type_code;
	}
	public void setBds(BaseDictService bds) {
		this.bds = bds;
	}


}
