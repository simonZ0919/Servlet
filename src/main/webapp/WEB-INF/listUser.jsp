<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>listUsers</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="css/style.css" />
	</head>
	<body>
		<div id="wrap">
			<div id="top_content"> 
				<%@include file="header.jsp" %>
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
						<!--traverse list, u:element, s:status-->
						<c:forEach items="${users}" var="u" varStatus="s" >
						<!--get index from status -->
						<tr class="row${s.index%2+1}">
							<td>
								${u.id}
							</td>
							<td>
								${u.username }
							</td>
							<td>
								${u.pwd }
							</td>
							<td>
								${u.email }
							</td>
							<td>
								<a href="delete.do?id=${u.id}" 
								onclick="return confirm('Delete user ${u.username}?');">
								delete</a>&nbsp;
							</td>
						</tr>
						</c:forEach>
					</table>
					<p>
						<input type="button" class="button" value="Add User" 
						onclick="location='toAdd.do'"/>
					</p>
				</div>
			</div>
			<%@include file="footer.jsp" %>
		</div>
	</body>
</html>
