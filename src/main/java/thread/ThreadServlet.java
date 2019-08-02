package thread;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/thread")
public class ThreadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private int count;
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// lock for thread safety
		synchronized (this) {
			count++;
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+":"+count);			
		}
	}

}
