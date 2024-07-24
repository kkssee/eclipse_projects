package com.test.sku.serialization;

import java.io.Serializable;

public class FileVO implements Serializable {
	String menu;
	
	String fname;
	byte[] fdata;
	
	public FileVO() { }
	public FileVO(String fname, byte[] fdata) {
		this.fname = fname;
		this.fdata = fdata;
	}
	public FileVO(String menu) {
		this.menu = menu;
	}
	

}
