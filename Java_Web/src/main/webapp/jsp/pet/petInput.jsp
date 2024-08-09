<%@page import="java.util.*"%>
<%@page import="java.text.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" 
integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" 
crossorigin="anonymous"></script>

<script type="text/javascript">
$(function() {
    console.log('jQuery ready');
 });
 
 function addPet() {
	 if(!confirm('펫 정보를 추가하시겠습니까?')) return;
	 
	 let cmd = $('#cmd').val();	
	 let price = $('#price').val();
	 let weight = $('#weight').val();
	 let birth= $('#birth').val();
	 let name = $('#name').val();
	 let origin = $('#origin').val();
	 let pic = $('#pic').val();
	 
	 let obj = {};	// JSON 표현식
	 obj.cmd = cmd;
	 obj.price = price;
	 obj.weight = weight;
	 obj.birth = birth;
	 obj.name = name;
	 obj.origin = origin;
	 obj.pic = pic;
	 
	$.ajax({
			url : 'pet',
			method : 'post',
			cache : false,
			data : obj,
			dataType : 'json',
			success : function(res) {
				alert(res.added ? '추가 성공' : '추가 실패');
				if (res.added) {
					location.href = "pet";
				}
			}, error : function(xhr, status, err) {
				alert('err: ' + err);
			}
		});
	}
</script>
<head>
<meta charset="UTF-8">
<title>petInput</title>
<style type="text/css">
	#main { width:fit-content; margin:0.5em auto; padding:1em; }
	form { border:1px solid black; text-align:center; padding:0.5em; }
	h3 { text-align:center; }
	div:last-child { margin-top:0.3em; text-align:center; }
	label { display:inline-black; width:3em; }
</style>
<script type="text/javascript">
	function formCheck() {
		var no = document.querySelector("#no").value;
		var price = document.querySelector("#price").value;
		var weight = document.querySelector("#weight").value;
		var birth = document.querySelector("#birth").value;
		var name = document.querySelector("#name").value;
		var origin = document.querySelector("#origin").value;
		var pic = document.querySelector("#pic").value;
		if(no=="" || price=="" || weight=="" || birth=="" || name=="" || origin=="" || pic=="") {
			alert('공백 불가');
			return false;
		} return true;
	}
</script>
</head>
<body>
<div id = "main">
	<h3>펫 추가</h3>
	<form action="pet" method="post" onsubmit="return formCheck();">
		<input type="hidden" name="cmd" id="cmd" value="addPet">
		
		<div><label for="name">이름</label>
			<input type="text" name="name" id="name">
		</div>
		<div><label for="origin">원산지</label>
			<input type="text" name="origin" id="origin">
		</div>
		<div><label for="weight">무게</label>
			<input type="number" name="weight" id="weight">
		</div>
		<div><label for="birth">생일</label>
			<input type="date" name="birth" id="birth">
		</div>
		<div><label for="price">가격</label>
			<input type="number" name="price" id="price">
		</div>
		<div><label for="pic">사진</label>
			<input type="text" name="pic" id="pic">
		</div>
		
		<div>
			<button type="reset">취소</button>
			<a href="javascript:addPet();">추가</a>
		</div>
	</form>
</div>


</body>
</html>