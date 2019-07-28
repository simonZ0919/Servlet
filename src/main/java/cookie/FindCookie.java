package cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindCookie extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out=resp.getWriter();
		Cookie[] cookies=req.getCookies();
		boolean findCart=false;
		if(cookies!=null) {
			for (Cookie cookie : cookies) {
				String name=cookie.getName();
				if("cart".equals(name)) {
					out.println(name+":"+cookie.getValue()+"<br>");
					findCart=true;
				}
			}
		}
		// if no name found, create new one
		if(!findCart){
			Cookie cookie=new Cookie("cart","empty");
			resp.addCookie(cookie);
		}
	}
	
}
