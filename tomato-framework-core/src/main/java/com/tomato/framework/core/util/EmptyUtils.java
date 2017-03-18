package com.tomato.framework.core.util;

import java.util.Collection;
import java.util.Map;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * 判空工具类
 * 
 *
 * @author gerry
 * @version  1.0, 2016年9月2日下午6:48:52
 */
public final class EmptyUtils {

	/**
	 * 判断集合是否为空 coll->null->true coll-> coll.size() == 0 -> true
	 * 
	 * @param coll
	 * @return
	 */
	public static <T> boolean isEmpty(Collection<T> coll) {
		return (coll == null || coll.isEmpty());
	}

	/**
	 * 判断集合是否不为空
	 * 
	 * @param coll
	 * @return
	 */
	public static <T> boolean isNotEmpty(Collection<T> coll) {
		return !isEmpty(coll);
	}

	/**
	 * 判断map是否为空
	 * 
	 * @param map
	 * @return
	 */
	public static <K, V> boolean isEmpty(Map<K, V> map) {
		return (map == null || map.isEmpty());
	}

	/**
	 * 判断map是否不为空
	 * 
	 * @param map
	 * @return
	 */
	public static <K, V> boolean isNotEmpty(Map<K, V> map) {
		return (map == null || map.isEmpty());
	}

	/**
	 * 判断一个对象是否为空
	 * 
	 * @param t
	 * @return
	 */
	public static <T> boolean isEmpty(T t) {
		if (t == null) {
			return true;
		}
		if (StringUtils.isEmpty(t.toString())) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断数组是否不为空
	 * 
	 * */
	public static <T> boolean isNotEmpty(T[] datas) {
		if (ObjectUtils.isEmpty(datas)) {
			if (datas.length == 0) {
				return false;
			}
			return true;
		}
		return true;
	}
	
	/**
	 * 判断数组是否不为空
	 * 
	 * */
	public static <T> boolean isEmpty(T[] datas) {
		return !isNotEmpty(datas);
	}
	

	/**
	 * 判断一个对象是否不为空
	 * 
	 * @param t
	 * @return
	 */
	public static <T> boolean isNotEmpty(T t) {
		return !isEmpty(t);
	}

}
