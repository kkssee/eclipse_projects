package com.test.sku.textio;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class UserIO 
{
	static Scanner kbd = new Scanner(System.in);
	static String menu = "추가(a) 목록(s) 검색(f) 수정(u) 삭제(d) 종료(x): ";
	
	static String showMenu() 
	{
		System.out.print(menu);
		String m = kbd.nextLine().trim();
		return m;
	}

	public static void add() 
	{	// 입력 -> 파일에 저장 -> 성공/실패 메시지
		System.out.print("글번호: ");
		int no = kbd.nextInt();	kbd.nextLine();
		
		System.out.print("제목: ");
		String title = kbd.nextLine();
		
		System.out.print("작성자: ");
		String author = kbd.nextLine();
		
		System.out.print("글내용: ");
		String contents = kbd.nextLine();

		Date regDate = new Date();
		
		// VO에 담아서 파일에 저장(VO, DataIO)
		BoardVO board = new BoardVO();
		board.setNo(no);
		board.setTitle(title);
		board.setAuthor(author);
		board.setContents(contents);
		board.setRegDate(regDate);
		board.setHits(0);
		
		boolean saved = DataIO.saveBoard(board);
		if(saved) System.out.println("\t\t저장 성공");
		else System.err.println("\t\t저장 실패");
	}
	public static void list() 
	{	
		List<BoardVO> list = DataIO.list();
		System.out.println();
		System.out.println("\t\t***게시글 목록***");
		
		for(int i = 0; i < list.size(); i++)
		{
			System.out.println(list.get(i));
		}
		
		System.out.println();
	}
	public static void find() 
	{	
		System.out.print("글번호 검색: ");
		int f = kbd.nextInt();	kbd.nextLine();
		
		int idx = DataIO.findBoard(f);
		List<BoardVO> list = DataIO.list();

		if(list.get(idx) != null)
		{
			System.out.println(list.get(idx));
			System.out.println();
		} else {
			System.err.println("검색된 글이 없습니다.");
		}
	}
	public static void update() 
	{
		System.out.print("수정할 글번호 / 제목: ");
		
		List<BoardVO> list = DataIO.list();
		String input = kbd.nextLine().trim();
		String[] s = input.split("\\s+");
		
		int idx = DataIO.searchByTitle(s);
		System.out.println(list.get(idx));
		
		System.out.print("수정하시겠습니까?(Y/N) ");
		String c = kbd.nextLine().trim();
		
		if(c.equals("Y")) {
			System.out.print("제목: ");
			String title = kbd.nextLine();
			System.out.print("내용: ");
			String contents = kbd.nextLine();
			
			boolean saved = DataIO.updateBoard(idx, title, contents);
			if(saved) System.out.println("\t\t수정 성공");
			else System.err.println("\t\t수정 실패");
		} else System.out.println("취소"); return;
	}
	public static void delete() 
	{
		System.out.print("삭제할 글번호 / 제목: ");
		
		List<BoardVO> list = DataIO.list();
		String input = kbd.nextLine().trim();
		String[] s = input.split("\\s+");
		
		int idx = DataIO.searchByTitle(s);
		System.out.println(list.get(idx));
		
		System.out.print("삭제하시겠습니까?(Y/N) ");
		String c = kbd.nextLine().trim();
		
		if(c.equals("Y")) {
			boolean deleted = DataIO.deleteBoard(idx);
			if(deleted) System.out.println("\t\t삭제 성공");
			else System.err.println("\t\t삭제 실패");
		} else System.out.println("취소"); return;
	}
}
