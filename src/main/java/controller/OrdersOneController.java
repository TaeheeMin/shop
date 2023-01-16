package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.OrdersService;


@WebServlet("/orders/ordersOne")
public class OrdersOneController extends HttpServlet {
	private OrdersService ordersService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ordersService = new OrdersService();
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		int orderCode = Integer.parseInt(request.getParameter("orderCode"));
		list = ordersService.selectOrdersOneList(orderCode);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/view/orders/test.jsp").forward(request, response);
	
	}
}
