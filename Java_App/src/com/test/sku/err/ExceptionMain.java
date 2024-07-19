package com.test.sku.err;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExceptionMain 
{
	static Scanner kbd = new Scanner(System.in);
	public static void main(String[] args) 
	{
		// 에러, 예외
		// Error: 
		// Exception: Mild Error
		// 소프트웨어 에러(실행 중 에러)
		// 비정상 종료
		
		//error01();
		//error02();
		//error03();
		//error04();
		/*try {
			error05();
		} catch (PasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		try {
			error06();
		} catch (UserLoginException e) {
			System.err.println(e.getMessage());
		}
		
		
	}
/*
	private static void error01()
	{
		int a = 5;
		int b = 0;
		try {
			int c = a/b;
			System.out.println(c);
		}catch(ArithmeticException e) {
			System.err.println(e.getMessage());
		} finally { //예외 발생과 무관하게 항상 실행됨
			System.out.println("finally~");
		}
		System.out.println("come out");
	}
	private static void error02() 
	{
		while(true) {
			try {
			System.out.print("Input number 1: ");
			int a = kbd.nextInt(); kbd.nextLine();
			System.out.print("Input number 2: ");
			int b = kbd.nextInt(); kbd.nextLine();
			
			
				System.out.printf("%d/%d = %d%n", a, b, a/b);
				kbd.close();
				break;
			}catch(Exception e) {
				if(e instanceof ArithmeticException) {
					System.err.println("zero included");
				}else if(e instanceof InputMismatchException){
					System.err.println("Characters are not allowed");
				}
			}
		}
		System.out.println("come out");
	}
	private static void error03() 
	{
		// abc.txt 파일을 열고 그 안에 저장된 텍스트를 화면에 표시
		// 없는 파일을 출력
		String fpath = "C:/test/data/abc.txt";
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(fpath);
			br = new BufferedReader(fr);
			String line = null;
			while((line = br.readLine())!=null)	
			{
				System.out.println(line);
			}
			System.out.println(fr);
		} catch (Exception e) {
			if(e instanceof FileNotFoundException) {
				System.err.println("File Not Found !");
			}
			else e.printStackTrace();
		} finally {
			try {
				fr.close();
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("end");
	}
	private static void error04() throws Exception 
	{
		String fpath = "C:/test/data/abc.txt";
		FileReader fr = new FileReader(fpath);
		BufferedReader br = new BufferedReader(fr);
		String line = null;
		while((line = br.readLine())!=null)	
		{
			System.out.println(line);
		}
		br.close();
	}*/
	private static void error05() throws PasswordException 
	{
		System.out.print("id pass: ");
		
		String uid = kbd.next();
		String pwd = kbd.nextLine();
		
		if(pwd.length() < 5) {
			throw new PasswordException("pwd must be more than 5 characters");
		}
		System.out.println("quit");
	}
	private static void error06() throws UserLoginException
	{
		// users.txt 파일에 아이디, 암호를 5개 저장하고
		// 키보드에서 로그인할 때 로그인에 실패할 경우 UserLogin Exception이
		// 발생하도록 하고 예외처리하여 이용자에게 적절한 메시지 표시, 반복
		String fpath = "C:/test/data/users.txt";
		FileReader fr;
			try {
				fr = new FileReader(fpath);
				String line = null;
				BufferedReader br = new BufferedReader(fr);
				List<Users> list = new ArrayList<>();
					try {
						while((line = br.readLine())!=null)
						{
							Users b = new Users(line);
							list.add(b);
							System.out.println(line);
						}
						System.out.print("Input id: ");
						String uid = kbd.nextLine();
						System.out.print("Input pwd: ");
						String pwd = kbd.nextLine();
						
						for(int i = 0; i < list.size(); i++)
						{
							if(list.get(i).getUid().equals(uid)&& list.get(i).getPwd().equals(pwd))
							{
								System.out.println("Login completed");
								return;
							}
						}
						throw new UserLoginException("Login Failed");
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						try {
							br.close();
						} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
	}
}
