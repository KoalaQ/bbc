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

import com.aitiny.dao.vo.Board;
import com.aitiny.dao.vo.Post;
import com.aitiny.dao.vo.Reply;
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
		if(!order.equals("id") && !order.equals("publishTime") && !order.equals("viewcount") && !order.equals("likes")){
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
			Board board=boardService.loadBoard(post.getBid());
			
			
			
			mav.addObject("post", post);
			mav.addObject("boardName", board.getName());
			mav.addObject("boardid", board.getId());
			mav.addObject("user",userService.findUser(post.getUid()));
			mav.addObject("replys", postService.findReply(id));
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
			@RequestParam("tag")String tag,
			@RequestParam("vali")String vali){
		ModelAndView mav=new ModelAndView();
		if(request.getSession().getAttribute("user")==null){
			mav.setViewName("postPre.do");
			return mav;
		}
		try {
			User user=(User) request.getSession().getAttribute("user");
			Post post=new Post(Integer.parseInt(bid), user.getId(), null, name,content, new Date(), null, summary, tag, user.getNickName());
			//System.out.println(user+","+post);
			//验证码错误
			if(!vali.equalsIgnoreCase((String) request.getSession().getAttribute("vali"))){
				mav.setViewName("postPre.do?bid="+bid);
				mav.addObject("post", post);
				System.out.println(post);
				return mav;
			}
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
			User user=(User) request.getSession().getAttribute("user");
			Post post=postService.getBlog(Integer.parseInt(id));
			
			if(post!=null)
			{
				//判断是否是当前用户的
				if(user.getId()!=post.getUid()){
					mav.setViewName("postPre.do");
					return mav;
				}
				if(request.getAttribute("post")==null){
					mav.addObject("post", post);
				}
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
			@RequestParam("pid")String pid,
			@RequestParam("vali")String vali){
		ModelAndView mav=new ModelAndView();
		if(request.getSession().getAttribute("user")==null){
			mav.setViewName("postPre.do");
			return mav;
		}
		try {
			User user=(User) request.getSession().getAttribute("user");
			Post post=new Post(Integer.parseInt(bid), user.getId(), null, name, content, new Date(), null, summary, tag, user.getNickName());
			post.setId(Integer.parseInt(pid));
			//验证码错误
			if(!vali.equalsIgnoreCase((String) request.getSession().getAttribute("vali"))){
				mav.setViewName("updatePre.do?id="+post.getId());
				mav.addObject("post", post);
				return mav;
			}
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
	/**
	 * 用户MyPosts.jsp。获得用户所有文章，按照发帖时间排序。先不分页
	 * @return
	 */
	@RequestMapping("mypost.do")
	public ModelAndView myPost(HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		mav.setViewName("/post/myPosts.jsp");
		//获得url上的数据
		int page=1;
		int lineSize=10;
		int type=1;//1为降序，0为升序。默认降序
		String order="publishTime";//排序的列，默认时间
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
		//判断用户是否在线
		if(request.getSession().getAttribute("user")==null){
		
			return mav;
		}
		//调用Service层读取数据
		try {
			User user=(User) request.getSession().getAttribute("user");
			Map<String,Object> map;
				map=postService.listPost("uid", user.getId().toString(), page, lineSize, order, type, EnumConstant.Post_Status_normal);

			mav.addObject("posts", map.get("all"));
			mav.addObject("total", map.get("count"));
			//System.out.println( map.get("count"));
			mav.addObject("lineSize", lineSize);			
			//System.out.println(map.get("all"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return mav;
		
	}
	/**
	 * 删除帖子，。先确认帖子是否存在，而且帖子是否是该用户的。删除结果直接返回该界面接收
	 * @param request
	 * @return
	 */
	@RequestMapping("delete.do")
	public ModelAndView deletePost(HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		mav.setViewName("mypost.do");
		String id=request.getParameter("id");
		if(id!=null && !id.equals(""))
		{
			try {
				User user=(User) request.getSession().getAttribute("user");
				Post post=postService.getBlog(Integer.parseInt(id));
				if(post!=null)
				{	//判断是否是当前用户的
					if(user.getId()!=post.getUid()){
						mav.addObject("info", 0);
						mav.setViewName("mypost.do");
						return mav;
					}	
				}
				
				if(postService.recycleBlog(Integer.parseInt(id))){
					mav.addObject("info", 1);
					mav.addObject("id", id);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				mav.addObject("info", 0);
			}
		}else{
			mav.addObject("info", 0);
		}
		return mav;	
	}
	@RequestMapping("postReply.do")
	public ModelAndView postReply(HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		mav.setViewName("redirect:/postDetail.do");
		String replyContent,vali;
		int pid = 0;
		//判断是否登陆
		if( request.getSession().getAttribute("user")==null){
				mav.addObject("error", "1");//用户未登录
				return mav;
		}
		try {
			pid = Integer.parseInt(request.getParameter("pid"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			mav.setViewName("error.jsp");//出现错误
			return mav;
		}
		//判断文章是否存在
		try {
			if(postService.getBlog(pid)==null){
				mav.addObject("error", "3");//文章不存在码错误
				return mav;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mav.setViewName("error.jsp");//出现错误
			return mav;
		}
		
		mav.addObject("pid", pid);
		replyContent=request.getParameter("replyContent");
		vali = request.getParameter("vali");
	
		//判断验证码
		if(!vali.equalsIgnoreCase((String) request.getSession().getAttribute("vali"))){
			mav.addObject("error", "2");//验证码错误
			mav.addObject("replyContent", replyContent);
			return mav;
		}else {
			User user=(User) request.getSession().getAttribute("user");
			Reply reply=new Reply(pid,user.getId(), 1, 0, replyContent, new Date(), 0);
			System.out.println(reply);
			try {
				if(postService.addReply(reply)){
					
					mav.addObject("error", "5");//评论成功
				}
			} catch (Exception e) {
				e.printStackTrace();
				mav.setViewName("error.jsp");//出现错误
				return mav;
			}
		}
		
		return mav;
		
	}
}
