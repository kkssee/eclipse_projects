package com.test.sku.inheritance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.util.Date;

public class Item {
	String name;
	String made;
	int price;
	Date pDate;
	
	public Item() {	}
	public Item(String name, String made, int price, Date pDate) {
		this.name = name;
		this.made = made;
		this.price = price;
		this.pDate = pDate;
	}
	public Item(String name, String made, int price, String sDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date pDate = null;
		try {
			pDate = sdf.parse(sDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.name = name;
		this.made = made;
		this.price = price;
		this.pDate = pDate;
	}
	

	@Override
	public String toString() {
		DecimalFormat nf = new DecimalFormat("#,###");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sDate = sdf.format(pDate);
		return String.format("%s\t%s\t%s\t%s", name, made, nf.format(price), sDate);
	}


	public String getName() {
		return name;
	}
	public String getMade() {
		return made;
	}
	public int getPrice() {
		return price;
	}
	public Date getpDate() {
		return pDate;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void setMade(String made) {
		this.made = made;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public void setpDate(Date pDate) {
		this.pDate = pDate;
	}
	public void setpDate(String sDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date p = null;
		try {
			p = sdf.parse(sDate);
			setpDate(p);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
}
