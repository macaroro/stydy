<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구구단 보기</title>

<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">

function req(){
$.ajax({
	url:'/index/test/5',
	method:'post',
	dataType:'json',
	success: function(res){
		
			alert(res.num);	
	},
	error: function(error){
		alert(error)
	}
})
return false;
}

$(function(){
	//ready함수로 실행이 순서대로 시작하면 #'fruit'를 얻지 못하므로
	//change가 일어날때만 수행하도록 한다.
$('#fruit').change(function(evt){
	// 과일이 바뀔때 마다 이벤트 발생==>change 이벤트
	//funtion(evt)는 change이벤트가 발생하면 함수 아래에 있는것이 실행됨
	$.ajax({
		url:'/index/fruit',//controller로!
		method:'post',//어떤 방식으로?
		data:{"fruit":evt.target.value},//현재 내가 선택한 과일
		dataType:'json',//무슨 문자열로 받을까?
		success: function(res){
			
	        alert(res.select);
	        //받은 과일을 알려줘
		
		},
		error: function(xhr,status,err){
			alert(err)
		}
	});
});
});


</script>
</head>
<body>

${msg}

<main>
<c:forEach var="dan" begin="2" end="9">
<a href="/index/gugu/${dan}">${dan}</a>
</c:forEach>


</main>
<a href="javascript:req()">dd</a>
<hr>
<select name="fruit" id="fruit">

<option value="apple">사과</option>
<option value="tomato">토마토</option>
<option value="mango">망고</option>
<option value="grape">포도</option>
</select>

</body>
</html>