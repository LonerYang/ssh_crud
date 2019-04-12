package com.ahead.dao;

import com.ahead.domain.User;

public interface UserDao extends BaseDao<User>{

	User getByUserCode(String user_code);
}
