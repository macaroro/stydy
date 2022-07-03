<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Upload Form</title>
</head>
<body>
<h3>Spring boot 파일 업로드 테스트</h3>
<form action="/files/upload" method="post" enctype="multipart/form-data">
<input type="hidden" name="fpath" value="files">
   작성자 <input type="text" name="writer" value="민경"><br>
   File <input type="file" name="files" multiple="multiple"><br>
   설명<input type="text" name="comments" id="comments"><br>
   <button type="submit">업로드</button>
</form>
</body>
</html>