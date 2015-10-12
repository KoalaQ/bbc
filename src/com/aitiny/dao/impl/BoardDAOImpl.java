package com.aitiny.dao.impl;

import org.springframework.stereotype.Repository;

import com.aitiny.dao.ADAO;
import com.aitiny.dao.IBoardDAO;
import com.aitiny.dao.vo.Board;
import com.aitiny.exception.MethodNotRealize;
@Repository("boardDAO")
public class BoardDAOImpl extends ADAO<Integer, Board> implements IBoardDAO {

	@Override
	protected void initADAO() {

		this.table="board";
		this.keyName="id";
		this.cls=Board.class;
	}
	@Override
	public boolean doCreate(Board vo) throws Exception {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO board (name,description,parentId,aid) VALUES (?,?,?,?)";
		Object[] params=new Object[]{vo.getName(),vo.getDescription(),vo.getParentId(),vo.getAid()};
		if(this.jdbcTemplate.update(sql,params)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean doUpdate(Board vo) throws Exception {
		// TODO Auto-generated method stub
		throw new MethodNotRealize("方法未实现");
	}

}
