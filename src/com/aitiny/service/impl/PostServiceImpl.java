package com.aitiny.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.aitiny.dao.IPostDAO;
import com.aitiny.dao.IReplyDAO;
import com.aitiny.dao.vo.Post;
import com.aitiny.dao.vo.Reply;
import com.aitiny.service.AService;
import com.aitiny.service.IPostService;
import com.aitiny.util.EnumConstant;
@Service("postService")
public class PostServiceImpl extends AService<Post> implements IPostService {
	@Autowired
	@Qualifier("postDAO")
	private IPostDAO postDAO;
	@Autowired
	@Qualifier("replyDAO")
	private IReplyDAO replyDAO;
	@Override
	protected void initDAO() {
		// TODO Auto-generated method stub
		this.mapDAO=postDAO;
	}
	@Override
	public boolean postBlog(Post post) throws Exception {
		// TODO Auto-generated method stub
		return this.postDAO.doCreate(post);
	}

	@Override
	public boolean modifyBlog(Post post) throws Exception {
		// TODO Auto-generated method stub
		return this.postDAO.doUpdate(post);
	}

	@Override
	public boolean recycleBlog(int id) throws Exception {
		// TODO Auto-generated method stub
		return this.postDAO.doUpdate(id, new String[]{"status"}, new Object[]{EnumConstant.Post_Status_recycle});
	}

	@Override
	public boolean deleteBlog(int id) throws Exception {
		// TODO Auto-generated method stub
		if(this.postDAO.doRemove(id)){
			this.replyDAO.doRemoveByPid(id);
			return true;
		}
		return false;
	}
	@Override
	public Post getBlog(int id) throws Exception {
		// TODO Auto-generated method stub
		return this.postDAO.findById(id);
	}

	@Override
	public Map<String, Object> listPost(String column, String keyWord,
			int currentPage, int lineSize, String orderColumn, int orderType)
			throws Exception {
		// TODO Auto-generated method stub
		return this.listOrder(column, keyWord, currentPage, lineSize, orderColumn, orderType);
	}

	@Override
	public boolean addReply(Reply reply) throws Exception {
		// TODO Auto-generated method stub
		return this.replyDAO.doCreate(reply);
	}




}
