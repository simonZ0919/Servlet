package simpleTest;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.buf.StringCache;

public class DateServlet extends HttpServlet{
	// get request/respone object, overwrite service() in HttpServlet
	public void service(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException{
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String dateString=sdf.format(date);
		// get request data
		String id=request.getParameter("id");
		String name=request.getParameter("username");
		Double h=Double.valueOf(request.getParameter("height"));
		Double w=Double.valueOf(request.getParameter("weight"));
		
		// arrays of string
		String[] subject=request.getParameterValues("subject");
		for (String sbj : subject) {
			System.out.println(sbj);
		}
		//response header, type of response, charset, default:iso8859-1, also by println
		response.setContentType("text/html;charset=utf-8");	
		response.getWriter().println("<h2>"+dateString+"</h2><h3>"+id+":"+name+
				"</h3><h3>BMI: "+w/h/h+"</h3>");
		//response.getWriter().close();//container close stream automatically
	}

}
