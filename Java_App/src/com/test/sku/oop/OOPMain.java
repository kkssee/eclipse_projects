package com.test.sku.oop;

import java.awt.RenderingHints.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

//유사한 형태의 object를 저장하는 클래스를 만들어서
// 그 클래스를 여러 번 load 시키면 한 페이지에 같은 부분이 계속 출력되게 할 수 있음
// static 변수는 1번만 저장되기 때문에 여러 번 저장할 수가 없으므로 instance변수를 사용해야 함
// main class가 public이어야 함_ 1개의 파일에는 public이 1개만 존재


/*
public class OOPMain 
{
	
static Scanner kbd = new Scanner(System.in);
	
	public static void main(String[] args) 
	{
//		Board b = new Board();		// 인스턴스 생성(객체 생성), Heap에 로드
//		b.num = 1;					// new Board() : 새로 Board라는 인스턴스를 생성해! () 단, 파라미터는 없어!
//		b.title = "게시판 Test";
//		b.author = "Smith";
		
		//Board b = new Board(-1, "게시판 Test", "Smith");	// 생성자2가 파라미터 3개인 생성자이므로 한 번에 쓸 수 있음 
		//b.title = "다른 제-목으로 수정";				// 변수 앞에 아무것도 안써주니  외부에서 main으로 들어와서 수정이 가능해짐 
												// -> 이걸 막기 위해 private 사용하는데 ,이러면 읽지도 못함(출력 불가)
		
//		Board b1 = new Board();
//		b1.num = 2;	// 얼마든지 여러번 가능
//		b1.title= "축하해요";
//		b1.author = "James";
		
		//Board b1 = new Board(2, "축하해요", "James");	
		
		//Board b2 = new Board();
		//b2.num = 3;	
		//b2.title = "사랑해요";
		//b2.author = "Romeo";
		
		//Board b2 = new Board(3, "사랑해요", "Romeo");	
		
		// 화면에 출력되기 위해서는 배열이 되어야 함
		// 이 객체들을 배열에 담기_ Borad[]
		
//		Board[] barr;				// array 생성
//		barr = new Board[3];		// arr에 메모리 할당
//		barr[0] = b;				// arr 초기화
//		barr[1] = b1;
//		barr[2] = b2;
		
//		for(int i=0; i<barr.length; i++)
//		{
//			System.out.printf("%d\t%s\t%s %n", 
//					barr[i].getNum(), barr[i].getTitle(),barr[i].getAuthor());
			
			// 화면에 출력되는 것은 무조건 memory에 저장되어있어야 하는 것임
		
		User u = getUser();
		String msg = login(u) ?"로그인 성공":"로그인 실패";
		System.out.println(msg);
		
	  } //end of main method

	private static User getUser()
	{	
		System.out.print("아이디 암호: ");
		String input = kbd.nextLine().trim();
		String[] data = input.split("\\s+");
		User u = new User(data[0], data[1]);
		return u;
	}

	private static boolean login(User u)
	{
		return u.getId().equals("smith") && u.getPwd().equals("1234");
	}

}
*/



/*
class Board //3대 특징 : 상속성, 다형성, 은닉성
{
	// 실물 객체 추상화_ 필요한 속성(ex)번호, 제목, 작성자, 본문, 작성일, 조회수)들만 나열
	// ex)게시판 _ 번호, 제목, 작성자, 본문, 작성일, 조회수_가 목록으로 출력되게끔
	// 속성들을 member 변수로 표현
	// private : 변수에 은닉성을 부여_but private은 메모리에 자동 load X -> 따라서 new 명령으로 메모리에 load
	// main()에 Board b = new Board();	하면 Board 안의 모든 변수들이 메모리에 load
	// → 이 멤버들이 저장된 저장소=heap 이라고 함_ 그 메모리의 주소가 b에 들어옴
	// main 메소드에서 부르기 위해 b.__ 하면 __에 멤버들이 보이지 않음 
	//Board Class 밖에서 초기화_ 여기서는 main메소드에서 _ 이걸 한줄로 자동으로 초기화 해주는 로직이 생성자
	// 생성자는 Class와 동일한 이름을 가짐
	
	private int num;			
	
	private String title;
	private String author;
	private String contents;
	private Date rDate;
	private int hits;
	
	// 이 상태에서는 외부 접근 불가이기 때문에 아무것도 할 수 없지만, 이 상황에서 각 변수의 데이터만 읽어올 수 있게 하는 방법을 이용
	//get() 이용
	
	

	
	// 생성된 인스턴스 멤버 변수를 초기화하는 생성자 // 파라미터_외부에서 전달되어 들어오는 값
	// 인스턴스가 생성된 직후에 자동으로 호출됨
	// 개발자가 생성자를 정의하지 않으면 컴파일러는 자동으로 기본 생성자를 선언함
	public Board() {}	
	
	//	this : 파라미터의 첫번째 값이 들어옴 , int num;이 로드된 위치 = this
	
	public Board(int num, String title, String author) {	//기본생성자(Constructor)_ 초기화 
															
//		this.num = num;				// num 앞에 데이터 필터링 로직 필요-> set() 이용(아래)
//		this.title = title;
//		this.author = author;
		
		setNum(num);
		setTitle(title);
		setAuthor(author);		// 위처럼 하면 아무리 set ( ) 써도 적용이 안되기때문에 setNum(num) 처럼 써서 사용해야 set메소드에 경유함
								// 데이터가 setting할 때 쓰이는 메소드라 return값이 없음 
	}
	
	
	public int getNum() {
		return num;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getContents() {
		return contents;
	}

	public Date getrDate() {
		return rDate;
	}

	public int getHits() {
		return hits;
	}

	// -------------------------------------------------------------------------
	
	public void setNum(int num) {
		if(num<=0)
		{
			// 할당하지 않고, 경고 띄우기
			System.err.println("num 초기화 실패 (글번호는 0보다 커야 합니다.)");
			return;			//return 쓰면 초기화 없이 end
		}
		this.num = num;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public void setrDate(Date rDate) {
		this.rDate = rDate;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}
}

	// 모든 클래스는 참조변수의 자료형이 될 수 있음 
	// 키보드에서 입력된 아이디, 암호를 사용하여 User 클래스의 인스턴스를 초기화하고
	// boolean login(User u)메소드를 작성하여 로그인 성공여부를 확인하는 기능을 작성해보세요
	// User 클래스 필요_모든 변수에 Encapsulation 적용하면 아무것도 못하니까, set(), get() 사용해야 함
class User 		// data 클래스에는 데이터만 넣어야 함_ 실행 로직은 method에 넣는 것
{
	private String id;
	private String pwd;
	
	public User() {}
	
	public User(String id, String pwd) 
	{
		this.id = id;
		this.pwd = pwd;
	}
	
	public String getId() {
		return id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

*/

// 0710 수업내용


public class OOPMain 
{
	
	static Scanner kbd = new Scanner(System.in);
	//static Member[] mems = new Member[4]; 
	static List<Member> mems = new ArrayList<>();		// 컬렉션 API 사용
	// static int cnt = 0;

	public static void main(String[] args)
	{
		//listSearch();	
		//3인 정보를 입력하고, list에서 검색하여 번호가 11번인 회원 정보를 화면에 표시
		
		//System.out.print("프로그램 종료");			
		//listDelete();
		initMems();
		//getMember();
		listUpdate();
		//removePhoneByNo();
		//memList();
	} 
	
	private static void initMems() 
	{
		mems.add(new Member(1, "smith", "010-1111-1111", "smith@gmail.com"));
		mems.add(new Member(2, "jackson", "010-2222-2222", "jackson@naver.com"));
		mems.add(new Member(3, "chris", "010-3333-3333", "evans@hanmail.com"));
	}
	private static void removePhoneByNo() 
	{
		// 전화번호 갱신 대상 회원의 번호, 새 전화번호
		/*
		System.out.println("회원번호를 입력하세요: ");
		int inputNum = kbd.nextInt();
		kbd.nextLine();
		Member uPh = listSearch(inputNum);
		System.out.println("새 전화번호를 입력하세요: ");
		String sNo = kbd.nextLine();
		
		Member key = new Member(inputNum, "laura", sNo, "jdsflkaf@jdskla");
		//System.out.println("새 전화번호를 입력하세요.");
		//String sNo = kbd.nextLine();
		if(mems.contains(key))
		{
			Member rem = mems.set(mems.indexOf(key), key);
			if(rem != null) System.out.println("수정성공");
			else System.err.println("수정실패");
		}
		
		memList();*/
		
		System.out.print("변경대상 회원번호: ");
		int no = kbd.nextInt(); kbd.nextLine();
		
		System.out.print("새 전화번호: ");
		String phone = kbd.nextLine();
		
		Member key = new Member(no, phone);
		if(mems.contains(key))
		{
			if(mems.contains(key))
			{
				int idx = mems.indexOf(key);
				mems.get(idx).setPhone(key.getPhone());
				System.out.println("전화번호 변경 성공");
			} else {
				System.err.println("전화번호 변경 실패");
			}
			memList();
		}
	}
	private static void listTest()	
	{
		// List 사용하기
		// List 중 가장 많이 사용되는 것은 Array List임
		// List<자료형> 변수명 = new 객체명<>();
		// <>를 가진 클래스 => 일반화클래스 (자료형이 특정화되지 않은 클래스를 의미)
		
		List<String> sList = new ArrayList<>();
		// CRUD(데이터 생성- create)
		sList.add("hello ");  							// add로 List에  값 저장
		sList.add("World ");
		sList.add("홍길동 ");
		sList.add("홍길동 ");
				
		//출력
		for(int i=0; i< sList.size();i++)
		{
			String v = sList.get(i);					// i = 인덱스 , // 데이터 읽어오기 (Read)
			System.out.print(v); 
		}
		
		System.out.println(sList.contains("World"));	// boolean 값으로 출력 _ 
														// contains _ sList에 들어있는지 검색할때 boolean 값으로 알아보는 메소드 
			//	안에서 돌아갈 떄는 equals() 사용해서 비교(m 오브젝트를 비교할때 equals 선언하지 않아도 Object 클래스에 이미 equals 들어있음)
		
		int idx = sList.indexOf("World");				// World가  들어있다면, 그 인덱스 번호 출력
		String value = sList.get(idx);					// 그 방번호를 이용해서 get()를 이용해서 value에 저장 후 read
		System.out.println("검색 결과:" + value);			
				
		sList.set(0, "임꺽정");		//set : 수정할때
		
		// 위의 걸 배열로 만들어서 toString 올려보자
		System.out.println(Arrays.toString (sList.toArray()));
		
		// delete :	List.remove(sList의 값)

		sList.remove("홍길동"	);					// 값으로 remove
		sList.remove(2);						// 방번호(인덱스)로 remove
		System.out.println(Arrays.toString (sList.toArray()));

		
		// 이 list 안에는 문자형만 저장이 가능_ <String>으로 생성했기 때문
		// List : 부모
		// 부모의 참조변수에 자식도 저장
		
		//Object obj = "Hello";	// 이 반대는 안됨_부모는 자식을 받아줄 수 있으나, 자식은 부모를 받아줄 수 없음
		//String st = new Object(); // (x) 자식("Hello")은 부모(obj)를 허용하지 않음 
	
		
	}
	private static Member listSearch(int inputNum)
	{
		//getMember();			//회원 정보를 입력받아서 mems라는 list에 저장(mems는 맨 위에 선언되어 있음)
		
		//Member key = new Member(11, "smith" , "111", "aaa@aaa.com");
		// 회원번호만 쓸 수 있는 생성자가 1개 더 필요_뒤의 정보는 어차피 필요 없으므로
		
		
		Member key = new Member(inputNum);

		if(mems.contains(key))			//key에서 회원번호로 비교해하게 만들어놓음
		{
			int idx = mems.indexOf(key);				//  memobj : 검색의 key
			Member found = mems.get(idx);				// get()에 idx 번호 줘서 object 가져오는 것
			System.out.println(found);					// 가져온 object를 화면에 출력하라
		}
		else System.out.println("존재하지 않는 번호입니다.");
		
		return key;
	}
	
	private static void listUpdate() {
		Member key = new Member(11, "laura", "234234324", "jdsflkaf@jdskla");
		System.out.println("새 전화번호를 입력하세요.");
		String sNo = kbd.nextLine();
		if(mems.contains(key))
		{
			Member rem = mems.set(mems.indexOf(key), key);
			if(rem != null) System.out.println("수정성공");
			else System.err.println("수정실패");
		}
		
		memList();
	}
	private static void listDelete() 
	{
		Member key = new Member(11);
		boolean removed = mems.remove(key);
		System.out.println("삭제결과: " + removed);
		memList();
	}	
	private static void getMember()
	{	
		
		while(true)
		{
			System.out.print("회원번호, 이름, 전화번호, 이메일을 입력하세요: ");
		
			String input = kbd.nextLine().trim();
			if(input.equals(""))	break;
			String[] data = input.split("\\s+");		//String[]	~String mEmail까지는 아래 Member m을 위한 것
			/*
			int mNum = Integer.parseInt(data[0]);
			String mName = data[1];
			String mPhone = data[2];
			String mEmail = data[3];
			
		
			Member m = new Member(mNum, mName, mPhone, mEmail);		//Member 인스턴스(객체)생성하면서 (파1,파2,파3,파4)를 넣고, 그 메모리 주소를 m에 저장
			*/
			
			
			// 위의 5줄을 한줄로 바로 인스턴스에 넣어서 사용 가능 ↓
			Member m = new Member (data);		//생성자가 없어서 에러발생_ tool 필요_public Member(String[] data) {} 생성해서 에러 사라짐
												// Constructor Overload : 생성자 중복정의 
			
 			//mems[cnt++] = m;					// m을 mems라는 array에 저장해야 cnt가 1씩 증가한다
			mems.add(m);					// 배열 -> list로 변경, list에서 추가는 add이므로 list명.add(변수)	
			
		}
	}	
	private static void memList()
	{
		//for(int i=0; i<cnt; i++)
		System.out.println("\t*** 회원정보 ***");
		System.out.println("번호\t이름\t전화번호\t\t이메일");
		for(int i=0; i<mems.size(); i++)			// 위에서 배열을 사용x -> cnt사용하지 x 때문에 크기를 mems.size()로 바꿔줘야 함
		{
			System.out.println(mems.get(i));
			
			//mems array를 출력하면  mems[i].getNum(), mems[i].getName(),mems[i].getPhone(),mems[i].getEmail() 나오도록 하려면?
			// mems[i] = 참조는 heap메모리에 들어있는 객체의 주소, mems는 그 주소들을 원소로 하는 집합 = 참조변수
			// mems[i] = 참조 1개를 의미_ 객체를 어떤형식으로 출력하라는 거? → com은 객체의 메모리 주소가 나옴 → 결과) com.test.sku.oop.Member@7cc355be :'7cc355be에 만들어진 Member 오브젝트'다!
			// mems[i] 를 출력시 mems[i].getNum()등의 결과와 같이 나오게 하려면?
			// println(mems[i]) → 'toString()'가 실행됨, 그래서 객체가 문자열로 표현됨(메모리 주소) ⇒ 'toString()' → toString()의 Method Override 요구됨
			// toString()의 Method Override → 아래에 class Member 밑에 참고
				
			
			/*
			System.out.printf("%d. %s\t%s\t%s %n",				// Override :이미 존재하는 메소드를 개조하는 방법
								mems[i].getNum(),						
								mems[i].getName(),
								mems[i].getPhone(),
								mems[i].getEmail()); */
		}
	}
}
	

	
	/* 키보드에서 회원의 번호, 이름, 전화, 이메일을 입력받아서
	 * Member인스턴스를 생성하고 배열에 저장한 후에 이용자가 아무것도 입력하지 않고
	 * 그냥 엔터를 누르면 지금까지 입력된 회원정보를 화면에 목록으로 표시하는 기능을 작성해보세요.
	 * Member라는 클래스 만들고, 번호, 이름, 전화, 이메일 변수를 만들고, 
	 * 정보가 들어올 때마다 인스턴스에 계속 생성되고, 그걸 배열에 저장
	 */

	
	

class Member extends Object		//Inheritance(상속_ 안쓰면 알아서 상속함)
{ 
	private int num;
	private String name;
	private String phone;
	private String email;
	
	
	// 생성자 여러개 생성 = 생성자 오버로드
	public Member () {}
	
	public Member(int num) 
	{
		setNum(num);
	}

	public Member(int num, String name, String phone, String email) 
	{
		setNum(num);
		setName(name);
		setPhone(phone);
		setEmail(email);
			
	}

	public Member(String[] data) { // 생성자 오버로드	(Constructor Overload) 목적 : 객체 생성을 다양한 방법으로 할 수 있도록 편의 제공
									// Overload 문법을 충족시키려면, 파라미터만 다르면 됨
		// 배열을 받아서 원소 초기화
		int mNum = Integer.parseInt(data[0]);		// Integer.parseInt: 숫자형의문자열을 십진수로 변환해주는 것
		String mName = data[1];
		String mPhone = data[2];
		String mEmail = data[3];
		
		// 기본 생성자의 파라미터에 있는 멤버 데려와서 넣어주기
		setNum( mNum );		//여기 () 파라미터는 위의 int mNum ...에 있는 변수들이 들어가야 함
		setName(mName);
		setPhone(mPhone);
		setEmail(mEmail);
	}
	
	
	public Member(int no, String phone2) {
		setNum(no);
		setPhone(phone2);
	}

	// toString()의 개조 = 마우스 우클릭 → source → override/implement methods → toString() 선택
	// JAVA 클래스는 생성하면 object 메소드를 컴파일러가 자동으로 상속하기 때문에 그 안에 있는 것들을 모두 저장
	// class Member extends Object가 필수로 쓰여야 하는데 개발자가 쓰지 않으면, 컴파일러가 알아서 써줌
	// 즉, Member는 최상위 클래스인 Object라는 클래스를 가져와서 그 Member안에 넣고, Object가 가진 메소드, 변수를 모두 가짐
	// 따라서 Object를 확장한 Member가 기능면에서 더 큼_ ★ extends = inheritance(상속)을 의미 ★
	
	/*String.format("%d. %s\t%s\t%s %n",			
					this.getNum(),this.getName(),this.getPhone(),this.getEmail()); */ 
	// 이렇게 넣어주고, 그걸 변수 str에 넣어서 return하기
	
	// @Override : 개발자가 override 잘했는지 com이 검사를 해줌 _ 필수는 아니지만 있으면 좋음
	
	
	
	//equals(Object obj) : Object에서 상속받은 equals
	// Object obj : 부모형이 되어버리면 Member에 들어있는 걸 쓸 수 없어서 원래 형으로 casting 해줘야 함
	@Override
	public boolean equals(Object obj) {
		Member other = (Member) obj;		// obj형 -> member형으로 형변환(부모 변환->자식형으로 변환)
		
		return this.getNum()==other.getNum();			// this.getNum()==other.getNum() 의 결과 :boolean을 우리한테 return
	}													// 이렇게 바꾸면 contains()가 작동함

	@Override
	public String toString() {
		String str = String.format("%d\t%s\t%s\t\t%s",
				this.getNum(),						
				this.getName(),
				this.getPhone(),
				this.getEmail()); 
		return str;
	}
 
	public int getNum()
	{	
		return num;
	}
	public String getName() 
	{
		return name;
	}
	public String getPhone() 
	{
		return phone;
		
	}
	public String getEmail() 
	{
		return email;
	}

	public void setNum(int num) {
		this.num = num;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
