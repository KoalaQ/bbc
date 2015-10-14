package com.aitiny.dao.impl;

import java.util.List;

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
	@Override
	public boolean doUpdate(Integer id, String[] Columns, Object[] values)
			throws Exception {
		// TODO Auto-generated method stub
		return this.adoUpdate(id, Columns, values);
	}
	@Override
	public boolean doRemove(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return this.adoRemoveByKey(id);
	}
	@Override
	public Board findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return this.afindByKey(id);
	}
	@Override
	public List<Board> findAll() throws Exception {
		// TODO Auto-generated method stub
		return this.afindAll();
	}
	@Override
	public List<Board> findAll(String column, String keyWord,
			Integer currentPage, Integer lineSize) throws Exception {
		// TODO Auto-generated method stub
		return this.afindPaging(column, keyWord, currentPage, lineSize);
	}
	@Override
	public List<Board> findAll(String column, String keyWord,
			Integer currentPage, Integer lineSize, String orderColumn,
			Integer orderType) throws Exception {
		// TODO Auto-generated method stub
		return this.afindPaging(column, keyWord, currentPage, lineSize, orderColumn, orderType);
	}
	@Override
	public Integer getAllCount(String column, String keyWord) throws Exception {
		// TODO Auto-generated method stub
		return this.agetPagingCount(column, keyWord);
	}

}
