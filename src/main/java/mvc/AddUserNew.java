package mvc;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import entity.User;

public class AddUserNew extends HttpServlet{
	// get request/respone object, overwrite service() in HttpServlet
	public void service(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException{
		request.setCharacterEncoding("utf-8");//for post only, before getParameter()
		response.setContentType("text/html;charset=utf-8");			
		PrintWriter out=response.getWriter();

		// add to database
		UserDAO dao=new UserDAO();
		try {
			User user=new User();		
			// get request data
			user.setUsername(request.getParameter("username"));
			user.setPwd(request.getParameter("password"));
			user.setEmail(request.getParameter("email"));
			dao.save(user);
			//redirect to ListUser, will clear response context
			response.sendRedirect("listnew");	
		} catch (Exception e) {
			e.printStackTrace();//log error
			out.println("System Error. Please retry later");// notice user
		}	
	}		
}
