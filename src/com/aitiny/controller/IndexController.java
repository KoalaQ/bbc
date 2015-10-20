package com.aitiny.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.aitiny.beans.IndexBean;
import com.aitiny.service.IBoardService;
import com.aitiny.service.IPostService;
import com.aitiny.service.ITopService;

@Controller
public class IndexController {
	@Autowired
	@Qualifier("postService")
	private IPostService postService;
	@Autowired
	@Qualifier("boardService")
	private IBoardService boardService;
	@Autowired
	@Qualifier("topService")
	private ITopService topService;
	@RequestMapping("/index.do")
	public ModelAndView index(HttpServletRequest request){
		ServletContext application=request.getServletContext();
		ModelAndView mav=new ModelAndView();
		mav.setViewName("/index.jsp");
		IndexBean index=new IndexBean();
		try {
			index.setRootBoard(boardService.loadRootBoards());
			index.setHotPosts(topService.getTop());
			index.setTodayNum(boardService.getTodayPostsCount());
			index.setYestNum(boardService.getLastdayPostsCount());
			index.setTotal(postService.postCount());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			mav.setViewName("/error.jsp");
			e.printStackTrace();
		}
		//添加到application属性
		if(application.getAttribute("yestNum")==null){
			application.setAttribute("yestNum",index.getYestNum());
		}
		if(application.getAttribute("todayNum")==null){
			application.setAttribute("todayNum",index.getTodayNum());
		}
		if(application.getAttribute("total")==null){
			application.setAttribute("total",index.getTotal());
		}
		mav.addObject("index",index);
		return mav;
		
	}
}
