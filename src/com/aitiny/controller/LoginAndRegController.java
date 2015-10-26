package com.aitiny.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aitiny.dao.vo.Admin;
import com.aitiny.dao.vo.User;
import com.aitiny.service.IAdminService;
import com.aitiny.service.IUserService;
import com.aitiny.service.IValidateService;
import com.aitiny.util.EnumConstant;
import com.aitiny.util.StringUtil;

@Controller
public class LoginAndRegController {
	
	
	@Autowired
	@Qualifier("userService")
	private IUserService userService;
	@Autowired
	@Qualifier("adminService")
	private IAdminService adminService;
	@Autowired
	@Qualifier("validateService")
	private IValidateService validateService;
	@RequestMapping("userRegister.do")
	/**
	 * 用户注册
	 * @param request
	 * @param email
	 * @param nickName
	 * @param password
	 * @param password2
	 * @return 返回到注册界面，提示消息。如果注册成功，则发送邮件，验证后可以使用
	 */
	public ModelAndView userRegister(HttpServletRequest request,
			@RequestParam("email")String email,
			@RequestParam("nickName")String nickName,
			@RequestParam("password")String password,
			@RequestParam("password2")String password2){
		ModelAndView mav=new ModelAndView();
		int error=0;		
		if(!password.equals(password2)){
			error= 1;
		}else{
			User user=new User();
			user.setEmail(email);
			user.setNickName(nickName.trim());
			user.setPassword(password);
			try {
				if(userService.register(user)){
					error=2;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		mav.setViewName("redirect:/register.jsp?error="+error);
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
	@RequestMapping("/user/check.do")
	public ModelAndView userRegisterEmailCheck(HttpServletRequest request,
			@RequestParam("uuid")String uuid){
		ModelAndView mav=new ModelAndView();
		mav.setViewName("redirect:/valiEmail.jsp?error=0");
		try {
			if(validateService.checkUserByUuid(uuid)){
				mav.setViewName("redirect:/login.jsp?error=1");	
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mav;
	}
	/**
	 * 处理注册前的验证，邮箱验证，昵称验证
	 * @param request
	 * @param type 0表示邮箱，1表示昵称
	 * @param value 数据
	 *  返回到界面的xml有两个节点。flag:错误类型(0:可以使用，1：已被使用，2：错误)；tips：消息
	 */
	@RequestMapping("userRegisterPre.do")
	public void userRegisterPre(HttpServletResponse response,
			@RequestParam("type")int type,
			@RequestParam("value")String value){
		String tips="可以使用";
		String flag="0";
		try {
			if(type==0){//邮箱
			
				if(this.userService.hasEmail(value)){
					flag="1";
					tips="已被使用";
				}
				
			}else if(type==1){//昵称
				if(this.userService.hasNickName(value)){
					flag="1";
					tips="已被使用";
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			flag="2";
			tips="出现错误";
			e.printStackTrace();
		}
		String message="<message>"
				+ "<flag>"
				+ flag
				+ "</flag>"
				+ "<tips>"
				+tips
				+ "</tips>"
				+ "</message>";
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().append(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 用户重新发送验证邮件
	 * @param response
	 * @param email
	 */
	@RequestMapping("resendEmail.do")
	public ModelAndView userRegisterSendEmail(HttpServletRequest request,
			@RequestParam("email")String  email,
		@RequestParam("vali")String  vali){
		ModelAndView mav=new ModelAndView();
		mav.setViewName("redirect:/valiEmail.jsp?error=1");
		if(!vali.equalsIgnoreCase((String) request.getSession().getAttribute("vali"))){
			mav.setViewName("redirect:/valiEmail.jsp?error=3");	//验证码错误直接返回
			return mav;
		}
		try {
			User user=userService.getUser(email);
			if(user!=null){
				if(user.getStatus()==EnumConstant.Usre_status_unverified){
					if(validateService.userSendUrl(user))
					{
						mav.setViewName("redirect:/valiEmail.jsp?error=2");
					}
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mav;
	}
	@RequestMapping("userLogin.do")
	public ModelAndView userLogin(HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		mav.setViewName("redirect:/login.jsp");
		String uuid=null;
		if(request.getSession().getAttribute("uuid")!=null){
			uuid=(String)request.getSession().getAttribute("uuid") ;
		}else{
			uuid=StringUtil.getUUID();
		}
		String email=request.getParameter("email");
		String password =request.getParameter("password");
		String vali=request.getParameter("vali");
		//没有验证码直接返回
		if(vali==null || vali==""){
			mav.addObject("error", 10);
			return mav;
		}
		if( vali.equalsIgnoreCase((String) request.getSession().getAttribute("vali"))){		
		try {
			int type=userService.login(new User(email, password, uuid));
			//System.out.println(type);
			switch(type){
			case EnumConstant.User_Wrong_password :
				mav.addObject("error", EnumConstant.User_Wrong_password);
				break;
			case EnumConstant.User_Status_lock :
				mav.addObject("error", EnumConstant.User_Status_lock);
				break;
			case EnumConstant.User_Status_unknow :
				mav.addObject("error", EnumConstant.User_Status_unknow);
				break;
			case EnumConstant.Usre_status_unverified :
				mav.addObject("error", EnumConstant.Usre_status_unverified);
				break;
			case EnumConstant.User_Status_online :
				User user=userService.getUser(email);
				request.getSession().setAttribute("user", user);
				mav.setViewName("redirect:/");
				break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mav.setViewName("/error.jsp");
			}
		}else{
			mav.addObject("error", 10);
		}
		return mav;
		
	}
	@RequestMapping("adminLogin.do")
	public ModelAndView adminLogin(HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		mav.setViewName("redirect:/login.jsp");
		String email=request.getParameter("email");
		String password =request.getParameter("password");
		String vali=request.getParameter("vali");
		//没有验证码直接返回
		if(vali==null || vali==""){
			mav.addObject("error", 10);
			return mav;
		}
		//用户名和密码未输入
				if(email==null || email=="" || password=="" || password==null ){
					mav.addObject("error", EnumConstant.User_Wrong_password);
					return mav;
				}
		if( vali.equalsIgnoreCase((String) request.getSession().getAttribute("vali"))){		
			try {
				if(adminService.login(new Admin(email, password, "", ""))){
					Admin admin=adminService.findByEmail(email);
					request.getSession().setAttribute("admin", admin);
					mav.setViewName("redirect:/");
				}else{
					mav.addObject("error", EnumConstant.User_Wrong_password);
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				mav.setViewName("/error.jsp");
			}
		}else{
			mav.addObject("error", 10);
		}
		return mav;
	}
	@RequestMapping("logout.do")
	public String logout(HttpServletRequest request){
		request.getSession().invalidate();
		
		return "login.jsp";
		
	}
}
