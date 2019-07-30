package session;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TestSession extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out=resp.getWriter();
		// get session object, true: default
		HttpSession session=req.getSession(true);
		System.out.println(session.getId());//print id
		// set max interval in s
		session.setMaxInactiveInterval(60);
		
		Date date =(Date)session.getAttribute("time");
		if(date==null) {// first visit, find no session
			out.println("first login");
		}
		else {
			out.println("last login:"+date);
		}
		session.setAttribute("time", new Date());
	}
	
}
