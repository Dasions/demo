package redisdemo.bean;

import java.io.Serializable;

public class RedisDemoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String id;
	String value;
	public String getId() {
		return id;
	}
	public void setId(String i) {
		this.id = i;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
