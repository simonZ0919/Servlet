package listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

// implement session listner
public class CountListener implements HttpSessionListener{

	// called after session created
	public void sessionCreated(HttpSessionEvent se) {
		// get session from session event
		HttpSession session=se.getSession();
		// get context from session 
		ServletContext ctx=session.getServletContext();
		
		Integer cnt=(Integer)ctx.getAttribute("count");
		cnt=cnt==null?1:++cnt;
		// bind to context, for all users
		ctx.setAttribute("count", cnt);
	}

	// called after session destroyed
	public void sessionDestroyed(HttpSessionEvent se) {
		ServletContext ctx=se.getSession().getServletContext();
		Integer cnt=(Integer)ctx.getAttribute("count");
		ctx.setAttribute("count", --cnt);
	}
	
}
