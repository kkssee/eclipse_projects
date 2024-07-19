package com.test.sku.err;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Users
{
	String uid;
	String pwd;

	public Users(String line) {
		String[] arr = line.split("\\:");
		
		setUid(arr[0]);
		setPwd(arr[1]);
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}
