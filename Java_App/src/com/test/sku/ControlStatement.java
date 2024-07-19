package com.test.sku;

import java.lang.reflect.Member;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;


public class ControlStatement 
{

	/* 
	 * 반복문, 조건문
	 * 반복문(while, do while, for)
	 * 조건문(if, else if, else, switch)
	 */
	public static void main(String[] args) 
	{
		/*boolean go = true;
		int i = 0;
		while(go)
		{
			// 조건이 true 일 경우에만 반복 실행되는 블럭
			i++;
			go = i < 10 ? true : false;
			System.out.print(i + " ");
		}
		*/
		//showGugu();		// 무작위로 추출된 정수(2~9) 사용하여 해당 수의 구구단 출력
		//getSumRange();
		//incDec();
		//getEven5();		//while 사용하여 랜덤 넘버 중 짝수 5개 출력
		//dateTest();
		//variableInit();
		//login();
		//switchTest();
		//forLoopTest();
		//arrayTest();
		//arrayCRUD();
		//System.out.print(greet("Juan"));
		//int[] num = new int[] {1,3,5,7,9};
		//int res = sum(num);
		//System.out.print(res);
		/*int[] arr = getNums(5);
		System.out.println(Arrays.toString(arr));
		for(int i=0;i<arr.length;i++) {
			System.out.print(arr[i] + " ");
		}*/
		//System.out.println(Arrays.toString(sortIntArr(8)));
		//loginInput();
		
		
	}
	private static void showGugu()
	{
		Random rd = new Random();
		int i = rd.nextInt(8) + 2;
		int j = 0;
		String guguForm = "%d * %d = %d %n";
		
		while(j<9)
		{
			System.out.printf(guguForm, i, ++j, i * j);
		}
	}
	private static void getSumRange()
	{
		// 무작위 숫자 추출하여 그 수 부터 10까지 합산
		// 출력 예시) n ~ 10까지 합산 = ?
		
		Random rd = new Random();
		String randForm = "%d ~ 10까지 합산 = %d";
		
		int i = rd.nextInt(9) + 1;
		int start = i;
		int sum = 0;
		
		while(i++ <= 10)
		{
			sum += i - 1;
			//i = i + 1;
		}
		System.out.printf(randForm, start, sum);
	}
	private static void incDec()
	{ 
		int i = 1;
		int delta = 1; 
		int cnt = 0;
		while(i>0)
		{
			System.out.printf("%d ", i);
			cnt++;
			delta = cnt>=5 ? -1:1;
			i += delta;
		}
	}
	private static void getEven5()
	{
		Random rd = new Random();
		int cnt = 0;
		while(cnt<5)
		{
			int rn = rd.nextInt(20) + 1;
			String s = rn % 2 == 0 ? rn + " " : "";
			System.out.print(s);
			cnt += !s.equals("") ? 1 : 0;
		}
	}
	private static void ifTest()	
	{
		int i = 0;
		if(i==1)
		{
			System.out.println("참");
		}
		else if(i==2)
		{
			
		}
		else
		{
			System.out.println("거짓");
		}
		System.out.println("메소드 종료");
	}
	private static void dateTest()
	{
		Calendar cal = Calendar.getInstance();
		String swd = new String();
		
		int y = cal.get(Calendar.YEAR);
		int M = cal.get(cal.MONTH);
		int d = cal.get(cal.DAY_OF_MONTH);
		int wd = cal.get(cal.DAY_OF_WEEK);
		int h = cal.get(cal.HOUR);
		int m = cal.get(cal.MINUTE); 
		int s = cal.get(cal.SECOND); 
		
		if(wd == 1) swd = "일";
		else if(wd == 2) swd = "월";
		else if(wd == 3) swd = "화";
		else if(wd == 4) swd = "수";
		else if(wd == 5) swd = "목";
		else if(wd == 6) swd = "금";
		else if(wd == 7) swd = "토";
		
		System.out.printf("%d년-%d월-%d일 %s요일 %d:%d:%d%n", y, M, d, swd, h, m, s);
	}
	private static void variableInit()
	{
		int v = 0;
		String s = null;
		s = "";
		String str = "";
		boolean result = str.equals(""); //String 초기화 안하면 or null로 하면 The local variable str may not have been initialized
		System.out.println(result);
	}
	private static void login()
	{
		// 키보드에서 id, pwd를 입력하고 smith, 1234인지 확인
		// 이용자가 로그인 실패하면 3회까지 기회 주고
		// 3회 넘으면 10분 후 다시 시도하라고 메시지 출력 후 프로그램 종료
		// 3회 만에 로그인에 성공하면 "로그인 성공" 출력 후 프로그램 종료
		
		Scanner kbd = new Scanner(System.in);

		
		int cnt = 0;
		while(true)	
		{
			System.out.print("ID: ");
			String id = kbd.nextLine();
			System.out.print("Password: ");
			String pwd = kbd.nextLine();
			
			if(id.equals("smith") && pwd.equals("1234")) 
			{
				System.out.println("로그인 성공");
				break;
			}
			else
			{
				if(++cnt == 3)
				{
					System.out.println("10분 후에 다시 시도하세요.");
					break;
				}
				else System.out.println("다시 한 번 시도하세요.");
			}
			
			/*
			if(id.equals("smith") && pwd.equals("1234")) 
			{
				System.out.println("로그인 성공");
				go = false;
				//continue;
			}*/
		}
		
		kbd.close();
	}
	private static void switchTest()
	{
		Calendar cal = Calendar.getInstance();
		String swd = new String();
		
		int y = cal.get(Calendar.YEAR);
		int M = cal.get(cal.MONTH) + 1;
		int d = cal.get(cal.DAY_OF_MONTH);
		int wd = cal.get(cal.DAY_OF_WEEK);
		int h = cal.get(cal.HOUR);
		int m = cal.get(cal.MINUTE); 
		int s = cal.get(cal.SECOND); 
		
		int nd = 2;
		
		switch(nd)
		{
		case 1: swd = "일"; break;
		case 2:	swd = "월";	break;
		case 3:	swd = "화";	break;
		case 4:	swd = "수"; break;
		case 5:	swd = "목";	break;
		case 6:	swd = "금";	break;
		case 7:	swd = "토";	break;
		default:
			System.out.println("존재하지 않습니다.");
			break;
		}
		System.out.printf("%d년-%d월-%d일 %s요일 %d:%d:%d%n", y, M, d, swd, h, m, s);
	}
	private static void forLoopTest()	
	{
		/*for(int i = 0, j = 0; i <=10; i++, j--)
		{
			System.out.println(i + " ");
		}*/
		
		// 한개의 for 루프를 사용하여 10~1~10을 출력
		/*for (int i = 10, cnt = 0; cnt < 19; cnt++)
		{
			if(cnt < 9)
			{
				System.out.print(i + " ");
				i--;
			}
			else if(cnt >= 9)
			{
				System.out.print(i + " ");
				i++;
			}
		}*/
		
		/* for (int i = 10, delta = -1; i <= 10; i+=delta)
		  {
		  		System.out.print(i + " ");
		  		if(i==1) delta = 1;
		  }
		*/
		
		// continue : while, for 문장에서 흐름을 다시 루프의 시작으로 돌린다.
		// break : while, for, switch 문장에서 반복을 종료한다.
		
		// 무작위 정수 10개를 출력하는데 모두 홀수여야 함 
		Random rd = new Random();
		int j;
		for(int cnt=0;cnt < 10;)
		{
			j = rd.nextInt(20)+1;
			if(j % 2 == 1)
			{
				System.out.print(j + " ");
				cnt++;
			}
		}
	}
	private static void arrayTest()
	{
		// 배열(Array)
		/* 동일한 자료형의 데이터를 일련의 공간에 다수개를 순서대로 저장하는 가장 단순한 자료구조
		 * 자료구조 중에서 데이터를 순서대로 순회하면서 보여줄 때 가장 빠른 성능을 보여준다
		 * 한 학생의 모든 과목에 대한 점수를 저장하려면 다수개의 정수 혹은 실수 저장공간이 요구된다
		 * 배열을 사용할 때는, 배열변수 선언, 메모리 할당, 초기화, 배열 사용 순으로 해야 한다
		*/
		
		int[] nums;			// 배열변수 선언
		nums = new int[5];	// 정수 5개 공간 확보(할당)
		nums[0] = 3;		// index를 사용하여 배열의 각 원소를 순회할 수 있다
		nums[1] = 4;
		nums[2] = 5;
		nums[3] = 6;
		nums[4] = 7;
		for(int i = 0; i < nums.length; i++)	// CRUD(Create, Read, Update, Delete)
		{
			System.out.printf("%d ", i);
		}
	}
	private static void arrayCRUD() {
		// 회원정보 관리 시스템 
		// * 번호, 이름, 전화번호
		// * num, name, phone 배열
		// * 키보드에서 회원정보를 입력받아서 배열에 저장하고 회원의 목록 표시
		// * 프로그램이 시작되면 번호 이름 전화번호 입력 화면 표시
		// * 다수의 회원정보를 입력할 수 있어야 함 이용자가 아무런 값도 입력하지 않고 엔터
		// * 입력 종료하고 지금까지 입력된 회원 목록을 화면에 표시
		// * 배열의 원소수는 초기 10개
		
		Scanner kbd = new Scanner(System.in);
		
		int[] num = new int[5];
		String[] name = new String[5];
		String[] phone = new String[5];
		
		String menu; 
		String sNo;
		
		while(true)
		{
			System.out.print("목록(s), 추가(a), 수정(u), 삭제(d), 검색(f), 종료(x)");
			
			menu = kbd.nextLine();
			
			if(menu.equals("s"))
			{
				System.out.println("\n\t*** 회원목록 ***\n");
				
				for(int i = 0; i < num.length; i++)
				{
					System.out.printf("%d\t%s\t%s%n", num[i], name[i], phone[i]);
				}
				System.out.print("\n");
				continue;
			}
			else if(menu.equals("a"))
			{
				System.out.print("\n회원정보를 입력하세요.\n");
				
				for(int i = 0; i < num.length; i++)
				{
					System.out.print("번호: ");
					sNo = kbd.nextLine();
					if(sNo.equals(""))
					{
						System.out.println("입력종료");
						continue;
					}
					num[i] = Integer.parseInt(sNo);
					
					System.out.print("이름: ");
					sNo = kbd.nextLine();
					if(sNo.equals(""))
					{
						System.out.println("입력종료");
						continue;
					}
					name[i] = sNo;
					
					
					System.out.print("전화번호: ");
					sNo = kbd.nextLine();
					if(sNo.equals(""))
					{
						System.out.println("입력종료");
						continue;
					}
					phone[i] = sNo;
				}
				System.out.print("\n");
				continue;
			}
			else if(menu.equals("u"))
			{
				// u 번호, 새 전번 
				//번호로 검색해서 그 회원의 전화번호를 새로 갱신함
				System.out.print("번호 검색: ");
				int i = kbd.nextInt();
				boolean memExist = false;
				kbd.nextLine();
				
				for(int j = 0; j < num.length; j++)
				{
					if(num[j] == i)
					{
						memExist = true;
						System.out.printf("%d\t%s\t%s%n", num[j], name[j], phone[j]);
						System.out.print("전화번호를 갱신하시겠습니까?(Y/N) ");
						//kbd.nextLine();
						sNo = kbd.nextLine();
						
						if(sNo.equals("Y") || sNo.equals("y"))
						{	
							System.out.print("전화번호 입력: ");
							phone[j] = kbd.nextLine();
							System.out.print("갱신되었습니다.\n\n");
							continue;
						}
						else if(sNo.equals("N") || sNo.equals("n"))
						{
							System.out.print("갱신이 취소되었습니다.\n\n");
							continue;
						}
					}
				}
				if(!memExist)
				{
					System.err.print("존재하지 않는 회원입니다.\n\n");
					continue;
				}
			}
			
			else if(menu.equals("d"))
			{
				System.out.print("번호 검색: ");
				int i = kbd.nextInt();
				boolean memExist = false;
				kbd.nextLine();
				
				for(int j = 0; j < num.length; j++)
				{
					if(num[j] == i)
					{
						memExist = true;
						System.out.printf("회원정보\t%d\t%s\t%s%n", num[j], name[j], phone[j]);
						System.out.print("회원정보를 삭제하시겠습니까?(Y/N) ");
						//kbd.nextLine();
						sNo = kbd.nextLine();
						
						if(sNo.equals("Y") || sNo.equals("y"))
						{	
							num[j] = 0;
							name[j] = new String();
							phone[j] = new String();
							System.out.print("삭제되었습니다.\n\n");
							continue;
						}
						else if(sNo.equals("N") || sNo.equals("n"))
						{
							System.out.print("삭제가 취소되었습니다.\n\n");
							continue;
						}
					}
				}
				
				if(!memExist)
				{
					System.err.print("존재하지 않는 회원입니다.\n\n");
					continue;
				}
					
				
			}
			else if(menu.equals("f"))
			{
				boolean memExist = false;
				System.out.print("회원번호 검색(n), 전화번호 검색(p): ");
				sNo = kbd.nextLine();
				if(sNo.equals("n"))
				{
					System.out.print("회원번호 검색: ");
					int i = kbd.nextInt();
					kbd.nextLine();
					
					for(int j = 0; j < num.length; j++)
					{
						if(num[j] == i)
						{
							memExist = true;
							System.out.printf("회원정보\t%d\t%s\t%s%n%n", num[j], name[j], phone[j]);
						}
					}
					if(!memExist)
					{
						System.err.print("존재하지 않는 회원입니다.\n\n");
						continue;
					}
				}
				else if(sNo.equals("p"))
				{
					System.out.print("전화번호 검색: ");
					String s = kbd.nextLine();
					
					for(int j = 0; j < num.length; j++)
					{
						if(phone[j].equals(s))
						{
							memExist = true;
							System.out.printf("회원정보\t%d\t%s\t%s%n%n", num[j], name[j], phone[j]);
						}
					}
					if(!memExist)
					{
						System.err.print("존재하지 않는 회원입니다.\n\n");
						continue;
					}
				}
				else System.err.print("잘못된 접근입니다.\n"); continue;
				
			}
			
			else if(menu.equals("x"))
			{
				System.out.print("\n프로그램 종료\n");
				kbd.close();
				return;
			}
			
			else
			{
				System.err.print("잘못된 접근입니다.\n\n"); 
				continue;
			}
		}
	}
	private static String greet(String name) { 
		// method: 코드의 집합에 이름을 붙인 것(function: 기능, 함수)
		//greet에 회원 이름 전달하면 그 이름에 "Hello"가 추가돼서 리턴됨
		//리턴된 문자열을 화면에 표시
		return "Hello, " + name;
	}
	private static int sum(int[] array) {
		// 배열을 받아서 그 배열의 합을 구하여 리턴
		int sum = 0;
		for(int i = 0; i< array.length; i++)
		{
			sum += array[i];
		}
		
		return sum;
		
	}
	private static int[] getNums(int num) {
		// getNums 메소드를 호출하면 무작위 정수를 원소로 하는 배열 리턴
		// 파라미터로 전달하는 숫자만큼 배열의 원소수를 지정하여 배열 생성
		Random rd = new Random();
		int[] arr = new int[num];
		for(int i = 0; i < num; i++)
		{
			arr[i] = rd.nextInt(20);
		}
		
		for(int i = 0; i < arr.length-1; i++)
		{
			for(int j = i + 1; j < arr.length; j++)
			{
				if(arr[i]<=arr[j])
				{
					continue;
				}
				else
				{
					int tmp = arr[i];
					arr[i] = arr[j];
					arr[j] = tmp;
					continue;
				}
			}
		}
		return arr;
	}
	private static int[] selectionSort(int[] arr) {
		// 선택정렬 알고리즘
		for(int i = 0; i < arr.length-1; i++)
		{
			for(int j = i + 1; j < arr.length; j++)
			{
				if(arr[i]<=arr[j])
				{
					continue;
				}
				else
				{
					int tmp = arr[i];
					arr[i] = arr[j];
					arr[j] = tmp;
					continue;
				}
			}
		}
		return arr;
	}
	private static int[] sortIntArr(int num) {
		// sortIntArr 메소드를 작성하고 파라미터로 정수를 전달하면
		// 파라미터가 의미하는 갯수만큼 정수배열을 생성하고 정렬하여 정렬된 배열을
		// 리턴하는 메소드를 선언하고 호출하여 그 리턴값을 출력
		// 선택정렬 알고리즘 구현한 메소드(selectionSort)를 선언하고 활용
		Random rd = new Random();
		int[] arr = new int[num];
		for(int i = 0; i < num; i++)
		{
			arr[i] = rd.nextInt(20);
		}
		
		return selectionSort(arr);
	}
	private static boolean login2(String id, String pwd)
	{
		// 키보드에서 id, pwd를 입력 받아서 로그인하고
		// 그 결과를 로그인 성공 / 실패로 표시
		
		 return id.equals("smith") && pwd.equals("1234");
		
	}
	private static void loginInput() {
		Scanner kbd = new Scanner(System.in);
		System.out.println("회원정보를 입력하세요.");
		
		System.out.print("ID PWD: ");
		String input = kbd.nextLine().trim();
		String[] arr = input.split("\\s+");
		String result = login2(arr[0], arr[1]) ? "로그인 성공" : "로그인 실패";
		System.out.println(result);
		kbd.close();
	}
	
	
	
	
	
	
	
	
	
	
	
}