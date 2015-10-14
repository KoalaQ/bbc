package com.aitiny.dao.impl;

import org.springframework.stereotype.Repository;

import com.aitiny.dao.ADAO;
import com.aitiny.dao.IReplyDAO;
import com.aitiny.dao.vo.Reply;
import com.aitiny.exception.MethodNotRealize;
@Repository("replyDAO")
public class ReplyDAOImpl extends ADAO<Integer, Reply> implements IReplyDAO {
	@Override
	protected void initADAO() {
		// TODO Auto-generated method stub
		this.cls=Reply.class;
		this.keyName="id";
		this.table="reply";
	}
	@Override
	public boolean doCreate(Reply vo) throws Exception {
		// TODO Auto-generated method stub
		String sql="INSERT INTO reply (pid,uid,type,parentid,content,time,rootid) VALUES (?,?,?,?,?,?,?)";
		Object[] params=new Object[]{vo.getPid(),vo.getUid(),vo.getType(),vo.getParentid(),vo.getContent(),vo.getTime(),vo.getRootid()};
		if(this.jdbcTemplate.update(sql, params)>0){
			return true;
		}
		return false;
	}


	@Override
	public boolean doUpdate(Reply vo) throws Exception {
		// TODO Auto-generated method stub
		throw new MethodNotRealize("该方法未实现");
	}



	

}
