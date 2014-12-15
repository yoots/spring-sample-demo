/*
 * Copyright (c) 2014 아시아나IDT
 * 
 * 이 프로그램은 저작권 보호법에 의해 보호됩니다.
 * 이 프로그램의 일부나 전부를 무단으로 복제하거나 배포하는 경우에는
 * 저작권의 침해가 되므로 주의하시기 바랍니다.
 */
package com.spring.demo.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 이 클래스는...
 *
 * @author yoots
 * Created on 2014. 7. 29.
 */

public class ResultMap<K, V> implements Map<K, V> , Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3136431927967921912L;
	
	private Map<K, V> result = new HashMap<K, V>();

	/**
	 * @return
	 * @see java.util.Map#size()
	 */
	public int size() {
		return result.size();
	}

	/**
	 * @return
	 * @see java.util.Map#isEmpty()
	 */
	public boolean isEmpty() {
		return result.isEmpty();
	}

	/**
	 * @param key
	 * @return
	 * @see java.util.Map#containsKey(java.lang.Object)
	 */
	public boolean containsKey(Object key) {
		return result.containsKey(key);
	}

	/**
	 * @param value
	 * @return
	 * @see java.util.Map#containsValue(java.lang.Object)
	 */
	public boolean containsValue(Object value) {
		return result.containsValue(value);
	}

	/**
	 * @param key
	 * @return
	 * @see java.util.Map#get(java.lang.Object)
	 */
	public V get(Object key) {
		return result.get(key);
	}

	/**
	 * @param key
	 * @param value
	 * @return
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	public V put(K key, V value) {
		return result.put(key, value);
	}

	/**
	 * @param key
	 * @return
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	public V remove(Object key) {
		return result.remove(key);
	}

	/**
	 * @param m
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	public void putAll(Map<? extends K, ? extends V> m) {
		result.putAll(m);
	}

	/**
	 * 
	 * @see java.util.Map#clear()
	 */
	public void clear() {
		result.clear();
	}

	/**
	 * @return
	 * @see java.util.Map#keySet()
	 */
	public Set<K> keySet() {
		return result.keySet();
	}

	/**
	 * @return
	 * @see java.util.Map#values()
	 */
	public Collection<V> values() {
		return result.values();
	}

	/**
	 * @return
	 * @see java.util.Map#entrySet()
	 */
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		return result.entrySet();
	}

	/**
	 * @param o
	 * @return
	 * @see java.util.Map#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		return result.equals(o);
	}

	/**
	 * @return
	 * @see java.util.Map#hashCode()
	 */
	public int hashCode() {
		return result.hashCode();
	}
	
}