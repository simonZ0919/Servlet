package cookie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddCookie extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		Cookie cookie=new Cookie("username", "Mary");
		// set lifetime of cookie in seconds, 0s:delete cookie
		cookie.setMaxAge(60);
		resp.addCookie(cookie);
	}
	
}
