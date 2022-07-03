<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 상세정보</title>

<style type="text/css">
h2{ text-align: center;}
table{   width: 50%; margin: 0 auto;

    border: 1px solid #444444;
    border-collapse: collapse;
  }
  th, td {
    border: 1px solid #444444;
    padding: 10px;
   
  }
 tr:nth-child(2n) {
    background-color: #bbdefb;
  }
 tr:nth-child(2n+1) {
    background-color: #e3f2fd;
  }
  th{ background-color: lightgreen;}
  tr:hover{background-color: orange;}
  div{text-align: center;}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">
function  deleted(){

	confirm('정말로 삭제?')
 
$.ajax({
	url:'/bbs/delete/${bbs.num}',
	method:'post',
	dataType:'json',
	success: function(res){
		
			alert(res.deleted? '삭제성공':'삭제실패');
			location.href="/bbs/list"
	
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

<h2>글 상제정보</h2>
<table>
<tr><th>글번호</th><th>제목</th><th>내용</th><th>작성자</th><th>날짜</th><th>부모글</th></tr>


<tr>
 <td id="num">${bbs.num}</td>
<td>${bbs.title}</td>
<td>${bbs.contents}</td>
<td>${bbs.author}</td>
<td>${bbs.wdate}</td>
<td>${bbs.pcode}</td>
</tr>
</table>
<div><a href="/bbs/edit/${bbs.num}">수정하기</a></div>
<div><a href="javascript:deleted();">삭제하기</a></div>
<div><a href="/bbs/list">글목록 보기</a></div>
</body>
</html>