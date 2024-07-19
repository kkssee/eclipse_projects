package com.test.sku.pet;

import java.util.List;
import java.util.Scanner;

public class UserIO 
{
	static Scanner kbd = new Scanner(System.in);
	public static String showMenu() 
	{
		System.out.print("추가(a) 목록(s) 검색(f) 수정(u) 삭제(d) 종료(x): ");
		String m = kbd.nextLine().trim();
		return m;
	}
	
	public static void add() 
	{ 
		while(true)
		{
			System.out.print("number:");
			int no = kbd.nextInt();    kbd.nextLine();
			
			if(FileIO.findByNo(no)!=null) {
				System.err.println("This number already exists !");
				continue;
			}
			
			System.out.print("species:");
			String species = kbd.nextLine();
			
			System.out.print("weight:");
			float weight = kbd.nextFloat();    kbd.nextLine();
			
			System.out.print("price:");
			int price = kbd.nextInt();    kbd.nextLine();
			
			PetVO p = new PetVO();
			p.setNo(no);
			p.setSpecies(species);
			p.setWeight(weight);
			p.setPrice(price);
			
			boolean added = FileIO.addObject(p);
			if(added) System.out.println("added successfully");
			else System.err.println("The pet not added");
			return;
		}
	}
	public static void list() 
	{
		System.out.println("\n\t     *** List ***");
		System.out.printf("%-5s%-15s%-12s%s%n", "no.", "species", "weight", "price");
		System.out.println("---------------------------------------");  
		List<PetVO> list = FileIO.getList();
		
		for(int i = 0; i < list.size(); i++)
		{
			System.out.println(list.get(i));
		}
		System.out.println();
	}
	
	public static void find() 
	{
		System.out.print("Search by number(n), by species(s): ");
		String c = kbd.nextLine().trim();
		switch(c)
		{
			case "n": {
				System.out.print("Search by number: ");
				int n = kbd.nextInt(); kbd.nextLine();
				PetVO p = FileIO.findByNo(n);
				if(p != null) {
					System.out.println(p);
				} else System.err.println("Not Found !");
				break;
			}
			case "s": {
				System.out.print("Search by species: ");
				String s = kbd.nextLine().trim();
				FileIO.findBySpecies(s); 
				PetVO p = FileIO.findBySpecies(s);
				if(p != null) {
					System.out.println(p);
				} else System.err.println("Not Found !");
				break;
			}
			default: System.err.println("Wrong input!\n"); return;
		}
		
		
	}
	public static void update() 
	{
		System.out.print("Search by number: ");
		int n = kbd.nextInt(); kbd.nextLine();
		PetVO p = FileIO.findByNo(n);
		if(p==null) {
			System.err.println("Not Found !");
			return;
		}
		
		List<PetVO> list = FileIO.getList();
		System.out.println(list.get(list.indexOf(p))+ "\n");
		
		System.out.print("update?(Y/N) ");
		String c = kbd.nextLine().trim(); 
		
		if(c.equals("Y") || c.equals("y")) {
			System.out.print("Weight:");
			float newWeight = kbd.nextFloat(); kbd.nextLine();
			
			System.out.print("Price:");
			int newPrice = kbd.nextInt(); kbd.nextLine();
			
			PetVO np = new PetVO();
			np.setNo(n);
			np.setWeight(newWeight);
			np.setPrice(newPrice);
			
			boolean updated = FileIO.update(np);
			if(updated) System.out.println("updated successfully");
			else System.err.println("Not Updated !\n");
		} else System.out.println("Update canceled\n"); return;
	}
	public static void delete() 
	{
		System.out.print("Search by number: ");
		int n = kbd.nextInt(); kbd.nextLine();
		PetVO p = FileIO.findByNo(n);
		if(p==null) {
			System.err.println("Not Found !\n");
			return;
		}
		
		List<PetVO> list = FileIO.getList();
		System.out.println(list.get(list.indexOf(p)));

		System.out.print("delete?(Y/N) ");
		String c = kbd.nextLine().trim();
		
		if(c.equals("Y") || c.equals("y")) {
			boolean deleted = FileIO.delete(p);
			if(deleted) System.out.println("deleted successfully");
			else System.err.println("Not deleted !\n");
		} else System.out.println("Delete canceled\n"); return;
	} 

}
