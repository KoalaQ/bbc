package com.aitiny.service;

import java.util.Date;
import java.util.Map;

import com.aitiny.dao.vo.Log;

public interface ILogService {
	public boolean insert(Log log)throws Exception;
	/**
	 * 移除过时的log
	 * @param time 设定时间，此前的log清除。
	 * @return
	 * @throws Exception
	 */
	public boolean remove(Date time)throws Exception;
	public Log find(int id)throws Exception;
	/**
	 * 
	 * @param column
	 * @param keyWord
	 * @param currentPage
	 * @param lineSize
	 * @return 该方法会返回两类数据：List<V>集合，Integer统计结果，所以使用Map保存，Map存放规则
	 *         <li> key=all：保存的是findAll()方法返回的List集合
	 *         <li> key=count: 保存的是getAllCount()方法的Integer数据
	 * @throws Exception
	 */
	public Map<String ,Object> listAll(String column, String keyWord,Integer currentPage, Integer lineSize)throws Exception;
	/**
	 * 
	 * @param column
	 * @param keyWord
	 * @param currentPage
	 * @param lineSize
	 * @param orderColumn
	 * @param orderType
	 * @return 该方法会返回两类数据：List<V>集合，Integer统计结果，所以使用Map保存，Map存放规则
	 *         <li> key=all：保存的是findAll()方法返回的List集合
	 *         <li> key=count: 保存的是getAllCount()方法的Integer数据
	 * @throws Exception
	 */
	public Map<String,Object> listAllOrder(String column, String keyWord,Integer currentPage, Integer lineSize, String orderColumn,Integer orderType)throws Exception;
	
}
