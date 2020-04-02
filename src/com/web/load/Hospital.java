package com.web.load;
import javax.servlet.*;
import javax.servlet.http.*;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.web.jdbc.DBHelper;

import data.model.HospitalData;

import java.io.*;
import java.sql.SQLException;
import java.util.*;


public class Hospital extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private DBHelper dbHelper;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Hospital() {
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

	public void doPost(HttpServletRequest request, HttpServletResponse response)  throws IOException, ServletException {
		// 接收 UTF-8
		request.setCharacterEncoding("utf-8");
		// 送出 UTF-8
		response.setCharacterEncoding("utf-8");
		String cityArea = request.getParameter("cityArea");		
		List<HospitalData> datas = new ArrayList<HospitalData>();
		JsonArray jArray = new JsonArray();
		
		try {
			datas = dbHelper.getHospitalDatas(cityArea);
			for(HospitalData hospital : datas) {
				JsonObject obj = new JsonObject();
				obj.addProperty("name", hospital.getName());
				obj.addProperty("phone", hospital.getPhone());
				obj.addProperty("address", hospital.getAddr());
				jArray.add(obj);
			}			
			System.out.println(jArray.toString());			

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		response.setContentType("application/json");
		response.getWriter().write(jArray.toString());
	}  
}