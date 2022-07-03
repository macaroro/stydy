<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">

function  update(){
	var serData = $('#update').serialize();
$.ajax({
	url:'/bbs/update',
	method:'post',
	data:serData,
	dataType:'json',
	success: function(res){
		
			alert(res.updated? '수정성공':'수정실패');
			location.href="/bbs/detail/${bbs.num}"
	
	},
	error: function(error){
		alert(error)
	}
});
return false;
}
</script>
</head>
<body>
<form onsubmit="return update();" id="update">
글번호<input type="text" id="num" name="num"  value="${bbs.num}" readonly>
<div><label for="title">제목</label>
		<input type="text" id="title" name="title"  value="${bbs.title}">
	</div>
작성자<input type="text" id="author" name="author" value="${bbs.author}" readonly>
<div><label for="contents">글내용</label>
		<textarea rows="5" cols="30" id="contents" name="contents" placeholder="글을 입력하세요" ></textarea>
	</div>

날짜<input type="text" id="wdate" name="wdate" value="${bbs.wdate}" readonly>
부모글<input type="text" id="pcode" name="pcode" value="${bbs.pcode}" readonly>


<button type="submit">수정</button>
<button type="reset">취소</button>
</form>
</body>
</html>