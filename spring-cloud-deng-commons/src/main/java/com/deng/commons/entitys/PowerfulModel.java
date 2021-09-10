package com.deng.commons.entitys;

import java.util.*;

/**
 * 数据结构 [Key=String,Value=Object]
 *
 * @Auther ZongCai
 * @Date 2021/7/8
 */
public class PowerfulModel extends Powerful implements Map<String, Object>{

	
	@SuppressWarnings("unused")
	private static final long         serialVersionUID         = 1L;
    private static final int          DEFAULT_INITIAL_CAPACITY = 16;
	
	private final Map<String, Object> map;

	public PowerfulModel() {
		this(DEFAULT_INITIAL_CAPACITY,false);
	}

	
	/**
	 * @param ordered 是否有顺序 true:有，false:没有
	 * @author shizhipeng
	 * 2021年1月12日
	 */
	public PowerfulModel(boolean ordered){
        this(DEFAULT_INITIAL_CAPACITY, ordered);
    }
	
	/**
	 * @param initialCapacity 初始化容量
	 * @author shizhipeng
	 * 2021年1月12日
	 */
	public PowerfulModel(int initialCapacity){
        this(initialCapacity, false);
    }
	
	/**
	 * @param initialCapacity 初始化容量
	 * @param ordered 是否有顺序 true:有，false:没有
	 * @author shizhipeng
	 * 2016年12月29日
	 */
	public PowerfulModel(int initialCapacity,boolean ordered) {
		if (ordered) {
			map = new LinkedHashMap<String, Object>(initialCapacity);
		} else {
			map = new HashMap<String, Object>(initialCapacity);
		}
	}

	public int size() {
		return map.size();
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}

	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}

	public Object get(Object key) {
		return map.get(key);
	}

	public Object put(String key, Object value) {
		return map.put(key, value);
	}

	public Object remove(Object key) {
		return map.remove(key);
	}

	public void putAll(Map<? extends String, ? extends Object> m) {
		map.putAll(m);
	}

	public void clear() {
		map.clear();
	}

	public Set<String> keySet() {
		return map.keySet();
	}

	public Collection<Object> values() {
		return map.values();
	}

	public Set<Entry<String, Object>> entrySet() {
		return map.entrySet();
	}

	
	
	/**
	 * 拼接Bean对象到PowerfulModel类型中，Key值相同进行覆盖操作。
	 * @param obj 拼接进来的对象
	 * @author shizhipeng
	 * 2021年1月12日
	 */
	public void jointObject(Object obj){
		if(obj == null){
			return;
		}
		PowerfulModel jointObj = (PowerfulModel) PowerfulModel.toPowerful(obj);
		for (Entry<String, Object> entry : jointObj.entrySet()) {
			String modelKey = entry.getKey();
			Object modelValue = toPowerful(entry.getValue());
			map.put(modelKey, modelValue);
		}
	}
	
}
