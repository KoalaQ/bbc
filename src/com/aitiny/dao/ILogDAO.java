package com.aitiny.dao;

import java.util.Date;

import com.aitiny.dao.vo.Log;

public interface ILogDAO extends IDAO<Integer, Log> {
		public boolean doRemove(Date time)throws Exception; 
}
