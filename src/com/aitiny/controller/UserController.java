package com.aitiny.controller;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.aitiny.dao.vo.User;
import com.aitiny.dao.vo.Validate;
import com.aitiny.service.IPostService;
import com.aitiny.service.IUserService;
import com.aitiny.service.IValidateService;
import com.aitiny.util.EnumConstant;

@Controller
@RequestMapping("/")
public class UserController {
	@Autowired
	@Qualifier("userService")
	private IUserService userService;
	@Autowired
	@Qualifier("postService")
	private IPostService postService;
	@Autowired
	@Qualifier("validateService")
	private IValidateService validateService;
	
	@RequestMapping("userinfo.do")
	public ModelAndView userInfo(HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		mav.setViewName("user/StuPersonalInfo.jsp");
		int id=0;
		try{
			id=Integer.parseInt(request.getParameter("uid"));
		}catch(Exception e){
			
		}
		int page=1;
		int lineSize=10;
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
				User user =userService.findUser(id);
				if(user!=null){
					mav.addObject("user", user);
					Map<String,Object> map=postService.listPost("uid", user.getId().toString(), page, lineSize, "publishTime", EnumConstant.Order_type_DESC, EnumConstant.Post_Status_normal);
					mav.addObject("posts", map.get("all"));
					mav.addObject("count", map.get("count"));
					mav.addObject("lineSize", lineSize);
				}else{
					mav.addObject("error", "0");//0表示没有该用户。
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				mav.addObject("error", "0");//0表示没有该用户。
				e.printStackTrace();
			}
		return mav;
	}

	/**
	 * 更改用户的信息，昵称，头像（为空不更改），主题（暂未支持）
	 * @param request
	 * @return
	 */
	@RequestMapping("userUpdate.do")
	public ModelAndView updateInfo(HttpServletRequest request,
			@RequestParam("photo")MultipartFile file ){
		ModelAndView mav=new ModelAndView();
		mav.setViewName("user/updateInfo.jsp");
		User user=(User) request.getSession().getAttribute("user");
		if(user==null){
			mav.addObject("info", "0");//用户未登陆
		}
		String newNickName=user.getNickName();
		int theme=user.getTheme();
		String photoPath=user.getPhotoPath();
		
		
		System.out.println(request.getParameter("nickNam"));
		if(request.getParameter("nickName")!=null){
			newNickName=request.getParameter("nickName");
		}
		if(request.getParameter("theme")!=null){
			theme=Integer.parseInt(request.getParameter("theme"));
		}
		//如果有新头像
		if(!file.isEmpty()){
		
			try {
				file.transferTo(new File(request.getServletContext().getRealPath("/upload")+File.separator+file.getOriginalFilename()));
			
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			photoPath=file.getOriginalFilename();
		}
		
		user.setNickName(newNickName);
		user.setPhotoPath(photoPath);
		user.setTheme(theme);
		try {
			if(userService.updateInfo(user)){
				mav.addObject("info", "1");//更新成功
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			mav.addObject("info", "2");//更新失败
			e.printStackTrace();
		}
		return mav;
		
	}
	/**
	 * 用户发送验证邮件
	 * @param response
	 * @param email
	 */
	@RequestMapping("changePasswordSendEmail.do")
	public ModelAndView changePasswordSendEmail(HttpServletRequest request,
			@RequestParam("email")String  email,
		@RequestParam("vali")String  vali){
		ModelAndView mav=new ModelAndView();
		mav.setViewName("redirect:/user/changePasswordPre.jsp?error=1");
		if(!vali.equalsIgnoreCase((String) request.getSession().getAttribute("vali"))){
			mav.setViewName("redirect:/user/changePasswordPre.jsp?error=3");	//验证码错误直接返回
			return mav;
		}
		try {
			User user=userService.getUser(email);
			if(user!=null){
		
					if(validateService.userSendValiPasswordUrl(user))
					{
						mav.setViewName("redirect:/user/changePasswordPre.jsp?error=2");
					}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mav;
	}
	
	/**
	 * 
	 * @param request
	 * @param email
	 * @param nickName
	 * @param password
	 * @param password2
	 * @return
	 */
	@RequestMapping("user/changePasswordPre.do")
	public ModelAndView userChangePasswordPre(HttpServletRequest request,
			@RequestParam("uuid")String uuid){
		ModelAndView mav=new ModelAndView();
	
		mav.setViewName("/user/changePassword.jsp");
		return mav;
	}
	/**
	 * 
	 * @param request
	 * @param email
	 * @param nickName
	 * @param password
	 * @param password2
	 * @return
	 */
	@RequestMapping("changePassword.do")
	public ModelAndView userChangePasswordDo(HttpServletRequest request,
			@RequestParam("uuid")String uuid,
			@RequestParam("vali")String vali,
			@RequestParam("password")String password){
		ModelAndView mav=new ModelAndView();
		mav.setViewName("/user/changePassword.jsp?error=0");//验证码错误
		if(!vali.equalsIgnoreCase((String) request.getSession().getAttribute("vali"))){
			return mav;
		}
		try {
			Validate validate=validateService.getByUuid(uuid);
			if(vali!=null){
				User user=new User();
				user.setId(validate.getUid());
				user.setPassword(password);
				if(userService.changePassword(user)){
					mav.setViewName("redirect:/login.jsp?error=9");
					
					
				}else{
					mav.setViewName("/user/changePassword.jsp?error=1");//更改失败
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			mav.setViewName("/user/changePassword.jsp?error=1");//更改失败
			//e.printStackTrace();
		}
		return mav;
	}

}
