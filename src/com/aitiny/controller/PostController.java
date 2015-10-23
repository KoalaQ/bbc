package com.aitiny.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aitiny.dao.vo.Post;
import com.aitiny.dao.vo.User;
import com.aitiny.service.IBoardService;
import com.aitiny.service.IPostService;
import com.aitiny.service.IUserService;
import com.aitiny.util.EnumConstant;

@Controller
@RequestMapping("/")
public class PostController {
	@Autowired
	@Qualifier("postService")
	private IPostService postService;
	@Autowired
	@Qualifier("boardService")
	private IBoardService boardService;
	@Autowired
	@Qualifier("userService")
	private IUserService userService;

	/**
	 * 处理Mainbbs.jsp界面的处理
	 * @return
	 */
	@RequestMapping("blog.do")
	public ModelAndView mainbbs(HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		mav.setViewName("/Mainbbs.jsp");
		//获得url上的数据
		int page=1;
		int lineSize=10;
		int type=1;//1为降序，0为升序。默认降序
		String order="publishTime";//排序的列，默认时间
		String bid="";//对应查询的bid，
		try {
			page=Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			lineSize=Integer.parseInt(request.getParameter("lineSize"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			type=Integer.parseInt(request.getParameter("type"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//判断升降序是否正确
		if(type!=0 && type !=1){
			type=1;
		}
		if(request.getParameter("order")!=null && !request.getParameter("order").equals("")){
			order=request.getParameter("order");
		}
		//处理order的信息
		if(!order.equals("id") && !order.equals("publishTime") && !order.equals("viewcounts") && !order.equals("likes")){
			order="publishTime";
		}
		if(request.getParameter("bid")!=null && !request.getParameter("bid").equals("")){
			bid=request.getParameter("bid");
		}
	
		//调用Service层读取数据
		try {
			Map<String,Object> map;
			if(bid.equals("")){
				map=postService.listPostLike("bid", bid, page, lineSize, order, type, EnumConstant.Post_Status_normal);
				
			}else{
				map=postService.listPost("bid", bid, page, lineSize, order, type, EnumConstant.Post_Status_normal);
				
			}
			mav.addObject("posts", map.get("all"));
			mav.addObject("total", map.get("count"));
			//System.out.println( map.get("count"));
			mav.addObject("lineSize", lineSize);			
			//System.out.println(map.get("all"));
			mav.addObject("boards", boardService.loadChildBoards());
			mav.addObject("bid", bid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return mav;
		
	}
	
	@RequestMapping("postDetail.do")
	public ModelAndView viewDetail(HttpServletRequest rquest,@RequestParam("pid")int id){
		ModelAndView mav=new ModelAndView();
		mav.setViewName("post/ViewPostDetail.jsp");
		try {
			Post post=postService.getBlog(id);
			String boardName=boardService.loadBoard(post.getBid()).getName();
			mav.addObject("post", post);
			mav.addObject("boardName", boardName);
			mav.addObject("user",userService.findUser(post.getUid()));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			mav.setViewName("error.jsp");
			e.printStackTrace();
		}
		
		
		return mav;
		
	}
	/**
	 * 可以接受bid参数.在转发出去.主要传送boards信息
	 * @param request
	 * @return
	 */
	@RequestMapping("postPre.do")
	public ModelAndView postPre(HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		mav.setViewName("post/AddPost.jsp");
		String bid="";
		try {
			bid=request.getParameter("bid");
			mav.addObject("boards", boardService.loadChildBoards());
			mav.addObject("postUrl", "post.do");
			if(bid!=null && !bid.equals("")){
				mav.addObject("bid", bid);	
			}
		} catch (Exception e) {
			// TODO: handle exception
			mav.setViewName("error.jsp");
		}
		
		return mav;
		
	}
	/**
	 * 提交文章
	 * @param request
	 * @return
	 */
	@RequestMapping("post.do")
	public ModelAndView postDo(HttpServletRequest request,
			@RequestParam("name")String name,
			@RequestParam("bid")String bid,
			@RequestParam("content")String content,
			@RequestParam("summary")String summary,
			@RequestParam("tag")String tag){
		ModelAndView mav=new ModelAndView();
		if(request.getSession().getAttribute("user")==null){
			mav.setViewName("postPre.do");
			return mav;
		}
		try {
			User user=(User) request.getSession().getAttribute("user");
			Post post=new Post(Integer.parseInt(bid), user.getId(), null, name, content, new Date(), null, summary, tag, user.getNickName());
			//System.out.println(user+","+post);
			if(postService.postBlog(post))
			{
				mav.setViewName("blog.do");//这个要转到用户的个人文章主页。现在转到主文章上
			}else{
				mav.setViewName("postPre.do?bid="+bid);
				mav.addObject("post", post);
			}
		} catch (Exception e) {
			// TODO: handle exception
			mav.setViewName("error.jsp");
			e.printStackTrace();
		}
		
		return mav;	
	}
	@RequestMapping("updatePre.do")
	public ModelAndView updatePre(HttpServletRequest request,
			@RequestParam("id") String id){
		ModelAndView mav=new ModelAndView();
		mav.setViewName("post/AddPost.jsp");
		if(request.getSession().getAttribute("user")==null){
			mav.setViewName("postPre.do");
			return mav;
		}
		try {
			
			Post post=postService.getBlog(Integer.parseInt(id));
			
			if(post!=null)
			{
				mav.addObject("post", post);
				mav.addObject("boards", boardService.loadChildBoards());
				mav.addObject("bid", post.getBid());
				mav.addObject("postUrl", "update.do");
			}else{
				mav.setViewName("postPre.do");//这个要转到用户的个人文章主页。现在转到主文章上
			}
		} catch (Exception e) {
			// TODO: handle exception
			mav.setViewName("error.jsp");
			e.printStackTrace();
		}
		return mav;
		
	}
	/**
	 * 修改文章
	 * @param request
	 * @return
	 */
	@RequestMapping("update.do")
	public ModelAndView updateDo(HttpServletRequest request,
			@RequestParam("name")String name,
			@RequestParam("bid")String bid,
			@RequestParam("content")String content,
			@RequestParam("summary")String summary,
			@RequestParam("tag")String tag,
			@RequestParam("pid")String pid){
		ModelAndView mav=new ModelAndView();
		if(request.getSession().getAttribute("user")==null){
			mav.setViewName("postPre.do");
			return mav;
		}
		try {
			User user=(User) request.getSession().getAttribute("user");
			Post post=new Post(Integer.parseInt(bid), user.getId(), null, name, content, new Date(), null, summary, tag, user.getNickName());
			post.setId(Integer.parseInt(pid));
			//System.out.println(user+","+post);
			if(postService.updateBlog(post))
			{
				mav.setViewName("blog.do");//这个要转到用户的个人文章主页。现在转到主文章上
			}else{
				mav.setViewName("postPre.do?bid="+bid);
				mav.addObject("post", post);
			}
		} catch (Exception e) {
			// TODO: handle exception
			mav.setViewName("error.jsp");
			e.printStackTrace();
		}
		
		return mav;	
	}
}
