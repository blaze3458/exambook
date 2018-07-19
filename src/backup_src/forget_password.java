package backup_src;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import classes.Data;

public class forget_password extends HttpServlet
{
	public boolean check = true;
	public String check_stat = "true";
	public String check_name = "Forget_user_name";
	Data Ch = new Data();
	private static final long serialVersionUID = 1L;
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException
	{

		PrintWriter pr = res.getWriter();
		pr.println("<!DOCTYPE html>");
		pr.println("<head><title>Forgot your password? . Site</title>");
		pr.println("<link rel=\"stylesheet\" href=\"Marketting.css\"></head>");
		pr.println("<body style = \"background:#89a8a5;\">");
		pr.println("<div class = \"top\" style = \"width:100%\" >");
		pr.println("<a class=\"header\" href=\"customer_index\">CUSTOMER</a> </div>");
		pr.println("<div class = \"lognav\">");
		pr.println("<p style=\"padding-left:40%; font-size: 20px;\" class =\"inform_parag\">Reset your password</p>");
		pr.println("<div class = \"loginner\">");
		if(!check)
			pr.println("<p class =\"inform_parag\">Can't find that username!</p>");

		pr.println("<form style = \"padding-left:10px;\" action = \"login_page\" method = \"POST\">");
		pr.println("<p class =\"inform_parag\">Username</p>");
		pr.println("<input id=\"login_inputs\" type = \"text\" name = \"Email\" required placeholder = \"Enter your email address\">");
		pr.println("<br/>");
		pr.println("<button id = \"login_button\" type = submit name = clear>Get Password</button></form>");
		pr.println("</div></div>");
		pr.println("</body></html>");
		
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		String name = req.getParameter("Email");
		//try {
		//	name = Ch.CheckForgetPassword(name);
		
		if(!name.equals(null))
		{
			name = null;
			RequestDispatcher reDis = req.getRequestDispatcher("/customer_index");
			reDis.forward(req, res);
			check = true;
		}
		else
		{
			check = false;
			doGet(req,res);
		//}
		} 
	//	catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
	}
}
