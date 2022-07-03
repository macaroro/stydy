<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>글목록</title>
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
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">
function search(page){
	
$('#page').val(page);
$('#searchForm').submit();
}
</script>
</head>
<body>
<h2>글목록</h2>
<main>
<table>
<tr><th>글번호</th><th>제목</th><th>작성자</th><th>날짜</th></tr>

<c:forEach var="b" items="${pageInfo.list}">
<tr>
 <td>${b.num}</td>
<td>${b.title}</td>
<td>${b.author}</td>
<td>${b.wdate}</td>
</tr>
</c:forEach>
</table>

<c:forEach var="i" items="${pageInfo.navigatepageNums}">
<c:choose>
<c:when test="${i==pageInfo.pageNum}">
 <span id="currpage">[${i}]</span>
</c:when>
<c:otherwise>

[<a href="javascript:search(${i});">${i}</a>]
</c:otherwise>
</c:choose>
</c:forEach>
</main>
<div id="search">
<form id="searchForm" method="post" action="/bbs/search">
<input type="hidden" id="psize" name="psize" value="2">
<input type="hidden" id="page" name="page" value="1">
<div>
<label for="category">검색항목</label>
<select name="category">
<option value="author" <c:if test="${category=='author'}">seleted</c:if>>작성자</option>
<option value="title" <c:if test="${category=='title'}">seleted</c:if>>제목</option>
</select>

<label for="key">검색어</label>
<input type="text" name="key" value="${key}">
</div>
<div id="button">
<button type="reset">취소</button>
<button type="submit">검색</button>
</div>
</form>
</div>
</body>
</html>