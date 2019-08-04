<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="entity.User"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<%
		User user=new User();
		user.setUsername("Kate");
		user.setPwd("123");
		request.setAttribute("user1", user);	
		pageContext.setAttribute("password", "pwd");
	%>
	<%--no execute comment, ${ELexpression}, specify scope --%>
	${requestScope.user1.username}<br>
	<%--find attribute name or by bound data --%>
	${user1[password]}<br>
	${user1['pwd']}<br>
	
	<%--bind name:no '' --%>
	${password=='pwd'}<br>
	<%--empty: is empty/null --%>
	${empty password}<br>
	
	<%-- get request value --%>
	${param.username}
</body>
</html>