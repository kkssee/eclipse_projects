package com.test.sku.jdbc;

import java.util.*;

public class PageItem {
	List<EmpVO> list = new ArrayList<>();
	int totalPage;
	int currPage;
	
	public PageItem() { }

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	public List<EmpVO> getList() {
		return list;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setList(List<EmpVO> list) {
		this.list = list;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}
	
	
}
