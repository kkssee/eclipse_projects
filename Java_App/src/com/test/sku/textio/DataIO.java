package com.test.sku.textio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DataIO 
{
	static String fpath = "C:/test/data/board.txt";
	
	public static boolean saveBoard(BoardVO b) 
	{
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(fpath, true));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String sDate = sdf.format(b.getRegDate());
			String line = String.format("%d|%s|%s|%s|%d|%s", 
					b.getNo(), b.getTitle(), b.getAuthor(), sDate, b.getHits(), b.getContents());
			pw.println(line);
			pw.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public static List<BoardVO> list() 
	{  
		try {
			BufferedReader br = new BufferedReader(new FileReader(fpath));
			String line = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<BoardVO> list = new ArrayList<>();
			while((line = br.readLine())!=null)
			{
				BoardVO b = new BoardVO(line);
				list.add(b);
			}
			br.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static int findBoard(int f)
	{
		List<BoardVO> list = list();
		BoardVO key = new BoardVO(f);
		
		if(list.contains(key))
		{
			int idx = list.indexOf(key);
			upHits(idx);
			return idx;
		}
		return 0;
	}
	public static boolean updateBoard(int idx, String title, String contents)
	{
		List<BoardVO> list = list();
		
		//BoardVO key = new BoardVO(title, contents);
		/*if(list.get(idx).) {
			mem.get(mem.indexOf(key)).setMph("010-0000-0000");
		}*/
		
		list.get(idx).setTitle(title);
		list.get(idx).setContents(contents);
		
		try {
			PrintWriter out = new PrintWriter(new FileWriter(fpath));
			for(int i = 0; i < list.size(); i++)
			{
				BoardVO b = list.get(i);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String sDate = sdf.format(b.getRegDate());
				
				String line = String.format("%d|%s|%s|%s|%d|%s", 
						b.getNo(), b.getTitle(), b.getAuthor(), sDate, b.getHits(), b.getContents());
				out.println(line);
				out.flush();
			}
			out.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	public static boolean deleteBoard(int idx)
	{
		List<BoardVO> list = list();
		
		try {
			list.remove(idx);
			
			PrintWriter out = new PrintWriter(new FileWriter(fpath));
			for(int i = 0; i < list.size(); i++)
			{
				BoardVO b = list.get(i); 
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String sDate = sdf.format(b.getRegDate());

				out.printf("%d|%s|%s|%s|%d|%s%n", 
						b.getNo(), b.getTitle(), b.getAuthor(), sDate, b.getHits(), b.getContents());
				out.flush();
			}
			out.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static int searchByTitle(String[] s) 
	{
		List<BoardVO> list = list();
		BoardVO key = new BoardVO(s);
		
		if(list.contains(key))
		{
			int idx = list.indexOf(key);
			upHits(idx);
			return idx;
		}
		return 0;
	}
	
	public static void upHits(int idx)
	{
		List<BoardVO> list = list();
		
		list.get(idx).setHits(list.get(idx).getHits()+1);
		
		try {
			PrintWriter out = new PrintWriter(new FileWriter(fpath));
			for(int i = 0; i < list.size(); i++)
			{
				BoardVO b = list.get(i);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String sDate = sdf.format(b.getRegDate());
				
				String line = String.format("%d|%s|%s|%s|%d|%s", 
						b.getNo(), b.getTitle(), b.getAuthor(), sDate, b.getHits(), b.getContents());
				out.println(line);
				out.flush();
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
