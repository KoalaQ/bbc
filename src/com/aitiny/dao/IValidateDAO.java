package com.aitiny.dao;

import java.util.Date;

import com.aitiny.dao.vo.Validate;

public interface IValidateDAO extends IDAO<Integer, Validate> {
	public boolean doRemove(Date time)throws Exception;
	public Validate findByUUID(String uuid)throws Exception;
	public Validate findByAidAndType(Integer aid,Integer type)throws Exception;
	public Validate findByUidAndTpye(Integer uid,Integer type)throws Exception;
}
