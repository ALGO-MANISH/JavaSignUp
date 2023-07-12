package com.developer.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String umail=request.getParameter("username");
		String upwd=request.getParameter("password");
		HttpSession session =request.getSession();
		RequestDispatcher rq=null;
		Connection con=null;
		
//		PrintWriter out =response.getWriter();
//		out.print("its working ");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/new?useSSL=false","root","Zxcvbnm@123");
			
			PreparedStatement ps=con.prepareStatement("select * from users where umail=? and upwd=?");
			ps.setString(1, umail);
			ps.setString(2, upwd);
			
			
			
		
			ResultSet rs=ps.executeQuery();
		
			
			if(rs.next()) {
				
				
			
				session.setAttribute("name", rs.getString("uname"));
				rq=request.getRequestDispatcher("index.jsp");
			
			
		}
		
			else {
				request.setAttribute("status", "failed");
				rq=request.getRequestDispatcher("login.jsp");
			}
			
		rq.forward(request, response);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}

}
