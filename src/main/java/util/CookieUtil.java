package util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author simon
 * Povide method for add/read/delete cookie
 */
public class CookieUtil {
	// add cookie 
	public static void addCookie(String name, String value,
			int age, String path, HttpServletResponse response) 
					throws UnsupportedEncodingException {
		// create cookie, encode value for non-Ascii 
		Cookie cookie=new Cookie(name, 
				URLEncoder.encode(value, "utf-8"));
		// set lifetime and path
		cookie.setMaxAge(age);
		cookie.setPath(path);
		response.addCookie(cookie);
	}
	
	//find cookie by name
	public static String findCookie(String name, HttpServletRequest request) 
			throws UnsupportedEncodingException {
		String value=null;
		Cookie[] cookies=request.getCookies();
		if(cookies!=null) {
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals(name)) {
					// decode cookie value
					value=URLDecoder.decode(cookie.getValue(), "utf-8");
				}
			}
		}
		return value;
	}
	
	// delete cookie by name 
	public static void deleteCookie(String name, String path,
			HttpServletResponse response) {
		Cookie cookie=new Cookie(name, null);
		cookie.setPath(path);
		cookie.setMaxAge(0);//time=0, delete
		response.addCookie(cookie);
		
	}
}
