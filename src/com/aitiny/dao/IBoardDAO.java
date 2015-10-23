package com.aitiny.dao;

import java.util.List;

import com.aitiny.dao.vo.Board;

public interface IBoardDAO extends IDAO<Integer, Board> {
 	/**
 	 * 获得提交书数目
 	 * @param type 昨天0，今天 1
 	 * @param board 对呀board id ，如果为0表示全部的
 	 * @return
 	 * @throws Exception
 	 */
 	public int getPostsCount(int type,int board)throws Exception;
 	public List<Board> findAllChilds() throws Exception ;
}
