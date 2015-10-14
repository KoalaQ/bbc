package com.aitiny.dao.impl;

import java.util.Date;
import java.util.List;

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
	public boolean doUpdate(Integer id, String[] Columns, Object[] values)
			throws Exception {
		// TODO Auto-generated method stub
		return this.adoUpdate(id, Columns, values);
	}
	@Override
	public boolean doRemove(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return this.adoRemoveByKey(id);
	}
	@Override
	public boolean doRemove(Date time) throws Exception {
		
		return this.adoRemoveByTime(time);
	}
	@Override
	public Integer getAllCount(Integer uid, Integer fuid) throws Exception {
		// TODO Auto-generated method stub
		return this.afindByColumnsCounts(new String[]{"fromUser", "toUser"}, new Object[]{uid,fuid});
	}
	@Override
	public List<Message> findAll(Integer uid, Integer fuid,
			Integer currentPage, Integer lineSize) throws Exception {
		return this.afindByColumns(new  String[]{"fromUser","toUser"}, new Object[]{uid,fuid,currentPage,lineSize});
	}
	@Override
	public Message findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return this.afindByKey(id);
	}
	@Override
	public Integer getAllCount(Integer uid) throws Exception {
		return this.afindByColumnsCounts(new String[]{"toUser"}, new Object[]{uid});
	}
	@Override
	public List<Message> findAll(Integer uid, Integer currentPage,
			Integer lineSize) throws Exception {

		return this.afindByColumns(new String[]{"toUser"}, new Object[]{uid,currentPage,lineSize});
	}
	@Override
	public List<Message> findAll() throws Exception {
		// TODO Auto-generated method stub
		return this.afindAll();
	}
	@Override
	public List<Message> findAll(String column, String keyWord,
			Integer currentPage, Integer lineSize) throws Exception {
		// TODO Auto-generated method stub
		return this.afindPaging(column, keyWord, currentPage, lineSize);
	}
	@Override
	public List<Message> findAll(String column, String keyWord,
			Integer currentPage, Integer lineSize, String orderColumn,
			Integer orderType) throws Exception {
		// TODO Auto-generated method stub
		return this.afindPaging(column, keyWord, currentPage, lineSize, orderColumn, orderType);
	}
	@Override
	public Integer getAllCount(String column, String keyWord) throws Exception {
		// TODO Auto-generated method stub
		return this.agetPagingCount(column, keyWord);
	}



}
