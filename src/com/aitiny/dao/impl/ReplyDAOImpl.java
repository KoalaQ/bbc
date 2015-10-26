package com.aitiny.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
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
	public boolean doRemoveByPid(Integer pid) throws Exception {
		// TODO Auto-generated method stub
		return this.adoRemoveByColumns(new String[]{"pid"}, new Object[]{pid});
	}
	@Override
	public Reply findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return this.afindByKey(id);
	}
	@Override
	public List<Reply> findAll() throws Exception {
		// TODO Auto-generated method stub
		return this.afindAll();
	}
	@Override
	public List<Reply> findAll(String column, String keyWord,
			Integer currentPage, Integer lineSize) throws Exception {
		// TODO Auto-generated method stub
		return this.afindPaging(column, keyWord, currentPage, lineSize);
	}
	@Override
	public List<Reply> findAll(String column, String keyWord,
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
	@Override
	public List<Reply> findAllByPid(Integer pid) throws Exception {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM reply WHERE pid="+pid;
		RowMapper<Reply> rowMapper=new BeanPropertyRowMapper<>(Reply.class);
		List<Reply> replys=null;
		try {
			replys=jdbcTemplate.query(sql, rowMapper);
		//	System.out.println(jdbcTemplate.query(sql, rowMapper,values));
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			return new ArrayList<Reply>();
		}
		return replys;
	}



	

}
