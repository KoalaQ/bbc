package com.aitiny.dao;

import com.aitiny.dao.vo.Fans;

public interface IFansDAO extends IDAO<Integer, Fans> {
	public boolean doRemove(Integer uid,Integer fuid)throws Exception;
}
