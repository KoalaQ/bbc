package com.aitiny.dao;

import java.util.List;

/**
 * 
 * @author koala
 *
 * @param <K> 主键标记，由子接口标记
 * @param <V> VO类型，子接口标记
 */
public interface IDAO<K,V> {
	/**
	 * 数据库增加，对相应的表可选择修改参数的个数，因为有些是默认的可以不用处理
	 * @param vo 要创建的对象
	 * @return 创建成功返回true，否则返回false
	 * @throws Exception
	 */
	public boolean doCreate(V vo) throws Exception;
	/**
	 * 修改操作，参数全更新
	 * @param vo 传入的对象修改，vo中id进行确定
	 * @return  插入成功返回true，否则返回false
	 * @throws Exception
	 */
	public boolean doUpdate (V vo)throws Exception;
	/**
	 * 修改对应id的相应Column的值，.有修改密码的表则重写此方法，防止其他表多余判断是否为密码字段。
	 * 用户一般不常修改个人信息，暂时这样，可以考虑添加专门修改密码的方法
	 * @param id  要修改的id
	 * @param Columns  要修改的Column，多个数组
	 * @param values   传入的参数，Object类型，数组，与Columns对应
	 * @return  修改成功返回true，否则返回false
	 * @throws Exception
	 */
	public boolean doUpdate(K id,String[] Columns,Object[] values)throws Exception;
	/**
	 * 修改对应id的相应Column的值，参数类型固定
	 * @param id  要修改的id
	 * @param Column  要修改的Column
	 * @param value   传入的参数，String类型
	 * @return  修改成功返回true，否则返回false
	 * @throws Exception
	 */
//	public boolean doUpdate(K id,String Column,String value)throws Exception;
//	/**
//	 * 修改对应id的相应Column的值，参数类型固定
//	 * @param id  要修改的id
//	 * @param Column  要修改的Column
//	 * @param value   传入的参数，Integer类型
//	 * @return  修改成功返回true，否则返回false
//	 * @throws Exception
//	 */
//	public boolean doUpdate(K id,String Column,Integer value)throws Exception;
//	/**
//	 * 修改对应id的相应Column的值，参数类型固定
//	 * @param id  要修改的id
//	 * @param Column  要修改的Column
//	 * @param value   传入的参数，Date类型
//	 * @return  修改成功返回true，否则返回false
//	 * @throws Exception
//	 */
//	public boolean doUpdate(K id,String Column,Date value)throws Exception;
	/**
	 * 通过id删除
	 * @param id 要删除的id
	 * @return 删除成功返回true，否则返回false
	 * @throws Exception
	 */
	public boolean doRemove(K id)throws Exception;
	/**
	 * 通过id查找
	 * @param id 要查找的id
	 * @return  返回查找的数据对象
	 * @throws Exception
	 */
	public V findById(K id)throws Exception;
	/**
	 *  查询所以数据
	 * @return 有数据返回list,没数据返回lise.size()=0
	 * @throws Exception
	 */
	public List<V> findAll()throws Exception;
	/**
	 * 分页模糊查询表中的全部数据
	 * @param column 要模糊查询的数据类
	 * @param keyWord 要模糊查询的关键字，如果关键为null（isEmpty()==true）,则查询全部数据
	 * @param currentPage  当前所在页
	 * @param lineSize  每页显示的数据行数
	 * @return 如果没有数据返回的List为空（size()==0）,如果有数据将会以List的形式返回
	 * @throws Exception
	 */
	public List<V> findAll(String column,String keyWord,Integer currentPage,Integer lineSize)throws Exception;
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
	public List<V> findAll(String column,String keyWord,Integer currentPage,Integer lineSize,String orderColumn,Integer orderType)throws Exception;
	/**
	 *  统计数据表之中的数据量
	 * @param column 模糊查询的列
	 * @param keyWord 模糊查询的关键字
	 * @return 如果有数据将会以，则会返回COUNT（）函数的统计结果，如果没有数据返回的0,
	 * @throws Exception
	 */
	public Integer getAllCount(String column,String keyWord)throws Exception;
	
}

	