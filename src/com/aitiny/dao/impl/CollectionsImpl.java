package com.aitiny.dao.impl;

import org.springframework.stereotype.Repository;

import com.aitiny.dao.ADAO;
import com.aitiny.dao.ICollectionsDAO;
import com.aitiny.dao.vo.Collections;
import com.aitiny.exception.MethodNotRealize;
@Repository("collectionsDAO")
public class CollectionsImpl  extends ADAO<Integer,Collections> implements ICollectionsDAO {

	@Override
	protected void initADAO() {

		this.cls=Collections.class;
		this.keyName="id";
		this.table="collections";
	}
	@Override
	public boolean doCreate(Collections vo) throws Exception {
		// TODO Auto-generated method stub
		String sql="INSERT INTO  collections(uid,pid,time,title,summary) VALUES(?,?,?,?,?) ";
		Object[] params=new Object[]{vo.getUid(),vo.getPid(),vo.getTime(),vo.getTitle(),vo.getSummary()};
		if(this.jdbcTemplate.update(sql, params)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean doUpdate(Collections vo) throws Exception {
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
