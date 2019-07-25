package simpleTest;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.PseudoColumnUsage;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.protocol.Resultset;

import util.DBUtils;

public class ListUser extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
	req.setCharacterEncoding("utf-8");	
	resp.setContentType("text/html;charset=utf-8");
	PrintWriter pw=resp.getWriter();
	
	//select from database
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet resultset=null;
	String sql="select * from t_user";
	try {
		connection = DBUtils.getConnection();
		statement=connection.prepareStatement(sql);
		resultset=statement.executeQuery();
		
		//output table
		pw.println("<table border='1' width='60%' cellpadding='0' cellspacing='0'> ");
		pw.println("<tr><th>id</th><th>Username</th><th>Password</th><th>Email</th></tr>");
		while(resultset.next()) {
			int id=resultset.getInt("id");
			String uname=resultset.getString("username");
			String pwd=resultset.getString("password");
			String email=resultset.getString("email");
			
			pw.println("<tr><td>"+id+"</td><td>"+uname+"</td><td>"+
					pwd+"</td><td>"+email+"</td></tr>");
		}
		pw.println("</table>");
		pw.println("<p><a href='form.html'>Sign up</a></p>");//jump to form.html
	} catch (Exception e) {
		e.printStackTrace();
		pw.println("System error. Please retry later");
	} finally {
		DBUtils.close(connection, statement, null);
	}	
	}
	
}
