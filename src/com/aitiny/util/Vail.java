package com.aitiny.util;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 @WebServlet(name="vail",urlPatterns="/vail.v")
public class Vail extends HttpServlet {

	//存放62个，大小写字母和数字
	String ver[]=new String[62];
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String vali="";
		try {
			//设置图像输出
			resp.setContentType("image/jpeg");
			//获取输出流
			OutputStream os=resp.getOutputStream();
			//在内存里准别一个image
			BufferedImage image=new BufferedImage(50, 20, BufferedImage.TYPE_INT_RGB);
			//画笔
			Graphics g=image.getGraphics();
			g.setColor(Color.white);
			g.fillRect(0, 0, 50, 20);
			//绘制干扰线
			g.setColor(new Color(200,200,200));
			for (int i = 0; i <20; i++) {
				int x1=(int)(Math.random()*50);
				int y1=(int)(Math.random()*20);
				int x2=(int)(Math.random()*50);
				int y2=(int)(Math.random()*20);
				g.drawLine(x1, y1, x2, y2);
			}
			//随机生成字符并绘图
			for (int i = 0; i < 4; i++) {
				String v=(ver[(int)(Math.random()*36)]);
				vali+=v;
				g.setColor(new Color((int)(Math.random()*150),(int)(Math.random()*150),(int)(Math.random()*150)));
				g.drawString(v, 8*i+10, 15);
			}
			g.dispose();
			//将验证码保存到Session
			HttpSession session=req.getSession();
			
			session.setAttribute("vali", vali);
			
			
			
			
			//以image的形式输出
			ImageIO.write(image, "JPEG", os);
		} catch (Exception e) {
			// TODO: handle exception
			resp.getOutputStream().println(e.getStackTrace().toString());
		}
		
	
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		//循环将数字大小写字母放入String数组中
		for(int i=0;i<10;i++)
		{
			ver[i]=new Integer(i).toString();
		}
		for(int i=0;i<26;i++)
		{
			ver[i+10]=new Character((char)(97+i)).toString();
		}
		for(int i=0;i<26;i++)
		{
			ver[i+10]=new Character((char)(65+i)).toString();
		}
		
	}
	//生成随机数字和字母,
	private String getValidateCode()
	{
		StringBuffer vali=new StringBuffer();
		for(int i=0;i<4;i++)
		{
			vali.append(ver[(int)(Math.random()*36)]);
		}
		return vali.toString();
	}

	
	
}
