<%@ page import="java.util.*,entity.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<!--application:context  -->
	online:<%=application.getAttribute("count") %><br>
	<%
		List<User>userList=(List<User>)application.getAttribute("userlist");
		for(User user:userList){
			out.println(user.getUsername());
		}
	%>
</body>
</html>