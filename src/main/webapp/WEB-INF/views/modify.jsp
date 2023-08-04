<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%@ page import="com.wp.termproject.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>게시글 수정</title>
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
	<h1>게시글 수정</h1><hr><br>
	<form action="${pageContext.request.contextPath}/userlist/modify" method="POST">
		<fieldset style="width: 600px; margin:auto;">
			<legend>글 수정</legend>
			<div class="field">
				<input type="text" name="title" placeholder="제목" value="${board.title}" required/>
			</div>
			<div class="field">
				<textarea rows="20" cols="70" name="content" placeholder="내용"required>${board.content}</textarea>
			</div>
			<input type="hidden" name ="bid" value="${board.id}" />
			<input type="hidden" name ="bday" value="${board.day}" />
			<div class="field">
				<input type="submit" value="수정하기"/>
			</div>
		</fieldset>
	</form>
</body>
</html>