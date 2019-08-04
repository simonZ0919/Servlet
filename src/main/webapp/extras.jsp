<!--errorPage: call error.jsp for exception, unable to use session  -->
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" 
	errorPage="error.jsp" session="false"%>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<!--declare variable or method outside service() -->
	<%!
		int i=10;
		int sum(int a, int b){
			return(a+b);
		}
	%>
	<%=sum(3, 3) %><br>
	<!--pageContext: accessed only by current jsp  -->
	<%pageContext.setAttribute("username", "John");	%>
	<!--get config param  -->
	<%=config.getInitParameter("uname") %>
</body>
</html>
