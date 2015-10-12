package com.aitiny.dao.impl;

import org.springframework.stereotype.Repository;

import com.aitiny.dao.ADAO;
import com.aitiny.dao.ITopDAO;
import com.aitiny.dao.vo.Top;
@Repository("topDAO")
public class TopDAOImpl extends ADAO<Integer, Top> implements ITopDAO {
	@Override
	protected void initADAO() {
		// TODO Auto-generated method stub
		this.cls=Top.class;
		this.keyName="id";
		this.table="top";
	}
	@Override
	public boolean doCreate(Top vo) throws Exception {
		String sql="INSERT INTO top(pid,time) VALUES(?,?)";
		if(this.jdbcTemplate.update(sql, vo.getPid(),vo.getTime())>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean doUpdate(Top vo) throws Exception {
		// TODO Auto-generated method stub
		String sql="UPDATE  top SET pid=?,time=? WHERE id=?";
		if(this.jdbcTemplate.update(sql, vo.getPid(),vo.getTime(),vo.getId())>0){
			return true;
		}
		return false;
	}



	
}
