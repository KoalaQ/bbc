package com.aitiny.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aitiny.dao.vo.Board;

public class IBoardServiceTest {
	ApplicationContext ctx=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	IBoardService boardService=(IBoardService) ctx.getBean("boardService");
	@Test
	public void testAddBoard() {
		Board board=new Board();
		board.setName("java 程序设计");
		board.setDescription("优秀的MVC框架");
		board.setParentId(6);
		board.setAid(1);
		try {
			System.out.println(boardService.addBoard(board));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void testRemoveBoard() {
		fail("Not yet implemented");
	}

	@Test
	public void testLoadBoard() {
		try {
			System.out.println(this.boardService.loadBoard(15));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testLoadChildBoards() {
	try {
		System.out.println(this.boardService.loadChildBoards(5));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	@Test
	public void testLoadAllBoards() {
		try {
			System.out.println(this.boardService.loadAllBoards());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testLoadRootBoards() {
		try {
			System.out.println(this.boardService.loadRootBoards());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testSaveOrUpdateBoard() {
		Board board=new Board();
		board.setName("java 程序设计");
		board.setDescription("优秀的MVC框架");
		board.setParentId(6);
		board.setBoardImg("fdasf");
		board.setAid(1);
		try {
			this.boardService.updateBoard(board);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
