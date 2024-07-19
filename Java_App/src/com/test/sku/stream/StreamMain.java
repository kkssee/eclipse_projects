package com.test.sku.stream;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.test.sku.list.Employee;

public class StreamMain 
{
	// Java의 Stream API 다루기
			/* Stream: 데이터의 흐름
			 * System.out.println(name);	=> 메모리의 데이터를 콘솔로 출력
			 * new Scanner(System.in);		=> 키보드로 입력받은 데이터를 메모리에 복사
			 * chatting: com1, com2
			 * text 데이터: Character Stream, binary 데이터: Byte Stream
			 * Reader, Writer				  InputStream, OutputStream	
			 */
			/* 스트림은 파이프처럼 연결하여 사용할 수 있다
			 * Node Stream, Filter Stream
			 * Node: 데이터 흐름의 시작과 끝에 위치하는 스트림
			 * Filter: Node의 위치에 올 수 없다
			 */
			/* 입력스트림, 출력스트림
			 * 입력스트림(Reader, InputStream): 데이터를 가져오는 스트림
			 * 출력스트림(Writer, OutputStream): 데이터를 특정 장소로 보내는 스트림
			 */
	
	static Scanner kbd = new Scanner(System.in);
	static List<Members> mem = new ArrayList<>();
	static List<User> user = new ArrayList<>();
	
	
	public static void main(String[] args) 
	{
		//list();
		
		//loginRead();
		//loginInput();
		
		//signUp();
		
		textInput7();
		for(int i=0; i<mem.size(); i++)
		{	
			System.out.println(mem.get(i));
		}
		
		textUpdate();
		
		for(int i=0; i<mem.size(); i++)
		{	
			System.out.printf("%d|%s|%s|%s%n",mem.get(i).getMno(), mem.get(i).getMnm(),mem.get(i).getMph(),mem.get(i).getMem());
		}
		
		textDelete();
	}
	
	/* 텍스트파일을 읽어서 내용을 화면에 표시
	 * 읽을 때 			=> FileReader -> data 
	 * 화면에 표시할 때 => sysout(data);
	 */
	private static void textInput() 
	{
		try {
			FileReader fr = new FileReader("C://Users//sku202-05//kdse//java/whatwhat.txt");	// Source Stream
			int ch = fr.read();
			System.out.println((char)ch);
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void textInput2() 
	{
		try {
			FileReader fr = new FileReader("C://Users//sku202-05//kdse//java/whatwhat.txt");	// Source Stream
	
			char[] cbuf = new char[12];
			int cnt = 0;
			do {
				cnt = fr.read(cbuf);
				String sdata = new String(cbuf);
				System.out.print(sdata);
			} while(cnt!=-1);
			System.out.println("읽기 완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void textInput3() 
	{
		try {
			FileReader fr = new FileReader("C://Users//sku202-05//kdse//java/whatwhat.txt");	
	
			char[] cbuf = new char[12];
			int cnt = 0;
			do {
				cnt = fr.read(cbuf);
				if(cnt!=-1) {
					String sdata = new String(cbuf,0,cnt);
					System.out.print(sdata);
				}
			} while(cnt!=-1);
			System.out.println("읽기 완료"); 
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void textInput4() 	// 성능젤좋아열
	{
		try {
			FileReader fr = new FileReader("C://Users//sku202-05//kdse//java/whatwhat.txt");	// Source Stream
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			
			while((line=br.readLine())!=null)
			{
				System.out.println(line);

			}
			System.out.println("읽기 완료"); 
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	private static void textInput5() 
	{
		File textFile = new File("C://Users//sku202-05//kdse//java/whatwhat.txt");
		int len = (int) textFile.length();
		try {
			FileInputStream fin = new FileInputStream(textFile);
			byte[] buf = new byte[len];
			fin.read(buf);
			String str = new String(buf);
			System.out.println(str);
			fin.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void textInput6() 
	{
		File textFile = new File("C:/test/data/members.txt");
		int len = (int) textFile.length();
		try {
			FileInputStream fin = new FileInputStream(textFile);
			byte[] buf = new byte[len];
			fin.read(buf);
			String str = new String(buf);
			String[] token = str.split("\\|");
			Members m = new Members(token);	
			
			mem.add(m);
			
			for(int i = 0; i < token.length; i++)
			{
				System.out.print(token[i]);
				if((i+1) % 4 == 0)
				{
					System.out.print("\n");
				}
			}
			fin.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		list();
	}
	private static void textInput7() 	// 리스트에 저장해열
	{
		try {
			FileReader fr = new FileReader("C:/test/data/members.txt");	
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			
			while((line=br.readLine())!=null)
			{
				String[] arr = line.split("\\|");
				Members u = new Members(arr);	
				mem.add(u);
			}
			System.out.println("읽기 완료"); 
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	/* 메모리에 문자열 -> 파일에 기록 (메모리에서 디스크로 데이터 이동: 메모리는 휘발성이기 때문에 영구보존을 위함)
	 * String을 file에 Write : FileWriter
	 * System.out.println(); => PrintStream.println()
	 */
	private static void signUp() 
	{
		try {
			String fname = "C:/test/data/users.txt";
			PrintWriter pw = new PrintWriter(new FileWriter(fname, true));
			
			System.out.println("회원정보를 입력하세요.");
			System.out.print("아이디 암호: ");
			String input = kbd.nextLine().trim();
			String[] arr = input.split("\\s+");
			
			pw.printf("%s:%s%n", arr[0], arr[1]);
			pw.close();		// pw fr 동시 => 메모리에만 작동하고 실제 목적지에 도달하지 않음
			textInput7();
			
			User check = new User(arr[0], arr[1]);
			String result = user.contains(check) ? "회원가입 성공" : "회원가입 실패";
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	private static void list() 
	{
		//textInput7();	// textInput7()에 list()를 집어넣으면 코드의 재사용성 떨어짐 효율감소
		System.out.println("\n\t*** 회원정보 ***");
		for(int i=0; i<user.size(); i++)
		{	
			System.out.println(user.get(i));
		}
	}
	
	private static void loginInput() {
		System.out.println("회원정보를 입력하세요.");
		System.out.print("아이디 암호: ");
		String input = kbd.nextLine().trim();
		String[] arr = input.split("\\s+");
		User check = new User(arr[0], arr[1]);
		String result = user.contains(check) ? "로그인 성공" : "로그인 실패";
		System.out.println(result);
		kbd.close();
	}
	private static void loginRead()
	{
		try {
			FileReader fr = new FileReader("C:/test/data/users.txt");	
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			
			while((line=br.readLine())!=null)
			{
				String[] token = line.split("\\:");
				User u = new User(token);	
				user.add(u);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	// 텍스트를 로드 -> 메모리에서 수정 -> 메모리에서 수정된 내용을 기존 파일에 덮어쓰기
	private static void textUpdate() 
	{
		File f =  new File("C:/test/data/members.txt");
		if(!f.exists()) {
			System.err.println("지정된 파일이 없습니다."); return;
		}
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line = null;
			//List<Members> list = new ArrayList<>();
			while((line = br.readLine())!=null) 
			{
				String[] arr = line.split("\\|");
				mem.add(new Members(arr));
			}
			br.close();
			
			// chris의 전화번호를 010-0000-0000으로 업데이트
			Members key = new Members("Jane Doe");
			if(mem.contains(key)) {
				mem.get(mem.indexOf(key)).setMph("010-0000-0000");
			}
			
			// 메모리에서 변경된 데이터를 파일에 덮어씀
			PrintWriter out = new PrintWriter(new FileWriter(f));	// 덮어쓰기
			for(int i = 0; i < mem.size(); i++)
			{
				Members m = mem.get(i);
				String ln = String.format("%d|%s|%s|%s",
						m.getMno(),
						m.getMnm(),
						m.getMph(),
						m.getMem());
				//System.out.println("검사:" + ln);
				out.println(ln);
				out.flush();
			}
			out.close();
			System.out.println("전화번호 변경완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void textDelete() 
	{
		File f =  new File("C:/test/data/members.txt");
		if(!f.exists()) {
			System.err.println("지정된 파일이 없습니다."); return;
		}
		try {
			Members key = new Members("Jane Doe");
			if(mem.contains(key)) {
				mem.remove(key);
			}
			
			// 메모리에서 변경된 데이터를 파일에 덮어씀
			PrintWriter out = new PrintWriter(new FileWriter(f));	// 덮어쓰기
			for(int i = 0; i < mem.size(); i++)
			{
				Members m = mem.get(i);
				//System.out.println("검사:" + ln);
				out.printf("%d|%s|%s|%s%n",
						m.getMno(), m.getMnm(), m.getMph(), m.getMem());
				out.flush();
			}
			out.close();
			System.out.println("전화번호 삭제완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


/* 키보드에서 아이디, 암호를 입력받아 users.txt 파일에 한 행으로 추가하는 기능
 * 아이디:암호
 * 회원정보 추가 성공, users.txt 파일의 목록을 화면에 표시해서 확인 
 */