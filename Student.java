package com;
import java.sql.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Student
 */
@WebServlet("/Student")
public class Student extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Student() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		
		String sid=request.getParameter("sid");
		String sname=request.getParameter("sname");
		String saddress=request.getParameter("saddress");
		String course[]=request.getParameterValues("course");
		
		pw.print(sid+" "+sname+" "+saddress);
		
		String courselist="";
		for(String c1:course)
		{
			courselist=courselist+" "+c1;
			
			pw.print("<br>"+c1);
		}
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/servletadjt","root","root");//Connection Established
			String sql="insert into student values(?,?,?,?)";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1, Integer.parseInt(sid));
			pst.setString(2, sname);
			pst.setString(3, saddress);
			pst.setString(4, courselist);
			
			
			pst.execute();
			
			pw.print(" Record Inserted");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
