<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored = "false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ログイン</title>
	<link href = "./css/style.css" rel = "stylesheet" type = "text/css">

</head>
<body>
<div class = "main-contents">

<h2>ログイン</h2>
<c:if test = "${ not empty errorMessages }">
	<div class = "errorMessages">
		<ul>
			<c:forEach items = "${ errorMessages }" var = "message" >
				<c:out value = "${ message }" /><br />
			</c:forEach>
		</ul>
	</div>
	<c:remove var = "errorMessages" scope = "session" />
</c:if>

<form action = "login" method = "post"><br />
	<label for = "login_id">ログインID</label><br />
	<input name = "login_id" id = "login_id" size = "25" style = "font-size: 15px"/><br />
	<br />
	<label for = "password">パスワード</label><br />
	<input name = "password" type = "password" id = "password" size = "25" style = "font-size: 15px" /><br />
	<br />
	<input type = "submit" value = "ログイン" /><br /><br />
	</form>
<div class="copyright">Copyright(c)Keisuke Kinoshita</div><br />
</div><br/>

</body>
</html>