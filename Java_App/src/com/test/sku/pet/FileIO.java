package com.test.sku.pet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FileIO 
{
	static String serPath = "C:/test/data/pet.ser";
	
	public static void initPets() 
	{
		List<PetVO> p = new ArrayList<>();
		p.add(new PetVO(1,"dog",12,400));
		p.add(new PetVO(2,"Persian",50,102000));
		p.add(new PetVO(3,"Lion",78,540000));
		p.add(new PetVO(4,"monkey",26,786000));
		p.add(new PetVO(5,"tiger",99,245000));
		
		overwrite(p);
	}

	public static boolean addObject(PetVO pet)
	{
		List<PetVO> list = getList();
		list.add(pet);
		return overwrite(list);
	}
	
	private static boolean overwrite(List<PetVO> list) 
	{
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(serPath)); 
			oos.writeObject(list);
			oos.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static List<PetVO> getList() 
	{
		try {
			File ser = new File(serPath);
			if(!ser.exists())	// 직렬화 파일이 없는 경우
			{
				List<PetVO> list = new ArrayList<>();
				overwrite(list);
			}
			
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(serPath));
			List<PetVO> list = (List<PetVO>)ois.readObject();
			ois.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static PetVO findByNo(int n) {
		List<PetVO> list = getList();
		PetVO key = new PetVO(n);
		if(list.contains(key)) {
			int idx = list.indexOf(key);
			return list.get(list.indexOf(key));
		}
		return null;
	}
	public static PetVO findBySpecies(String s) {
		List<PetVO> list = getList();
		for(int i=0;i<list.size();i++) {
			PetVO p = list.get(i);
			if(list.get(i).getSpecies().contains(s)) 	
				return list.get(i);
		}
		return null;
	}
	public static boolean update(PetVO np) {
		List<PetVO> list = getList();
		if(list.contains(np)) {
			PetVO found = list.get(list.indexOf(np));
			found.setWeight(np.getWeight());
			found.setPrice(np.getPrice());
			return overwrite(list);
		}
		return false;
	}
	public static boolean delete(PetVO p) {
		List<PetVO> list = getList();
		if(list.contains(p)) {
			list.remove(p);
			return overwrite(list);
		}
		return false;
	}

}
