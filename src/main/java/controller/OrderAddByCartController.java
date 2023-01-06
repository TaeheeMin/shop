package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.OrdersService;
import vo.Orders;

@WebServlet("/orders/ordersAddByCart")
public class OrderAddByCartController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//값받아서 넘겨주기
		int goodsCode = Integer.parseInt(request.getParameter("goodsCode")); 
		int ordersPrice = Integer.parseInt(request.getParameter("cartPrice"));
		int orderQuantity = Integer.parseInt(request.getParameter("cartQuantity"));
		

		request.setAttribute("goodsCode", goodsCode);
		request.setAttribute("orderQuantity", orderQuantity);
		request.setAttribute("ordersPrice", ordersPrice);
		
		
		
		request.getRequestDispatcher("/WEB-INF/view/orders/ordersPage.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		// 값 받아오기
		Orders orders = new Orders();;
		int goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
		int addressCode = Integer.parseInt(request.getParameter("addressCode"));
		int orderQuantity = Integer.parseInt(request.getParameter("orderQuantity"));
		int orderPrice = Integer.parseInt(request.getParameter("orderPrice"));
		String customerId = request.getParameter("customerId");		
		//값 저장 
		orders.setAdrressCode(goodsCode);	
		orders.setGoodsCode(addressCode);
		orders.setCustomerId(customerId);
		orders.setOrderQuantity(orderQuantity);
		orders.setOrderPrice(orderPrice);

	
		int row = 0;
		OrdersService ordersService = new OrdersService();
		row = ordersService.AddOrder(orders);
		// 결과
		if(row == 1) {
			// 리스트로 이동
			System.out.println("성공");
			response.sendRedirect(request.getContextPath()+"/orders/ordersComplete"); 
		} else {
			// 폼이동
			System.out.println("실패");
			response.sendRedirect(request.getContextPath()+"/orders/ordersAdd");
		}
	}

}
