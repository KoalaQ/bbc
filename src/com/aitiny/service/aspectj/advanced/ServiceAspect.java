package com.aitiny.service.aspectj.advanced;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aitiny.dao.vo.Admin;
import com.aitiny.dao.vo.Board;
import com.aitiny.dao.vo.Log;
import com.aitiny.dao.vo.Post;
import com.aitiny.dao.vo.User;
import com.aitiny.service.ILogService;
import com.aitiny.service.IPostService;
import com.aitiny.service.IUserService;
/**
 * @author koala
 *
 */
@Aspect
public class ServiceAspect {
	@Autowired
	@Qualifier("logService")
	private ILogService logService;
	@Autowired
	@Qualifier("postService")
	private IPostService postService;
	@Autowired
	@Qualifier("userService")
	private IUserService userService;
	@AfterReturning(value="execution(*  com.aitiny.service.impl.AdminServiceImpl.insert(..))",returning="retVal")	
	public void loginsert(JoinPoint jp,boolean retVal){
		if(retVal){
			Admin admin=(Admin) jp.getArgs()[0];
			Admin insertAdmin=(Admin) jp.getArgs()[1];
			Log log=new Log(admin.getId(), null, "添加新管理员："+insertAdmin.getId()+","+insertAdmin.getNickName(), "增加新成员");
			try {
				logService.insert(log);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@AfterReturning(value="execution(*  com.aitiny.service.impl.AdminServiceImpl.changeLevel(..))",returning="retVal")	
	public void changeLevel(JoinPoint jp,boolean retVal){
		if(retVal){
			Admin admin=(Admin) jp.getArgs()[0];
			Admin insertAdmin=(Admin) jp.getArgs()[1];
			Log log=new Log(admin.getId(), null, "改变管理员level："+insertAdmin.getId()+",更改Level为："+insertAdmin.getLevel(), "更改用户Level");
			try {
				logService.insert(log);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@AfterReturning(value="execution(*  com.aitiny.service.impl.AdminServiceImpl.addBoard(..))",returning="retVal")	
	public void addBoard(JoinPoint jp,boolean retVal){
		if(retVal){
			Board board=(Board) jp.getArgs()[0];
			Log log=new Log(board.getAid(), null, "添加board："+board.getName(), "添加board");
			try {
				logService.insert(log);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Before("execution(*  com.aitiny.service.impl.PostServiceImpl.deleteBlog(..))")	
	public void deleteBlog(JoinPoint jp){
	
			int id=(int) jp.getArgs()[0];
			Post post = null;
			try {
				post = postService.getBlog(id);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Log log=new Log(null, post.getUid(), "删除文章："+post.getName(), "删除文章");
			try {
				logService.insert(log);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	@AfterReturning(value="execution(*  com.aitiny.service.impl.UserServiceImpl.register(..))",returning="retVal")	
	public void register(JoinPoint jp,boolean retVal){
		if(retVal){
			User user=(User) jp.getArgs()[0];
			User ruser = null;
			try {
				ruser = userService.getUser(user.getEmail());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Log log=new Log(null, ruser.getId(), "用户注册："+user.getNickName(), "用户注册"); 
			try {
				logService.insert(log);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
