package com.aitiny.service;

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


}
