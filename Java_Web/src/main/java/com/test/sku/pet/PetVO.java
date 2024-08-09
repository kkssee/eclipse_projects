package com.test.sku.pet;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class PetVO {
	int no;
	int price;
	float weight;
	Date birth;
	String name;
	String origin;
	String pic;
	
	public PetVO() { }
	public PetVO(int price, float weight, Date birth, String name, String origin, String pic) {
		this.price = price;
		this.weight = weight;
		this.birth = birth;
		this.name = name;
		this.origin = origin;
		this.pic = pic;
	}
	public PetVO(int no, int price, float weight, String pic) { // constructor for update pet
		this.no = no;
		this.price = price;
		this.weight = weight;
		this.pic = pic;
	}
	
	public int getNo() {
		return no;
	}
	public int getPrice() {
		return price;
	}
	public float getWeight() {
		return weight;
	}
	public Date getBirth() {
		return birth;
	}
	public String getName() {
		return name;
	}
	public String getOrigin() {
		return origin;
	}
	public String getPic() {
		return pic;
	}

	public void setNo(int no) {
		this.no = no;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
}
