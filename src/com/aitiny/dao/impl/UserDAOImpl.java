package com.aitiny.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.aitiny.dao.IUserDAO;
import com.aitiny.dao.vo.User;
import com.aitiny.util.BeanToObjectUtil;
import com.aitiny.util.Encode;
@Repository("userDAO")
public class UserDAOImpl implements IUserDAO {
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	@Override
	public boolean doCreate(User vo) throws Exception {
//    通过类的全插入，不对id进行插入。
//		使用BeanToObjectUtil工具类获取属性值。由于数据库有默认参数。故不适用，仅练习使用
//		String sql="INSERT INTO user(email,password,nickName,photoPath,available,vantages,viewcount,postcount,grade,fanscount,concerncount,theme,regtime) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
//		Object[] params=BeanToObjectUtil.getFiledValues(vo);
		String sql="INSERT INTO user(email,password,nickName,regtime) VALUES(?,?,?,?)";
		Object[] params=new Object[]{vo.getEmail(),Encode.getEncode("MD5", vo.getPassword()),vo.getNickName(),new Date()};
		if(jdbcTemplate.update(sql, params)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean doUpdate(User vo) throws Exception {
		// TODO Auto-generated method stub
//	    通过类的全插入，不对id进行插入。
//			使用BeanToObjectUtil工具类获取属性值。由于数据库有默认参数。故不适用，仅练习使用
			String sql="UPDATE  user SET email=?,password=?,nickName=?,photoPath=?,available=?,vantages=?,viewcount=?,postcount=?,grade=?,fanscount=?,concerncount=?,theme=?,regtime=?  "
					+ " WHERE id="+vo.getId();
			Object[] params=BeanToObjectUtil.getFiledValues(vo);
					if(jdbcTemplate.update(sql, params)>0){
				return true;
			}
			return false;
	}

	@Override
	public boolean doUpdate(Integer id, String Column, Object value)
			throws Exception {
		if(Column.equalsIgnoreCase("id")){
			return false;
		}
		if(Column.equalsIgnoreCase("password")){
			value=Encode.getEncode("MD5", value.toString());
		}
		String sql="UPDATE  user SET "+Column+ " =?  WHERE id="+id;
		if(jdbcTemplate.update(sql, value)>0){
			return true;
			}
		return false;
	}
	@Override
	public boolean doRemove(Integer id) throws Exception {
		String sql="DELETE  FROM user  WHERE id="+id;
		if(jdbcTemplate.update(sql)>0){
			return true;
			}
		return false;
	}

	@Override
	public User findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		String sql="SELECT *FROM user WHERE id=?";
		RowMapper<User> rowMapper=new BeanPropertyRowMapper<>(User.class);
		User user=jdbcTemplate.queryForObject(sql, rowMapper,id);
		return user;
	}
	@Override
	public User findByEmail(String email) throws Exception {
		// TODO Auto-generated method stub
		String sql="SELECT *FROM user WHERE email=?";
		RowMapper<User> rowMapper=new BeanPropertyRowMapper<>(User.class);
		User user=jdbcTemplate.queryForObject(sql, rowMapper,email);
		return user;
	}

	@Override
	public User findByNickName(String nickName) throws Exception {
		String sql="SELECT *FROM user WHERE nickName=?";
		RowMapper<User> rowMapper=new BeanPropertyRowMapper<>(User.class);
		User user=jdbcTemplate.queryForObject(sql, rowMapper,nickName);
		return user;
	}
	@Override
	public List<User> findAll() throws Exception {
		String sql="SELECT *FROM user";
		
		RowMapper<User> rowMapper=new BeanPropertyRowMapper<>(User.class);
		List<User> allUser=jdbcTemplate.query(sql, rowMapper);
		return allUser;
	}

	@Override
	public List<User> findAll(String column, String keyWord,
			Integer currentPage, Integer lineSize) throws Exception {
		// TODO Auto-generated method stub
		/*
		 * 分页sql加上字段排序
		 * SELECT * FROM (SELECT * FROM user  WHERE nickName='kit'   ORDER BY user.regtime desc) id 
		 * 	LIMIT (currentPage-1)*lineSize, lineSize 
		 */
		//
		String sql="SELECT * FROM user "
				+ " WHERE "+column+" LIKE  ?  "
				+ "  LIMIT ?,?";
		RowMapper<User> rowMapper=new BeanPropertyRowMapper<>(User.class);
		Object[] params=new Object[]{"%"+keyWord+"%",(currentPage-1)*lineSize,lineSize};
	//	System.out.println(params[0]+","+params[0]+","+params[0]);
		List<User> users=jdbcTemplate.query(sql, rowMapper,params);
		return users;
	}
	@Override
	public List<User> findAll(String column, String keyWord,
			Integer currentPage, Integer lineSize, String orderColumn,
			Integer orderType) throws Exception {
		/*
		 * 分页sql加上字段排序
		 * SELECT * FROM (SELECT * FROM user  WHERE nickName='kit'   ORDER BY user.regtime desc) id 
		 * 	LIMIT (currentPage-1)*lineSize, lineSize 
		 */
		//默认升序，如果orderType不为0则为降序，改变type
		String type="";
		if(orderType!=0){
			type="DESC";
		}
		//此处如果将orderColumn放入可变参数？号中，则升降序失效。应该是预处理机制问题，比如column不能在参数中，不然报错
		String sql="SELECT * FROM "
				+ " (Select * FROM user WHERE user."+column+" LIKE  ?  ORDER BY   "+orderColumn+" "+type+" )"
				+ "  id LIMIT ?,?";
		//System.out.println(sql);
		RowMapper<User> rowMapper=new BeanPropertyRowMapper<>(User.class);
		Object[] params=new Object[]{"%"+keyWord+"%",(currentPage-1)*lineSize,lineSize};
	//	System.out.println(params[0]+","+params[0]+","+params[0]);
		List<User> users=jdbcTemplate.query(sql, rowMapper,params);
		return users;
	}

	@Override
	public Integer getAllCount(String column, String keyWord)
			throws Exception {
		String sql="SELECT COUNT(*) FROM user "
				+ " WHERE "+column+" LIKE  ?  ";
		RowMapper<User> rowMapper=new BeanPropertyRowMapper<>(User.class);
		Object[] params=new Object[]{"%"+keyWord+"%"};
	//	System.out.println(params[0]+","+params[0]+","+params[0]);
		Integer count=jdbcTemplate.queryForObject(sql, params, Integer.class);
		return count;
	}





}
