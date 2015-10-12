package com.aitiny.dao.impl;

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
	public boolean doUpdate(Integer id, String[] Columns, Object[] value)
			throws Exception {
		// TODO Auto-generated method stub
		throw new MethodNotRealize("该方法未实现");
	}


}
