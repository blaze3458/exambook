package backup_src;

import javax.servlet.http.*;

import java.io.*;

public class Test2 extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet (HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		Writer wr = res.getWriter();
		String name = req.getParameter("your_name");
		String email = req.getParameter("e_mail");
		wr.write("<html>");
		wr.write("<body>");
		if(email.contains("@") && email.contains(".com"))
		{
			wr.write("<h1>Name : "+name+"</h1> ");
			wr.write("</br>");
			wr.write("<h1>Email : "+email+"</h1>");
		}
		else
		{
			wr.write("<div align=center>");
			wr.write(" <h1>wrong mail</h1> ");
			wr.write("</div>");
		}
		wr.write("</body>");
		wr.write("</html>");
	}

}
