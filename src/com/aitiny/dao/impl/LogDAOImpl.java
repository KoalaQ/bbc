package com.aitiny.dao.impl;

import java.util.Date;
import java.util.List;

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
	@Override
	public boolean doRemove(Date time) throws Exception {
		// TODO Auto-generated method stub
		
		return this.adoRemoveByTime(time);
	}
	@Override
	public boolean doRemove(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return this.adoRemoveByKey(id);
	}
	@Override
	public Log findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return this.afindByKey(id);
	}
	@Override
	public List<Log> findAll() throws Exception {
		// TODO Auto-generated method stub
		return this.afindAll();
	}
	@Override
	public List<Log> findAll(String column, String keyWord,
			Integer currentPage, Integer lineSize) throws Exception {
		// TODO Auto-generated method stub
		return this.afindPaging(column, keyWord, currentPage, lineSize);
	}
	@Override
	public List<Log> findAll(String column, String keyWord,
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
