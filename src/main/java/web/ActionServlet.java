package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import entity.User;

/**
 * *.do: handle path with *.do
 */
@WebServlet("*.do")
public class ActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// get request path
		String uri=request.getRequestURI();
		// get source path, *login.do, path:login
		String path=uri.substring(
				uri.lastIndexOf("/"), uri.lastIndexOf("."));
		
		if("/login".equals(path)) {
			processLogin(request,response);
		}else if ("/list".equals(path)) {
			processList(request,response);
		}else if ("/add".equals(path)) {
			processAdd(request,response);
		}else if ("/delete".equals(path)) {
			processDelete(request,response);
		}else if ("/toLogin".equals(path)) {
			request.getRequestDispatcher("/WEB-INF/login.jsp").
				forward(request, response);
		}else if ("/toAdd".equals(path)) {
			request.getRequestDispatcher("/WEB-INF/addUser.jsp").
				forward(request, response);
		}
	}

	private void processDelete(HttpServletRequest req, HttpServletResponse resp) 
			throws UnsupportedEncodingException,IOException, ServletException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		int id=Integer.parseInt(req.getParameter("id"));	
		
		UserDAO dao=new UserDAO();
		try {
			dao.delete(id);
			resp.sendRedirect("list.do");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		
	}

	private void processAdd(HttpServletRequest request, HttpServletResponse response) 
			throws UnsupportedEncodingException,IOException, ServletException {
		request.setCharacterEncoding("utf-8");//for post only, before getParameter()
		response.setContentType("text/html;charset=utf-8");			

		// add to database
		UserDAO dao=new UserDAO();
		try {
			// get request data
			String username=request.getParameter("username");
			User user=dao.findByUsername(username);
			
			//dispatch to addUser.jsp if name has been used
			if(user!=null) {
				request.setAttribute("msg", "name has been used");
				request.getRequestDispatcher("addUser.jsp").forward(request, response);
			}
			// save to database
			else {
				user=new User();
				user.setUsername(username);
				user.setPwd(request.getParameter("password"));
				user.setEmail(request.getParameter("email"));				
				dao.save(user);
				//redirect to list.do, will clear response context
				response.sendRedirect("list.do");					
			}
		} catch (Exception e) {
			e.printStackTrace();//log error
			throw new ServletException(e);
		}	
	}

	private void processList(HttpServletRequest req, HttpServletResponse resp) 
			throws IOException, ServletException {
		// session verify  
		HttpSession session=req.getSession();
		Object obj=session.getAttribute("user");
		
		/* if no session found, redirect to listUser and return */
		if(obj==null){
			resp.sendRedirect("toLogin.do");// cannot redirect to /WEB-INF/...
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
			RequestDispatcher dispatcher=req.getRequestDispatcher("/WEB-INF/listUser.jsp");
			// call jsp
			dispatcher.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

	private void processLogin(HttpServletRequest req,HttpServletResponse resp) 
			throws UnsupportedEncodingException, ServletException {
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
				resp.sendRedirect("list.do");
			}else {
				req.setAttribute("login_failed", "invalid username/password");
				req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			//resp.getWriter().println("System Error. Please retry later");
			throw new ServletException(e);// throw exception to container
		}					
	}

}
