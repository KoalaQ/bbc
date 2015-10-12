package com.aitiny.dao.impl;

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
		// TODO Auto-generated method stub
		throw new MethodNotRealize("方法未实现");
	}

	

	
}
