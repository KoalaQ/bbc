package com.aitiny.service;



import java.util.List;
import java.util.Map;

import com.aitiny.dao.vo.Post;
import com.aitiny.dao.vo.Reply;

public interface IPostService {
	/**
	 * 提交文章，会更新board的中对应的提交量。在每天12点会定时重置
	 * @param post
	 * @return
	 * @throws Exception
	 */
		public boolean postBlog(Post post)throws Exception;
		/**
		 * 
		 * @param post(id,name,content,publishTime,files,summary,tag)
		 * @return
		 * @throws Exception
		 */
		public boolean updateBlog(Post post)throws Exception;
		public int postCount()throws Exception;
		/**
		 * 修改文章
		 * @param post (name,content,publishTime,files,summary,tag)
		 * @return
		 * @throws Exception
		 */
		public boolean modifyBlog(Post post)throws Exception;
		/**
		 * 文章放入回收站，文章评论不会被删除。前期分析失误。经验不足。这地方修改需要代码量有些多。下一次在完善
		 * @param id
		 * @return
		 * @throws Exception
		 */
		public boolean recycleBlog(int id)throws Exception;
		/**
		 * 彻底删除文章，级联删除评论
		 * @param id
		 * @return
		 * @throws Exception
		 */
		public boolean deleteBlog(int id)throws Exception;
		

		/**
		 * 查找博客，同时会为浏览量和用户浏览量增加
		 * @param id
		 * @return
		 * @throws Exception
		 */
		public Post getBlog(int id)throws Exception;
		/**
		 * 
		 * @param column
		 * @param keyWord 精确等于查询
		 * @param currentPage
		 * @param lineSize
		 * @param orderColumn
		 * @param orderType
		 * @return该方法会返回两类数据：List<Post>集合，Integer统计结果，所以使用Map保存，Map存放规则
	 *         <li> key=all：保存的是findAll()方法返回的List集合
	 *         <li> key=count: 保存的是getAllCount()方法的Integer数据
		 * @throws Exception
		 */
		public Map<String, Object> listPost(String column, String keyWord,int currentPage,int lineSize,
				String orderColumn,int orderType,int status)throws Exception;
		/**
		 * 
		 * @param column
		 * @param keyWord 模糊查询
		 * @param currentPage
		 * @param lineSize
		 * @param orderColumn
		 * @param orderType
		 * @return该方法会返回两类数据：List<Post>集合，Integer统计结果，所以使用Map保存，Map存放规则
	 *         <li> key=all：保存的是findAll()方法返回的List集合
	 *         <li> key=count: 保存的是getAllCount()方法的Integer数据
		 * @throws Exception
		 */
		public Map<String, Object> listPostLike(String column, String keyWord,int currentPage,int lineSize,
				String orderColumn,int orderType,int status)throws Exception;
		/**
		 * 添加评论
		 * @param reply(pid,uid,type,parentid,content,time,rootid)
		 * @return
		 * @throws Exception
		 */
		public boolean addReply(Reply reply)throws Exception;
		//暂时未写
		/**
		 * 获取评论的思路，
		 * 1：打开文章显示第一根评论、或者只显示根评论数目
		 * 2：当用户点击评论数目时候使用ajax加载评论。根评论和评论的回复是否分页。。。？
		 * 3：留到实现的时候在写
		 */
		
		public List<Reply> findReply(int pid)throws Exception;
		//public Map<String, Object> search(String keyWord,int currentPage,int lineSize)throws Exception;
}
