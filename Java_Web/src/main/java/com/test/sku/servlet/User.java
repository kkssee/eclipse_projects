package com.test.sku.servlet;

public class User {
	private String uid;
	private String pwd;
	
	public User() { }
	public User(String uid, String pwd) {
		this.uid = uid;
		this.pwd = pwd;
	}
	
	
	
	@Override
	public String toString() {
		
		return super.toString();
	}
	public String getUid() {
		return uid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	
}
