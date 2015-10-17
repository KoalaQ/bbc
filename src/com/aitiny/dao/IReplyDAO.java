package com.aitiny.dao;

import com.aitiny.dao.vo.Reply;

public interface IReplyDAO extends IDAO<Integer, Reply> {
	public boolean doRemoveByPid(Integer pid)throws Exception;
}
