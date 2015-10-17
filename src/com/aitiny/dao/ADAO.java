package com.aitiny.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.aitiny.util.EnumConstant;
/**
 * 加载啦jdbcTemplate
 * 封装算法，全部为protected，子类选择使用
 * @author koala
 *注意：继承后需要设置table，cls和泛型。同时初始化时设置jdbcTemplate
 * @param <K>
 * @param <V>
 */
public abstract class ADAO<K,V>{

	@Autowired
	@Qualifier("jdbcTemplate")
		protected JdbcTemplate jdbcTemplate;
		protected String table;
		protected Class<V> cls;
		protected String keyName;
		/**
		 * 需要设定table,cls,keyName。可以更改为通过资源文件然后注入实现
		 */
		@PostConstruct
		protected  abstract void initADAO();
		/**
		 * 更新数据操作
		 * @param key 要更新的主键
		 * @param Columns 要更新的Columns，可以是多个
		 * @param values 对应的Columns的values
		 * @return 
		 * @throws Exception
		 */
		protected boolean adoUpdate(K key, String[] Columns, Object[] values)
				throws Exception {
			if(Columns.length!=values.length){
				return false;
			}
			String sql="";
			StringBuilder sb=new StringBuilder();
			sb.append("UPDATE  "+table+" SET  ");
			sb.append(this.sqlString(Columns));
			sb.append(" WHERE  "+keyName+"="+key);
			sql=sb.toString();
		//	System.out.println(sql);	
			if(jdbcTemplate.update(sql, values)>0){
				return true;
				}
			return false;
		}
		/**
		 * 主键移除数据
		 * @param key 主键
		 * @return
		 * @throws Exception
		 */
		protected boolean adoRemoveByKey(K key) throws Exception {
			String sql="DELETE  FROM "+table+"  WHERE "+keyName+"="+key;
			if(jdbcTemplate.update(sql)>0){
				return true;
				}
			return false;
		}
		/**
		 * 多列确定删除
		 * @param columns
		 * @param values
		 * @return
		 * @throws Exception
		 */
		protected boolean adoRemoveByColumns(String [] columns,Object[] values)throws Exception{
			if(columns.length!=values.length){
				
			}
			String sql="";
			StringBuilder sb=new StringBuilder();
			sb.append("DELETE  FROM "+table+"  ");
			sb.append(sqlAndString(columns));
			sql=sb.toString();
			if(jdbcTemplate.update(sql,values)>0){
				return true;
				}
			return false;
		}
		/**
		 * 根据时间删除数据，log,message等需要
		 * @param columns
		 * @param values
		 * @return
		 * @throws Exception
		 */
		protected boolean adoRemoveByTime(Date time)throws Exception{
	
			String sql="DELETE  FROM "+table+"  WHERE time < ? ";

			if(jdbcTemplate.update(sql,time)>0){
				return true;
				}
			return false;
		}
		/**
		 *  多个columns对应的values进行查询找到数据，有分页,values后两个数据第一个为page ->(currentPage-1)*lineSize,第二个为lineSize
		 * @param columns
		 * @param values
		 * @return 返回List，如果没有数据size()=0
		 * @throws Exception
		 */
		public List<V> afindByColumns(String [] columns,Object[] values, String orderColumn,
				Integer orderType)throws Exception{
			if(columns.length+2!=values.length)
			{
				return  new ArrayList<V>();
			}
			String type="";
			if(orderType==EnumConstant.Order_type_DESC){
				type="DESC";
			}
			String sql="";
			StringBuilder sb=new StringBuilder();
			sb.append("SELECT *   FROM "+table+"   ");
			sb.append(sqlAndString(columns));
			sb.append("  ORDER BY   "+orderColumn+" "+type+"  LIMIT  ?,? ");
			sql=sb.toString();
		//	System.out.println(sql+","+values[0]+values[1]+values[2]);
			RowMapper<V> rowMapper=new BeanPropertyRowMapper<>(cls);
			List<V> v=null;
			try {
				v=jdbcTemplate.query(sql, rowMapper,values);
			//	System.out.println(jdbcTemplate.query(sql, rowMapper,values));
			} catch (EmptyResultDataAccessException e) {
				e.printStackTrace();
				return new ArrayList<V>();
			}
			//System.out.println(v.get(0));
			return v;
		}
		/**
		 * 多个columns对应的values进行查询找到数据,数据量
		 * @param columns
		 * @param values
		 * @return
		 * @throws Exception
		 */
		public Integer afindByColumnsCounts(String [] columns,Object[] values)throws Exception{
			if(columns.length!=values.length)
			{
				return  0;
			}
			String sql="";
			StringBuilder sb=new StringBuilder();
			sb.append("SELECT COUNT(*)   FROM "+table+"  WHERE  ");
			sb.append(sqlAndString(columns));
			sql=sb.toString();	
			return 	jdbcTemplate.queryForObject(sql, values, Integer.class);
		}
		/**
		 * 主键查寻数据
		 * @param key
		 * @return 返回对应表类型的vo，如果没有vo为null
		 * @throws Exception
		 */
		protected V afindByKey(K key) throws Exception {
			// TODO Auto-generated method stub
			//System.out.println(jdbcTemplate);
			V v=null;
			String sql="SELECT *FROM "+table+" WHERE "+keyName+"=?";
			RowMapper<V> rowMapper=new BeanPropertyRowMapper<>(cls);
			try {
				v=jdbcTemplate.queryForObject(sql, rowMapper,key);
			} catch (EmptyResultDataAccessException e) {
				return null;
			}
			return v;
		}

		/**
		 * 查找所有数据
		 * @return 返回所有数据List<V>,如果没有数据返回list.size()=0
		 * @throws Exception
		 */
		protected List<V> afindAll() throws Exception {
			String sql="SELECT *FROM "+table;
			
			RowMapper<V> rowMapper=new BeanPropertyRowMapper<>(cls);
			List<V> v=null;
			try {
				v=jdbcTemplate.query(sql, rowMapper);
			} catch (EmptyResultDataAccessException e) {
				return new ArrayList<V>();
			}
			return v;
		}

		/**
		 * 有分页模糊查询
		 * @param column
		 * @param keyWord
		 * @param currentPage
		 * @param lineSize
		 * @return
		 * @throws Exception
		 */
		protected List<V> afindPaging(String column, String keyWord,
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
			List<V> v=null;
			try {
				v=jdbcTemplate.query(sql, rowMapper,params);
			} catch (EmptyResultDataAccessException e) {
				return new ArrayList<V>();
			}
			return v;
		}
/**
 * 分页模糊查询，加一个排序列和升序标志
 * @param column
 * @param keyWord
 * @param currentPage
 * @param lineSize
 * @param orderColumn
 * @param orderType
 * @return
 * @throws Exception
 */
		protected List<V> afindPaging(String column, String keyWord,
				Integer currentPage, Integer lineSize, String orderColumn,
				Integer orderType) throws Exception {
			/*
			 * 分页sql加上字段排序
			 * SELECT * FROM (SELECT * FROM user  WHERE nickName='kit'   ORDER BY user.regtime desc) key 
			 * 	LIMIT (currentPage-1)*lineSize, lineSize 
			 */
			//默认升序，，改变type
			String type="";
			if(orderType==EnumConstant.Order_type_DESC){
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
			List<V> v=null;
			try {
				v=jdbcTemplate.query(sql, rowMapper,params);
			} catch (EmptyResultDataAccessException e) {
				return new ArrayList<V>();
			}
			return v;
		}

	/**
	 * 匹配分页使用，获取分页模糊查询数据量
	 * @param column
	 * @param keyWord
	 * @return
	 * @throws Exception
	 */
		protected Integer agetPagingCount(String column, String keyWord)
				throws Exception {
			String sql="SELECT COUNT(*) FROM "+ table
					+ "  WHERE "+column+" LIKE  ?  ";
			Object[] params=new Object[]{"%"+keyWord+"%"};
		//	System.out.println(params[0]+","+params[0]+","+params[0]);
			Integer count=jdbcTemplate.queryForObject(sql, params, Integer.class);
			return count;
		}
		
		
		/**
		 * 给column后拼接为 columns[0]=? ,  columns[1]=?,....形式。如果columns为空则返回一个空格 “ ”。
		 * @param columns
		 * @return
		 */
		private String sqlString(String[] columns){
			StringBuilder sb=new StringBuilder();
			for(int i=0;i<columns.length;i++){	
				if(columns[i].equalsIgnoreCase(keyName)){
					return " ";
				}
				sb.append("  "+columns[i]+"=?  ");
				if(i<columns.length-1){
					sb.append("  ,  ");
				}
			}
			return sb.toString();
		}
		/**
		 * 给column后拼接为 columns[0]=? and columns[1]= ? and ....形式。如果columns为空则返回一个空格 “ ”。
		 * @param columns
		 * @return
		 */
		private String sqlAndString(String[] columns){
			StringBuilder sb=new StringBuilder();
			if(columns.length>0){
				sb.append(" WHERE ");
			}
			for(int i=0;i<columns.length;i++){	
				if(columns[i].equalsIgnoreCase(keyName)){
					return " ";
				}
				sb.append("  "+columns[i]+"=?  ");
				if(i<columns.length-1){
					sb.append(" AND  ");
				}
			}
			return sb.toString();
		}
		/**
		 * 拓展使用
		 * @return
		 */
		protected JdbcTemplate getJdbcTemplate() {
			return jdbcTemplate;
		}

}
