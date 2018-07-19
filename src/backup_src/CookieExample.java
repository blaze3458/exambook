package backup_src;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

public class CookieExample extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private static String request_type = null;
	@Override
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException
	{
		String cookieValue = req.getParameter("CookieValue");
		String cookieName = req.getParameter("CookieName");
		Cookie a_cook = null;
		if(cookieValue != null && cookieName != null)
		{
			a_cook = new Cookie(cookieName, cookieValue);
			a_cook.setPath(req.getContextPath()+ "/");
			res.addCookie(a_cook);	
		}
		
		//Cookie CoookieName
		//Cookie[] cook_arr = req.getCookies();
		//Cookie[i].getName();
		//Cookie[i].getValue();
		
		res.setContentType("text/html");
		
		PrintWriter pr = res.getWriter();
		pr.println("<html>");
		pr.println("<head> <title>Cookie Example</title> </head>");
		pr.println("<body>");
		pr.println("<h6> Cookies Example </h6>");
		pr.println("<p> Your browser is sending the following cookies:");
		
		Cookie[] cook_arr = req.getCookies();
		if(cook_arr != null && cook_arr.length > 0)
		{
			for(int i = 0; i < cook_arr.length; i++)
			{
				Cookie cook = cook_arr[i];
				String name = cook.getName();
				String value = cook.getValue();
				if(request_type == "clear")
				{
					cook.setValue("");
					cook.setPath("/");
					cook.setMaxAge(0);
					res.addCookie(cook);
				}
				else 
				{
					pr.println("<p>No. "+i);
					pr.println("<p>Cookie Name : "+name);
					pr.println("<p>Cookie Value : "+ value);
				}	
			}
		}
		else
			pr.println("<p> No cookie");
		
		if(a_cook != null)
		{
			String a_cook_name = a_cook.getName();
			String a_cook_val = a_cook.getValue();
			pr.println("<p> You just sent the following cookie to your browser:");
			pr.println("<p> Name:"+a_cook_name);
			pr.println("<p> Value:"+a_cook_val);
		}
		pr.println("<P>Create a cookie send the browser");
		pr.println("<form action = \"Cookie\" method = \"POST\">");
		pr.println("Name : <input type = text name= CookieName required>");
		pr.println("Value: <input type = Value name= CookieValue required>");
		pr.println("<input type = submit name = sub></form>");
		pr.println("<form action = Cookie method = POST>");
		pr.println("<button type = submit name = clear>Clear cookie</button></form>");
		pr.println("</body>");
		pr.println("</html>");
	}
	@Override
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException
    {
		System.out.println("POST REQUEST : ");
		System.out.println("\tIP:"+request.getRemoteAddr()+" Host:"+request.getRemoteHost());
		if(request.getParameter("clear") != null)
		{
			System.out.println("Request clear");
			request_type = "clear";
		}
		else
			request_type = "cookie";
        doGet(request, response);
    }

}
