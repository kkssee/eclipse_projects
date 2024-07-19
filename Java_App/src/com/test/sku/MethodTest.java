package com.test.sku;

import java.util.Scanner;

public class MethodTest {
	
	static Scanner kbd = new Scanner(System.in);
	
	static int[] num = new int[10];
	static String[] name = new String[10];
	static String[] phone = new String[10];
	
	static String menu;  
	static String sNo;

	public static void main(String[] args) 
	{
		// Member Variables
		/* static variable(class variable): 프로그램 시작할 때 자동으로 메모리에 로드됨
		 * instance variable: 클래스의 인스턴스가 생성될 때 로드됨 */
		selectMenu();
		return;
	}
	
	private static void selectMenu() {
		while(true)
		{
			System.out.print("목록(s) 추가(a) 수정(u) 삭제(d) 검색(f) 종료(x)\t");
			
			menu = kbd.nextLine();
			
			if(menu.equals("s"))		list();
			else if(menu.equals("a"))	add();
			else if(menu.equals("u"))	update();
			else if(menu.equals("d"))	delete();
			else if(menu.equals("f"))	find();
			else if(menu.equals("x"))	break;
			else	System.err.print("잘못된 접근입니다.\n\n");
		}
		
		System.out.print("프로그램 종료\n");
		kbd.close();
		return;
	}
	private static void list() {
		System.out.println("\n*** 회원목록 ***\n");
		
		for(int i = 0; i < num.length; i++)
		{
			System.out.printf("%d\t%s\t%s%n", num[i], name[i], phone[i]);
		}
		System.out.print("\n");
	}
	private static void add() 
	{
		System.out.print("\n회원정보를 입력하세요.\n");
		
		for(int i = 0; i < num.length; i++)
		{
			while(true)
			{
				System.out.print("번호: ");
				sNo = kbd.nextLine();
				
				if(sNo.equals(""))
				{
					System.out.println("입력종료");
					return;
				}
				
				int res = isFound(Integer.parseInt(sNo));
				
				if(res == -1)
				{
					num[i] = Integer.parseInt(sNo);
					break;
				}
				else if(res == i)
				{
					System.err.println("이미 존재하는 회원입니다."); 
					continue;
				}
			}
			
			/*do 
			{
				System.out.print("번호: ");
				sNo = kbd.nextLine();
				num[i] = Integer.parseInt(sNo);
			}
			while(isFound(Integer.parseInt(sNo))!=-1);*/
			
			System.out.print("이름: ");
			sNo = kbd.nextLine();
			if(sNo.equals(""))
			{
				System.out.println("입력종료");
				break;
			}
			name[i] = sNo;
			
			System.out.print("전화번호: ");
			sNo = kbd.nextLine();
			if(sNo.equals(""))
			{
				System.out.println("입력종료");
				break;
			}
			phone[i] = sNo;
		}
		System.out.print("\n");
	}
	private static void update() {
		while(true)
		{
			System.out.print("번호 검색: ");
			int i = kbd.nextInt();
			kbd.nextLine();
			
			int res = isFound(i);
			if(res == -1)
			{
				System.err.print("존재하지 않는 회원입니다.\n\n");
				continue;
			}
			else
			{
				System.out.printf("%d\t%s\t%s%n", num[res], name[res], phone[res]);
				System.out.print("전화번호를 갱신하시겠습니까?(Y/N) ");
				sNo = kbd.nextLine();
				
				if(sNo.equals("Y") || sNo.equals("y"))
				{	
					System.out.print("전화번호 입력: ");
					phone[res] = kbd.nextLine();
					System.out.print("갱신되었습니다.\n\n");
					break;
				}
				else
				{
					System.out.print("갱신이 취소되었습니다.\n\n");
					break;
				}
			}
		}
	}
	private static void delete() {
		while(true)
		{
			System.out.print("번호 검색: ");
			int i = kbd.nextInt();
			boolean memExist = false;
			kbd.nextLine();
			
			if(isFound(i)==-1) 
			{
				System.err.print("존재하지 않는 회원입니다.\n\n");
				continue;
			}
			else
			{
				memExist = true;
				int res = isFound(i);
				System.out.printf("회원정보\t%d\t%s\t%s%n", num[res], name[res], phone[res]);
				System.out.print("회원정보를 삭제하시겠습니까?(Y/N) ");
				sNo = kbd.nextLine();
				
				if(sNo.equals("Y") || sNo.equals("y"))
				{	
					num[res] = 0;
					name[res] = new String();
					phone[res] = new String();
					System.out.print("삭제되었습니다.\n\n");
					break;
				}
				else if(sNo.equals("N") || sNo.equals("n"))
				{
					System.out.print("삭제가 취소되었습니다.\n\n");
					break;
				}
			}
			
			break;
		}
	}
	private static void find() {
		boolean memExist = false;
		int res = -1;
		
		while(true)
		{
			System.out.print("회원번호 검색(n), 전화번호 검색(p): ");
			sNo = kbd.nextLine();
			
			if(sNo.equals("n")) res = searchByNo();
			else if(sNo.equals("p")) res = searchByPhone(); 
			else System.err.print("잘못된 접근입니다.\n");
			
			if(res == -1)
			{
				System.err.print("존재하지 않는 회원입니다.\n\n");
				continue;
			}
			else 
			{
				System.out.printf("회원정보\t%d\t%s\t%s%n%n", num[res], name[res], phone[res]);
				return;
			}			
		}
	}
	private static int isFound(int isNum) {
		for(int i = 0; i < num.length; i++)
		{
			if(num[i] == isNum) return i;
		}
		return -1;
	}
	private static int searchByNo() {
		System.out.print("회원번호 검색: ");
		int i = kbd.nextInt();
		kbd.nextLine();
		int res = isFound(i);
		return res;
	}
	private static int searchByPhone() {
		System.out.print("전화번호 검색: ");
		String s = kbd.nextLine();
		
		for(int j = 0; j < num.length; j++)
		{
			if(phone[j].equals(s))
			{
				return j;
			}
		}
		return -1;
	}
	
}