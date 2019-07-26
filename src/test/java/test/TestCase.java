package test;

import java.util.List;

import dao.UserDAO;
import entity.User;

public class TestCase {

	public static void main(String[] args) throws Exception {
		UserDAO dao=new UserDAO();
		List<User> users=dao.findAll();
		System.out.println(users);
		
		User user=new User();
		user.setUsername("Mary");
		user.setPwd("123");
		user.setEmail("zz@gmail.com");
		dao.save(user);
	}

}
