package com.aitiny.service;

import java.util.Date;
import java.util.Map;

import com.aitiny.dao.vo.Message;

/**
 * 消息会在一段时间内自动清除浏览过3小时以上的消息,，status=1
 * @author koala
 *
 */
public interface IMessageService {
	 public boolean send(Message message)throws Exception;
	 public boolean hasRead(int [] id)throws Exception;
	 public boolean delete(Date time)throws Exception;
	 /**
	  * 
	  * @param uid
	  * @param fid
	  * @param currentPage
	  * @param lineSize
	  * @return 该方法会返回两类数据：List<V>集合，Integer统计结果，所以使用Map保存，Map存放规则
	 *         <li> key=all：保存的是findAll()方法返回的List集合
	 *         <li> key=count: 保存的是getAllCount()方法的Integer数据
	  * @throws Exception
	  */
	 public Map<String ,Object> getMessages(int uid,int fid,int currentPage,int lineSize)throws Exception;
	 /**
	  * 
	  * @param uid
	  * @param currentPage
	  * @param lineSize
	  * @return 该方法会返回两类数据：List<V>集合，Integer统计结果，所以使用Map保存，Map存放规则
	 *         <li> key=all：保存的是findAll()方法返回的List集合
	 *         <li> key=count: 保存的是getAllCount()方法的Integer数据
	  * @throws Exception
	  */
	 public Map<String ,Object> getNewMessages(int uid,int currentPage,int lineSize)throws Exception;
	 
	 
}
