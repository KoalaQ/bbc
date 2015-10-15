package com.aitiny.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.aitiny.dao.ADAO;
import com.aitiny.dao.IUserDAO;
import com.aitiny.dao.vo.User;
import com.aitiny.dao.vo.Validate;
import com.aitiny.util.BeanToObjectUtil;
import com.aitiny.util.Encode;
import com.aitiny.util.EnumConstant;

@Repository("userDAO")
public class UserDAOImpl  extends ADAO<Integer,User> implements IUserDAO{



	protected  void initADAO(){
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
		public User findByEmail(String email) throws Exception {
			// TODO Auto-generated method stub
		
			List<User> user=this.afindByColumns(new String[]{"email"}, new Object[]{email,0,10},"id",EnumConstant.Order_type_ASEC);
			if(user.size()<=0){
				return null;
			}
			return user.get(0);
		}


		public User findByNickName(String nickName) throws Exception {
			List<User> user=this.afindByColumns(new String[]{"nickName"}, new Object[]{nickName,0,10},"id",EnumConstant.Order_type_ASEC);
			if(user.size()<=0){
				return null;
			}
			return user.get(0);
		}

		
		
		
		@Override
		public boolean doRemove(Integer id) throws Exception {
			// TODO Auto-generated method stub
			return this.adoRemoveByKey(id);
		}

		@Override
		public User findById(Integer id) throws Exception {
			// TODO Auto-generated method stub
			return this.afindByKey(id);
		}

		@Override
		public List<User> findAll() throws Exception {
			// TODO Auto-generated method stub
			return this.afindAll();
		}

		@Override
		public List<User> findAll(String column, String keyWord,
				Integer currentPage, Integer lineSize) throws Exception {
			// TODO Auto-generated method stub
			return this.afindPaging(column, keyWord, currentPage, lineSize);
		}

		@Override
		public List<User> findAll(String column, String keyWord,
				Integer currentPage, Integer lineSize, String orderColumn,
				Integer orderType) throws Exception {
			// TODO Auto-generated method stub
			return this.afindPaging(column, keyWord, currentPage, lineSize, orderColumn, orderType);
		}

		@Override
		public Integer getAllCount(String column, String keyWord)
				throws Exception {
			// TODO Auto-generated method stub
			return this.agetPagingCount(column, keyWord);
		}




}
