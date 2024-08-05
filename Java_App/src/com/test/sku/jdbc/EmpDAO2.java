package com.test.sku.jdbc;

import java.sql.*;
import java.text.*;
import java.util.*;

public class EmpDAO2 
{
	/* 1. oracle에 접속하는 기능 필요
	 2. oracle 접속해제 하는 close 기능 필요
	 3. 목록보기 기능, 부서정보, 사원 한 사람 한사람의 상세정보 (Read)
	 4. 사원 정보 추가(Create)
	 5. 사원 정보 수정(Update)
	 6. 사원정보 검색(Find)
	 7. 사원정보 삭제(Delete)
	 */
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	static Scanner kbd = new Scanner(System.in);

	private Connection getConn()
	{
		try
		{
			Class.forName("oracle.jdbc.OracleDriver");
			conn =  DriverManager.getConnection(
		            "jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
			return conn;
		}catch(Exception e)
		{
			e.printStackTrace();
		}return null;
	}
	
	public Map<Integer, String[]> getEmpsByDept() {
		conn = getConn();
		
		String sql = "SELECT deptno, COUNT(empno) \"사원수\", LISTAGG(ename,',') \r\n"
				+ "WITHIN GROUP (ORDER BY deptno) names \r\n"
				+ "FROM emp2 GROUP BY deptno";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			Map<Integer, String[]> map = new HashMap<>();
			while(rs.next()) {
				int deptno = rs.getInt("DEPTNO");
				int cnt = rs.getInt("사원수");
				String names = rs.getString("NAMES");
				map.put(deptno, names.split("\\,"));
			} return map;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public PageItem getPage(int page, int ipp)
	{
		conn = getConn();
		
		String sql2 = "SELECT * FROM\r\n"
				+ "(\r\n"
				+ "    SELECT t2.*, TRUNC((RN-1)/? + 1) AS page FROM\r\n"
				+ "    ( \r\n"
				+ "        SELECT t.*, ROWNUM RN FROM\r\n"
				+ "        (   \r\n"
				+ "            SELECT h.* FROM\r\n"
				+ "            (\r\n"
				+ "                SELECT empno, LPAD('  ', (LEVEL - 1)*3, '    ') || ename \"이름\", sal, deptno, job, hiredate, mgr FROM emp2\r\n"
				+ "                START WITH mgr IS NULL \r\n"
				+ "                CONNECT BY PRIOR empno = mgr  \r\n"
				+ "                ORDER SIBLINGS BY ename\r\n"
				+ "            )h\r\n"
				+ "        )t\r\n"
				+ "    )t2\r\n"
				+ ") CROSS JOIN (SELECT CEIL(COUNT(*)/?) total FROM emp2) WHERE page = ?";
		
		try 
		{
			pstmt = conn.prepareStatement(sql2);
			
			pstmt.setInt(1, ipp);
			pstmt.setInt(2, ipp);
			pstmt.setInt(3, page);
			rs = pstmt.executeQuery();
			List<EmpVO> list = new ArrayList<>();
			PageItem pi = new PageItem();
			int i = 0;
			while(rs.next())
			{
				if(i == 0) {
					int currPage = rs.getInt("PAGE");
					int totalPage = rs.getInt("TOTAL");
					pi.setCurrPage(currPage);
					pi.setTotalPage(totalPage);
				}
				
				int empno = rs.getInt("EMPNO");
		    	String ename = rs.getString("이름");
		    	java.sql.Date hiredate = rs.getDate("HIREDATE");
		    	int salary = rs.getInt("SAL");
		    	int deptno = rs.getInt("DEPTNO");
		    	String job = rs.getString("JOB");
		    	int mgr = rs.getInt("MGR");
		           
		    	EmpVO emp = new EmpVO();
		    	emp.setEmpno(empno);
		    	emp.setEname(ename);
		    	emp.setHiredate(hiredate);
		    	emp.setSal(salary);
		    	emp.setDeptno(deptno);
		    	emp.setJob(job);
		    	emp.setMgr(mgr);
		    	list.add(emp);
		    	i++;
			}
			//System.out.printf("현재 페이지수/총페이지수 : ", );
			pi.setList(list);
			return pi;
		} catch (SQLException e) {
			e.printStackTrace();
		} return null;
	}
	
	public List<EmpVO> getList()	//empno, ename, sal, deptno, job, hiredate, mgr, comm
	{	
		// 무조건 맨 처음엔 DB에 연결해야 함
		conn = getConn();
		try
		{
			pstmt = conn.prepareStatement("SELECT * FROM emp2 ORDER BY empno");		
			//prepareStatement() : com이 알아서 sql 문장을 만들어줌, 그래서 파라미터로 sql문장을 주게 되어있음 
			
			rs = pstmt.executeQuery();			// executeQuery : 결과를 줘라!
			List<EmpVO> list = new ArrayList<>();
			while(rs.next())
	        {
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
	        }
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return null;
	}	
	
	public boolean updatesal(EmpVO emp)		//EmpVO 안에 새 updatesal이 들어갑
	{// 1. DB연결
		conn = getConn();
		try 
		{
			String sql = "UPDATE emp2 SET sal = ? WHERE empno= ?" ;
			//prepareStatement() : com이 알아서 sql 문장을 만들어줌, 그래서 파라미터로 sql문장을 주게 되어있음 
			// sql문장의 서식을 주고 prepareStatement()에게 만들라고 시킴_ ? 를 주고 ? 자리에 실제값이 들어가게끔 만드는 것_
			// 그 값을 누가?  pstmt가 그 작업을 한다_ ?를 인식해서 실제 값을 주고 완성된 값을 sql에 전달
			System.out.println("수정할 사원의 번호를 입력하세요: ");
			int uEmpno = kbd.nextInt();
			kbd.nextLine().trim();
			System.out.println("수정할 급여액을 입력하세요 : ");
			int uSal = kbd.nextInt();
			kbd.nextLine().trim();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uSal);
			pstmt.setInt(2, uEmpno);

			int rows = pstmt.executeUpdate();		// 여기에 값이 들어가지 않도록 주의!	
			return rows> 0;							
				
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		//close all 필요
		return false;
	}
	
	public boolean add(EmpVO emp)
	{//empno, ename, job, mgr, hiredate, sal
		conn = getConn();
		
		try {
			String sql = "INSERT INTO emp2(empno, ename, deptno, sal, hiredate, job, mgr) VALUES (?,?,?,?,?,?,?)" ;
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, emp.getEmpno());
			pstmt.setString(2, emp.getEname());
			pstmt.setInt(3, emp.getDeptno());
			pstmt.setInt(4, emp.getSal());
			pstmt.setDate(5, emp.getHiredate());	// 현재 날짜를 쓰고 싶으면 이 자리에 SYSDATE 사용
			pstmt.setString(6, emp.getJob());
			pstmt.setInt(7, emp.getMgr());

			 
			int rows = pstmt.executeUpdate();
			return rows>0;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally
		{
			closeAll();
		}
		
		return false;
		
	}
	
	public List<EmpVO> findByEmpno()
	{
		conn = getConn();
		
		try
		{
			String sql = "SELECT * FROM emp2 WHERE empno = ? " ;
			pstmt = conn.prepareStatement(sql);
			
			System.out.println("검색할 사원의 번호를 입력하세요: ");
			int sEmpno = kbd.nextInt();
			kbd.nextLine().trim();
			
			
			pstmt.setInt(1, sEmpno);
			
			rs = pstmt.executeQuery();
			List<EmpVO> list = new ArrayList<>();
				while(rs.next())
		        {
		           int empno = rs.getInt("EMPNO");
		           String ename = rs.getString("ENAME");
		           java.sql.Date hiredate = rs.getDate("HIREDATE");
		           int deptno = rs.getInt("DEPTNO");
		           int salary = rs.getInt("SAL");
		           String job = rs.getNString("JOB");
		           
		           EmpVO findbyempno = new EmpVO();
		           findbyempno.setEmpno(empno);
		           findbyempno.setEname(ename);
		           findbyempno.setHiredate(hiredate);
		           findbyempno.setDeptno(deptno);
		           findbyempno.setSal(salary);
		           findbyempno.setJob(job);
		           list.add(findbyempno);
		        }
				return list;
		} catch (SQLException e) {
			
			e.printStackTrace();
		} return null;
	}

	public List<EmpVO> findByDeptno()
	{
		conn = getConn();
		
		try
		{
			String sql = "SELECT * FROM emp2 WHERE deptno = ? " ;
			pstmt = conn.prepareStatement(sql);
			
			System.out.println("검색할 사원의 부서번호를 입력하세요: ");
			int sDeptno = kbd.nextInt();
			kbd.nextLine().trim();
			pstmt.setInt(1, sDeptno);
			
			rs = pstmt.executeQuery();
			List<EmpVO> list = new ArrayList<>();
				while(rs.next())
		        {
		           int empno = rs.getInt("EMPNO");
		           String ename = rs.getString("ENAME");
		           java.sql.Date hiredate = rs.getDate("HIREDATE");
		           int deptno = rs.getInt("DEPTNO");
		           int salary = rs.getInt("SAL");
		           String job = rs.getNString("JOB");
		           
		           EmpVO findbyempno = new EmpVO();
		           findbyempno.setEmpno(empno);
		           findbyempno.setEname(ename);
		           findbyempno.setHiredate(hiredate);
		           findbyempno.setDeptno(deptno);
		           findbyempno.setSal(salary);
		           findbyempno.setJob(job);
		           list.add(findbyempno);
		        }
				return list;
		} catch (SQLException e) {
			
			e.printStackTrace();
		} return null;
	}
	
	public List<EmpVO> findByJob(String job)	
	{
		conn = getConn();
	
		try
		{
			String sql = "SELECT * FROM emp2 WHERE job = ?" ;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,job);
			
			rs = pstmt.executeQuery();					
			List<EmpVO> list = new ArrayList<>();
			while(rs.next())
	        {
	           int empno = rs.getInt("EMPNO");
	           String ename = rs.getString("ENAME");
	           java.sql.Date hiredate = rs.getDate("HIREDATE");
	           int deptno = rs.getInt("DEPTNO");
	           int salary = rs.getInt("SAL");
	           String jobs = rs.getNString("JOB");
	           
	           EmpVO findbyjob = new EmpVO();
	           findbyjob.setEmpno(empno);
	           findbyjob.setEname(ename);
	           findbyjob.setHiredate(hiredate);			
	           findbyjob.setDeptno(deptno);
	           findbyjob.setSal(salary);
	           findbyjob.setJob(jobs);
	           list.add(findbyjob);
	        }
			
			 return list;
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	
	public boolean delete()
	{
		conn = getConn();
		try 
		{
			String sql = "DELETE FROM emp2 WHERE empno = ?" ;	
			pstmt = conn.prepareStatement(sql);
			
			System.out.println("삭제할 사원의 번호를 입력하세요: ");
			int dEmpno = kbd.nextInt();
			kbd.nextLine().trim();
			pstmt.setInt(1, dEmpno);
			
			int rows = pstmt.executeUpdate();		//★ executeUpdate : 얘는 결과를 줄 것이 X -> int로 리턴_행의수
			return rows> 0;					
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return false;
	}
	
	//1인당 1개의 map을 만들어서 list에 담아라
	public List<Map<String, String>> getJoinResult(int deptno)
	{
		String sql = "SELECT e.empno, e.ename, e.deptno, sal, d.dname, s.grade \"호봉수\"\r\n"
	               + "FROM emp2 e JOIN dept d ON e.deptno = d.deptno\r\n"
	               + "JOIN salgrade s ON e.sal BETWEEN s.losal AND s.hisal\r\n"
	               + "WHERE e.deptno = ?"
	               + "ORDER BY s.grade DESC";
		
		//	\"호봉수\" _ \" = escape 문자열 :  본래 갖고 있는 기능을 escape시키고, 문자열 취급
		
		// DB 접속
		conn = getConn();
		
		try 
		{
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, deptno);
			rs = pstmt.executeQuery();	//select문장 이니까 
			// arrayList와 map 만들어주기
			List<Map<String, String>> list = new ArrayList<>();
			Map<String, String> map = null;			//map은 변수만 선언
			// key, value 있으니 컬럼명, 값 넣기 좋아서 map 사용
			
			while(rs.next())
			{	
				map  = new HashMap<>();
				int empno = rs.getInt("EMPNO");
				String ename = rs.getString("ENAME");
				int dno = rs.getInt("DEPTNO");
				int sal = rs.getInt("SAL");
				String dname = rs.getString("DNAME");
				int grade = rs.getInt("호봉수");			// 아까 한글로 써놔서 "호봉수"로 받아옴
				
				map.put("EMPNO","" + empno);
				map.put("ENAME", ename);
				map.put("DEPTNO","" + dno);
				map.put("SAL","" + sal);
				map.put("DNAME",dname);
				map.put("호봉수","" + grade);
				list.add(map);
				
			}
			return list;
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	private void closeAll()
	{
		try {
			if(rs != null)		rs.close();
			if(conn!= null)		conn.close();
			if(pstmt != null)	pstmt.close();

		}catch(Exception e)
		{
			e.printStackTrace();
		}

	}




}