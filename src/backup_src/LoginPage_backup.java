package backup_src;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import classes.Data;

public class LoginPage_backup extends HttpServlet
{
	public boolean check = true;
	public String check_stat = "true";
	public String check_name = "User_name_pass";
	Data Ch = new Data();
	private static final long serialVersionUID = 1L;
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		Cookie[] cook_arr = req.getCookies();
		Cookie a_cook = null;
		if(cook_arr != null && cook_arr.length > 0)
		{
			Cookie cook = cook_arr[0];
			String cookVal = cook.getValue();
			System.out.println("CokieValue: "+cookVal);
			if(check_stat.equals(cookVal))
			{
				a_cook = new Cookie(check_name,"false");
				a_cook.setPath(req.getContextPath()+ "/");
				res.addCookie(a_cook);
				check = true;
			}
		}
		else
		{
			a_cook = new Cookie(check_name,check_stat);
			a_cook.setPath(req.getContextPath()+ "/");
			res.addCookie(a_cook);
		}
		PrintWriter pr = res.getWriter();
		pr.println("<!DOCTYPE html>");
		pr.println("<head><title>Index</title>");
		pr.println("<link rel=\"stylesheet\" href=\"Marketting.css\"></head>");
		pr.println("<body style = \"background:#89a8a5;\">");
		pr.println("<div class = \"top\" style = \"width:100%\" >");
		pr.println("<a class=\"header\" href=\"customer_index\">CUSTOMER</a> </div>");
		pr.println("<div class = \"lognav\">");
		pr.println("<p class =\"inform_parag\">LOGIN TO YOUR ACCOUNT</p>");
		pr.println("<div class = \"loginner\">");
		if(!check)
			pr.println("<p class =\"inform_parag\">Wrong name or password</p>");

		pr.println("<form style = \"padding-left:10px;\" action = \"login_page\" method = \"POST\">");
		pr.println("<p class =\"inform_parag\">Username</p>");
		pr.println("<input id=\"login_inputs\" type = \"text\" name = \"UserName\" required>");
		pr.println("<p class =\"inform_parag\">Password</p>");
		pr.println("<input id=\"login_inputs\" type = \"password\" name = \"Pass\" required>");
		pr.println("<br/>");
		pr.println("<a href=\"reset_password\">Did you forget your password?</a>");
		pr.println("<button id = \"login_button\" type = submit name = clear>Login</button></form>");
		pr.println("<a href =\"signup_page\">Create an account.</a>");
		pr.println("</div></div>");
		pr.println("</body></html>");
		
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		String name = req.getParameter("UserName");
		String password = req.getParameter("Pass");
		
		System.out.println(name+":"+password);
		try {
				if(Ch.CheckUserNamePassword(name,password))
				{
					name = null;
					password = null;
					Cookie a_cook = new Cookie(check_name,check_stat);
					a_cook.setPath(req.getContextPath()+ "/");
					res.addCookie(a_cook);
					RequestDispatcher reDis = req.getRequestDispatcher("/customer_index");
					reDis.forward(req, res);
					check = true;
				}
				else
				{
					check = false;
					doGet(req,res);
				}
			} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
