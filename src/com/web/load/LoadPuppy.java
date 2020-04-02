package com.web.load;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.Map.Entry;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.*;
import com.web.jdbc.DBHelper;

import data.model.Animal;

/**
 * Servlet implementation class LoadPuppy
 */
public class LoadPuppy extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBHelper dbHelper;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadPuppy() {
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
		String page = "";
		String type = "";
		int num = 0;
		int countAnimal = 0;
		Map<String, String> animals = new HashMap<String, String>();
		JsonArray jArray = new JsonArray();

		countAnimal = dbHelper.countAnimal();
		System.out.println("共有 " + countAnimal + " 筆動物資料...");

		try {
			HttpSession session = request.getSession(false);
			if (session == null){
				System.out.println("no session, get new one. ");
				session = request.getSession();
			}
			System.out.println("session id: " + session.getId());


			if(request.getParameter("page") == null || request.getParameter("page").equals("")) {
				page = "default";
			}
			else {
				page = request.getParameter("page");
			}

			if(request.getParameter("type") == null || request.getParameter("type").equals("")) {
				type = "all";
			}
			else {
				type = request.getParameter("type");
			}
			System.out.println("page: " + page);
			System.out.println("type: " + type);
			if (page.toLowerCase().equals("default")) {
				num = 0;
				session.setAttribute("page", num);
				animals = dbHelper.getAnimal(type, num, 25);
			}
			else if (page.toLowerCase().equals("pre")) {
				num = (int)session.getAttribute("page");
				if(num >= 25) {
					num -= 25;
				}
				session.setAttribute("page", num);
				animals = dbHelper.getAnimal(type, num, 25);
			}
			else if (page.toLowerCase().equals("next")) {
				if(session.getAttribute("page") == null) {
					num = 0;
				}
				else {
					num = (int)session.getAttribute("page");
					num += 25;
				}
				session.setAttribute("page", num);
				animals = dbHelper.getAnimal(type, num, 25);
			}
			else {
				// do something ??
			}

			System.out.println("第 " + num + " 筆 ~ 第 " + (num+animals.size()-1) + " 筆");

			for(Entry<String, String> animal : animals.entrySet()) {
				JsonObject obj = new JsonObject();
				obj.addProperty("id", animal.getKey());
				obj.addProperty("img", animal.getValue());
				jArray.add(obj);
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		System.out.println(jArray.toString());

		response.setContentType("application/json");
		response.getWriter().write(jArray.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String checkid = request.getParameter("id");
		System.out.println("check_id: " + checkid);

		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		Animal animal = dbHelper.selectAnimalInfoByCheckId(checkid);
		System.out.println(animal.toString());
		response.getWriter().write(animal.toString());
	}

}
