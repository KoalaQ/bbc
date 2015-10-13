package com.aitiny.dao;

import com.aitiny.dao.vo.Validate;

public interface IValidateDAO extends IDAO<Integer, Validate> {
	public Validate findByUUID(String uuid)throws Exception;
	public Validate findByAidAndType(Integer aid,Integer type)throws Exception;
	public Validate findByUidAndTpye(Integer uid,Integer type)throws Exception;
}
