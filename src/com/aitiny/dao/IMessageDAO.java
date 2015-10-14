package com.aitiny.dao;

import java.util.Date;
import java.util.List;

import com.aitiny.dao.vo.Message;

public interface IMessageDAO extends IDAO<Integer, Message> {
/**
 *  查询和好友的聊天记录
 * @param uid 用户id
 * @param fuid 好友id
 * @param currentPage
 * @param lineSize
 * @return
 * @throws Exception
 */
	public List<Message> findAll(Integer uid, Integer fuid,Integer currentPage,Integer lineSize)throws Exception;

	/**
	 *  统计查询和好友的聊天记录数目
	 * @param uid 用户id
	 * @param fuid 好友id
	 * @return 
	 * @throws Exception
	 */
	public Integer getAllCount(Integer uid, Integer fuid)throws Exception;
	/**
	 * 移除聊天记录
	 * @param time 在此之前的消息都会被移除
	 * @return
	 * @throws Exception
	 */
	public boolean doRemove(Date time)throws Exception;
	
	/**
	 *  查询用户的未读消息
	 * @param uid 用户id
	 * @param fuid 好友id
	 * @param currentPage
	 * @param lineSize
	 * @return
	 * @throws Exception
	 */
		public List<Message> findAll(Integer uid,Integer currentPage,Integer lineSize)throws Exception;
		/**
		 *   统计用户的未读消息数目
		 * @param uid 用户id
		 * @param fuid 好友id
		 * @return 
		 * @throws Exception
		 */
		public Integer getAllCount(Integer uid)throws Exception;
}
