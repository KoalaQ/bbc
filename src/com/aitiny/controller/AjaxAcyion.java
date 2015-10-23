package com.aitiny.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.aitiny.service.IUserService;

@WebServlet(name="ajax",urlPatterns="/AjaxAcyion.action")
public class AjaxAcyion extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int type=Integer.parseInt(request.getParameter("type"));
		String value=request.getParameter("value");
		System.out.println("进入"+type+value);
		String tips="可以使用";
		String flag="0";
		
		String message="<message>"
				+ "<flag>"
				+ flag
				+ "</flag>"
				+ "<tips>"
				+tips
				+ "</tips>"
				+ "</message>";
		try {
			response.getWriter().append(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
