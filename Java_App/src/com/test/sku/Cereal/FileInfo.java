package com.test.sku.Cereal;

import java.io.*;
import java.time.LocalDate;

public class FileInfo implements Serializable{
	int fno;
	int fsize;
	String fname;
	String fdesc;
	String fauth;
	LocalDate fdate;
	byte[] fdata;
	
	public FileInfo() { }
	public FileInfo(String fname, byte[] fdata, String fauth, String fdesc) {
		this.fname = fname;
		this.fdata = fdata;
		this.fauth = fauth;
		this.fdesc = fdesc;
	}
	
	@Override
	public String toString() {
		String s = String.format("%s\t%s\t%s\t%s", fname, fdesc, fauth, fdate);
		return s;
	}
}
