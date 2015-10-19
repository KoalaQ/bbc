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
	
		
		
		
}
