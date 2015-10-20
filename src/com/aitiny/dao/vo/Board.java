package com.aitiny.dao.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Board implements Serializable {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private Integer id;
		private String name;
		private String description;
		private Integer parentId;
		private Integer aid;
		private String boardImg;
		private Integer postsCount;
		private Integer lastPosts;
		private Integer todayPosts;
		private List<Board> boards = new ArrayList<Board>();
		public Board(String name, String description, Integer parentId,
				Integer aid) {
			super();
			this.name = name;
			this.description = description;
			this.parentId = parentId;
			this.aid = aid;
		}
		public Board() {
			super();
		}
		@Override
		public String toString() {
			return "Board [id=" + id + ", name=" + name + ", description="
					+ description + ", parentId=" + parentId + ", aid=" + aid
					+ ", boardImg=" + boardImg + ", postsCount=" + postsCount
					+ ", lastPosts=" + lastPosts + ", todayPosts=" + todayPosts
					+ "]";
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public Integer getParentId() {
			return parentId;
		}
		public void setParentId(Integer parentId) {
			this.parentId = parentId;
		}
		public Integer getAid() {
			return aid;
		}
		public void setAid(Integer aid) {
			this.aid = aid;
		}
		public String getBoardImg() {
			return boardImg;
		}
		public void setBoardImg(String boardImg) {
			this.boardImg = boardImg;
		}
		public Integer getPostsCount() {
			return postsCount;
		}
		public void setPostsCount(Integer postsCount) {
			this.postsCount = postsCount;
		}
		public Integer getTodayPosts() {
			return todayPosts;
		}
		public void setTodayPosts(Integer todayPosts) {
			this.todayPosts = todayPosts;
		}
		public Integer getLastPosts() {
			return lastPosts;
		}
		public void setLastPosts(Integer lastPosts) {
			this.lastPosts = lastPosts;
		}
		public List<Board> getBoards() {
			return boards;
		}
		public void setBoards(List<Board> boards) {
			this.boards = boards;
		}
	
		
		
}
