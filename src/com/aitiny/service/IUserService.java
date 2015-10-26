package com.aitiny.service;

import java.util.Map;

import com.aitiny.dao.vo.Collections;
import com.aitiny.dao.vo.Fans;
import com.aitiny.dao.vo.User;

public interface IUserService {
	/**
	 * 用户的登陆。如果用户已登陆，会强行登陆，修改uuid,页面获取user信息保存到Cookie，session。而在登陆中的用户则会检查session或cookie中的uuid。
	 * 如果与服务器上的不想同，则反馈登陆异常
	 *  （）不支持多方登陆。
	 * @param user (email,password，uuid)
	 * @param type  
	 * @return 返回的数字会代表不同的用户状态，
	 * @throws Exception
	 */
	public int login(User user)throws Exception;
	public User getUser(String email)throws Exception;
	/**
	 * 用户注销，更改状态。也会有定时任务更改状态。
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean logout(User user)throws Exception;
	
	public boolean hasEmail(String email)throws Exception;
	public boolean hasNickName(String nickName)throws Exception;
	/**
	 * 
	 * @param user (email,password,nickName)
	 * @return
	 * @throws Exception
	 */
	public boolean register(User user)throws Exception;
	/**
	 * 
	 * @param user  (id,password)
	 * @param valiCode
	 * @return
	 * @throws Exception
	 */
	public boolean changePassword(User user,String valiCode)throws Exception;
	/**
	 * 
	 * @param user  (id,password)
	 * @param valiCode
	 * @return
	 * @throws Exception
	 */
	public boolean changePassword(User user)throws Exception;
	/**
	 * 更新用户信息
	 * @param user 更新的属性有，nickName,photoPath,theme
	 * @return
	 * @throws Exception
	 */
	public boolean updateInfo(User user)throws Exception;
	/**
	 * 增加积分。同时会根据积分更新相对于等级
	 * @param vantages 增加的积分量，可以写到资源文件中。现在写到静态变量中
	 * @return 
	 * @throws Exception
	 */
	public boolean addVantages(int id,int vantages )throws Exception;
	/**
	 * 更改邮箱验证状态为已验证
	 * @return
	 * @throws Exception
	 */
	 public boolean checkEmail(User user)throws Exception;
	 /**
	  * 查找用户
	  * @param id
	  * @return
	  * @throws Exception
	  */
	 public User findUser(int id)throws Exception;
		/**
		 * 
		 * @param column
		 * @param keyWord
		 * @param currentPage
		 * @param lineSize
		 * @param orderColumn
		 * @param orderType
		 * @return该方法会返回两类数据：List<User>集合，Integer统计结果，所以使用Map保存，Map存放规则
	 *         <li> key=all：保存的是findAll()方法返回的List集合
	 *         <li> key=count: 保存的是getAllCount()方法的Integer数据
		 * @throws Exception
		 */
		public Map<String, Object> listUser(String column, String keyWord,int currentPage,int lineSize,
				String orderColumn,int orderType)throws Exception;
		/**
		 * 添加粉丝。自己是fuid
		 * @param fans
		 * @return
		 * @throws Exception
		 */
		public boolean addFans(Fans fans)throws Exception;
		/**
		 * 删除粉丝。删除自己关注的人，
		 * @param fans
		 * @return
		 * @throws Exception
		 */
		public boolean deleteFans(Fans fans)throws Exception;
		/**
		 * 
		 * @param column
		 * @param keyWord
		 * @param currentPage
		 * @param lineSize
		 * @param orderColumn
		 * @param orderType
		 * @return该方法会返回两类数据：List<User>集合，Integer统计结果，所以使用Map保存，Map存放规则
	 *         <li> key=all：保存的是findAll()方法返回的List集合
	 *         <li> key=count: 保存的是getAllCount()方法的Integer数据
		 * @throws Exception
		 */
		public Map<String, Object> listFans(String column, String keyWord,int currentPage,int lineSize)throws Exception;
		public boolean  addCollect(Collections coll)throws Exception;
		public boolean deleteCollect(int id)throws Exception;
		/**
		 * 
		 * @param column
		 * @param keyWord
		 * @param currentPage
		 * @param lineSize
		 * @param orderColumn
		 * @param orderType
		 * @return该方法会返回两类数据：List<User>集合，Integer统计结果，所以使用Map保存，Map存放规则
	 *         <li> key=all：保存的是findAll()方法返回的List集合
	 *         <li> key=count: 保存的是getAllCount()方法的Integer数据
		 * @throws Exception
		 */
		public Map<String, Object> listCollections(String column, String keyWord,int currentPage,int lineSize)throws Exception;
		
		
}
