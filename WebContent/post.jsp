<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored = "false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新規投稿画面</title>
</head>
<body>
<div class = "main-contents">
<h2>新規投稿画面</h2>
<c:if test = "${ not empty errorMessages }">
	<div class = "errorMessages">
		<ul>
			<c:forEach items = "${ errorMessages }" var = "message" >
				<li><c:out value = "${ message }" />
			</c:forEach>
		</ul>
	</div>
	<c:remove var = "errorMessages" scope = "session" />
</c:if>
<div class="form-area">
	<form action="newPost" method="post">
		新規投稿<br />
		件名(50文字まで)<br />
		<input type = "text" name = "subject" size = "50" ><br />
		本文(1000文字まで)<br />
		<textarea name="text" cols="100" rows="5" class="text-box"></textarea><br />
		カテゴリー(10文字まで)<br />
		<input type = "text" name = "category" size = "50"><br />
		<input type="submit" value="投稿"><input type = "reset" value = "リセット">
	</form>
<a href = "./">戻る</a>
</div>
</div>
</body>
</html>