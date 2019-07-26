package mvc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;

public class DelUserNew extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		int id=Integer.parseInt(req.getParameter("id"));	
		PrintWriter out=resp.getWriter();
		
		UserDAO dao=new UserDAO();
		try {
			dao.delete(id);
			resp.sendRedirect("listnew");
		} catch (Exception e) {
			e.printStackTrace();
			out.println("System Error. Please retry later");
		} 
	}
}
