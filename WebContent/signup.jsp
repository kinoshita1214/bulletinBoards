<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored = "false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ユーザー登録</title>
</head>
<body>
<div class = "main-contents">
<c:if test = "${ not empty errorMessage }">
	<div class = "errorMessages">
		<ul>
			<c:forEach items = "${ errorMessages }" var = "message">
				<li><c:out value = "${ message }" />
			</c:forEach>
		</ul>
	</div>
	<c:remove var = "errorMessages" scope = "session" />
</c:if>
<form action = "signup" method = "post"><br />
	<label for = "login_id">ログインID</label><br />
	<input name = "login_id" id = "login_id" /><br />

	<label for = "name">ユーザー名</label><br />
	<input name = "name" id = "name" /><br />

	<label for = "password">パスワード</label><br />
	<input name = "password" id = "password" /><br />

	<label for = "branch.name">支店名</label><br />
	<select name = "branches">
		<c:forEach var = "management" items = "${ managements }">
			<option value = "${ management.branch_name }"><c:out value = "${ management.branch_name }" /></option>
		</c:forEach>
	</select><br />

	<label for = "department.name">部署・役所名</label><br />
	<select name = "departments">
		<c:forEach var = "management" items = "${ managements }">
			<option value = "${ management.department_name  }"><c:out value = "${ management.department_name }" /></option>
		</c:forEach>
	</select><br />
	<input type = "submit" value = "登録" /><br />
	<a href = "./">戻る</a>
</form>
</div>
</body>
</html>