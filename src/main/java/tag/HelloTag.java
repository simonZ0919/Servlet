package tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class HelloTag extends SimpleTagSupport {
	// attribute of tage, name/type matched
	private String msg;
	private int lines;
	
	
	public HelloTag() {
		System.out.println("construct object");
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setLines(int lines) {
		this.lines = lines;
	}

	public void doTag() throws JspException, IOException {
//		get pagecontext of jsp
		PageContext context=(PageContext) getJspContext();
		JspWriter out=context.getOut();
		for (int i = 0; i < lines; i++) {
			out.println(msg+"<br>");
		}
	}
}
