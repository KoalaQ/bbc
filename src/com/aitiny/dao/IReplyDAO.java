package com.aitiny.dao;



import java.util.List;

import com.aitiny.dao.vo.Reply;

public interface IReplyDAO extends IDAO<Integer, Reply> {
	public boolean doRemoveByPid(Integer pid)throws Exception;
	public List<Reply> findAllByPid(Integer pid)throws Exception;
	 
}
