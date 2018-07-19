package backup_src;

import javax.servlet.http.*;
import java.io.*;
/*CUSTOMERID EMAIL,NAME,SURNAME,PASSWORD,USERNAME,ADRESS,CITY,COUNTRY,POSTCODE,PHONE*/
public class customer_index extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		 Cookie[] cook_arr = req.getCookies();
		 if(cook_arr != null && cook_arr.length > 0)
		 {
			//System.out.println(cook_arr[0].getValue());
			Cookie a_cook = new Cookie("User_name_pass","true");
			a_cook.setPath(req.getContextPath()+ "/");
			res.addCookie(a_cook);
		 }
		 PrintWriter pr = res.getWriter();
		 pr.println("<!DOCTYPE html>");
		 pr.println("<head><title>Index</title>");
		 pr.println("<link rel=\"stylesheet\" href=\"Marketting.css\"></head>");
		 pr.println("<body style = \"background:#89a8a5;\">");
		 pr.println("<div class = \"top\">");
		 pr.println("<a class=\"header\" href=\"customer_index\">CUSTOMER</a> </div>");
		 pr.println("<div class = \"loginside\">");
/*IF NOT LOG IN*/
		 pr.println("<a href=\"login_page\">Log In</a> or ");
		 pr.println("<a href=\"\">Sign Up</a>");
/*IF LOGIN*/
		 /*
		 pr.println("<a href=\"my_profile\">GetName()</a>"); 
		   
		 */
		 pr.println("</div>");
		 pr.println("<div class =\"topnav\"><div style = \"overflow:auto;\">");
		 pr.println("<div class = \"undertopnav\">");
		 pr.println("<a class = \"top_item\" href=customer_index>Home</a>");
		 pr.println("<a class = \"top_item\" href=customer_index>Home-2</a>");
		 pr.println("</div></div></div>");
		 pr.println("<div class =\"sidenav\" style=\"top:109px;\">");
		 pr.println("<div class = \"leftmenuinner\">");
		 pr.println("<div class = \"leftmenuinnerinner\">");
		 pr.println("<a target=\"_top\" href =\"\">Sidenav </a>");
		 pr.println("<a target=\"_top\" href =\"\">Sidenav </a>");
		 pr.println("<a target=\"_top\" href =\"\">Sidenav </a>");
		 pr.println("<a target=\"_top\" href =\"\">Sidenav </a>");
		 pr.println("</div></div></div>");
		 pr.println("<div class =\"belowtopnav\"></div>");
		 pr.println("</body></html>");
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		doGet(req,res);
	}

}
