package com.aitiny.dao.impl;

import org.springframework.stereotype.Repository;

import com.aitiny.dao.ADAO;
import com.aitiny.dao.IFansDAO;
import com.aitiny.dao.vo.Fans;
import com.aitiny.exception.MethodNotRealize;
@Repository("fansDAO")
public class FansDAOImpl extends ADAO<Integer, Fans> implements IFansDAO {

	@Override
	protected void initADAO() {

		this.cls=Fans.class;
		this.keyName="id";
		this.table="fans";
	}
	@Override
	public boolean doCreate(Fans vo) throws Exception {
		// TODO Auto-generated method stub
		String sql="INSERT INTO fans(uid,fuid,type,time) VALUES(?,?,?,?)";
		Object[] params=new Object[]{vo.getUid(),vo.getFuid(),vo.getType(),vo.getTime()};
		if(this.jdbcTemplate.update(sql, params)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean doUpdate(Fans vo) throws Exception {
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
