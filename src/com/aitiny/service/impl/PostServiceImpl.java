package com.aitiny.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.aitiny.dao.IBoardDAO;
import com.aitiny.dao.IPostDAO;
import com.aitiny.dao.IReplyDAO;
import com.aitiny.dao.IUserDAO;
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
	@Autowired
	@Qualifier("boardDAO")
	private IBoardDAO boardDAO;
	@Autowired
	@Qualifier("userDAO")
	private IUserDAO userDAO;
	@Override
	protected void initDAO() {
		// TODO Auto-generated method stub
		this.mapDAO=postDAO;
	}
	@Override
	public boolean postBlog(Post post) throws Exception {
		// TODO Auto-generated method stub
		if(this.postDAO.doCreate(post)){
			boardDAO.doUpdate(post.getBid(), new String[]{"todayPosts"},new Object[]{boardDAO.findById(post.getBid()).getTodayPosts()+1} );
			return true;
		}
		return false;
	}

	@Override
	public boolean updateBlog(Post post) throws Exception {
		// TODO Auto-generated method stub
		return postDAO.doUpdate(post);
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
		Post post=this.postDAO.findById(id);
		if(post!=null){
			postDAO.doUpdate(id, new String[]{"viewcount"} ,new Object[]{post.getViewcount()+1});
		}
		return post;
	}

	@Override
	public Map<String, Object> listPost(String column, String keyWord,
			int currentPage, int lineSize, String orderColumn, int orderType,int status)
			throws Exception {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("all", this.postDAO.findAllAvailable(column, keyWord, currentPage, lineSize, orderColumn, orderType, status));
		map.put("count", this.postDAO.getAllCountAvailable(column, keyWord, status));
		
		return map;
	}
	@Override
	public Map<String, Object> listPostLike(String column, String keyWord,
			int currentPage, int lineSize, String orderColumn, int orderType,int status)
			throws Exception {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("all", this.postDAO.findAllAvailableLike(column, keyWord, currentPage, lineSize, orderColumn, orderType, status));
		map.put("count", this.postDAO.getAllCountAvailableLike(column, keyWord, status));
		
		return map;
	}

	@Override
	public boolean addReply(Reply reply) throws Exception {
		// TODO Auto-generated method stub
		return this.replyDAO.doCreate(reply);
	}
	@Override
	public int postCount() throws Exception {
		// TODO Auto-generated method stub
		return this.postDAO.getAllCount("id", "");
	}
	@Override
	public List<Reply> findReply(int pid) throws Exception {
		// TODO Auto-generated method stub
		List<Reply> replys=replyDAO.findAllByPid(pid);
		for(Reply reply : replys){
			reply.setUser(userDAO.findById(reply.getUid()));
		}
		return replys;
	}




}
