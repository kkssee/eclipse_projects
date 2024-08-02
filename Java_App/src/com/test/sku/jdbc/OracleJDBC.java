package com.test.sku.jdbc;

import java.text.*;
import java.util.*;

public class OracleJDBC 
{
	static enum MENU { ADD, PAGE, FIND, UPDATE, DELETE, JOIN, EXIT };
	
	static Scanner kbd = new Scanner(System.in);
	static EmpDAO2 dao = new EmpDAO2();
	static EmpVO emp = new EmpVO();
	
   public static void main(String[] args) 
   {
     boolean go = true;
     while(go)
     {	
	     switch(showMenu()) {
		     case ADD: add(); break;
		     //case PAGE: getlist(); break;
		     case PAGE: getPage(); break;
		     case FIND: findByEmpno(); break;
		     //case "e": findByDeptno(); break;
		     case JOIN: getJoinResult(); break;
		     case UPDATE: update(); break;
		     case DELETE: delete(); break;
		     case EXIT: System.out.println("프로그램 종료"); break;
		     default: System.err.println("Wrong input"); break;
	     }    
     } 	 
     
  }
   
   private static MENU showMenu() {
	   System.out.println();
	   System.out.println("추가(a) 목록(s) 페이지별 목록(p) 사원번호로 검색(f)"
	  	 					+ " 부서번호로 검색(e) join결과(j) 수정(u) 삭제(d) 종료(x): ");
	   String m = kbd.nextLine().trim();
	   MENU menu = null;
	   switch(m) {
	   case "a": menu = MENU.ADD; break;
	   //case "a": menu = MENU.ADD; break;
	   case "p": menu = MENU.PAGE; break;
	   case "f": menu = MENU.FIND; break;
	   case "u": menu = MENU.UPDATE; break;
	   case "d": menu = MENU.DELETE; break;
	   case "j": menu = MENU.JOIN; break;
	   case "x": menu = MENU.EXIT; break;
	   }
	   return menu;
   }

   private static void getPage()
   {   	
	   	System.out.print("페이지 번호를 입력하세요: ");
   		int page = kbd.nextInt(); kbd.nextLine();
   		System.out.print("페이지 번호를 입력하세요: ");
   		int ipp = kbd.nextInt(); kbd.nextLine();
   		
	   PageItem pi = dao.getPage(page, ipp);
	   List<EmpVO> list = pi.getList();
	   
  	   System.out.println("사원번호\t사원이름\t급여\t부서번호\t직무\t\t고용일");
       for(EmpVO pagination : list)
       {
      	 System.out.println(pagination);
       }
       System.out.printf("현재 페이지: " + pi.currPage + " / 총 페이지: " + pi.totalPage +"\n");
   }
   
   private static void getJoinResult()
   {
	   System.out.println("부서 번호를 입력하세요: ");
	   int deptno = kbd.nextInt();
       kbd.nextLine();
       
	   List<Map<String, String>> result = dao.getJoinResult(deptno);
	 /* 이건 map(python의 딕셔너리 형태로 나옴 - 이건 web에서 사용할 수 없어서 아래처럼 써야 함)
	   for(Map<String, String> res : result)
	   {
		   System.out.println(res);
	   }
	  */
	   
	   System.out.println("사원번호\t사원이름\t부서번호\t급여\t직무\t\t호봉수");
	   for(int i=0; i<result.size(); i++)
	   {
		   Map<String, String> map = result.get(i);
		   String sEmpno = map.get("EMPNO");
		   String ename = map.get("ENAME");
		   String sDeptno = map.get("DEPTNO");
		   String sSal = map.get("SAL");
		   String sDname = map.get("DNAME");
		   String sGrade = map.get("호봉수");			// 아까 한글로 써놔서 "호봉수"로 받아옴
		   
		   
		   String line = String.format("%s\t%s\t%s\t%s\t%s\t%s", sEmpno, ename, sDeptno, sSal, sDname, sGrade);
		   System.out.println(line);
	   }
	
   }
   
   private static void getlist()
   {
	   List<EmpVO> list = dao.getList();
  	   System.out.println("사원번호\t사원이름\t급여\t부서번호\t직무\t\t고용일");
       for(EmpVO searchemp : list)
       {
      	 System.out.println(searchemp);
      
       }
   }
   
   private static void add()
   {
	                
       System.out.println("사원번호를 입력하세요: ");
       int empno = kbd.nextInt();
       kbd.nextLine();
       
       System.out.println("사원의 이름을 입력하세요 : ");
       String ename = kbd.nextLine().trim();
       
       System.out.println("부서 번호를 입력하세요: ");
       int deptno = kbd.nextInt();
       kbd.nextLine();
       
       System.out.println("급여를 입력하세요: ");
       int sal = kbd.nextInt();
       kbd.nextLine();
       
       System.out.println("입사일을 입력하세요: ");
       String hdate = kbd.nextLine().trim();
       
       System.out.println("직무를 입력하세요: ");
       String job = kbd.nextLine().trim();
       
       System.out.println("상급자를 입력하세요: ");
       int mgr = kbd.nextInt(); kbd.nextLine();
       
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       java.sql.Date hiredate = null;
		try {
			   hiredate = new java.sql.Date(sdf.parse(hdate).getTime());
		} catch (ParseException e) {
			
			e.printStackTrace();
		}		
       emp.setEmpno(empno);
       emp.setEname(ename);
       emp.setDeptno(deptno);
       emp.setSal(sal);
       emp.setHiredate(hiredate);
       emp.setJob(job);
       emp.setMgr(mgr);
       
       boolean added = dao.add(emp);
       System.out.println(emp);
  	   System.out.println("추가 결과 : " + added);
   }
   
   private static void findByEmpno()
   {
	   List<EmpVO> findbyempno = dao.findByEmpno();
	    System.out.println("사원번호\t사원이름\t급여\t부서번호\t직무\t\t고용일");
	    for(EmpVO emp2 : findbyempno)
	    {	
	    	System.out.println(emp2);
	    }
   }
   
   private static void findByDeptno()
   {
	   List<EmpVO> findbydeptno = dao.findByDeptno();
	    System.out.println("사원번호\t사원이름\t급여\t부서번호\t직무\t\t고용일");
	    for(EmpVO emp2 : findbydeptno)
	    {	
	    	System.out.println(emp2);
	    }
   }
   
   private static void update()
   {

  	 boolean updated =  dao.updatesal(emp);
  	 System.out.println("수정결과" + updated);
   }
   
   private static void delete()
   {
  	 boolean deleted = dao.delete();
       System.out.println("사원정보 삭제 결과: " + deleted);
   }
}
