package com.aitiny.dao.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.aitiny.dao.ADAO;
import com.aitiny.dao.IUserDAO;
import com.aitiny.dao.vo.User;
import com.aitiny.util.BeanToObjectUtil;
import com.aitiny.util.Encode;

@Repository("userDAO")
public class UserDAOImpl  extends ADAO<Integer,User> implements IUserDAO{
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate ajdbcTemplate;


	protected  void initADAO(){
		this.jdbcTemplate=this.ajdbcTemplate;
		this.table="user";
		this.cls=User.class;
		this.keyName="id";
	}

	public boolean doCreate(User vo) throws Exception {
//	    通过类的全插入，不对id进行插入。
//			使用BeanToObjectUtil工具类获取属性值。由于数据库有默认参数。故不适用，仅练习使用
//			String sql="INSERT INTO user(email,password,nickName,photoPath,available,vantages,viewcount,postcount,grade,fanscount,concerncount,theme,regtime) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
//			Object[] params=BeanToObjectUtil.getFiledValues(vo);
			String sql="INSERT INTO user(email,password,nickName,regtime) VALUES(?,?,?,?)";
			Object[] params=new Object[]{vo.getEmail(),Encode.getEncode(Encode.MD5, vo.getPassword()),vo.getNickName(),new Date()};
			if(jdbcTemplate.update(sql, params)>0){
				return true;
			}
			return false;
		}
		public boolean doUpdate(User vo) throws Exception {
			// TODO Auto-generated method stub
//		    通过类的全插入，不对id进行插入。
//				使用BeanToObjectUtil工具类获取属性值。由于数据库有默认参数。故不适用，仅练习使用
				String sql="UPDATE  user SET email=?,password=?,nickName=?,photoPath=?,available=?,vantages=?,viewcount=?,postcount=?,grade=?,fanscount=?,concerncount=?,theme=?,regtime=?  "
						+ " WHERE id="+vo.getId();
				Object[] params=BeanToObjectUtil.getFiledValues(vo);
						if(jdbcTemplate.update(sql, params)>0){
					return true;
				}
				return false;
		}
		public boolean doUpdate(Integer id, String Column, Object value)
				throws Exception {
			if(Column.equalsIgnoreCase("id")){
				return false;
			}
			if(Column.equalsIgnoreCase("password")){
				value=Encode.getEncode(Encode.MD5, value.toString());
			}
			String sql="UPDATE  user SET "+Column+ " =?  WHERE id="+id;
			if(jdbcTemplate.update(sql, value)>0){
				return true;
				}
			return false;
		}
		public User findByEmail(String email) throws Exception {
			// TODO Auto-generated method stub
			String sql="SELECT * FROM user WHERE email=?";
			RowMapper<User> rowMapper=new BeanPropertyRowMapper<>(User.class);
			User user=jdbcTemplate.queryForObject(sql, rowMapper,email);
			return user;
		}


		public User findByNickName(String nickName) throws Exception {
			String sql="SELECT *FROM user WHERE nickName=?";
			RowMapper<User> rowMapper=new BeanPropertyRowMapper<>(User.class);
			User user=jdbcTemplate.queryForObject(sql, rowMapper,nickName);
			return user;
		}




}
