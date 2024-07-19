package com.test.sku.stream;

/* C:/test/data/members.txt
 * 한 행에 한 회원의 정보가 기록되어 있다
 * 번호|이름|전화|이메일
 * String[] token = line.split("\\s|");
 * List<Member> list;
 * 텍스트파일(members.txt)에서 목록을 가져와서 화면에 표시
 */

class Members
{
	private int mno;
	private String mnm;
	private String mph;
	private String mem;
	
	public Members() {}
	public Members(String[] token) {
			int mno = Integer.parseInt(token[0]);
			String mnm = token[1];
			String mph = token[2];
			String mem = token[3];
			
			setMno(mno);
			setMnm(mnm);
			setMph(mph);
			setMem(mem);
	}
	public Members(String mnm) {
		this.mnm = mnm;
	}
	/*@Override
	public String toString() {
		String str = String.format("%d\t%s\t%s\t%s",
				this.getMno(),						
				this.getMnm(),
				this.getMph(),
				this.getMem());
		return str;
	}*/
	@Override
	public boolean equals(Object obj) {
		Members other = (Members) obj;
		return this.getMnm().equals(other.getMnm());
	}
	
	public int getMno() {
		return mno;
	}
	public String getMnm() {
		return mnm;
	}
	public String getMph() {
		return mph;
	}
	public String getMem() {
		return mem;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public void setMnm(String mnm) {
		this.mnm = mnm;
	}
	public void setMph(String mph) {
		this.mph = mph;
	}
	public void setMem(String mem) {
		this.mem = mem;
	}
	
}

