package simpleTest;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DBUtils;

public class DelUser extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		int id=Integer.parseInt(req.getParameter("id"));	
		PrintWriter out=resp.getWriter();
		
		Connection connection = null;
		PreparedStatement statement = null;
		String sqlDel="delete from t_user where id=?";
		try {
			connection = DBUtils.getConnection();
			statement=connection.prepareStatement(sqlDel);
			statement.setInt(1, id);
			statement.executeUpdate();
			
			resp.sendRedirect("list");
		} catch (Exception e) {
			e.printStackTrace();
			out.println("System Error. Please retry later");
		} finally {
			DBUtils.close(connection, statement, null);
		}
	}
}
