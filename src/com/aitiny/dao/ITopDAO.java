package com.aitiny.dao;

import java.util.Date;
import java.util.List;

import com.aitiny.dao.vo.Top;

public interface ITopDAO extends IDAO<Integer, Top> {
	public boolean doRemvoe(Date time)throws Exception;
 	public boolean doRemove(int pid)throws Exception;
 	/**
 	 * 查找时间在传入时间之后的所有Top.
 	 * @param time
 	 * @return
 	 * @throws Exception
 	 */
 	public List<Top> findByTime(Date time)throws Exception;
}
