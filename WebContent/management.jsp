<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored = "false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ユーザー管理</title>
</head>
<body>
<div class = "main-contents">

<div class = "managements">
	<c:forEach items = "${ managements }" var = "management">
		<div class = "management">
			<div class = "users">
				<div class="login_id-name">
				<table border = 1>
					<tr><td><span class="login_id"><c:out value="${management.login_id}" /></span></td>
					<td><span class="name"><c:out value="${management.name}" /></span></td>
					<td><span class="branch_name"><c:out value="${management.branch_name}" /></span></td>
					<td><span class="department_name"><c:out value="${management.department_name}" /></span></td>
					<td><input type = "submit" value = "編集"></td>
				</table>
				</div>
			</div>
		</div>
	</c:forEach>
</div>
<div class = "copyright">Copyright(c)keisuke kinoshita</div>
</div>
</body>
</html>