package com.aitiny.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.aitiny.dao.IDAO;

public abstract class AService<V> {
	protected IDAO mapDAO;
	protected IDAO findDAO;
	/**
	 * 设置mapDAO
	 */
	@PostConstruct
	protected abstract void initDAO();
	protected V findById(int id) throws Exception{
		return (V) findDAO.findById(id);
	}
	/**
	 * 模糊分页查询，emp表中的全部数据，查询时会调用IDAO的两个操作方法<br>
	 *   <li> findAll(column,keyWord,currentPage,lineSize)查询符合条件的分页数据，返回list
	 *   <li> getAllCount(column,keyWord)统计返回的数据数量
	 * @param column  模糊查询的字段
	 * @param keyWord 模糊查询的关键字空字符串查询全部
	 * @param currentPage  当前所在页
	 * @param lineSize		每页的数据长度
	 * @return 该方法会返回两类数据：List<V>集合，Integer统计结果，所以使用Map保存，Map存放规则
	 *         <li> key=all：保存的是findAll()方法返回的List集合
	 *         <li> key=count: 保存的是getAllCount()方法的Integer数据
	 * @throws Exception
	 */
	protected Map<String, Object> list(String column, String keyWord,
			int currentPage, int lineSize) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("all", this.mapDAO.findAll(column, keyWord, currentPage, lineSize));
		map.put("count", this.mapDAO.getAllCount(column, keyWord));
		
		return map;
	}
	/**
	 * 模糊分页查询，emp表中的全部数据，查询时会调用IDAO的两个操作方法<br>
	 *   <li> findAll(column,keyWord,currentPage,lineSize,orderColumn,orderType)查询符合条件的分页数据，返回list
	 *   <li> getAllCount(column,keyWord)统计返回的数据数量
	 * @param column  模糊查询的字段
	 * @param keyWord 模糊查询的关键字空字符串查询全部
	 * @param currentPage  当前所在页
	 * @param lineSize		每页的数据长度
	 * @return 该方法会返回两类数据：List<Emp>集合，Integer统计结果，所以使用Map保存，Map存放规则
	 *         <li> key=all：保存的是findAll()方法返回的List集合
	 *         <li> key=count: 保存的是getAllCount()方法的Integer数据
	 * @throws Exception
	 */
	protected Map<String, Object> listOrder(String column, String keyWord,
			int currentPage, int lineSize,String orderColumn,
			Integer orderType ) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("all", this.mapDAO.findAll(column, keyWord, currentPage, lineSize,orderColumn,orderType));
		map.put("count", this.mapDAO.getAllCount(column, keyWord));
		
		return map;
	}

}
