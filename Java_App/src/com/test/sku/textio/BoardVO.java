package com.test.sku.textio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BoardVO 
{
	private int no;
	private String title;
	private String author;
	private Date regDate; 
	private int hits;
	private String contents;
	
	public BoardVO() {}
	public BoardVO(String line) 
	{
		String[] arr = line.split("\\|");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		setNo(Integer.parseInt(arr[0]));
		setTitle(arr[1]);
		setAuthor(arr[2]);
		try {
			setRegDate(sdf.parse(arr[3]));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		setHits(Integer.parseInt(arr[4]));
		setContents(arr[5]);
	}
	public BoardVO(int f) 
	{
		setNo(f);
	}
	public BoardVO(String title, String contents) 
	{
		setNo(Integer.parseInt(title));
		setTitle(contents);
	}
	public BoardVO(String[] s) 
	{
		setNo(Integer.parseInt(s[0]));
		setTitle(s[1]);
	}
	
	@Override
	public boolean equals(Object obj) 
	{
		BoardVO other = (BoardVO) obj;
		return this.getNo()==other.getNo();
	}
	@Override
	public String toString() 
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sDate = sdf.format(regDate);
		String s = String.format("%s  %-15s\t%s\t%s\t%d", 
				no, title, author, sDate, hits);
		return s;
	}
	
	public int getNo() {
		return no;
	}
	public String getTitle() {
		return title;
	}
	public String getAuthor() {
		return author;
	}
	public Date getRegDate() {
		return regDate;
	}
	public int getHits() {
		return hits;
	}
	public String getContents() {
		return contents;
	}
	
	public void setNo(int no) {
		this.no = no;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
}
