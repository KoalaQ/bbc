package com.aitiny.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.aitiny.dao.ADAO;
import com.aitiny.dao.IPostDAO;
import com.aitiny.dao.vo.Post;
import com.aitiny.exception.MethodNotRealize;
import com.aitiny.util.EnumConstant;
@Repository("postDAO")
public class PostDAOImpl extends ADAO<Integer, Post> implements IPostDAO {
	@Override
	protected void initADAO() {
		// TODO Auto-generated method stub
		this.cls=Post.class;
		this.keyName="id";
		this.table="post";
	}

	@Override
	public boolean doCreate(Post vo) throws Exception {
		// TODO Auto-generated method stub
		String sql="INSERT INTO post(bid,uid,aid,name,content,publishTime,files,summary,tag) VALUES(?,?,?,?,?,?,?,?,?)";
		Object[] params=new Object[]{vo.getBid(),vo.getUid(),vo.getAid(),vo.getName(),vo.getContent(),vo.getPublishTime(),vo.getFiles(),vo.getSummary(),vo.getTag()};
		if(this.jdbcTemplate.update(sql, params)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean doUpdate(Post vo) throws Exception {
		String sql="UPDATE  post SET name=?,content=?,publishTime=?,files=?,summary=?,tag=?  WHERE id=? ";
		Object[] params=new Object[]{vo.getName(),vo.getContent(),vo.getPublishTime(),vo.getFiles(),vo.getSummary(),vo.getTag(),vo.getId()};
		if(this.jdbcTemplate.update(sql, params)>0){
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
	public boolean doRemove(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return this.adoRemoveByKey(id);
	}
	@Override
	public Post findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return this.afindByKey(id);
	}
	@Override
	public List<Post> findAll() throws Exception {
		// TODO Auto-generated method stub
		throw new MethodNotRealize("方法未实现");
	}
	@Override
	public List<Post> findAll(String column, String keyWord,
			Integer currentPage, Integer lineSize) throws Exception {
		throw new MethodNotRealize("方法未实现");
	}
	@Override
	public List<Post> findAll(String column, String keyWord,
			Integer currentPage, Integer lineSize, String orderColumn,
			Integer orderType) throws Exception {
		throw new MethodNotRealize("方法未实现");
	}
	@Override
	public Integer getAllCount(String column, String keyWord) throws Exception {
		// TODO Auto-generated method stub
		throw new MethodNotRealize("方法未实现");
	}

	@Override
	public List<Post> findAllAvailable(int status) throws Exception {
		// TODO Auto-generated method stub
		String sql="SELECT *FROM "+table +" WHERE status="+status ;
		
		RowMapper<Post> rowMapper=new BeanPropertyRowMapper<>(cls);
		List<Post> posts=null;
		try {
			posts=jdbcTemplate.query(sql, rowMapper);
		} catch (EmptyResultDataAccessException e) {
			return new ArrayList<Post>();
		}
		return posts;
	}

	@Override
	public List<Post> findAllAvailable(String column, String keyWord,
			Integer currentPage, Integer lineSize, int status) throws Exception {
		String sql="SELECT * FROM "+table+" "
				+ " WHERE "+column+" LIKE  ?  AND status="+status
				+ "  LIMIT ?,?";
		RowMapper<Post> rowMapper=new BeanPropertyRowMapper<>(cls);
		Object[] params=new Object[]{"%"+keyWord+"%",(currentPage-1)*lineSize,lineSize};
	//	System.out.println(params[0]+","+params[0]+","+params[0]);
		List<Post> posts=null;
		try {
			posts=jdbcTemplate.query(sql, rowMapper,params);
		} catch (EmptyResultDataAccessException e) {
			return new ArrayList<Post>();
		}
		return posts;
	}

	@Override
	public List<Post> findAllAvailable(String column, String keyWord,
			Integer currentPage, Integer lineSize, String orderColumn,
			Integer orderType, int status) throws Exception {
		String type="";
		if(orderType==EnumConstant.Order_type_DESC){
			type="DESC";
		}
		//此处如果将orderColumn放入可变参数？号中，则升降序失效。应该是预处理机制问题，比如column不能在参数中，不然报错
		String sql="SELECT * FROM "
				+ " (Select * FROM "+table+" WHERE  status="+status+" AND "+table+"."+column+" LIKE  ?  ORDER BY   "+orderColumn+" "+type+  " )"
				+ "  "+keyName+" LIMIT ?,?";
		//System.out.println(sql);
		RowMapper<Post> rowMapper=new BeanPropertyRowMapper<>(cls);
		Object[] params=new Object[]{"%"+keyWord+"%",(currentPage-1)*lineSize,lineSize};
	//	System.out.println(params[0]+","+params[0]+","+params[0]);
		List<Post> posts=null;
		try {
			posts=jdbcTemplate.query(sql, rowMapper,params);
		} catch (EmptyResultDataAccessException e) {
			return new ArrayList<Post>();
		}
		return posts;
	}

	@Override
	public Integer getAllCountAvailable(String column, String keyWord,
			int status) throws Exception {
		String sql="SELECT COUNT(*) FROM "+ table
				+ "  WHERE status="+status+" AND "+column+" LIKE  ?  ";
		Object[] params=new Object[]{"%"+keyWord+"%"};
	//	System.out.println(params[0]+","+params[0]+","+params[0]);
		Integer count=jdbcTemplate.queryForObject(sql, params, Integer.class);
		return count;
	}

	
	
	
	


	

	
}
