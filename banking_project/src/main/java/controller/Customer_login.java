package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Customer_dao;
import dto.Customer;
@WebServlet("/customerlogin")
public class Customer_login extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int cust_id=Integer.parseInt(req.getParameter("custid"));
		String password=req.getParameter("password");
		
		Customer_dao dao=new Customer_dao();
		Customer customer=dao.login(cust_id);
		if(customer==null) {
			resp.getWriter().print("<h1>Invalid customer id<h1>");
			req.getRequestDispatcher("Login.html").include(req, resp);
		}
		else {
			if(customer.getPassword().equals(password)) {
				req.getSession().setAttribute("customer", customer);			
				resp.getWriter().print("<h1>Logged in  Successfully<h1>");
				req.getRequestDispatcher("CustomeHome.html").include(req, resp);
			}
			else {
				resp.getWriter().print("<h1>Invalid Password<h1>");
				req.getRequestDispatcher("Login.html").include(req, resp);
			}
			}
		}
		
	}


