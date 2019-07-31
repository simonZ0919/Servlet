package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommentFilter implements Filter{

	// construct after container starts
	public CommentFilter() {
		System.out.println("construct filter");
	}
	// init after instance filter
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("init filer");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// convert to child class
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;
		
		req.setCharacterEncoding("utf-8");
		String content=req.getParameter("content");
		if(content.contains("fuck")) {// reject
			response.getWriter().println("block comment");
		}else {//pass filter
			chain.doFilter(req, resp);
		}
	}

	@Override
	public void destroy() {
		
	}
	
}
