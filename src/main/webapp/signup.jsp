<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
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
	<h1>회원가입</h1><hr><br>
	<form action="SignUp" method="POST">
		<fieldset style="width: 300px; margin:auto;">
			<legend>회원가입</legend>
			<div class="field">
				<label for="sign_name">이름 :</label>
				<input type="text" name="sign_name" required/>
			</div>
			<div class="field">
				<label for="sign_id">아이디 :</label>
				<input type="text" name="sign_id" required/>
			</div>
			<div class="field">
				<label for="sign_passwd1">비밀번호 :</label>
				<input type="password" name="sign_passwd1" required/>
			</div>
			<div class="field">
				<label for="sign_passwd2">비밀번호 확인 :</label>
				<input type="password" name="sign_passwd2" required/>
			</div>
			<div class="field">
				<input type="submit" value="완료"/>
				<input type="reset" value="초기화" />
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