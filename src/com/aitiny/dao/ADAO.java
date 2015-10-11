package com.aitiny.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.aitiny.dao.vo.User;
import com.aitiny.util.BeanToObjectUtil;
import com.aitiny.util.Encode;
/**
 * 封装算法有：
 * <li> doUpdate(Integer key, String Column, Object value) 指定Colunm的修改参数
 * <li> doRemove(Integer key)  指定key删除
 * <li>findBykey(Integer key) 指定key的查找
 * <li>findAll()  查找所有数据
 * <li>findAll(String column, String keyWord,Integer currentPage, Integer lineSize)分页模糊查找
 * <li>findAll(String column, String keyWord,Integer currentPage, Integer lineSize, String orderColumn,Integer orderType)分页排序模糊查找
 *<li>getAllCount(String column, String keyWord) 模糊查找的数量
 * @author koala
 *注意：继承后需要设置table，cls和泛型。同时初始化时设置jdbcTemplate
 * @param <K>
 * @param <V>
 */
public abstract class ADAO<K,V>{

		protected JdbcTemplate jdbcTemplate;
		protected String table;
		protected Class<V> cls;
		protected String keyName;
		/**
		 * 需要设定table,cls,keyName,jdbcTemplate。可以更改为通过资源文件然后注入实现
		 */
		@PostConstruct
		protected  abstract void initADAO();
		
		public boolean doUpdate(K key, String Column, Object value)
				throws Exception {
			if(Column.equalsIgnoreCase(keyName)){
				return false;
			}
			String sql="UPDATE  "+table+" SET "+Column+ " =?  WHERE  "+keyName+"="+key;
			if(jdbcTemplate.update(sql, value)>0){
				return true;
				}
			return false;
		}

		public boolean doRemove(K key) throws Exception {
			String sql="DELETE  FROM "+table+"  WHERE "+keyName+"="+key;
			if(jdbcTemplate.update(sql)>0){
				return true;
				}
			return false;
		}


		public V findById(K key) throws Exception {
			// TODO Auto-generated method stub
			//System.out.println(jdbcTemplate);
			String sql="SELECT *FROM "+table+" WHERE "+keyName+"=?";
			RowMapper<V> rowMapper=new BeanPropertyRowMapper<>(cls);
			V v=jdbcTemplate.queryForObject(sql, rowMapper,key);
			return v;
		}


		public List<V> findAll() throws Exception {
			String sql="SELECT *FROM "+table;
			
			RowMapper<V> rowMapper=new BeanPropertyRowMapper<>(cls);
			List<V> v=jdbcTemplate.query(sql, rowMapper);
			return v;
		}


		public List<V> findAll(String column, String keyWord,
				Integer currentPage, Integer lineSize) throws Exception {
			// TODO Auto-generated method stub
			/*
			 * 分页sql加上字段排序
			 * SELECT * FROM (SELECT * FROM user  WHERE nickName='kit'   ORDER BY user.regtime desc) key 
			 * 	LIMIT (currentPage-1)*lineSize, lineSize 
			 */
			//
			String sql="SELECT * FROM "+table+" "
					+ " WHERE "+column+" LIKE  ?  "
					+ "  LIMIT ?,?";
			RowMapper<V> rowMapper=new BeanPropertyRowMapper<>(cls);
			Object[] params=new Object[]{"%"+keyWord+"%",(currentPage-1)*lineSize,lineSize};
		//	System.out.println(params[0]+","+params[0]+","+params[0]);
			List<V> v=jdbcTemplate.query(sql, rowMapper,params);
			return v;
		}

		public List<V> findAll(String column, String keyWord,
				Integer currentPage, Integer lineSize, String orderColumn,
				Integer orderType) throws Exception {
			/*
			 * 分页sql加上字段排序
			 * SELECT * FROM (SELECT * FROM user  WHERE nickName='kit'   ORDER BY user.regtime desc) key 
			 * 	LIMIT (currentPage-1)*lineSize, lineSize 
			 */
			//默认升序，如果orderType不为0则为降序，改变type
			String type="";
			if(orderType!=0){
				type="DESC";
			}
			//此处如果将orderColumn放入可变参数？号中，则升降序失效。应该是预处理机制问题，比如column不能在参数中，不然报错
			String sql="SELECT * FROM "
					+ " (Select * FROM "+table+" WHERE "+table+"."+column+" LIKE  ?  ORDER BY   "+orderColumn+" "+type+  " )"
					+ "  "+keyName+" LIMIT ?,?";
			//System.out.println(sql);
			RowMapper<V> rowMapper=new BeanPropertyRowMapper<>(cls);
			Object[] params=new Object[]{"%"+keyWord+"%",(currentPage-1)*lineSize,lineSize};
		//	System.out.println(params[0]+","+params[0]+","+params[0]);
			List<V> v=jdbcTemplate.query(sql, rowMapper,params);
			return v;
		}


		public Integer getAllCount(String column, String keyWord)
				throws Exception {
			String sql="SELECT COUNT(*) FROM "+ table
					+ "  WHERE "+column+" LIKE  ?  ";
			RowMapper<V> rowMapper=new BeanPropertyRowMapper<>(cls);
			Object[] params=new Object[]{"%"+keyWord+"%"};
		//	System.out.println(params[0]+","+params[0]+","+params[0]);
			Integer count=jdbcTemplate.queryForObject(sql, params, Integer.class);
			return count;
		}


}
