package com.aitiny.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.aitiny.dao.ADAO;
import com.aitiny.dao.IAdminDAO;
import com.aitiny.dao.vo.Admin;
import com.aitiny.util.Encode;
@Repository("adminDAO")
public class AdminDAOImpl extends ADAO<Integer, Admin> implements IAdminDAO {



	@Override
	protected void initADAO() {
		this.table="admin";
		this.cls=Admin.class;
		this.keyName="id";
	}

	@Override
	public boolean doCreate(Admin vo) throws Exception {
		// TODO Auto-generated method stub
		String sql="INSERT INTO admin(email,password,nickName,name) VALUES(?,?,?,?)";
		Object[] params=new Object[]{vo.getEmail(),Encode.getEncode("MD5", vo.getPassword()),vo.getNickName(),vo.getName()};
		if(this.jdbcTemplate.update(sql,params)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean doUpdate(Admin vo) throws Exception {
		// TODO Auto-generated method stub
		String sql="UPDATE admin SET email=?,password=?,level=?,nickName=?,name=?,photoPath=? "
				+ "  WHERE id=? ";
		Object[] params=new Object[]{vo.getEmail(),Encode.getEncode(Encode.MD5, vo.getPassword()),vo.getLevel(),
					vo.getNickName(),vo.getName(),vo.getPhotoPath(),vo.getId()};
		if(this.jdbcTemplate.update(sql, params)>0){
			return true;
		}
		return false;
	}

	@Override
	public Admin findByEmail(String email) throws Exception {
		// TODO Auto-generated method stub
		//email中有特殊字符@会使得链接字符串sql无法执行，使用预处理正常
		String sql="SELECT * FROM admin WHERE email=?";
		RowMapper<Admin> rowMapper=new BeanPropertyRowMapper<>(Admin.class);
		Admin admin =this.jdbcTemplate.queryForObject(sql, rowMapper,email);
		return admin;
	}
	
	public boolean doUpdate(Integer key, String[] Columns, Object[] values)
			throws Exception {
		if(Columns.length!=values.length){
			return false;
		}
		String sql="";
		StringBuilder sb=new StringBuilder();
		sb.append("UPDATE  "+table+" SET  ");
		for(int i=0;i<values.length;i++){	
			if(Columns[i].equalsIgnoreCase(keyName)){
				return false;
			}
			if(Columns[i].equalsIgnoreCase("password")){
				values[i]=Encode.getEncode(Encode.MD5, values[i].toString());
			}
			sb.append("  "+Columns[i]+"=? ");
			if(i<values.length-1){
				sb.append(" , ");
			}
		}
		sb.append(" WHERE  "+keyName+"="+key);
		sql=sb.toString();
		System.out.println(sql);	
		if(jdbcTemplate.update(sql, values)>0){
			return true;
			}
		return false;
	}
	@Override
	public Admin findByNickName(String nickName) throws Exception {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM admin WHERE nickName=?";
		RowMapper<Admin> rowMapper=new BeanPropertyRowMapper<>(Admin.class);
		Admin admin=this.jdbcTemplate.queryForObject(sql, rowMapper,nickName);
		return admin;
	}

	@Override
	public Admin findByName(String name) throws Exception {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM admin WHERE name=?";
		RowMapper<Admin> rowMapper=new BeanPropertyRowMapper<>(Admin.class);
		Admin admin=this.jdbcTemplate.queryForObject(sql, rowMapper,name);
		return admin;
	}

}
