package com.developer.registration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistrationServlet
 */

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String uname=request.getParameter("name");
		String umail=request.getParameter("email");
		String upwd=request.getParameter("pass");
		String umobile=request.getParameter("contact");
		RequestDispatcher rq=null;
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/new?useSSL=false","root","Zxcvbnm@123");
			PreparedStatement ps= con.prepareStatement("insert into users(uname,umail,upwd,umobile) values(?,?,?,?)");
			
			ps.setString(1, uname);
			ps.setString(2, umail);
			ps.setString(3, upwd);
			ps.setString(4, umobile);	
			
			int rowCount=ps.executeUpdate();
			rq=request.getRequestDispatcher("registration.jsp");
			if(rowCount>0) {
				request.setAttribute("status", "success");
			}else {
				request.setAttribute("status", "Failed");
			}
			rq.forward(request, response);
			
		} catch (Exception e) {
			System.out.println(e);
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		
	}

}
