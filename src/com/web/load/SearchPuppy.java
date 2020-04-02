package com.web.load;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.web.jdbc.DBHelper;

/**
 * Servlet implementation class SearchPuppy
 */
public class SearchPuppy extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBHelper dbHelper;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchPuppy() {
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
		String type;
		if(request.getParameter("type") == null) {
			response.sendRedirect("SearchPuppy?type=all");
		}
		else if (request.getParameter("type").equals("all") ||
				request.getParameter("type").equals("dog") || 
				request.getParameter("type").equals("cat") || 
				request.getParameter("type").equals("other")) {
			type = request.getParameter("type");
			System.out.println("SearchPuppy:type:" + type);
			request.setAttribute("type", type);
			RequestDispatcher rd = request.getRequestDispatcher("adoption.jsp");
			rd.forward(request, response);
		}
		else {
			response.sendRedirect("SearchPuppy?type=other");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String area;
		String kind;
		String sex;
		String bodytype;
		String age;
		String colour;
		String status;
		String sterilization;
		String bacterin;
		String page;
		List<String> optionsList = new ArrayList<String>();
		Map<String, String> animals = new HashMap<String, String>();
		JsonArray jArray = new JsonArray();

		int num = 0;
		try {
			HttpSession session = request.getSession(false);
			if (session == null){
				System.out.println("no session, get new one. ");
				session = request.getSession();
			}
			System.out.println("session id: " + session.getId());

			System.out.println("---------------查詢條件--------------------");
			area = request.getParameter("area");
			optionsList.add(area);
			System.out.println("SearchPuppy:area:" + area);

			kind = request.getParameter("kind");
			optionsList.add(kind);
			System.out.println("SearchPuppy:kind:" + kind);

			sex = request.getParameter("sex");
			optionsList.add(sex);
			System.out.println("SearchPuppy:sex:" + sex);

			bodytype = request.getParameter("bodytype");
			optionsList.add(bodytype);
			System.out.println("SearchPuppy:bodytype:" + bodytype);

			age = request.getParameter("age");
			optionsList.add(age);
			System.out.println("SearchPuppy:age:" + age);

			colour = request.getParameter("colour");
			optionsList.add(colour);
			System.out.println("SearchPuppy:colour:" + colour);

			status = request.getParameter("status");
			optionsList.add(status);
			System.out.println("SearchPuppy:status:" + status);

			sterilization = request.getParameter("sterilization");
			optionsList.add(sterilization);
			System.out.println("SearchPuppy:sterilization:" + sterilization);

			bacterin = request.getParameter("bacterin");
			optionsList.add(bacterin);
			System.out.println("SearchPuppy:bacterin:" + bacterin);
			
			page = request.getParameter("page");
			System.out.println("SearchPuppy:page:" + page);
			System.out.println("---------------------------------------");

			if (page.toLowerCase().equals("default")) {
				num = 0;
				session.setAttribute("page", num);
				animals = dbHelper.searchAnimalByOption(optionsList, num, 25);
			}
			else if (page.toLowerCase().equals("pre")) {
				num = (int)session.getAttribute("page");
				if(num >= 25) {
					num -= 25;
				}
				session.setAttribute("page", num);
				animals = dbHelper.searchAnimalByOption(optionsList, num, 25);
			}
			else if (page.toLowerCase().equals("next")) {
				num = (int)session.getAttribute("page");
				num += 25;		
				session.setAttribute("page", num);
				animals = dbHelper.searchAnimalByOption(optionsList, num, 25);
			}
			else {
				// do something ??
			}
			
			//animals = dbHelper.searchAnimalByOption(optionsList, 0, 25);
			System.out.println("第 " + num + " 筆 ~ 第 " + (num+animals.size()-1) + " 筆");
			for(Entry<String, String> animal : animals.entrySet()) {
				JsonObject obj = new JsonObject();
				obj.addProperty("id", animal.getKey());
				obj.addProperty("img", animal.getValue());
				jArray.add(obj);
			}
			System.out.println(jArray.toString());
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		response.setContentType("application/json");
		response.getWriter().write(jArray.toString());
	}
}
