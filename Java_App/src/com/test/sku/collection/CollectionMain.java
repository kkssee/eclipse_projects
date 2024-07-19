package com.test.sku.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.test.sku.list.Employee;

public class CollectionMain {

	public static void main(String[] args) 
	{
		// Set, Map
		/* List : 유순, 중복
		 * Set : 무순, 중복불가
		 * Map : key, value가 쌍으로 저장 => 검색성능최고
		 * */
		
		mapTest();
	}
	

	private static void setList() 
	{
		Set<String> set = new HashSet<>();
		set.add("Smith");
		set.add("James");
		set.add("Julie");
		set.add("Laura");
		set.add("James");
		
		//System.out.println("원소의 개수: " + set.size()); // 4 
		
		Iterator<String> it = set.iterator();
		while(it.hasNext())
		{
			//String value= it.next();
			//System.out.println(value);
			
			// Collection은 오브젝트만 원소로 받음
			// 그러므로 원칙적을 기본형 데이터 저장할 수 없다
			//Set<Integer> iSet = new HashSet<>();
			//Integer one = Integer.valueOf(1);	// 기본형 데이터는 각 자료형의 Wrapper 오브젝트를
												//생성하여 Collection에 저장할 수 있다
			//iSet.add(one);
			// Auto-Boxing(기본형 데이터를 그대로 저장하는 것이 아니라 내부에서
			// Wrapper 클래스를 사용하여 오브젝트를 생성하고 그 참조를 컬렉션에 저장
			// => iSet.add(2); == iSet.add(Integer.valueOf(2));
			
			break;
		}
		
		/*// 무작위 정수(1~20)를 중복되지 않게 10개 추출
		Random rd = new Random();
		Set<Integer> rSet = new HashSet<>();
		while(rSet.size()<10) rSet.add(rd.nextInt(20)+1);
		System.out.println(rSet);
		
		// 리스트로 정렬
		List<Integer> rList = new ArrayList<>(rSet);
		Collections.sort(rList);
		System.out.println(Arrays.toString(rList.toArray()));*/
		
		// 중복되지 않도록 Employee 오브젝트를 저장하려고 한다
		// Employee 오브젝트 2개를 생성할 때 사번을 동일하게 설정하여 Set에 저장해보세요
		Set<Employee> empSet = new HashSet<>();	// Hash 들어가 있으면 HashCode무조건 쓰임
		
		Employee emp1 = new Employee(11);
		Employee emp2 = new Employee(11);
		
		empSet.add(emp1);
		empSet.add(emp2);
		
		//List<Employee> empList = new ArrayList<>(empSet);
		System.out.println("원소의 개수: " + empSet.size());	
		// 2? Set인데 중복허용? => equals 했을 때 같은 값의 오브젝트는 해쉬 코드의 값도 같아야함
		/*•If two objects are equal according to the equals method, 
		 * then calling the hashCode method on each of the two objects 
		 * must produce thesame integer result. */
		// employee 클래스에 해쉬코드 오버라이드 고쳐주면 1 됨	
	}
	private static void mapTest() 
	{
		// map: key, value가 연결되어 쌍으로 저장되는 자료구조 => 검색성능 좋음
		// key를 해싱하여 value가 저장되는 위치 계산
		
		Map<String, String> map = new HashMap<>();
		map.put("smith", "010-1111-1111");
		map.put("james", "010-2222-2222");
		map.put("evans", "010-3333-3333");
		map.put("laura", "010-4444-4444");
		map.put("julie", "010-5555-5555");
		
		System.out.println(map.get("smith"));
		
		
	}
}

