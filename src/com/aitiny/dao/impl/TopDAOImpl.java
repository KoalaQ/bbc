package com.aitiny.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
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
		String sql="INSERT INTO top(id,pid,time) VALUES(?,?,?)";
		if(this.jdbcTemplate.update(sql,vo.getId(), vo.getPid(),vo.getTime())>0){
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
	@Override
	public boolean doUpdate(Integer id, String[] Columns, Object[] values)
			throws Exception {
		// TODO Auto-generated method stub
		return this.adoUpdate(id, Columns, values);
	}
	@Override
	public boolean doRemvoe(Date time) throws Exception {
		// TODO Auto-generated method stub
		return this.adoRemoveByTime(time);
	}
	@Override
	public boolean doRemove(int pid) throws Exception {
		// TODO Auto-generated method stub
		return this.adoRemoveByColumns(new String[]{"pid"}, new Object[]{pid});
	}
	@Override
	public boolean doRemove(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return this.adoRemoveByKey(id);
	}
	@Override
	public Top findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return this.afindByKey(id);
	}
	@Override
	public List<Top> findByTime(Date time) throws Exception {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM top WHERE time > ? ";
		RowMapper<Top> rowMapper=new BeanPropertyRowMapper<>(Top.class);
		List<Top> tops=null;
		try {
			tops=jdbcTemplate.query(sql, rowMapper,time);
		//	System.out.println(jdbcTemplate.query(sql, rowMapper,values));
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			return new ArrayList<Top>();
		}
		//System.out.println(v.get(0));
		return tops;
	}
	@Override
	public List<Top> findAll() throws Exception {
		// TODO Auto-generated method stub
		return this.afindAll();
	}
	@Override
	public List<Top> findAll(String column, String keyWord,
			Integer currentPage, Integer lineSize) throws Exception {
		// TODO Auto-generated method stub
		return this.afindPaging(column, keyWord, currentPage, lineSize);
	}
	@Override
	public List<Top> findAll(String column, String keyWord,
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
