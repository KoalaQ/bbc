package com.aitiny.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.aitiny.dao.vo.Admin;
import com.aitiny.dao.vo.User;
import com.aitiny.service.IAdminService;
import com.aitiny.service.IUserService;
import com.aitiny.util.EnumConstant;
import com.aitiny.util.StringUtil;

@Controller("/")
public class LoginController {
	@Autowired
	@Qualifier("userService")
	private IUserService userService;
	@Autowired
	@Qualifier("adminService")
	private IAdminService adminService;
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
}
