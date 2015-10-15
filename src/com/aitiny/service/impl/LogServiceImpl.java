package com.aitiny.service.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.aitiny.dao.ILogDAO;
import com.aitiny.dao.vo.Log;
import com.aitiny.service.AService;
import com.aitiny.service.ILogService;
@Service("logService")
public class LogServiceImpl extends AService<Log> implements ILogService {
	@Autowired
	@Qualifier("logDAO")
	private ILogDAO logDAO;
	@Override
	protected void initDAO() {
		// TODO Auto-generated method stub
		this.findDAO=logDAO;
		this.mapDAO=logDAO;
	}
	@Override
	public boolean insert(Log log) throws Exception {
		// TODO Auto-generated method stub
		log.setTime(new Date());
		return logDAO.doCreate(log);
	}

	@Override
	public boolean remove(Date time) throws Exception {
		// TODO Auto-generated method stub
		return this.logDAO.doRemove(time);
	}

	@Override
	public Log find(int id) throws Exception {
		// TODO Auto-generated method stub
		return this.findById(id);
	}

	@Override
	public Map<String ,Object> listAll(String column, String keyWord,
			Integer currentPage, Integer lineSize) throws Exception {
		// TODO Auto-generated method stub
		return this.list(column, keyWord, currentPage, lineSize);
	}

	@Override
	public Map<String ,Object> listAllOrder(String column, String keyWord,
			Integer currentPage, Integer lineSize, String orderColumn,
			Integer orderType) throws Exception {
		// TODO Auto-generated method stub
		return  this.listOrder(column, keyWord, currentPage, lineSize, orderColumn, orderType);
	}




}
