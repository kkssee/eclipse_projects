package com.test.sku.list;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class Employee 
{
	
	private int empno;			// employee number
	private String ename; 		// employeename
	private int deptno;			// department number
	private int sal;			// salary
	private LocalDate hiredate;	// hiredate
	
	public Employee() {}
	public Employee(String[] data) {
		int eno = Integer.parseInt(data[0]);		// Integer.parseInt: 숫자형의문자열을 십진수로 변환해주는 것
		String enm = data[1];
		int dpno = Integer.parseInt(data[2]);
		int money = Integer.parseInt(data[3]);
		LocalDate date = dateForm(data[4]);
		
		setEmpno(eno);
		setEname(enm);
		setDeptno(dpno);
		setSal(money);
		setHiredate(date);
	}
	public Employee(int i, String string, int j, int k, String string2) {
		setEmpno(i);
		setEname(string);
		setDeptno(j);
		setSal(k);
		setHiredate(dateForm(string2));
	}
	public Employee(int eNo) {
		setEmpno(eNo);
	}
	public Employee(String enm) {
		
		setEname(enm);
	}
	public Employee(int eNo, int nSal) {
		setEmpno(eNo);
		setSal(nSal);
	}
	@Override
	public String toString() {
		String str = String.format("%d\t%s\t%d\t%d\t\t%s",
				this.getEmpno(),						
				this.getEname(),
				this.getDeptno(),
				this.getSal(),
				this.getHiredate()); 
		return str;
	}
	@Override
	public boolean equals(Object obj) {
		Employee other = (Employee) obj;		// obj형 -> member형으로 형변환(부모 변환->자식형으로 변환)
		
		return this.getEmpno()==other.getEmpno();			// this.getNum()==other.getNum() 의 결과 :boolean을 우리한테 return
	}		
	@Override	// 해쉬코드 노테이션 함 읽기
	public int hashCode() {
		return Objects.hash(this.getEmpno());
	}
	
	private static LocalDate dateForm(String sDate) 
	{
		LocalDate hiredate = null;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            hiredate = LocalDate.parse(sDate, formatter);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
        return hiredate;
	}
	
	public int getEmpno() {
		return empno;
	}
	public String getEname() {
		return ename;
	}
	public int getDeptno() {
		return deptno;
	}
	public int getSal() {
		return sal;
	}
	public LocalDate getHiredate() {
		return hiredate;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public void setSal(int sal) {
		this.sal = sal;
	}
	public void setHiredate(LocalDate hiredate) {
		this.hiredate = hiredate;
	}
	
}
