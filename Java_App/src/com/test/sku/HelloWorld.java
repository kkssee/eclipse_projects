package com.test.sku;

import java.util.Scanner;
import java.util.Random;

public class HelloWorld {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello World\n");
		
		//devDataTypes();	//서로 다른 자료형의 연산
		//incDecTest();		// Pre, Post Increment Operators
		//printGuguPre(5);	// 구구단 5단, 전증가 방식
		//printGuguPost(7);	// 구구단 7단, 후증가 방식
		//inputDanPrint();	// 키보드에서 입력된 수의 구구단
		//notEquals();		// boolean 값 리턴
		//oddOrEven();			// 키보드 입력값이 홀수인지 짝수인지 화면에 표시
		//logicalAnd();
		//logicalOpTest();
		//logIn();
		//xor();
	
	}
	
	private static void devDataTypes()
	{
		/*
		5/2면 int 정수형의 연산 결과 - 결과값은 무조건 정수
		double a = 5/2;			=>2.0
		1. 형변환 연산자로 4바이트 정수형을 8바이트 double로 바꿔줌
		double a = (double)5/2;	=>2.5
		2. 연산할 값 중 하나만 실수(8byte)로 쓰면 나머지 값도 8byte로 연산
		*/
		double a = 5/2.0; 
		System.out.println(a);
	}
	private static void incDecTest() //Camel case, Snake case
	{
		// Increment, Decrement Operators
		int v = 0;
		v++;	// Post-Increment Operator
		System.out.println(v);	// 1
		++v;	// Pre-Increment Operator
		System.out.println(v);	// 2
		System.out.println(v++);// 2
		System.out.println(v);	// 3
		System.out.println(++v);// 4
		System.out.println(v);	// 4
	}
	private static void printGuguPre(int i)
	{
		int j = 0;
		String guguForm = "%d * %d = %d %n";
		
		System.out.printf(guguForm, i, ++j, i * j);
		System.out.printf(guguForm, i, ++j, i * j);
		System.out.printf(guguForm, i, ++j, i * j);
		System.out.printf(guguForm, i, ++j, i * j);
		System.out.printf(guguForm, i, ++j, i * j);
		System.out.printf(guguForm, i, ++j, i * j);
		System.out.printf(guguForm, i, ++j, i * j);
		System.out.printf(guguForm, i, ++j, i * j);
		System.out.printf(guguForm, i, ++j, i * j);
	}
	private static void printGuguPost(int i)
	{
		int j = 1;
		String guguForm = "%d * %d = %d %n";
		
		System.out.printf(guguForm, i, j, i * j++);
		System.out.printf(guguForm, i, j, i * j++);
		System.out.printf(guguForm, i, j, i * j++);
		System.out.printf(guguForm, i, j, i * j++);
		System.out.printf(guguForm, i, j, i * j++);
		System.out.printf(guguForm, i, j, i * j++);
		System.out.printf(guguForm, i, j, i * j++);
		System.out.printf(guguForm, i, j, i * j++);
		System.out.printf(guguForm, i, j, i * j++);
	}
	private static void inputDanPrint()
	{
		Scanner kbd = new Scanner(System.in);
		System.out.println("Input a Gugudan number.");
		
		int i = kbd.nextInt();
		int j = 0;
		
		String guguForm = "%d * %d = %d %n";
		
		System.out.printf(guguForm, i, ++j, i * j);
		System.out.printf(guguForm, i, ++j, i * j);
		System.out.printf(guguForm, i, ++j, i * j);
		System.out.printf(guguForm, i, ++j, i * j);
		System.out.printf(guguForm, i, ++j, i * j);
		System.out.printf(guguForm, i, ++j, i * j);
		System.out.printf(guguForm, i, ++j, i * j);
		System.out.printf(guguForm, i, ++j, i * j);
		System.out.printf(guguForm, i, ++j, i * j);
		
		kbd.close();
	}
	private static void notEquals()
	{
		int a = 3;
		int b = 5;
		// 비교, 논리 연산자들은 항상 boolean 값을 리턴한다.
		System.out.println(a != b);	//true
		System.out.println(a == b); //false
	}
	private static void oddOrEven()	
	{
		//출력 예시) "3은 홀수입니다" (자료형, 산술, 비교, 3항연산자)
		Scanner kbd = new Scanner(System.in);
		System.out.print("수를 입력하세요. ");

		int i = kbd.nextInt();
		String msg = String.format("%d는(은) %s입니다.", i, (i % 2 == 0) ? "짝수" : "홀수");

		System.out.println(msg);
		
		kbd.close();
	}
	private static void logicalAnd()	
	{
		boolean result = true && true;	//true
		result = false && true; 	// Short Circuit Logical AND Operator
		result = true || false;		// Short Circuit Logical OR Operator
		result = false & true;		// 비단축 논리 AND 연산자
		result = true | false;		// 비단축 논리 OR 연산자
		
		System.out.println(result);
		
	}
	private static void logicalOpTest() {
		
		Random rd = new Random();
		
		int a = rd.nextInt(20);
		int b = rd.nextInt(20);
		
		String logiForm = "정수 1: %d, 정수 2: %d%n%s";
		
		System.out.printf(logiForm, a, b, ((a%2==1)&&(b%2==1)?"유효한 수" : "무효한 수"));
		
	}
	private static void logIn() {
		// 둘 다 짝수가 아니라면, 논리 부정연산자(!), !true
		// 키보드에서 아이디(smith), 패스워드(1234) 입력 받아서 맞으면 
		// 로그인 성공 / 로그인 실패 출력
		Scanner kbd = new Scanner(System.in);
		System.out.print("Input your ID and Password.\nID: ");

		String id = kbd.nextLine();
		System.out.print("PW: ");
		String pwd = kbd.nextLine();
		
		String login = !(id.equals("smith") && pwd.equals("1234")) ? "로그인 실패" : "로그인 성공";

		System.out.println(login);
		
		kbd.close();
		
	}
	/*private static void xor() 
	{
		boolean result = true || true;
		result = true ^ true;	//false
	}*/
	private static void bitShiftOp()
	{
		int a = 1;
		int b =  a << 1;
		System.out.println(b);	// 2
		
		b = a << 2; 
		System.out.println(b); 	// 4
	}

}
