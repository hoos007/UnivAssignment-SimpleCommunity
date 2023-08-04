<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%@ page import="java.io.PrintWriter" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>게시판 글쓰기</title>
<style>
	h1{
		text-align: center;
	}
	.field{
		margin: 5px 0;
	}
</style>
</head>
<body>
	<h1>게시판 글쓰기</h1><hr><br>
	<form action="${pageContext.request.contextPath}/userlist/write?mod=1" method="POST">
		<fieldset style="width: 600px; margin:auto;">
			<legend>글쓰기</legend>
			<div class="field">
				<input type="text" name="title" placeholder="제목" required/>
			</div>
			<div class="field">
				<textarea rows="20" cols="70" name="content" placeholder="내용" required></textarea>
			</div>
			<div class="field">
				<input type="submit" value="저장하기"/>
				<input type="reset" value="초기화"/>
			</div>
		</fieldset>
	</form>
</body>
</html>