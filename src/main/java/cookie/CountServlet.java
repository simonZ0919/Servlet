package cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.CookieUtil;


public class CountServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out=resp.getWriter();		
		
		String count=CookieUtil.findCookie("count", req);
		
		if(count==null) {
			CookieUtil.addCookie("count", "1", 30*24*3600, "/Servlet", resp);
			out.println("count:"+1);
		}
		else {
			int val=Integer.parseInt(count)+1;
			CookieUtil.addCookie("count", String.valueOf(val), 30*24*3600, "/Servlet", resp);
			out.println("count:"+val);		
		}
	}
	
}
