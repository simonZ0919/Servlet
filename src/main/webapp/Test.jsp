<%@page import="java.util.*,java.text.*"
	contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>	
<html>
	<head></head>
	<body>
		<!--java expression  -->
		time:<%=new Date() %><br>
		<!-- code segement -->
		Date:<%
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			out.println(sdf.format(new Date()));
		%>
	</body>
</html>