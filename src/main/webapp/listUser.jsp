<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@page import="entity.*,java.util.*,java.text.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>listUsers</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="css/style.css" />
	</head>
	<body>
		<div id="wrap">
			<div id="top_content"> 
				<div id="header">
					<div id="rightheader">
						<p>
						<%
							SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
							out.println(sdf.format(new Date()));
						%>
							<br />
						</p>
					</div>
					<div id="topheader">
						<h1 id="title">
							<a href="#">main</a>
						</h1>
					</div>
					<div id="navigation">
					</div>
				</div>
				<div id="content">
					<p id="whereami">
					</p>
					<h1>
						User List
					</h1>
					<table class="table">
						<tr class="table_header">
							<td>
								ID
							</td>
							<td>
								Username
							</td>
							<td>
								Pwd
							</td>
							<td>
								Email
							</td>
							<td>
								Operation
							</td>
						</tr>
						<%
							/* get data from Servlet through key */
							List<User> userList=(List<User>)request.getAttribute("users");
							for(int i=0;i<userList.size();i++){
								User user=userList.get(i);
						%>
						<tr class="row<%=i%2+1%>">
							<td>
								<%=user.getId()%>
							</td>
							<td>
								<%=user.getUsername()%>
							</td>
							<td>
								<%=user.getPwd()%>
							</td>
							<td>
								<%=user.getEmail()%>
							</td>
							<td>
								<a href="deletenew?id=<%=user.getId()%>" 
								onclick="return confirm('Delete user <%=user.getUsername()%>?');">
								delete</a>&nbsp;
							</td>
						</tr><%
							}
						%>
					</table>
					<p>
						<input type="button" class="button" value="Add User" 
						onclick="location='addUser.jsp'"/>
					</p>
				</div>
			</div>
			<div id="footer">
				<div id="footer_bg">
				ABC@126.com
				</div>
			</div>
		</div>
	</body>
</html>
