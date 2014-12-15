/**
 * 
 */
package com.spring.demo.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

/**
 * @author user
 */
@Component
public class EntityMap<K, V> implements Serializable{

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -6671321224282115857L;
	
	/**
	 * 
	 */
	private Map<K, V> param = new LinkedHashMap<K, V>();

	/**
	 * @param value
	 * @return
	 * @see java.util.LinkedHashMap#containsValue(java.lang.Object)
	 */
	public boolean containsValue(Object value) {
		return param.containsValue(value);
	}

	/**
	 * @param key
	 * @return
	 * @see java.util.LinkedHashMap#get(java.lang.Object)
	 */
	public V get(Object key) {
		return param.get(key);
	}

	/**
	 * 
	 * @see java.util.LinkedHashMap#clear()
	 */
	public void clear() {
		param.clear();
	}

	/**
	 * @param o
	 * @return
	 * @see java.util.AbstractMap#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		return param.equals(o);
	}

	/**
	 * @return
	 * @see java.util.HashMap#size()
	 */
	public int size() {
		return param.size();
	}

	/**
	 * @return
	 * @see java.util.HashMap#isEmpty()
	 */
	public boolean isEmpty() {
		return param.isEmpty();
	}

	/**
	 * @param key
	 * @return
	 * @see java.util.HashMap#containsKey(java.lang.Object)
	 */
	public boolean containsKey(Object key) {
		return param.containsKey(key);
	}

	/**
	 * @return
	 * @see java.util.AbstractMap#hashCode()
	 */
	public int hashCode() {
		return param.hashCode();
	}

	/**
	 * @param key
	 * @param value
	 * @return
	 * @see java.util.HashMap#put(java.lang.Object, java.lang.Object)
	 */
	public V put(K key, V value) {
		return param.put(key, value);
	}

	/**
	 * @return
	 * @see java.util.AbstractMap#toString()
	 */
	public String toString() {
		return param.toString();
	}

	/**
	 * @param m
	 * @see java.util.HashMap#putAll(java.util.Map)
	 */
	public void putAll(Map<? extends K, ? extends V> m) {
		param.putAll(m);
	}

	/**
	 * @param key
	 * @return
	 * @see java.util.HashMap#remove(java.lang.Object)
	 */
	public V remove(Object key) {
		return param.remove(key);
	}

	/**
	 * @return
	 * @see java.util.HashMap#keySet()
	 */
	public Set<K> keySet() {
		return param.keySet();
	}

	/**
	 * @return
	 * @see java.util.HashMap#values()
	 */
	public Collection<V> values() {
		return param.values();
	}

	/**
	 * @return
	 * @see java.util.HashMap#entrySet()
	 */
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		return param.entrySet();
	}
	
	/**
	 * 
	 * <pre>
	 * getMap
	 * @return
	 * </pre>
	 */
	public Map<K, V> get(){
		return param;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#finalize()
	 */
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}
}
