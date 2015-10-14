package com.aitiny.dao;

import com.aitiny.dao.vo.Admin;
import com.aitiny.dao.vo.User;

public class ClassTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			Class<String> cls = null;
			//System.out.println(cls.getSimpleName());
			Admin admin=new Admin();
			User user=new User();
			Object obj=admin;
			Object obju=user;
			System.out.println(User.class+","+obj.getClass());
			System.out.println(obj instanceof Admin);
			System.out.println(obju instanceof Admin);
			System.out.println(obj.getClass().getName());
//			admin = (Admin) obju;
//			System.out.println(obju instanceof Admin);
			
	}

}
