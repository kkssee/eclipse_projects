package com.test.sku.jdbc;

import java.sql.*;
import java.util.*;

public class EmpDAO {
	// 오라클 접속기능, 접속해제 기능
	// 목록보기, 부서정보, 사원상세: Read
	// 사원정보 추가, 수정, 삭제: Create, Update, Delete
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private static Scanner kbd = new Scanner(System.in);
	
	private Connection getConn() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
	        conn = DriverManager.getConnection(
	                  "jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
	        return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<EmpVO> getList() {	// empno, ename, sal, deptno, job, hiredate, mgr, conn
		conn = getConn();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM emp2 ORDER BY empno");
			List<EmpVO> list = new ArrayList<>();
	        
	        while(rs.next()) {
	           int empno = rs.getInt("EMPNO");
	           String ename = rs.getString("ENAME");
	           java.sql.Date hiredate = rs.getDate("HIREDATE");
	           int salary = rs.getInt("SAL");
	           int deptno = rs.getInt("DEPTNO");
	           String job = rs.getString("JOB");
	           
	           EmpVO emp = new EmpVO();
	           emp.setEmpno(empno);
	           emp.setEname(ename);
	           emp.setHiredate(hiredate);
	           emp.setSal(salary);
	           emp.setDeptno(deptno);
	           emp.setJob(job);
	           list.add(emp);
	        } return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		} return null;
	}
	
	public boolean updateSal(EmpVO emp) {
		conn = getConn();
		try {
			stmt = conn.createStatement();
			String sql = "UPDATE emp2 SET sal = " + emp.getSal() + " WHERE empno = " + emp.getEmpno();
			int rows = stmt.executeUpdate(sql);
			return rows>0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		} return false;
	}
	
	// 사원정보 추가(add()), 사번으로 검색(findByEmpno()),
	// 부서번호로 검색(findByDeptno()), 직무명칭으로 검색(findByJob()),
	// 사번으로 삭제(deleteByEmpno())
	
	public boolean add() {
		conn = getConn();
		
		try {
			stmt = conn.createStatement();
			
			System.out.println("사번 이름 직업 매니저 고용일자 급여: ");
	    	 String input = kbd.nextLine();
	    	 String[] token = input.split("\\s+");
	    	 
	    	 String sql = String.format("INSERT INTO emp2 "
	    	 		+ "(empno, ename, job, mgr, hiredate, sal) VALUES( %d, '%s', '%s', %d, '%s', %d)",
	    			 Integer.parseInt(token[0]), token[1], token[2], 
	    			 Integer.parseInt(token[3]), token[4], Integer.parseInt(token[5]));
	    	 
			int rows = stmt.executeUpdate(sql);
			return rows>0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		} return false;
	}
	
	public void findByEmpno() {
		conn = getConn();
		try {
			stmt = conn.createStatement();
			System.out.println("사번으로 검색: ");
			int empno = kbd.nextInt(); kbd.nextLine();
			String sql = String.format("SELECT * FROM emp2 WHERE empno = '%d'", empno);
			rs = stmt.executeQuery(sql);
			
            String ename = rs.getString("ENAME");
            java.sql.Date hiredate = rs.getDate("HIREDATE");
            float salary = rs.getFloat("SAL");
            
            System.out.printf("%d\t%s\t%s\t%f \n", empno,ename,hiredate,salary);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
	}
	
	private void closeAll() {
		try {
			if(rs != null)		rs.close();
			if(stmt != null)	stmt.close();
			if(conn != null)	conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
