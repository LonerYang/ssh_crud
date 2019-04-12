package com.ahead.service;

import java.util.List;

import com.ahead.domain.BaseDict;

public interface BaseDictService {

	List<BaseDict> getListByTypeCode(String dict_type_code);

}
