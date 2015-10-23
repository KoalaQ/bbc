package com.aitiny.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.aitiny.dao.IBoardDAO;
import com.aitiny.dao.vo.Board;
import com.aitiny.exception.MethodNotRealize;
import com.aitiny.service.IBoardService;
import com.aitiny.util.EnumConstant;
@Service("boardService")
public class BoardServiceImpl implements IBoardService {
	@Autowired
	@Qualifier("boardDAO")
	private IBoardDAO boardDAO;
	@Override
	public boolean addBoard(Board board) throws Exception {
		// TODO Auto-generated method stub
		return this.boardDAO.doCreate(board);
	}

	@Override
	public boolean removeBoard(Board board) throws Exception {
		// TODO Auto-generated method stub
		throw new MethodNotRealize("该方法未实现");
		//return this.boardDAO.doRemove(board.getAid());
	}

	@Override
	public Board loadBoard(int id) throws Exception{
		// TODO Auto-generated method stub
		return this.boardDAO.findById(id);
	}

	@Override
	public List<Board> loadChildBoards(int parentId)throws Exception {
		// TODO Auto-generated method stub
		return this.boardDAO.afindByColumns(new String[]{"parentID"}, new Object[]{parentId,0,100}, "id", EnumConstant.Order_type_ASEC);
	}
	@Override
	public List<Board> loadChildBoards()throws Exception {
		// TODO Auto-generated method stub
		return this.boardDAO.findAllChilds();
	}
	@Override
	public List<Board> loadAllBoards()throws Exception {
		// TODO Auto-generated method stub
		return boardDAO.findAll();
	}

	@Override
	public List<Board> loadRootBoards() throws Exception{
		// TODO Auto-generated method stub
		List<Board> rootBoards= boardDAO.afindByColumns(new String[]{"parentId"}, new Object[]{0,0,100}, "id", EnumConstant.Order_type_ASEC);
		for(Board board : rootBoards){
			board.setBoards(boardDAO.afindByColumns(new String[]{"parentId"}, new Object[]{board.getId(),0,100}, "id", EnumConstant.Order_type_ASEC));
		}
		return rootBoards;
		
	}

	@Override
	public boolean updateBoard(Board board)throws Exception {
		// TODO Auto-generated method stub
		return boardDAO.doUpdate(board);
	}
	
	@Override
	public int getTodayPostsCount() throws Exception {
		// TODO Auto-generated method stub
		return this.boardDAO.getPostsCount(1, 0);
	}

	@Override
	public int getLastdayPostsCount() throws Exception {
		// TODO Auto-generated method stub
		return this.boardDAO.getPostsCount(0, 0);
	}

	@Override
	public int getBoardTodayCount(int id) throws Exception {
		// TODO Auto-generated method stub
		return this.boardDAO.getPostsCount(0, id);
	}





}
