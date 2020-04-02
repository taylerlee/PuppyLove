package com.web.load;

import javax.servlet.*;
import javax.servlet.http.*;

import com.web.jdbc.DBHelper;

import java.io.*;
import java.sql.SQLException;

/**
 * Servlet implementation class MessageUs
 */
public class MessageUs extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBHelper dbHelper;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MessageUs() {
		super();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			dbHelper = new DBHelper(getServletContext().getInitParameter("driver"), 
					getServletContext().getInitParameter("dbserverip"), 
					Integer.parseInt(getServletContext().getInitParameter("dbserverport")), 
					getServletContext().getInitParameter("dbusername"),
					getServletContext().getInitParameter("dbpassword"),
					getServletContext().getInitParameter("database"));

		} catch (NumberFormatException e) {			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("收到contect");
		// 接收 UTF-8
		request.setCharacterEncoding("utf-8");
		// 宣告值 地區 變數
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String message = request.getParameter("message");
		try {
			dbHelper.setContect(name, email, phone, message);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
