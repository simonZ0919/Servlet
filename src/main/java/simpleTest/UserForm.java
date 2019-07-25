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

public class UserForm extends HttpServlet{
	// get request/respone object, overwrite service() in HttpServlet
	public void service(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException{
		// get current date
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String ds=sdf.format(date);
		
		request.setCharacterEncoding("utf-8");//for post only, before getParameter()
		// get request data
		String name=request.getParameter("username");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		Double h=Double.valueOf(request.getParameter("height"));
		Double w=Double.valueOf(request.getParameter("weight"));
		
		// arrays of string, no selection:return null
		String[] subject=request.getParameterValues("subject");
		if(subject!=null) {
			for (String sbj : subject) {
				System.out.println(sbj);
			}			
		}
		
		//response header, type of response, charset, default:iso8859-1, also for println()
		response.setContentType("text/html;charset=utf-8");	
		response.getWriter().println("<h3>"+name+"-"+password+"</h3><h3>Email:"+email+
				"</h3><h3>BMI: "+w/h/h+" "+ds+"</h3>");
		//response.getWriter().close();//container close stream automatically
	}

}
