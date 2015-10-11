package com.aitiny.dao;

import com.aitiny.dao.vo.User;

public interface IUserDAO extends IDAO<Integer, User> {
	public User findByEmail(String email)throws Exception;
	public User findByNickName(String nickName)throws Exception;
}
