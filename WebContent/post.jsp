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
<link href = "css/style.css" rel = "stylesheet" type = "text/css">
</head>
<body>

<div class = "main-contents">
<h2>新規投稿</h2>
<c:if test = "${ not empty errorMessages }">
	<div class = "errorMessages">
		<ul>
			<c:forEach items = "${ errorMessages }" var = "message" >
				<c:out value = "${ message }" />
			</c:forEach>
		</ul>
	</div>
	<c:remove var = "errorMessages" scope = "session" />
</c:if>
<div class="form-area">
	<form action="newPost" method="post">
		件名(50文字まで)<br />
		<input type = "text" name = "subject" size = "50" style ="font-size: 15pt" value = "${ post.subject }" ><br /><br />
		本文(1000文字まで)<br />
		<textarea name="text" id = "text" cols="95" rows="15" style ="font-size: 15pt" class="text-box" >${ post.text }</textarea><br /><br />
		カテゴリー(10文字まで)<br />
		<input type = "text" name = "category" size = "15" style ="font-size: 15pt" value = "${ post.category }"><br />
		<br />
		<input type="submit" value="投稿">      <input type = "reset" value = "リセット">
	</form><br />
<a href = "./">戻る</a>
</div>
</div>
</body>
</html>