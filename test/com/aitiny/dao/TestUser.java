package com.aitiny.dao;

import java.util.Date;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.aitiny.dao.impl.UserDAOImpl;
import com.aitiny.dao.vo.User;
import com.aitiny.util.BeanToObjectUtil;

public class TestUser {

			ApplicationContext ctx=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
			JdbcTemplate jdbcTemplate=	(JdbcTemplate) ctx.getBean("jdbcTemplate");
			UserDAOImpl userDAO=(UserDAOImpl) ctx.getBean("userDAO");

	@Test
	public void test() {
		try {
				
				System.out.println( jdbcTemplate);
				BasicDataSource bd= (BasicDataSource) ctx.getBean("dataSource");
				System.out.println(bd.getPassword()+","+bd.getUsername());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testfindById() {

		 System.out.println(userDAO);
		 try {
			System.out.println(userDAO.findById(6));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testfindAll() {
		 try {
			System.out.println(userDAO.findAll().size()+", "+userDAO.findAll().get(0));

			for(Object o : userDAO.findAll()){
				System.out.println(o.toString());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testdoCreate(){
		try {
			User vo=new User( "test7@qq.com", "123", "kit");
			System.out.println(userDAO.doCreate(vo));
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testdoUpdate(){
		try {
			User vo=new User( "doupdate@qq.com", "123", "kit", "c:/1jpg", 0, 0, 0, 0, 0, 0, 0, 0,new Date());
			vo.setId(6);
			System.out.println(userDAO.doUpdate(vo));
			//System.out.println(userDAO.doUpdate(4, "email", "dog@qq.com"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testdoRemove(){
		try {
			System.out.println(userDAO.doRemove(4));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testfindAllfenye(){
		try {
			List<User> users=userDAO.findAll("id", "", 1,5);
			for(User user : users){
				System.out.println(user);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void findAllOrder(){
		try {
			List<User> users=userDAO.findAll("id", "", 1,5,"id",1);
			for(User user : users){
				System.out.println(user);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testgetAllCount(){
		try {
			System.out.println(userDAO.getAllCount("nickName", "t"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testt(){
		try {
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
