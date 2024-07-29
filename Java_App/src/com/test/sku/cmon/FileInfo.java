package com.test.sku.cmon;

import java.io.Serializable;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.test.sku.pet.PetVO;

public class FileInfo implements Serializable,Comparable<FileInfo>  {
	
	int num;
	String fname;
	String author;
	Date date;
	String desc;
	long flength;
	
	
	
	public FileInfo() {
		
	}
	
	public FileInfo(int num) {
		super();
		this.num = num;
	}


	public FileInfo(int num, String fname, String author,long flength, Date date, String desc) {
		super();
		this.num = num;
		this.fname = fname;
		this.author = author;
		this.date = date;
		this.desc = desc;
		this.flength = flength;
		
	}
		
	@Override
	public boolean equals(Object obj) {
		FileInfo other = (FileInfo) obj;
		return other.getNum() == this.getNum();
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sdate = sdf.format(date);
		String s = String.format("\n%d\t%-15s\t%s\t%d\t%s\t%s", num, fname, author, flength, sdate, desc);	
		return s;
	}

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getauthor() {
		return author;
	}
	public void setauthor(String author) {
		this.author = author;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getdesc() {
		return desc;
	}
	public void setdesc(String desc) {
		this.desc = desc;
	}

	public long getFlength() {
		return flength;
	}

	public void setFlength(long flength) {
		this.flength = flength;
	}

	@Override
	public int compareTo(FileInfo other) {		
		if(this.getNum()>other.getNum())	return 1;
		else if(this.getNum()==other.getNum()) return 0;
		else return -1;
		 
		
	}

	
}
