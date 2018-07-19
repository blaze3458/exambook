package backup_src;

import javax.servlet.http.*;

import java.io.*;


public class HelloServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		PrintWriter wr = res.getWriter();
		wr.write("<h1>HelloServlet</h1>");
	}
}
