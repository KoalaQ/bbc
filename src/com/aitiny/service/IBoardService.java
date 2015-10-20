package com.aitiny.service;

import java.util.List;

import com.aitiny.dao.vo.Board;

public interface IBoardService {
	public boolean addBoard(Board board)throws Exception;
	public boolean removeBoard(Board board)throws Exception;
	
	 //加载Board
    public Board loadBoard(int id)throws Exception;
       
    //加载子板块
    public List<Board> loadChildBoards(int parentId)throws Exception;
    
    //加载板块
    public List<Board> loadAllBoards()throws Exception;
    
    //加载根栏目.0为根节点
    public List<Board> loadRootBoards()throws Exception;
    
    //保存更新版块Board（name,description,boardImg）
    public boolean  updateBoard(Board board)throws Exception;
    
	
	public int getTodayPostsCount()throws Exception;
	public int getLastdayPostsCount()throws Exception;
	public int getBoardTodayCount(int id)throws Exception;
}
