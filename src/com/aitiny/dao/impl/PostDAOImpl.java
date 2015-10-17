package com.aitiny.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aitiny.dao.ADAO;
import com.aitiny.dao.IPostDAO;
import com.aitiny.dao.vo.Post;
import com.aitiny.exception.MethodNotRealize;
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
		return this.afindAll();
	}
	@Override
	public List<Post> findAll(String column, String keyWord,
			Integer currentPage, Integer lineSize) throws Exception {
		// TODO Auto-generated method stub
		return this.afindPaging(column, keyWord, currentPage, lineSize);
	}
	@Override
	public List<Post> findAll(String column, String keyWord,
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
