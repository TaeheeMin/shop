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
import vo.Customer;
import vo.Emp;

/**
 * Servlet implementation class OrdersListByAdminController
 */
@WebServlet("/orders/ordersListByAdmin")
public class OrdersListByAdminController extends HttpServlet {
	private OrdersService ordersService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 직원로그인세션 불러오기 - 직원이상만 가능
		HttpSession session = request.getSession();
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		
		// 직원로그인이 안되어있을경우, 직원등록폼 진입 불가 -> 홈으로 인동
		if(loginEmp == null) {
			System.out.println("직원만 접근 가능합니다");
			response.sendRedirect(request.getContextPath()+"/Home");
			return;
		}	
		
		ordersService = new OrdersService();
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		list = ordersService.selectOrdersListByAdmin();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/view/orders/ordersListByAdmin.jsp").forward(request, response);
	}

}
