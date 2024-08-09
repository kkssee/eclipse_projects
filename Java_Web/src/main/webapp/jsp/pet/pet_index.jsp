<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome to Pet</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" 
integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" 
crossorigin="anonymous"></script>
<script type="text/javascript">
$(function() {
    console.log('jQuery ready');
 });
 
function search() {
	var cat = $('#category').val();
	alert('검색할 카테고리:' + cat);
}
 
function goSearch() {
	var cat = $('#category').val();
	alert($('#key').val());

	if(cat==='no') {
		location.href ="pet?cmd=petDetail&no="+parseInt($('#key').val());
	} else if(cat==='name') {
		location.href ="pet?cmd=search&name="+$('#key').val();
	} else alert('err');
}
</script>
<style type ="text/css">
	img{width : 80%;}
	main div:last-child{display:fle; justify-content : center; margin-top : 4em;}
	li{display: inline-block; width : 5em;}
</style>
</head>
<body>
<main>
<img src = "./img/pet/petBanner.png" width="200" height="250">	
   <nav>		
      <ul>
         <li><a href="pet?cmd=addForm">추가</a>
         <li><a href="pet?cmd=petList">목록</a>
         <li><a href="pet?cmd=petUpdate">수정</a>
         <li><a href="pet?cmd=petDelete">삭제</a>
         <li><a href="pet">메인</a> 
      </ul>	
   </nav>
   <br>
   <form action="pet" method="post">
   <input type="hidden" name="cmd" id="cmd" value="search">
   <select name="category" id="category"> 
   		<option value="no">번호</option>
   		<option value="name">이름</option>
   </select>
   		<input type="text" name="key" id="key">
		<button type="button" onclick="goSearch();">검색</button>
   </form>
</main>
</body>
</html>