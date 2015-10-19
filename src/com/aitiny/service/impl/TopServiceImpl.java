package com.aitiny.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.aitiny.dao.IPostDAO;
import com.aitiny.dao.ITopDAO;
import com.aitiny.dao.vo.Post;
import com.aitiny.dao.vo.Top;
import com.aitiny.service.ITopService;
import com.aitiny.util.EnumConstant;
/**
 * 简单的实现头条刷新。用点击量和收藏量来衡量，各区一半。
 * @author koala
 *
 */
@Service("topService")
public class TopServiceImpl implements ITopService {
	@Autowired
	@Qualifier("topDAO")
	private ITopDAO topDAO;
	@Autowired
	@Qualifier("postDAO")
	private IPostDAO postDAO;
	@Override
	public boolean refreshTop(int topCounts) throws Exception {
		// TODO Auto-generated method stub
		List<Top> oldTops=topDAO.findByTime(new Date());
		int needUp=topCounts-oldTops.size();//先获得所需的新数目
		//在最后添加一个不会被匹配的项，防止List越界，index++在最后一个匹配成功后会自增到越界
		oldTops.add(new Top(100, 100, null));
		//System.out.println("需要："+needUp);
		
		List<Top> newTops=this.getNewTops(needUp);
		//System.out.println("new Top:"+newTops);
		int index=0;
		int newIndex=0;
		if(oldTops.size()>0){
				for (int i = 0; i <topCounts; i++) {
					
						if(oldTops.get(index).getId()!=i+1 ){
							this.addToTop(i+1, newTops.get(newIndex++).getPid(), new Date());
						}else{
							index++;
						}
				}
		}else{
			for (int i = 0; i <topCounts; i++) {
					this.addToTop(i+1, newTops.get(i).getPid(), new Date());
			}
		}
		return false;
	}

	@Override
	public boolean addToTop(int topNum, int pid, Date time) throws Exception {
		// TODO Auto-generated method stub
		Top top=new Top(topNum, pid, time);
		/**
		 * 如果已在top表中，则先删除原先位置。插入为主控制,。但插入后在有效期内会被自动排序改变顺序时间（因为点击量足够期上头条啦，可以过期数）但不可删除。
		 */
		if(this.topDAO.afindByColumns(new String[]{"pid"}, new Object[]{pid,0,10}, "id", EnumConstant.Order_type_DESC).size()!=0){
			this.topDAO.doRemove(pid);
		}
		if(this.topDAO.findById(topNum)==null){
			return this.topDAO.doCreate(top);
		}else{
			return this.topDAO.doUpdate(top);
		}
	}

	@Override
	public boolean removeTop(int pid) throws Exception {
		// TODO Auto-generated method stub
		return this.topDAO.doRemove(pid);
	}
	private List<Top> getNewTops(int counts) throws Exception{
		List<Top> tops=new ArrayList<Top>();
		List<Post> viewMore =postDAO.afindByColumns(new String[]{}, new Object[]{0,10}, "viewCount",EnumConstant .Order_type_DESC);
		List<Post> likeMore =postDAO.afindByColumns(new String[]{}, new Object[]{0,10}, "likes", EnumConstant.Order_type_DESC);
		//System.out.println("viewMore"+viewMore);
		//System.out.println("likeMore"+likeMore);
		int flag=0;
		int viewindex=0;
		int likeindex=0;
		for (int i = 0; i < counts*2; i++) {
			if(flag==0){
				tops.add(new Top(0, viewMore.get(viewindex++).getId(), new Date()));
				flag=1;
			}else{
				tops.add(new Top(0, likeMore.get(likeindex++).getId(), new Date()));
				flag=0;
			}
			
		}		
		return tops;
		
	}

	@Override
	public  List<Post> viewTop(int topCounts) throws Exception {
		List<Post> viewMore =postDAO.afindByColumns(new String[]{}, new Object[]{0,topCounts}, "viewCount",EnumConstant .Order_type_DESC);	
		return viewMore;
	}

	@Override
	public  List<Post> likeTop(int topCounts) throws Exception {
		List<Post> viewMore =postDAO.afindByColumns(new String[]{}, new Object[]{0,topCounts}, "likes",EnumConstant .Order_type_DESC);	
		return viewMore;
	}
	
}
