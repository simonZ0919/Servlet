<%@ page  contentType="text/html; charset=utf-8" 
	pageEncoding="utf-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body style="color:red">
	<!-- get error description, isErrorPage="true" -->
	<%=exception.getMessage() %>
	<a href="login.jsp">Login</a>
</body>
</html>