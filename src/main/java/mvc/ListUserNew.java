package mvc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import entity.User;

public class ListUserNew extends HttpServlet{
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		// session verify  
		HttpSession session=req.getSession();
		Object obj=session.getAttribute("user");
		/* if no session found, redirect to listUser and return */
		if(obj==null){
			resp.sendRedirect("login.jsp");
			return;
		}

		req.setCharacterEncoding("utf-8");	
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		
		// access database through dao
		UserDAO dao=new UserDAO();
		try {
			List<User> users=dao.findAll();
			
			// bind data to request, by key-value
			req.setAttribute("users", users);
			// get dispatcher
			RequestDispatcher dispatcher=req.getRequestDispatcher("listUser.jsp");
			// call jsp
			dispatcher.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			pw.println("System error. Please retry later");
		}
	}
}
