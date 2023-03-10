package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.OrdersService;
import vo.Orders;

/**
 * Servlet implementation class OrdersModifyByAdminController
 */
@WebServlet("/orders/ordersModifyByAdmin")
public class OrdersModifyByAdminController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 관리자 주문상태 수정
		// 값받아오기
		request.setCharacterEncoding("utf-8");
		Orders orders = new Orders();
		orders.setOrderCode(Integer.parseInt(request.getParameter("orderCode")));
		orders.setOrderState(request.getParameter("orderState"));
	
		
		int row = 0;
		OrdersService ordersService = new OrdersService();
		row = ordersService.modifyByAdminOrders(row, orders);
	
		if(row == 1) {			
			System.out.println("수정성공");
			response.sendRedirect(request.getContextPath()+"/orders/ordersListByAdmin"); 
		} else { 
			System.out.println("수정실패");
			response.sendRedirect(request.getContextPath()+"/orders/ordersModify="+orders.getOrderCode());
		}
	
	}
}


