package com.spring.demo.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MapUtil {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -7160968420289600151L;
	
    /**
     * 기본문자열
     */
    private static final String DEFAULT_STRING = "";
    /**
     * 기본숫자
     */
    private static final int DEFAULT_NUMBER = 0;

    /** The map. */
    private Map<String,Object> data;

    private String messageId;
    
    /**
     * 
     * @return
     */
    public String getMessageId() {
		return getString("messageId");
	}

    /**
     * 
     * @param messageId
     */
	public void setMessageId(String messageId) {
		this.messageId = messageId;
		data.put("messageId", this.messageId);
	}

	/**
     * Instantiates a new map util.
     *
     * @param map the map
     */
    public MapUtil(Map<String,Object> data){
        this.data = data;
    }

    /**
     * Gets the single instance of MapUtil.
     *
     * @return single instance of MapUtil
     */
    public synchronized static MapUtil getInstance(){
        return new MapUtil(new HashMap<String,Object>());
    }

    /**
     * Checks if is null.
     *
     * @param data the data
     * @return true, if is null
     */
    public static boolean isNull(Map<String,Object> data){
        return data == null;
    }

    /**
     * Checks if is empty.
     *
     * @param data the data
     * @return true, if is empty
     */
    public static boolean isEmpty(Map<String,Object> data){
        return isNull(data) || data.isEmpty();
    }

    /**
     * key에 할당된 Object를 반환한다.
     *
     * @param data the data
     * @param key the key
     * @return the object
     */
    public static Object get(Map<String,Object> data, String key){
        if(isEmpty(data)) {
            return null;
        }
        return data.get(key);
    }

    /**
     * key에 할당된 Object의 Empty 여부를 반환한다.
     * data 또는 Object가 null 이면 true 반환.
     * Empty를 확인 가능한 Object는 Map, List, String, Object[] 이다.
     *
     * @param data the data
     * @param key the key
     * @return true, if is empty value
     */
    public static boolean isEmptyValue(Map<String,Object> data, String key){
        Object o = get(data, key);
        if(o == null){
            return true;
        }

        if(o instanceof Map){
            @SuppressWarnings("rawtypes")
            Map value = (Map)o;
            return value.isEmpty();
        }

        if(o instanceof List){
            @SuppressWarnings("rawtypes")
            List value = (List)o;
            return value.isEmpty();
        }

        if(o instanceof Object[]){
            Object[] value = (Object[])o;
            return value.length == 0;
        }

        if(o instanceof int[]){
            int[] value = (int[])o;
            return value.length == 0;
        } 

        if(o instanceof float[]){
            float[] value = (float[])o;
            return value.length == 0;
        }

        if(o instanceof double[]){
            double[] value = (double[])o;
            return value.length == 0;
        }

        if(o instanceof long[]){
            long[] value = (long[])o;
            return value.length == 0;
        }

        if(o instanceof String){
            String value = (String)o;
            return value.isEmpty();
        }

        return false;
    }

    /**
     * key에 할당된 Object를 String으로 반환한다.
     * 만약 해당값이 null 일 경우 defaultString으로 반환한다.
     *
     * @param data the data
     * @param key the key
     * @return the object
     */
    public static String getString(Map<String,Object> data, String key, String defaultString){
        if(get(data, key) == null) {
            return defaultString;
        }

        return get(data, key).toString();
    }

    /**
     * key에 할당된 Object를 String으로 반환한다.
     * 만약 해당값이 null 일 경우 DEFAULT_STRING으로 반환한다.
     *
     * @param data the data
     * @param key the key
     * @return the object
     */
    public static String getString(Map<String,Object> data, String key){
        return getString(data, key, DEFAULT_STRING);
    }

    /**
     * key에 할당된 Object를 int으로 반환한다.
     * 만약 해당값이 null 일 경우 defaultInt로 반환한다.
     *
     * @param data the data
     * @param key the key
     * @return the object
     */
    public static int getInt(Map<String,Object> data, String key, int defaultInt){
        int result = defaultInt;
        String value = getString(data, key);

        if(!value.isEmpty()){
            try{
                result = Integer.parseInt(value);
            }catch(Exception e){
                result = defaultInt;
            }
        }
        return result;
    }

    /**
     * key에 할당된 Object를 int으로 반환한다.
     * 만약 해당값이 null 일 경우 DEFAULT_NUMBER로 반환한다.
     *
     * @param data the data
     * @param key the key
     * @return the object
     */
    public static int getInt(Map<String,Object> data, String key){
        return getInt(data, key, DEFAULT_NUMBER);
    }

    /**
     * key에 할당된 Object를 float으로 반환한다.
     * 만약 해당값이 null 일 경우 defaultFloat으로 반환한다.
     *
     * @param data the data
     * @param key the key
     * @return the object
     */
    public static float getFloat(Map<String,Object> data, String key, float defaultFloat){
        float result = defaultFloat;
        String value = getString(data, key);

        if(!value.isEmpty()){
            try{
                result = Float.parseFloat(value);
            }catch(Exception e){
                result = defaultFloat;
            }
        }
        return result;
    }
    
    /**
     * key에 할당된 Object를 float으로 반환한다.
     * 만약 해당값이 null 일 경우 DEFAULT_NUMBER으로 반환한다.
     *
     * @param data the data
     * @param key the key
     * @return the object
     */
    public static float getFloat(Map<String,Object> data, String key){
        return getFloat(data, key, (float)DEFAULT_NUMBER);
    }

    /**
     * key에 할당된 Object를 long으로 반환한다.
     * 만약 해당값이 null 일 경우 defaultLong으로 반환한다.
     *
     * @param data the data
     * @param key the key
     * @return the object
     */
    public static long getLong(Map<String,Object> data, String key, long defaultLong){
        long result = defaultLong;
        String value = getString(data, key);
        
        if(!value.isEmpty()){
            try{
                result = Long.parseLong(value);
            }catch(Exception e){
                result = defaultLong;
            }
        }
        return result;
    }

    /**
     * key에 할당된 Object를 long으로 반환한다.
     * 만약 해당값이 null 일 경우 DEFAULT_NUMBER으로 반환한다.
     *
     * @param data the data
     * @param key the key
     * @return the object
     */
    public static long getLong(Map<String,Object> data, String key){
        return getLong(data, key, (long)DEFAULT_NUMBER);
    }

    /**
     * key에 할당된 Object를 double으로 반환한다.
     * 만약 해당값이 null 일 경우 defaultDouble으로 반환한다.
     *
     * @param data the data
     * @param key the key
     * @return the object
     */
    public static double getDouble(Map<String,Object> data, String key, double defaultDouble){
        double result = defaultDouble;
        String value = getString(data, key);
        
        if(!value.isEmpty()){
            try{
                result = Double.parseDouble(value);
            }catch(Exception e){
                result = defaultDouble;
            }
        }
        return result;
    }

    /**
     * key에 할당된 Object를 double으로 반환한다.
     * 만약 해당값이 null 일 경우 DEFAULT_NUMBER으로 반환한다.
     *
     * @param data the data
     * @param key the key
     * @return the object
     */
    public static double getDouble(Map<String,Object> data, String key){
        return getDouble(data, key, (double)DEFAULT_NUMBER);
    }
    
    /**
     * key에 할당된 Object를 BigDecimal 반환한다.
     * 만약 해당값이 null 일 경우 defaultBigDecimal 반환한다.
     *
     * @param data the data
     * @param key the key
     * @param defaultBigDecimal the default big decimal
     * @return the big decimal
     */
    public static BigDecimal getBigDecimal(Map<String,Object> data, String key, BigDecimal defaultBigDecimal){
        BigDecimal  result  = defaultBigDecimal;
        Object      value   = get(data, key);
        if(value != null){
            try{
                result = (BigDecimal)value;
            }catch(Exception e){
                result = defaultBigDecimal;
            }
        }
        return result;
    }

    /**
     * key에 할당된 Object를 BigDecimal 반환한다.
     * 만약 해당값이 null 일 경우 DEFAULT_NUMBER으로 반환한다.
     *
     * @param data the data
     * @param key the key
     * @return the big decimal
     */
    public static BigDecimal getBigDecimal(Map<String,Object> data, String key){
        return getBigDecimal(data, key, BigDecimal.valueOf((double)DEFAULT_NUMBER));
    }

    /**
     * key에 할당된 Object를 Map으로 반환한다.
     *
     * @param data the data
     * @param key the key
     * @return the object
     */
    public static Map<String,Object> getMap(Map<String,Object> data, String key) {
        @SuppressWarnings("unchecked")
        Map<String,Object> value = (Map<String,Object>)get(data, key);
        return value;
    }

    /**
     * key에 할당된 Object를 MapUtil으로 감싸서 반환한다.
     *
     * @param data the data
     * @param key the key
     * @return the object
     */
    public static MapUtil getMapUtil(Map<String,Object> data, String key){
        return new MapUtil(getMap(data, key));
    }

    /**
     * key에 할당된 Object를 List< Map < String , Object > >으로 반환한다.
     *
     * @param data the data
     * @param key the key
     * @return the object
     */
    public static List<Map<String,Object>> getList(Map<String,Object> data, String key){
        @SuppressWarnings("unchecked")
        List<Map<String,Object>> value = (List<Map<String,Object>>)get(data, key);
        return value;
    }

    /**
     * key에 할당된 Object를 List < String > 으로 반환한다.
     *
     * @param data the data
     * @param key the key
     * @return the string list
     */
    public static List<String> getStringList(Map<String,Object> data, String key){
        @SuppressWarnings("unchecked")
        List<String> value = (List<String>)get(data, key);
        return value;
    }


    /**
     * Map 본체를 반환
     *
     * @return the map
     */
    public Map<String,Object> map(){
        return data;
    }

    /**
     * Checks if is null.
     *
     * @return true, if is null
     */
    public boolean isNull(){
        return isNull(data);
    }

    /**
     * Checks if is empty.
     *
     * @return true, if is empty
     */
    public boolean isEmpty(){
        return isEmpty(data);
    }

    /**
     * Put.
     *
     * @param key the key
     * @param value the value
     */
    public void put(String key, Object value){
        data.put(key, value);
    }

    /**
     * src의 srcKey 에 할당된 값을 desKey 에 할당한다.
     *
     * @param desKey the des key
     * @param src the src
     * @param srcKey the src key
     */
    public void putBindMap(String desKey, Map<String,Object> src, String srcKey){
        put(desKey, src.get(srcKey));
    }

    /**
     * src의 srcKey 에 할당된 값을 desKey 에 할당한다.
     *
     * @param desKey the des key
     * @param src the src
     * @param srcKey the src key
     */
    public void putBindMap(String desKey, MapUtil src, String srcKey){
        put(desKey, src.get(srcKey));
    }

    /**
     * src의 key 에 할당된 값을 key 에 할당한다.
     *
     * @param key the key
     * @param src the src
     */
    public void putBindMap(String key, Map<String,Object> src){
        putBindMap(key, src, key);
    }

    /**
     * src의 key 에 할당된 값을 key 에 할당한다.
     *
     * @param key the key
     * @param src the src
     */
    public void putBindMap(String key, MapUtil src){
        putBindMap(key, src, key);
    }

    /**
     * src의 모든 값을 key 에 할당한다.
     *
     * @param src the src
     */
    public void putBindMapAll(Map<String,Object> src){
        data.putAll(src);
    }

    /**
     * src의 모든 값을 key 에 할당한다.
     *
     * @param src the src
     */
    public void putBindMapAll(MapUtil src){
        data.putAll(src.map());
    }

    /**
     * src의 모든 값중에 Prime객체(String, int, float, double, long ...)만을 key 에 할당한다.
     *
     * @param src the src
     */
    public void putBindMapAllSingle(Map<String,Object> src){
        for(String key : src.keySet()){
            Object o = src.get(key);
            if(o instanceof Map || o instanceof List){
                continue;
            }

            data.put(key, o);
        }
    }

    /**
     * src의 모든 값중에 Prime객체(String, int, float, double, long ...)만을 key 에 할당한다.
     *
     * @param src the src
     */
    public void putBindMapAllSingle(MapUtil src){
        putBindMapAllSingle(src.map());
    }

    /**
     * src의 keys에 할당된 값을 keys 에 할당한다.
     *
     * @param src the src
     * @param keys the keys
     */
    public void putBindMap(Map<String,Object> src, String... keys){
        for (String key : keys) {
            put(key, src.get(key));
        }
    }

    /**
     * src의 keys에 할당된 값을 keys 에 할당한다.
     *
     * @param src the src
     * @param keys the keys
     */
    public void putBindMap(MapUtil src, String... keys){
        putBindMap(src.map(), keys);
    }

    /**
     * keygroups 를 token 으로 split 하여 src의 keygroups[1] 값을 keygroups[0]에 할당한다.
     * 만약 keygroups 를 token 으로 split 하여 keygroups 갯수가 1일 경우 src의 keygroup[0] 값을 keygroup[0]에 할당한다.
     *
     * @param token the token
     * @param src the src
     * @param keygroups the keygroups
     */
    public void putBindMapFilter(String token, Map<String,Object> src, String... keygroups){
        for (String keygroup : keygroups) {
            String[] keys = keygroup.split(token);
            if(keys.length == 1){
                put(keys[0], src.get(keys[0]));
            } else {
                put(keys[0], src.get(keys[1]));
            }
        }
    }

    /**
     * keygroups 를 token 으로 split 하여 src의 keygroups[1] 값을 keygroups[0]에 할당한다.
     * 만약 keygroups 를 token 으로 split 하여 keygroups 갯수가 1일 경우 src의 keygroup[0] 값을 keygroup[0]에 할당한다.
     *
     * @param token the token
     * @param src the src
     * @param keygroups the keygroups
     */
    public void putBindMapFilter(String token, MapUtil src, String... keygroups){
        putBindMapFilter(token, src.map(), keygroups);
    }
    
    /**
     * HashMap를 생성하여 key에 할당한다.
     *
     * @param key the key
     * @return the map util
     */
    public MapUtil createMap(String key){
        put(key, new HashMap<String,Object>());
        return getMapUtil(key);
    }

    /**
     * key에 할당된 값을 제거한다.
     *
     * @param key the key
     */
    public void remove(String key){
        if(!isEmpty()){
            data.remove(key);
        }
    }

    /**
     * key에 할당된 Object를 반환한다.
     *
     * @param key the key
     * @return the object
     */
    public Object get(String key){
        return get(data, key);
    }

    /**
     * key에 할당된 Object를 String으로 반환한다.
     * 만약 해당값이 null 일 경우 defaultString으로 반환한다.
     *
     * @param key the key
     * @return the object
     */
    public String getString(String key, String defaultString){
        return getString(data, key, defaultString);
    }

    /**
     * key에 할당된 Object를 String으로 반환한다.
     * 만약 해당값이 null 일 경우 DEFAULT_STRING으로 반환한다.
     *
     * @param key the key
     * @return the object
     */
    public String getString(String key){
        return getString(data, key, DEFAULT_STRING);
    }

    /**
     * key에 할당된 Object를 int으로 반환한다.
     * 만약 해당값이 null 일 경우 defaultInt로 반환한다.
     *
     * @param key the key
     * @return the object
     */
    public int getInt(String key, int defaultInt){
        return getInt(data, key, defaultInt);
    }

    /**
     * key에 할당된 Object를 int으로 반환한다.
     * 만약 해당값이 null 일 경우 DEFAULT_NUMBER로 반환한다.
     *
     * @param key the key
     * @return the object
     */
    public int getInt(String key){
        return getInt(data, key, DEFAULT_NUMBER);
    }

    /**
     * key에 할당된 Object를 float으로 반환한다.
     * 만약 해당값이 null 일 경우 defaultFloat으로 반환한다.
     *
     * @param key the key
     * @return the object
     */
    public float getFloat(String key, float defaultFloat){
        return getFloat(data, key, defaultFloat);
    }
    
    /**
     * key에 할당된 Object를 float으로 반환한다.
     * 만약 해당값이 null 일 경우 DEFAULT_NUMBER으로 반환한다.
     *
     * @param key the key
     * @return the object
     */
    public float getFloat(String key){
        return getFloat(data, key, (float)DEFAULT_NUMBER);
    }

    /**
     * key에 할당된 Object를 long으로 반환한다.
     * 만약 해당값이 null 일 경우 defaultLong으로 반환한다.
     *
     * @param key the key
     * @return the object
     */
    public long getLong(String key, long defaultLong){
        return getLong(data, key, defaultLong);
    }

    /**
     * key에 할당된 Object를 long으로 반환한다.
     * 만약 해당값이 null 일 경우 DEFAULT_NUMBER으로 반환한다.
     *
     * @param key the key
     * @return the object
     */
    public long getLong(String key){
        return getLong(data, key, (long)DEFAULT_NUMBER);
    }

    /**
     * key에 할당된 Object를 double으로 반환한다.
     * 만약 해당값이 null 일 경우 defaultDouble으로 반환한다.
     *
     * @param key the key
     * @return the object
     */
    public double getDouble(String key, double defaultDouble){
        return getDouble(data, key, defaultDouble);
    }

    /**
     * key에 할당된 Object를 double으로 반환한다.
     * 만약 해당값이 null 일 경우 DEFAULT_NUMBER으로 반환한다.
     *
     * @param key the key
     * @return the object
     */
    public double getDouble(String key){
        return getDouble(data, key, (double)DEFAULT_NUMBER);
    }
    
    /**
     * key에 할당된 Object를 BigDecimal으로 반환한다.
     * 만약 해당값이 null 일 경우 defaultBigDecimal으로 반환한다.
     *
     * @param key the key
     * @param defaultBigDecimal the default big decimal
     * @return the big decimal
     */
    public BigDecimal getBigDecimal(String key, BigDecimal defaultBigDecimal){
        return getBigDecimal(data, key, defaultBigDecimal);
    }

    /**
     * key에 할당된 Object를 BigDecimal으로 반환한다.
     * 만약 해당값이 null 일 경우 DEFAULT_NUMBER으로 반환한다.
     *
     * @param key the key
     * @return the big decimal
     */
    public BigDecimal getBigDecimal(String key){
        return getBigDecimal(data, key);
    }

    /**
     * key에 할당된 Object를 Map으로 반환한다.
     *
     * @param key the key
     * @return the object
     */
    public Map<String,Object> getMap(String key) {
        return getMap(data, key);
    }

    /**
     * key에 할당된 Object를 MapUtil으로 감싸서 반환한다.
     *
     * @param key the key
     * @return the object
     */
    public MapUtil getMapUtil(String key){
        return new MapUtil(getMap(data, key));
    }

    /**
     * key에 할당된 Object를 Map으로 반환한다.
     * 이때 Object가 null이면 HashMap을 생성하여 할당 후 반환한다.
     *
     * @param key the key
     * @return the map with create
     */
    public Map<String,Object> getMapWithCreate(String key){
        if(isNull()){
            return null;
        }

        Map<String,Object> value = getMap(key);
        if(value == null) {
            value = new HashMap<String,Object>();
            put(key, value);
        }
        return value;
    }

    /**
     * key에 할당된 Object를 MapUtil으로 감싸서 반환한다.
     * 이때 Object가 null이면 HashMap을 생성하여 할당 후 MapUtil으로 감싸서 반환한다.
     *
     * @param key the key
     * @return the map util with create
     */
    public MapUtil getMapUtilWithCreate(String key){
        return new MapUtil(getMapWithCreate(key));
    }

    /**
     * key에 할당된 Object를 List < Map < String, Object > >으로 반환한다.
     *
     * @param key the key
     * @return the object
     */
    public List<Map<String,Object>> getList(String key){
        @SuppressWarnings("unchecked")
        List<Map<String,Object>> value = (List<Map<String,Object>>)get(data, key);
        return value;
    }

    /**
     * key에 할당된 Object를 List < String >으로 반환한다.
     *
     * @param key the key
     * @return the string list
     */
    public List<String> getStringList(String key){
        @SuppressWarnings("unchecked")
        List<String> value = (List<String>)get(data, key);
        return value;
    }

    /**
     * key에 할당된 Object를 List으로 반환한다.
     * 이때 Object가 null이면 ArrayList를 생성하여 할당 후 반환한다.
     *
     * @param key the key
     * @return the list with create
     */
    public List<Map<String, Object>> getListWithCreate(String key) {
        if(isNull()){
            return null;
        }

        List<Map<String,Object>> value = getList(key);
        if(value == null) {
            value = new ArrayList<Map<String,Object>>();
            put(key, value);
        }
        return value;
    }

}
