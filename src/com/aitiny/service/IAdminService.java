package com.aitiny.service;

import java.util.Map;

import com.aitiny.dao.vo.Admin;
import com.aitiny.dao.vo.Board;
/**
 * admin的service层，异常还是全部抛出，应抛出不能处理的异常
 * 仅支持最高权限管理员创建从属管理员，提升管理员的level，修改信息等
 * 不提供低级管理员个人修改个人信息，暂时
 * @author koala
 *
 */
public interface IAdminService {
		/**
		 * 高级管理员方法，添加管理员。不允许添加高级管理员
		 * @param admin
		 * @return
		 * @throws Exception
		 */
		public boolean insert(Admin admin,Admin insertAdmin)throws Exception;
		/**
		 * 高级管理员方法，修改管理员Level。不允许修改为高级管理员
		 * @param admin
		 * @return
		 * @throws Exception
		 */
		public boolean changeLevel(Admin admin,Admin insertAdmin)throws Exception;
		/**
		 * 高级管理员功能，修改管理愿信息。不允许修改为高级管理员
		 * @param admin
		 * @return
		 * @throws Exception
		 */
		public boolean updateInfo(Admin admin,Admin insertAdmin)throws Exception;
		
		//通用管理员方法
		public boolean login(Admin admin)throws Exception;
		public boolean changePhoto(Admin admin)throws Exception;
		public boolean changePassword(Admin admin,String validateInfo)throws Exception;
		//public boolean ChangeUserPassword() throws Exception;
		public boolean addBoard(Board board)throws Exception;
		public boolean removeBoard(Board board)throws Exception;
		/**
		 * 模糊分页查询，emp表中的全部数据，查询时会调用IEmoDAO的两个操作方法<br>
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
		public Map<String,Object> listBoards(String column,String keyWord,int currentPage,int lineSize)throws Exception;
		
		
		
}
