package com.aitiny.service;

import java.util.Date;

/**
 * 暂时想到刷新和添加。10个top
 * @author koala
 *
 */
public interface ITopService {
	/**
	 * 会先确定手动添加置顶的是否过期。然后对应插入。会选择文章状态为正常的，可以通过status进行拉黑
	 * @return
	 * @throws Exception
	 */
		public boolean refreshTop(int topCounts)throws Exception;
		/**
		 * 浏览的top
		 * @return
		 * @throws Exception
		 */
		public boolean viewTop(int topCounts)throws Exception;
			/**
			 * 收藏的top
			 * @return
			 * @throws Exception
			 */
		public boolean likeTop(int topCounts)throws Exception;
		
		/**
		 * 手动插入置顶的文章，
		 * @param topNum 要插入的位置
		 * @param pid  要插入文章的
		 * @param time 过期时间
		 * @return
		 * @throws Exception
		 */
		public boolean addToTop(int topNum,int pid,Date time)throws Exception;
		/**
		 * 将文章从置顶中移除
		 * @param pid
		 * @return
		 * @throws Exception
		 */
		public boolean removeTop(int pid)throws Exception;
	
}
