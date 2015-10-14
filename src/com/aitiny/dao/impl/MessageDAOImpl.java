package com.aitiny.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.aitiny.dao.ADAO;
import com.aitiny.dao.IMessageDAO;
import com.aitiny.dao.vo.Message;
import com.aitiny.exception.MethodNotRealize;
@Repository("messageDAO")
public class MessageDAOImpl extends ADAO<Integer, Message> implements IMessageDAO {
	@Override
	protected void initADAO() {
		// TODO Auto-generated method stub
		this.cls=Message.class;
		this.keyName="id";
		this.table="message";
	}
	@Override
	public boolean doCreate(Message vo) throws Exception {
		String sql="INSERT INTO message(fromUser,toUser,content,time) VALUES(?,?,?,?)";
		Object[] params=new Object[]{vo.getFromUser(),vo.getToUser(),vo.getContent(),vo.getTime()};
		if(this.jdbcTemplate.update(sql, params)>0){
			return true;
		}
		return false;
	}
	@Override
	public boolean doUpdate(Message vo) throws Exception {
		// TODO Auto-generated method stub
		throw new MethodNotRealize("该方法未实现");
	}

	@Override
	public List<Message> findAll(Integer uid, Integer fuid,
			Integer currentPage, Integer lineSize) throws Exception {
		String sql="SELECT * FROM "+table+" "
				+ " WHERE  fromUser =? AND toUser=?  "
				+ "  LIMIT ?,?";
		RowMapper<Message> rowMapper=new BeanPropertyRowMapper<>(cls);
		Object[] params=new Object[]{uid,fuid,(currentPage-1)*lineSize,lineSize};
	//	System.out.println(params[0]+","+params[0]+","+params[0]);
		 List<Message> messages=null;
		try {
			messages=jdbcTemplate.query(sql, rowMapper,params);
		} catch (EmptyResultDataAccessException e) {
			return new ArrayList<Message>();
		}
		return messages;
	}
	@Override
	public Integer getAllCount(Integer uid, Integer fuid) throws Exception {
		// TODO Auto-generated method stub
		String sql="SELECT COUNT(*) FROM "+ table
				+ " WHERE  fromUser =? AND toUser=?   ";
		Object[] params=new Object[]{uid,fuid};
	//	System.out.println(params[0]+","+params[0]+","+params[0]);
		Integer count=jdbcTemplate.queryForObject(sql, params, Integer.class);
		return count;
	}
	@Override
	public List<Message> findAll(Integer uid, Integer currentPage,
			Integer lineSize) throws Exception {
		String sql="SELECT * FROM "+table+" "
				+ " WHERE  toUser =?  "
				+ "  LIMIT ?,?";
		RowMapper<Message> rowMapper=new BeanPropertyRowMapper<>(cls);
		Object[] params=new Object[]{uid,(currentPage-1)*lineSize,lineSize};
	//	System.out.println(params[0]+","+params[0]+","+params[0]);
		 List<Message> messages=null;
		try {
			messages=jdbcTemplate.query(sql, rowMapper,params);
		} catch (EmptyResultDataAccessException e) {
			return new ArrayList<Message>();
		}
		return messages;
	}
	@Override
	public Integer getAllCount(Integer uid) throws Exception {
		String sql="SELECT COUNT(*) FROM "+ table
				+ " WHERE  toUser =?   ";
		Object[] params=new Object[]{uid};
	//	System.out.println(params[0]+","+params[0]+","+params[0]);
		Integer count=jdbcTemplate.queryForObject(sql, params, Integer.class);
		return count;
	}
	@Override
	public boolean doRemove(Date time) throws Exception {
		String sql="DELETE  FROM "+table+"  WHERE time<?";
		if(jdbcTemplate.update(sql,time)>0){
			return true;
			}
		return false;
	}


}
