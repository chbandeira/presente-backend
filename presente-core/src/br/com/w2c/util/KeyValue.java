package br.com.w2c.util;

import java.io.Serializable;

/**
 * 
 * @author charlles
 * @since 03/10/2013
 */
public class KeyValue implements Serializable {

	private static final long serialVersionUID = -4793192334018871154L;

	private String key;
	
	private String value;
	
	public KeyValue() {}
	
	public KeyValue(Long key, String value) {
		this.key = String.valueOf(key);
		this.value = value;
	}

	public KeyValue(String key, Long value) {
		this.key = key;
		this.value = String.valueOf(value);
	}
	
	public KeyValue(Long key, Long value) {
		this.key = String.valueOf(key);
		this.value =  String.valueOf(value);
	}
	
	public KeyValue(String key, String value) {
		this.key = key;
		this.value =  value;
	}
	
	public String getKey() {
		return key;
	}
	
	public Long getKeyLong() {
		return Long.valueOf(key);
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	public void setKey(Long key) {
		this.key = String.valueOf(key);
	}

	public String getValue() {
		return value;
	}
	
	public Long getValueLong() {
		return Long.valueOf(value);
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public void setValue(Long value) {
		this.value = String.valueOf(value);
	}

	@Override
	public String toString() {
		return "KeyValueTO [key=" + key + ", value=" + value + "]";
	}
}