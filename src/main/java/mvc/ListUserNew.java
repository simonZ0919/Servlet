package mvc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import entity.User;

public class ListUserNew extends HttpServlet{
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");	
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		
		// access database through dao
		UserDAO dao=new UserDAO();
		try {
			List<User> users=dao.findAll();
			//output table
			pw.println("<table border='1' width='60%' cellpadding='0' cellspacing='0'> ");
			pw.println("<tr><th>id</th><th>Username</th><th>Password</th><th>Email</th><th>Action</th></tr>");
			for (User user : users) {
				int id=user.getId();
				String uname=user.getUsername();
				String pwd=user.getPwd();
				String email=user.getEmail();
				
				pw.println("<tr><td>"+id+"</td><td>"+uname+"</td><td>"+
						pwd+"</td><td>"+email+"</td><td><a href='deletenew?id="+id+"'>Remove</a></td></tr>");
			}
			pw.println("</table>");
			pw.println("<p><a href='form.html'>Sign up</a></p>");//jump to form.html
		} catch (Exception e) {
			e.printStackTrace();
			pw.println("System error. Please retry later");
		}
	}
}
