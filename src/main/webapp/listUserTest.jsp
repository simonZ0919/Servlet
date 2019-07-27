<%@page import="dao.*,entity.*,java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
	.row1{
		background-color: blue;
	}
	.row2{
		background-color: green;
	}

</style>
</head>
<body>
	<table border="1" width="60%" cellpading="0" cellspacing="0">	
	<tr>
		<th>id</th>
		<th>Username</th>
		<th>Password</th>
		<th>Email</th>
	</tr>
	<%
		UserDAO dao=new UserDAO();
		List<User> users=dao.findAll();
		for(int i=0;i<users.size();i++){
			User user=users.get(i);
			%>
			<tr class="row<%=i%2+1%>">
			<td><%=user.getId()%></td>
			<td><%=user.getUsername()%></td>
			<td><%=user.getPwd()%></td>
			<td><%=user.getEmail()%></td>
			</tr>
			<% 
			
		}
	
	%>
	</table>

</body>
</html>