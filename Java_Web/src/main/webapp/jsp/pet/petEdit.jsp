<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.test.sku.pet.PetVO"%>
<%
   int no = (int)request.getAttribute("no");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>펫 수정</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" 
integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" 
crossorigin="anonymous"></script>
<script type="text/javascript">
$(function() {
    console.log('jQuery ready');
 });
 
 function updatePet() {
	 let cmd = $('#cmd').val();	// document.querySelector('#cmd').value
	 let no = $('#no').val();
	 let price = $('#price').val();
	 let weight = $('#weight').val();
	 let pic = $('#pic').val();
	 
	 let obj = {};	// JSON 표현식
	 obj.cmd = cmd;
	 obj.no = no;
	 obj.price = price;
	 obj.weight = weight;
	 obj.pic = pic;
	 
	// 비동기 js 요청(응답을 변수에 저장할 수 있다)
	 $.ajax({ // 어떤 서버에 어떤 데이터를 전송하고 응답은 어떤 변수에 받겠다
		url : 'pet',
		method: 'post',
		cache: 'false',
		data:obj,
		dataType:'json',
		success:function(res){
			alert(res.updated?'update success':'update failed');
			if (res.updated) {
				location.href = "pet?cmd=petDetail&no="+no;
			}
		},
		error:function(xhr, status, err) {
			alert('err: ', err);
		}
	 }); return false;
 }
 
</script>
</head>
<body>
<main>
<h3>암호 변경</h3>
<div>번호 <%=no%></div>
<form action="pet" method="post" onsubmit="return updatePet();">
   <input type="hidden" name="cmd" id="cmd" value="petUpdate">
   <input type="hidden" name="no" id="no" value="<%=no%>">
   <div>새 체중 <input type="number" name="weight" id="weight"></div>
   <div>새 가격 <input type="number" name="price" id="price"></div>
   <div>새 사진 <input type="text" name="pic" id="pic"></div>
   <div>
      <button type="reset">취소</button>
      <button type="submit">저장</button>
   </div>
</form>
</main>
</body>
</html>