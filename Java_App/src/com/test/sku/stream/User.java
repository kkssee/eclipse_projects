package com.test.sku.stream;

/* 파일을 사용한 로그인 기능
 * C:/test/data/users.txt
 * smith:1234 형식으로 5개 행 생성
 * 이용자가 키보드에서 로그인하면 User 클래스의 인스턴스를 사용하여 id, pwd를 저장하고
 * 파일에서 로드된 List<User> 자료구조와 비교하여 회원정보에 존재하는지 확인
 * 로그인 성공/실패 메시지 출력
 */

class User
{
	private String id;
	private String pwd;
	
	public User() {}
	public User(String[] token) {
			String uid = token[0];
			String upwd = token[1];
			
			setId(uid);
			setPwd(upwd);
	}
	public User(String id, String pwd) {
		setId(id);
		setPwd(pwd);
	}
	
	@Override
	public String toString() {
		String str = String.format("%s\t%s",
				this.getId(),						
				this.getPwd());
		return str;
	}
	@Override
	public boolean equals(Object obj) {
		User other = (User) obj;		// obj형 -> member형으로 형변환(부모 변환->자식형으로 변환)
		
		return this.getId().equals(other.getId()) && this.getPwd().equals(other.getPwd());			// this.getNum()==other.getNum() 의 결과 :boolean을 우리한테 return
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
	
}
