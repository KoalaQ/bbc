package com.aitiny.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.aitiny.dao.IDAO;

public abstract class AService {
	protected IDAO mapDAO;
	/**
	 * 设置mapDAO
	 */
	@PostConstruct
	protected abstract void initDAO();
	public Map<String, Object> list(String column, String keyWord,
			int currentPage, int lineSize) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("all", this.mapDAO.findAll(column, keyWord, currentPage, lineSize));
		map.put("count", this.mapDAO.getAllCount(column, keyWord));
		
		return map;
	}

}
