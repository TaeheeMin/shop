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

import service.OrdersService;
import vo.*;


@WebServlet("/orders/ordersComplete")
public class OrdersCompleteController extends HttpServlet {
	private OrdersService ordersService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1-1) 로그인세션 불러오기
		HttpSession session = request.getSession();
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		
		// 1-2) 로그인되지 않은경우, 주문페이지 폼 진입 불가 -> 홈화면으로 이동
		if(loginCustomer == null) {
			response.sendRedirect(request.getContextPath()+"/Home");
			return;
		}
		this.ordersService = new OrdersService();
		System.out.println("[OrdersCompletes컨트롤러 진입]");
		ArrayList<Orders> list = (ArrayList<Orders>)session.getAttribute("list");
		ArrayList<HashMap<String, Object>> orderOne = new ArrayList<HashMap<String, Object>>();
		for(int i = 0; i < list.size(); i++) {
			orderOne = ordersService.selectOrdersOneList(list.get(i).getOrderCode());
		}
		
		session.setAttribute("orderOne", orderOne);
		
		request.getRequestDispatcher("/WEB-INF/view/orders/ordersComplete.jsp").forward(request, response);
	}
}
