package controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Customer_dao;
import dto.Customer;

@WebServlet("/customersignup")
public class Customer_signup extends HttpServlet
{
 @Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 Customer_dao dao=new Customer_dao();
	Date date=Date.valueOf(req.getParameter("dob"));
	//String  name=req.getParameter("name");
	//String mobile=req.getParameter("mobile");
	//String password=req.getParameter("password");
	//String gender=req.getParameter("gender");
	//String dob=req.getParameter("dob");
	
	
	Long mobile=Long.parseLong(req.getParameter("mobile"));
	String email=req.getParameter("email");
     int age=(Period.between(date.toLocalDate(),LocalDate.now()).getYears());
		if (age<18)
		{
			resp.getWriter().print("<h1>You Have to be 18+ to create a Bank account</h1>");
		    req.getRequestDispatcher("Signup.html").include(req, resp);
		}
		else {
			if(dao.check(mobile).isEmpty()&&dao.check(email).isEmpty())
			{
			Customer customer=new Customer();
			customer.setName(req.getParameter("name"));
			customer.setGender(req.getParameter("gender"));
			customer.setPassword(req.getParameter("password"));
            customer.setDob(date);
            customer.setEmail(email);
            customer.setMobile(mobile);
            
            dao.save(customer);
            Customer customer2=dao.check(email).get(0);          
            
            resp.getWriter().print("<h1>Account Created Successfully</h1>");
           
            if(customer2.getGender().equals("male"))
            	resp.getWriter().print("<h1>Hello sir</h1>");
            else 
            	resp.getWriter().print("<h1>Hello Medom</h1>");
                resp.getWriter().print("<h1> Your Customer ID is:"+customer2.getCust_id()+"</h1>");
            req.getRequestDispatcher("Home.html").include(req, resp);
			
			}
		    else {
			resp.getWriter().print("<h1>Email or Phone Number is already Exists</h1>");
		    req.getRequestDispatcher("Signup.html").include(req, resp);
		    }
         }
}
}