package com.aitiny.dao;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aitiny.dao.vo.Board;
  
public class IBoardDAOTest {
	ApplicationContext ctx=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	IBoardDAO boardDAO=(IBoardDAO) ctx.getBean("boardDAO");
	@Test
	public void testDoCreate() {
		Board board=new Board();
		board.setName("java 程序设计");
		board.setDescription("优秀的MVC框架");
		board.setParentId(6);
		board.setAid(1);
		try {
			System.out.println(this.boardDAO.doCreate(board));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDoUpdateV() {
		fail("Not yet implemented");
	}

	@Test
	public void testDoUpdateKStringObject() {
		try {
			System.out.println(this.boardDAO.doUpdate(1, new String[]{"description","aid"},new Object[]{ "lalafdfl","123"}));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDoRemove() {
		try {
			System.out.println(this.boardDAO.doRemove(8));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testFindById() {
		try {
			System.out.println(this.boardDAO.findById(3));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testFindAll() {
		try {
			System.out.println(this.boardDAO.findAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testFindAllStringStringIntegerInteger() {
		try {
			System.out.println(this.boardDAO.findAll("id","",1,3));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testFindAllStringStringIntegerIntegerStringInteger() {
		try {
			System.out.println(this.boardDAO.findAll("id","",1,3,"id",1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetAllCount() {
		try {
			System.out.println(this.boardDAO.getAllCount("id", ""));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
