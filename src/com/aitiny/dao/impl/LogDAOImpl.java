package com.aitiny.dao.impl;

import org.springframework.stereotype.Repository;

import com.aitiny.dao.ADAO;
import com.aitiny.dao.ILogDAO;
import com.aitiny.dao.vo.Log;
import com.aitiny.exception.MethodNotRealize;
@Repository("logDAO")
public class LogDAOImpl extends ADAO<Integer, Log> implements ILogDAO {

	@Override
	protected void initADAO() {
		// TODO Auto-generated method stub
		this.cls=Log.class;
		this.keyName="id";
		this.table="log";
	}
	@Override
	public boolean doCreate(Log vo) throws Exception {
		// TODO Auto-generated method stub
		String sql="INSERT INTO log(aid,uid,content,time,name) VALUES (?,?,?,?,?)";
		Object[] params=new Object[]{vo.getAid(),vo.getUid(),vo.getcontent(),vo.getTime(),vo.getName()};
		if(this.jdbcTemplate.update(sql, params)>0){
			return true;
		}
		return false;
	}


	@Override
	public boolean doUpdate(Log vo) throws Exception {
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
