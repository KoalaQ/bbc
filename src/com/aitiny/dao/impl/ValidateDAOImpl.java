package com.aitiny.dao.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
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
		String sql="INSERT INTO validate(uuid,type,message,uid,aid,time,valicode) VALUES(?,?,?,?,?,?,?)";
		Object[] params=new Object[]{vo.getUuid(),vo.getType(),vo.getMessage(),vo.getUid(),vo.getAid(),vo.getTime(),vo.getValicode()};
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
	@Override
	public Validate findByUUID(String uuid) throws Exception {
		Validate validate=null;
		String sql="SELECT * FROM validate WHERE uuid=?";
		RowMapper<Validate> rowMapper=new BeanPropertyRowMapper<>(Validate.class);
		try {
			validate=this.jdbcTemplate.queryForObject(sql, rowMapper,uuid);
		} catch (EmptyResultDataAccessException e) {
			// TODO: handle exception
			return null;
		}
		
		return validate;
	}
	@Override
	public Validate findByAidAndType(Integer aid, Integer type)
			throws Exception {
		// TODO Auto-generated method stub
		Validate validate=null;
		String sql="SELECT * FROM validate WHERE aid=?  AND type=? ";
		RowMapper<Validate> rowMapper=new BeanPropertyRowMapper<>(Validate.class);
		try {
			validate=this.jdbcTemplate.queryForObject(sql, rowMapper,aid,type);
		} catch (EmptyResultDataAccessException e) {
			// TODO: handle exception
			return null;
		}
		
		return validate;
	}
	@Override
	public Validate findByUidAndTpye(Integer uid, Integer type)
			throws Exception {
		// TODO Auto-generated method stub
		Validate validate=null;
		String sql="SELECT * FROM validate WHERE uid=? AND type=?";
		RowMapper<Validate> rowMapper=new BeanPropertyRowMapper<>(Validate.class);
		try {
			validate=this.jdbcTemplate.queryForObject(sql, rowMapper,uid,type);
		} catch (EmptyResultDataAccessException e) {
			// TODO: handle exception
			return null;
		}
		
		return validate;
	}


	
}
