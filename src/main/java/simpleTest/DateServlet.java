package simpleTest;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DateServlet extends HttpServlet{
	// get request/respone object, overwrite service() in HttpServlet
	public void service(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException{
		//response header, type of response
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String dateString=sdf.format(date);
		
		
		String id=request.getParameter("id");
		String name=request.getParameter("username");
		
		pw.println("<h2>"+dateString+"</h2><h3>"+
				id+":"+name+"</h3>");
		pw.close();
	}

}
