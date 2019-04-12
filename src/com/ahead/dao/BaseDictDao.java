package com.ahead.dao;

import java.util.List;

import com.ahead.domain.BaseDict;

public interface BaseDictDao extends BaseDao<BaseDict>{

	List<BaseDict> getListByTypeCode(String dict_type_code);

}
