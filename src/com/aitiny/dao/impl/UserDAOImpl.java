package com.aitiny.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aitiny.dao.IUserDAO;
import com.aitiny.dao.vo.User;
@Repository("userDAO")
public class UserDAOImpl implements IUserDAO {
	@Autowired(required=false)
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	@Override
	public boolean doCreate(User vo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdate(User vo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdate(Integer id, String Column, Object value)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdate(Integer id, String Column, String value)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdate(Integer id, String Column, Integer value)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdate(Integer id, String Column, Date value)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemove(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll(String column, String keyWord,
			Integer currentPage, Integer lineSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllCount(String column, String keyWord)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
