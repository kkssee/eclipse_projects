package com.test.sku.jdbc;

import java.sql.*;
import java.text.*;

public class EmpVO {
	private int empno;
	private String ename;
	private int sal;
	private int deptno;
	private String job;
	private java.sql.Date hiredate;
	private int mgr;
	private float comm;
	
	public EmpVO() {}
	public EmpVO(int i) {
		this.empno = i;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sDate = sdf.format(hiredate);
		String s = String.format("%d\t%s\t%s\t%d\t%s\t%d", 
				empno,ename,sal,deptno,job,sDate);
		return s;
	}
	
	@Override
	public boolean equals(Object obj) {
		EmpVO other = (EmpVO) obj;
		return this.getEmpno()==other.getEmpno();
	}

	public int getEmpno() {
		return empno;
	}
	public String getEname() {
		return ename;
	}
	public int getSal() {
		return sal;
	}
	public int getDeptno() {
		return deptno;
	}
	public String getJob() {
		return job;
	}
	public java.sql.Date getHiredate() {
		return hiredate;
	}
	public int getMgr() {
		return mgr;
	}
	public float getComm() {
		return comm;
	}
	
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public void setSal(int sal) {
		this.sal = sal;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	public void setMgr(int mgr) {
		this.mgr = mgr;
	}
	public void setComm(float comm) {
		this.comm = comm;
	}
	
}
