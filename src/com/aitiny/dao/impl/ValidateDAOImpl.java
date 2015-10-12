package com.aitiny.dao.impl;

import org.springframework.stereotype.Repository;

import com.aitiny.dao.ADAO;
import com.aitiny.dao.IValidateDAO;
import com.aitiny.dao.vo.Validate;
import com.aitiny.exception.MethodNotRealize;
@Repository("validateDAO")
public class ValidateDAOImpl extends ADAO<Integer, Validate> implements IValidateDAO {
	@Override
	protected void initADAO() {
		// TODO Auto-generated method stub
		this.cls=Validate.class;
		this.keyName="id";
		this.table="validate";
	}
	@Override
	public boolean doCreate(Validate vo) throws Exception {
		// TODO Auto-generated method stub
		String sql="INSERT INTO validate(uuid,type,message,uid,aid,time) VALUES(?,?,?,?,?,?)";
		Object[] params=new Object[]{vo.getUuid(),vo.getType(),vo.getMessage(),vo.getUid(),vo.getAid(),vo.getTime()};
		if(this.jdbcTemplate.update(sql, params)>0){
			return true;
		}
		
		return false;
	}


	@Override
	public boolean doUpdate(Validate vo) throws Exception {
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
