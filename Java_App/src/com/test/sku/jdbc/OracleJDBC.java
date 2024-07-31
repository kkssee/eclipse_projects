package com.test.sku.jdbc;


public class OracleJDBC {
   public static void main(String[] args) {
	  EmpDAO dao = new EmpDAO();
	 /*List<EmpVO> list = dao.getList();
      for(EmpVO emp : list) {
    	  System.out.println(emp);
      }*/
      
      //EmpVO emp = new EmpVO(7369);
      //emp.setSal(880);
      
      //boolean updated = dao.updateSal(emp);
      //System.out.println("수정결과: " + updated);
      
      //boolean added = dao.add();
      //System.out.println("추가결과: " + added);
      
      dao.findByEmpno();
   }
}
