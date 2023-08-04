<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style>
	body{
		text-align: center;
	}
	.field{
		margin: 5px 0;
	}
	#msg{
		color: red;
	}
	label {
		width:100px;	
		float:left;
		text-align: right;
	}
</style>
</head>
<body>
	<h1>게시판 시스템</h1><hr><br>
	<form action="LoginCheck" method="POST">
		<fieldset style="width: 300px; margin:auto;">
			<legend>로그인</legend>
			<div class="field">
				<label for="userid">아이디 :</label>
				<input type="text" name="userid" required/>
			</div>
			<div class="field">
				<label for="passwd">비밀번호 :</label>
				<input type="password" name="passwd" required/>
			</div>
			<div class="field">
				<input type="submit" value="로그인"/>
				<input type="button" onclick="location='signup.jsp'" value="회원가입"/>
			</div>
		</fieldset>
		<div id="msg">
			<br>
			<c:if test="${msg != null}">
				${msg}
			</c:if>
		</div>
	</form>
</body>
</html>