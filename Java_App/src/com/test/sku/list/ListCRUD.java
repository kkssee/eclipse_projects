package com.test.sku.list;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class ListCRUD 
{
	// List Collection API를 사용한 CRUD 실습
	// 사원정보 관리 시스템
	
	static Scanner kbd = new Scanner(System.in);
	static List<Employee> empee = new ArrayList<>();	// 컬렉션 API 사용

	public static void main(String[] args) {
		// 사원: Employee(데이터 모델)
		// empno, ename, deptno, sal, hiredate 
		// 추가(a), 목록(s), 검색(f), 수정(u), 삭제(d), 종료(x):
		// private, constructor, getter, setter, Override, Overload
		// 기능구현은 현재 클래스에 클래스 메소드 선언으로 해결
		
		initEmployee();
		menu();
		
	}
	
	private static void initEmployee() 
	{
		empee.add(new Employee(5112, "Jane Doe", 10, 3500000, "2024-07-10"));
		empee.add(new Employee(7369, "Charlotte Quinn", 7, 5400000, "2017-09-21"));
		empee.add(new Employee(8427, "Chris Evans", 4, 2000000, "2022-11-06"));
	}
	
	private static void menu() {
		while(true)
		{
			System.out.print("추가(a), 목록(s), 검색(f), 수정(u), 삭제(d), 종료(x): ");
			String menu = kbd.nextLine();
			
			switch(menu)
			{
				case "a": add();	break;
				case "s": list();	break;
				case "f": find();	break;
				case "u": update();	break;
				case "d": delete();	break;
				case "x": System.out.print("프로그램 종료\n"); kbd.close(); return;
				default: System.err.print("잘못된 접근입니다.\n\n");
			}
		}
	}
	private static void add() 
	{
		while(true)
		{
			System.out.print("사원번호, 이름, 부서번호, 월급, 입사날짜 입력: ");
		
			String input = kbd.nextLine().trim();
			if(!input.equals(""))	
			{
				String[] sInput = input.split("\\s+");
				if(sInput.length == 5)
				{
					String[] data = input.split("\\s+");
					Employee e = new Employee(data);		
					
		 			//mems[cnt++] = m;
					empee.add(e);
					//return;
				}
				else System.out.println("올바른 입력이 아닙니다.");  break;
				
			}
			else
			{
				System.out.println("입력종료");
				return;
			}
		}
	}
	private static void list() 
	{
		System.out.println("\t*** 회원정보 ***");
		System.out.println("번호\t이름\t\t부서번호\t월급\t\t입사날짜");
		for(int i=0; i<empee.size(); i++)
		{	
			System.out.println(empee.get(i));
		}
	}
	private static void find() 
	{
		System.out.print("번호검색(n), 이름검색(m): ");
		String wf = kbd.nextLine();	
		switch(wf)
		{
			case "n":
			{
				/*System.out.print("사원번호 검색: ");
				int eNo = kbd.nextInt(); kbd.nextLine();
				Employee key = new Employee(eNo);
				if(empee.contains(key))			//key에서 회원번호로 비교해하게 만들어놓음
				{		
					System.out.println("번호\t이름\t\t부서번호\t월급\t\t입사날짜");
					int idx = empee.indexOf(key);
					//Employee found = empee.get(idx);	// get()에 idx 번호 줘서 object 가져오는 것
					System.out.println(empee.get(idx));			// 가져온 object를 화면에 출력하라
				}
				else System.out.println("존재하지 않는 번호입니다.");*/
				searchByNo();
				break;
			}
			case "m":
			{
				System.out.print("사원이름 검색: ");
				String enm = kbd.nextLine(); 
				String name[];
				Employee key = new Employee(enm);
				
				for(int i = 0; i< empee.size(); i++)
				{
					if(enm.equals(empee.get(i).getEname())) {
						System.out.println("번호\t이름\t\t부서번호\t월급\t\t입사날짜");
						System.out.println(empee.get(i));	
					}
				}
				
				break;
			}
			default: System.err.print("잘못된 접근입니다.\n\n");
		}
	}
	private static void update() 
	{
		Employee key = searchByNo();
		
		try {
			empee.get(empee.indexOf(key)).setSal(key.getSal());
		} catch(Exception e) {
			System.err.println("변경 실패");
		}
		/*System.out.print("변경대상 사원번호: ");
		int eNo = kbd.nextInt(); kbd.nextLine();
		
		System.out.print("변경 후 월급: ");
		int nSal = kbd.nextInt();
		
		Employee key = new Employee(eNo, nSal);
		
		if(empee.contains(key))
		{
			int idx = empee.indexOf(key);
			empee.get(idx).setSal(key.getSal());
			System.out.println("변경 성공");
		} else {
			System.err.println("변경 실패");
		}*/
		
	}
	private static void delete() 
	{
		/*System.out.print("삭제할 사원번호 입력: ");
		int eNo = kbd.nextInt();
		kbd.nextLine();
		Employee key = new Employee(eNo);*/
		
		Employee key = searchByNo();
		
		boolean removed = empee.remove(key);
		System.out.println(removed? "삭제되었습니다." : "존재하지 않는 번호입니다.");
	}

	private static Employee searchByNo() 
	{
		while(true)
		{
			System.out.print("사원번호 입력: ");
			String eNo = kbd.nextLine();
			Employee key = new Employee(Integer.parseInt(eNo));
			if(empee.contains(key))	
			{		
				System.out.println("번호\t이름\t\t부서번호\t월급\t\t입사날짜");
				int idx = empee.indexOf(key);
				System.out.println(empee.get(idx));			

				return key;
			}
			else System.err.println("존재하지 않는 번호입니다."); return null;
		}
	}
	
}
