package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.OrdersService;
import service.ReviewService;
import vo.Orders;


@WebServlet("/orders/ordersRemove")
public class OrdersRemoveController extends HttpServlet {	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 값 받아오기
		int orderCode = Integer.parseInt(request.getParameter("orderCode"));
		String customerId = request.getParameter("customerId");
		Orders orders = new Orders();
		orders.setCustomerId(customerId);
		OrdersService ordersService = new OrdersService();
		int row = ordersService.removeByCustomerOrder(orderCode, orders);
		
		// 결과
		if(row == 1) {
			// 리스트 이동
			System.out.println("삭제성공");
			response.sendRedirect(request.getContextPath()+"/orders/ordersList"); 
		} else {
			// 리스트 이동
			System.out.println("삭제실패");
			response.sendRedirect(request.getContextPath()+"/orders/ordersList?orderCode="+orderCode);
		}	
	}
}
