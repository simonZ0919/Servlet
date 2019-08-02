package listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import dao.UserDAO;
import entity.User;
/**
 * @author zxh
 * store data in catche for future use 
 */
@WebListener("/CatcheListener")

public class CatcheListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// search DB and load to catch after container started
		UserDAO dao=new UserDAO();
		try {
			List<User> userlList=dao.findAll();
			// get context and bind object
			ServletContext ctx=sce.getServletContext();
			ctx.setAttribute("userlist", userlList);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
	
}
