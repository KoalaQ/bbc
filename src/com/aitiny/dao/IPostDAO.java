package com.aitiny.dao;

import java.util.List;

import com.aitiny.dao.vo.Post;
/**
 * 增加的方法是查询状态正常的文章或查询状态为删除的文章.由状态决定.父类中方法失效
 * @author koala
 *
 */
public interface IPostDAO extends IDAO<Integer, Post> {
	
	public List<Post> findAllAvailable(int status)throws Exception;
	/**
	 * 分页模糊查询表中的全部数据
	 * @param column 要模糊查询的数据类
	 * @param keyWord 要模糊查询的关键字，如果关键为null（isEmpty()==true）,则查询全部数据
	 * @param currentPage  当前所在页
	 * @param lineSize  每页显示的数据行数
	 * @return 如果没有数据返回的List为空（size()==0）,如果有数据将会以List的形式返回
	 * @throws Exception
	 */
	public List<Post> findAllAvailable(String column,String keyWord,Integer currentPage,Integer lineSize,int status)throws Exception;
	/**
	 * 分页查询表中的全部数据，有排序参数.不是模糊查询的
	 * @param column 要查询的数据类
	 * @param keyWord 要查询的关键字，如果关键为null（isEmpty()==true）,则查询全部数据
	 * @param currentPage  当前所在页
	 * @param lineSize  每页显示的数据行数
	 * @param orderColumn  排序的列
	 * @param orderType  排序类型，0升序，1降序
	 * @return 如果没有数据返回的List为空（size()==0）,如果有数据将会以List的形式返回
	 * @throws Exception
	 */
	public List<Post> findAllAvailable(String column,String keyWord,Integer currentPage,Integer lineSize,String orderColumn,Integer orderType,int status)throws Exception;
	/**
	 * 分页模糊查询表中的全部数据，有排序参数
	 * @param column 要模糊查询的数据类
	 * @param keyWord 要模糊查询的关键字，如果关键为null（isEmpty()==true）,则查询全部数据
	 * @param currentPage  当前所在页
	 * @param lineSize  每页显示的数据行数
	 * @param orderColumn  排序的列
	 * @param orderType  排序类型，0升序，1降序
	 * @return 如果没有数据返回的List为空（size()==0）,如果有数据将会以List的形式返回
	 * @throws Exception
	 */
	public List<Post> findAllAvailableLike(String column, String keyWord,
			Integer currentPage, Integer lineSize, String orderColumn,
			Integer orderType, int status) throws Exception;
	/**
	 *  统计数据表之中的数据量
	 * @param column 模糊查询的列
	 * @param keyWord 模糊查询的关键字
	 * @return 如果有数据将会以，则会返回COUNT（）函数的统计结果，如果没有数据返回的0,
	 * @throws Exception
	 */
	public Integer getAllCountAvailable(String column,String keyWord,int status)throws Exception;
	/**
	 *  统计数据表之中的数据量
	 * @param column 查询的列
	 * @param keyWord 查询的关键字
	 * @return 如果有数据将会以，则会返回COUNT（）函数的统计结果，如果没有数据返回的0,
	 * @throws Exception
	 */
	public Integer getAllCountAvailableLike(String column, String keyWord,
			int status) throws Exception;
}
