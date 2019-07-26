package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.User;
import util.DBUtils;

public class UserDAO {
	public void save(User user) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBUtils.getConnection();
			String sql="insert into t_user values(null,?,?,?)";
			statement=connection.prepareStatement(sql);
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPwd());
			statement.setString(3, user.getEmail());
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBUtils.close(connection, statement, null);
		}
	}
	
	// find data in database, return object of record
	public List<User> findAll() throws Exception {
		List<User> users=new ArrayList<User>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultset = null;
		try {
			connection = DBUtils.getConnection();
			String sql="SELECT * FROM t_user";
			statement=connection.prepareStatement(sql);
			resultset=statement.executeQuery();
			while(resultset.next()) {
				int id=resultset.getInt("id");
				String uname=resultset.getString("username");
				String pwd=resultset.getString("password");
				String email=resultset.getString("email");
				// add to list of User
				User user=new User();
				user.setId(id);
				user.setUsername(uname);
				user.setPwd(pwd);
				user.setEmail(email);
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBUtils.close(connection, statement, resultset);
		}
		return users;
	}
	
	public void delete(int id) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBUtils.getConnection();
			String sql="delete from t_user where id=?";
			statement =connection.prepareStatement(sql);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBUtils.close(connection, statement, null);
		}
	}
}
