<%@page import="entity.User"%>
<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<%
		User user=new User();
		user.setUsername("Zol");
		user.setPwd("12333");
		request.setAttribute("user", user);	
	%>
	<!-- if tag, save result in pageContext -->
	<c:if test="${user.username=='Zol'}" 
	 	var="isFound" scope="page">user found</c:if>
	<c:if test="!isFound">no user found</c:if><br>
		
	<!-- if...else -->
	<c:choose>
		<c:when test="${user.username=='Zol'}">user found</c:when>
		<c:otherwise>no user found</c:otherwise>
	</c:choose><br>
	<!--forEach: traverse, see listUser.jsp-->
</body>
</html>