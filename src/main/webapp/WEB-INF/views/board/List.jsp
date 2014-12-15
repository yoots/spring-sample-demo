<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
    <head>
	    <title>
		CreWorld
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    </head>
<body>
<h2>Hello World!</h2>

<c:forEach var="sample" items="${list}">
	<div>${sample.title}</div>
</c:forEach>

<br/><br/><br/><br/>

<c:forEach var="sample" items="${paging.list}">
	<div>${sample.title}</div>
</c:forEach>

</body>
</html>
