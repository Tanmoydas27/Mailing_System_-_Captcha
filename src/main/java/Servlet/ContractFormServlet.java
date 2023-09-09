package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.Document;

import bean.ContactUs;

@WebServlet(urlPatterns = "/ContactForm")
public class ContractFormServlet  extends HttpServlet
{
	@Override
	
	protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String message = req.getParameter("message");
		
		
//		
//		
//		out.print(name+ " "+email+" "+phone+" "+message);
		
		ContactUs cu = new ContactUs();
		cu.setName(name);
		cu.setEmail(email);
		cu.setPhone(phone);
		cu.setMessage(message);
		
		
		try {
			int response = cu.SendMessages();
			
			PrintWriter out = resp.getWriter();
			
			if(response == 1)
			{
				out.print("1");
			}
			else
			{
				out.print("0");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		
		
		
	}

}
