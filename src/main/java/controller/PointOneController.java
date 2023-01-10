package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.PointHistoryService;
import vo.Customer;


@WebServlet("/point/pointOne")
public class PointOneController extends HttpServlet {
	private PointHistoryService pointHistoryService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//현재 포인트 		
		
		HttpSession session = request.getSession();
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		System.out.println("loginCustomer : "+loginCustomer.getCustomerId());
		
		pointHistoryService = new PointHistoryService();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = pointHistoryService.selectPoint(loginCustomer);
		System.out.println(map);
		request.setAttribute("map", map);
		request.getRequestDispatcher("/WEB-INF/view/point/pointOne.jsp").forward(request, response);
				
	}
	
}
