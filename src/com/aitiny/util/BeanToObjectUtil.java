package com.aitiny.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 提供将一个传入的类的属性转换为对应Object[]形式，顺序为类定义参数先后顺序，应于数据库字段匹配
 * @author koala
 *
 */
public class BeanToObjectUtil {
	/**
	 * 获得类参数的属性类型和属性名
	 * @param o 传入的类
	 * @return 返回的为Map<String,String>
	 * <li>第一个String为属性名字，有唯一性
	 * <li>第二个为属性的类型
	 */
	private static Map<String,String>  getFiledTypeAndNames(Object o){
		Map<String,String> fileNames=new LinkedHashMap<String,String>();
		//Class cls=o.getClass();
		Field[] fileds=o.getClass().getDeclaredFields();
		for(int i=0;i<fileds.length;i++){
			//System.out.println(fileds[i].getName()+" , "+fileds[i].getType().getName());
			fileNames.put(fileds[i].getName(), fileds[i].getType().getName());
		}
		return fileNames;
	}
	/**
	 * 获得类参数的属性名。忽略第一个属性id
	 * @param o 传入的类
	 * @return 返回的为List<String>
	 */
	private static List<String>  getFiledNames(Object o){
		List<String> fileNames=new ArrayList<String>();
		//Class cls=o.getClass();
		Field[] fileds=o.getClass().getDeclaredFields();
		//循环忽略第一个id参数
		for(int i=0;i<fileds.length-1;i++){
			//System.out.println(fileds[i].getName()+" , "+fileds[i].getType().getName());
			fileNames.add(fileds[i+1].getName());
		}
		return fileNames;
	}
	/**
	 * 获得传入类所有参数的值
	 * @param o 
	 * @return 返回class中所有属性值放入Object[]
	 */
	public static Object[] getFiledValues(Object o){
		List<String> fileNames=getFiledNames(o);
		Object[] values=new Object[fileNames.size()];
		for(int i=0;i<fileNames.size();i++){
			values[i]=getValuesByName(o,fileNames.get(i));
		}
		return values;
	}
	/**
	 * 通过get方法获取属性
	 * @param o 对应的类
	 * @param name 要获取的名字
	 * @return 返回属性值Object类型
	 */
	private  static Object getValuesByName(Object o,String name){
		String methodName="get"+StringUtil.initcap(name);
		Object obj=null;
		try {
			Method method=o.getClass().getDeclaredMethod(methodName);
			//System.out.println(method.getName());
			obj=method.invoke(o);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
//	public static void main(String[] args) {
//		Top top=new Top(12, 128, new Date());
//		for(Object o : getFiledValues(top)){
//			System.out.println(o.toString());
//		}
//		
//	}
	
}
