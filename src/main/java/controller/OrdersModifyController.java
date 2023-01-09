package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.OrdersService;
import vo.Orders;

@WebServlet("/orders/ordersModify")
public class OrdersModifyController extends HttpServlet {	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//고객용 주문상태 수정
		
		// 값받아오기
		String orderState = request.getParameter("orderState");
		System.out.println();
		request.setCharacterEncoding("utf-8");
		Orders orders = new Orders();
		orders.setOrderCode(Integer.parseInt(request.getParameter("orderCode")));
		orders.setOrderState(orderState);
		orders.setCustomerId(request.getParameter("customerId"));
		
		int row = 0;
		OrdersService ordersService = new OrdersService();
		row = ordersService.modifyByCustomerOrders(row, orders);
	
		if(row == 1) {			
			System.out.println("수정성공");
			response.sendRedirect(request.getContextPath()+"/orders/ordersOne"); 
		} else { 
			System.out.println("수정실패");
			response.sendRedirect(request.getContextPath()+"/orders/ordersModify="+orders.getOrderCode());
		}
	
	}
}


