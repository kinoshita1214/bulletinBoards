<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>掲示板</title>
	<link href="./css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="main-contents">

<div class="header">

	<c:if test="${ not empty loginUser }">
		<a href="post">新規投稿画面</a>
		<a href="management">ユーザー管理画面</a>
		<a href="logout">ログアウト</a>
	</c:if>
</div>

<c:if test="${ not empty loginUser }">
	<div class="profile">
		<div class="name"><h2><c:out value="${loginUser.name}" /></h2></div>
		<div class="login_id">
			@<c:out value="${loginUser.login_id}" />
		</div>

	</div>
</c:if>


<div class="posts">
	<c:forEach items="${posts}" var="message">
		<div class="login_id-name">
			<span class="login_id"><c:out value = "${post.login_id}" /></span>
			<span class="name"><c:out value = "${post.name}" /></span>
		</div>
		<div class = "subject"><c:out value = "${ post.subject }" /></div>
		<div class = "text"><c:out value = "${post.text}" /></div>
		<div class = "date"><fmt:formatDate value = "${post.insertDate}" pattern="yyyy/MM/dd HH:mm:ss" /></div>
	</c:forEach>
</div>

<div class="copyright">Copyright(c)keisuke kinoshita</div>
</div>
</body>
</html>