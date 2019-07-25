package simpleTest;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DBUtils;

public class UserForm extends HttpServlet{
	// get request/respone object, overwrite service() in HttpServlet
	public void service(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException{
		// get current date
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String ds=sdf.format(date);
		
		request.setCharacterEncoding("utf-8");//for post only, before getParameter()
		// get request data
		String name=request.getParameter("username");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		
		// arrays of string, no selection:return null
		String[] subject=request.getParameterValues("subject");
		if(subject!=null) {
			for (String sbj : subject) {
				System.out.println(sbj);
			}			
		}
		
		//response header, type of response, charset, default:iso8859-1, also for println()
		response.setContentType("text/html;charset=utf-8");	
		PrintWriter out=response.getWriter();

		// add to database	
		Connection connection = null;
		PreparedStatement ps = null;
		String sql="insert into t_user values(null,?,?,?)";
		try {
			connection = DBUtils.getConnection();
			ps=connection.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, password);
			ps.setString(3, email);
			ps.executeUpdate();
			
			//redirect to ListUser, will clear response context
			response.sendRedirect("list");	
			out.println("<h3>"+ds+"</h3>");// context cleared
		} catch (Exception e) {
			e.printStackTrace();//log error
			out.println("System Error. Please retry later");// notice user
		} finally {
			DBUtils.close(connection, ps, null);
		}
	
		//response.getWriter().close();//container close stream automatically
	}		

}
