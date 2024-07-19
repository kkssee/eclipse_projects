package com.test.sku.pet;

import java.text.DecimalFormat;
import java.io.Serializable;

public class PetVO implements Comparable<PetVO>, Serializable
{
	int no;
	String species;
	float weight;
	int price;
	
	public PetVO() {}
	public PetVO(String line) 
	{
		String[] s = line.split("\\|");
		setNo(Integer.parseInt(s[0]));
		setSpecies(s[1]);
		setWeight(Float.parseFloat(s[2]));
		setPrice(Integer.parseInt(s[3]));
	}
	public PetVO(int n) {
		setNo(n);
	}
	
	public PetVO(int i, String string, float d, int j) {
		setNo(i);
		setSpecies(string);
		setWeight(d);
		setPrice(j);
	}
	
	@Override
	public boolean equals(Object obj)
 {
		PetVO other = (PetVO) obj;
		return this.getNo()==other.getNo();
	}
	@Override
	public String toString() 
	{
		DecimalFormat nf = new DecimalFormat("#,###");
		DecimalFormat df = new DecimalFormat("#.##");
		String s = String.format("%-5d%-15s%-12s%s",
				no, species, df.format(weight), nf.format(price));
		return s;
	}
	@Override
	public int compareTo(PetVO other) 
	{
		// 왼쪽의 객체가 크면 양수, 동일하면 0, 작으면 음수
		if(this.getNo() > other.getNo()) return 1;
		else if(this.getNo() < other.getNo()) return -1;
		else return 0;
	}
	
	public int getNo() {
		return no;
	}
	public String getSpecies() {
		return species;
	}
	public float getWeight() {
		return weight;
	}
	public int getPrice() {
		return price;
	}
	
	public void setNo(int no) {
		this.no = no;
	}
	public void setSpecies(String species) {
		this.species = species;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
