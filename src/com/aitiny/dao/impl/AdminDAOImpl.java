package com.aitiny.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aitiny.dao.ADAO;
import com.aitiny.dao.IAdminDAO;
import com.aitiny.dao.vo.Admin;
import com.aitiny.util.Encode;
import com.aitiny.util.EnumConstant;
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
		//System.out.println(sql);	
		if(jdbcTemplate.update(sql, values)>0){
			return true;
			}
		return false;
	}

	@Override
	public boolean doRemove(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return this.adoRemoveByKey(id);
	}

	@Override
	public Admin findByEmail(String email) throws Exception {
		// TODO Auto-generated method stub
		//email中有特殊字符@会使得链接字符串sql无法执行，使用预处理正常
		List<Admin> admins=this.afindByColumns(new String[]{"email"}, new Object[]{email,0,10},"id",EnumConstant.Order_type_ASEC);
		if(admins.size()>0){
			return admins.get(0);
		}
		return null;
	}
	
	@Override
	public Admin findByNickName(String nickName) throws Exception {
		// TODO Auto-generated method stub

		List<Admin> admins=this.afindByColumns(new String[]{"nickName"}, new Object[]{nickName,0,10},"id",EnumConstant.Order_type_ASEC);
		if(admins.size()>0){
			return admins.get(0);
		}
		return null;
	}

	@Override
	public Admin findByName(String name) throws Exception {
		// TODO Auto-generated method stub
		List<Admin> admins=this.afindByColumns(new String[]{"name"}, new Object[]{name,0,10},"id",EnumConstant.Order_type_ASEC);
		if(admins.size()>0){
			return admins.get(0);
		}
		return null;
	}

	@Override
	public Admin findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return this.afindByKey(id);
	}

	@Override
	public List<Admin> findAll() throws Exception {
		// TODO Auto-generated method stub
		return this.afindAll();
	}

	@Override
	public List<Admin> findAll(String column, String keyWord,
			Integer currentPage, Integer lineSize) throws Exception {
		// TODO Auto-generated method stub
		return this.afindPaging(column, keyWord, currentPage, lineSize);
	}

	@Override
	public List<Admin> findAll(String column, String keyWord,
			Integer currentPage, Integer lineSize, String orderColumn,
			Integer orderType) throws Exception {
		// TODO Auto-generated method stub
		return this.afindPaging(column, keyWord, currentPage, lineSize, orderColumn, orderType);
	}

	@Override
	public Integer getAllCount(String column, String keyWord) throws Exception {
		// TODO Auto-generated method stub
		return this.agetPagingCount(column, keyWord);
	}

}
