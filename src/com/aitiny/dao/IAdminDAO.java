package com.aitiny.dao;

import com.aitiny.dao.vo.Admin;

public interface IAdminDAO extends IDAO<Integer, Admin> {
	public boolean doUpdatePassword(Integer id,String password)throws Exception;
	public boolean doUpdatePhoto(Integer id,String photoPath)throws Exception;
	public Admin findByEmail(String email)throws Exception;
	/**
	 * 通过昵称查找
	 * @param nickName
	 * @return
	 * @throws Exception
	 */
	public Admin findByNickName(String nickName)throws Exception;
	/**
	 * 通过真是姓名查找
	 * @param nickName
	 * @return
	 * @throws Exception
	 */
	public Admin findByName(String nickName)throws Exception;
}
