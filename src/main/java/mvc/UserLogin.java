package mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import dao.UserDAO;
import entity.User;

public class UserLogin extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");		
		String username=req.getParameter("username");
		String password=req.getParameter("password");		
		UserDAO dao=new UserDAO();
		
		try {// login in success
			User user=dao.findByUsername(username);
			if(user!=null && user.getPwd().equals(password)) {
				// add session 
				HttpSession session=req.getSession();
				session.setAttribute("user", "signIn");
				resp.sendRedirect("listnew");
			}else {
				req.setAttribute("msg", "invalid username/password");
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			//resp.getWriter().println("System Error. Please retry later");
			// throw exception to container
			throw new ServletException(e);
		}
	}
	
}
