package com.aitiny.beans;

import java.io.Serializable;
import java.util.List;

import com.aitiny.dao.vo.Board;
import com.aitiny.dao.vo.Post;
import com.aitiny.dao.vo.User;

public class IndexBean implements Serializable {
	private List<Board> rootBoard;
	private int todayNum;
	private int yestNum;
	private int highestNum;
	private List<Post> hotPosts;
	private User user;
	private int total;
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<Board> getRootBoard() {
		return rootBoard;
	}
	public void setRootBoard(List<Board> rootBoard) {
		this.rootBoard = rootBoard;
	}
	public int getTodayNum() {
		return todayNum;
	}
	public void setTodayNum(int todayNum) {
		this.todayNum = todayNum;
	}
	public int getYestNum() {
		return yestNum;
	}
	public void setYestNum(int yestNum) {
		this.yestNum = yestNum;
	}
	public int getHighestNum() {
		return highestNum;
	}
	public void setHighestNum(int highestNum) {
		this.highestNum = highestNum;
	}
	public List<Post> getHotPosts() {
		return hotPosts;
	}
	public void setHotPosts(List<Post> hotPosts) {
		this.hotPosts = hotPosts;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
