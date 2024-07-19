package com.test.sku.pet;

public class PetMain 
{
	public static void main(String[] args) 
	{
		/* Pet Store 관리 프로그램
		 * Pet(no, species, weight, price)
		 * pets, data
		 * 이용자 입출력, 파일 입출력, PetVO, PetMain
		 * 추가(a) 목록(s) 검색(f) 수정(u) 삭제(d) 종료(x)
		 */
		
		boolean go = true;
		FileIO.initPets();
		
		while(go)
		{
			String m = UserIO.showMenu();
			
			switch(m)
			{
				case "a": UserIO.add(); break;
				case "s": UserIO.list(); break;
				case "f": UserIO.find(); break;
				case "u": UserIO.update(); break;
				case "d": UserIO.delete(); break;
				case "x": go = false; break;
			}
		}
		System.out.println("\t\t프로그램 종료");
	}
}
