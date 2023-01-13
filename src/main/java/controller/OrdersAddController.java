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

import service.CustomerAddressService;
import service.GoodsService;
import service.OrdersService;
import service.PointHistoryService;
import vo.Customer;
import vo.CustomerAddress;
import vo.Orders;
import service.CartService;


@WebServlet("/orders/ordersAdd")
public class OrdersAddController extends HttpServlet {
	private CustomerAddressService customerAddressService;
	private GoodsService goodsService;
	private CartService cartService;
	private PointHistoryService pointHistoryService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1-1) 로그인세션 불러오기
		HttpSession session = request.getSession();
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		
		// 1-2) 로그인되지 않은경우, 주문페이지 폼 진입 불가 -> 홈화면으로 이동
		if(loginCustomer == null) {
			response.sendRedirect(request.getContextPath()+"/Home");
			return;
		}
		System.out.println("[OrdersAdd컨트롤러 진입]");

		// 2) Service
		this.goodsService = new GoodsService();
		this.cartService = new CartService();
		this.customerAddressService = new CustomerAddressService();
		
		// 3) 주문할 상품목록(장바구니) 불러오기 
		ArrayList<HashMap<String,Object>> cartList = cartService.getCartList(loginCustomer.getCustomerId());
		request.setAttribute("cartList", cartList);
		
		// 4) 선택된 회원주소 불러오기
		CustomerAddress cusAddress = new CustomerAddress();
		if(request.getParameter("addressCode") != null) {
			String customerId = loginCustomer.getCustomerId();
			int addressCode = Integer.parseInt(request.getParameter("addressCode"));
			cusAddress = customerAddressService.myAddress(customerId, addressCode);
		}

		request.setAttribute("myAddress", cusAddress);
		request.getRequestDispatcher("/WEB-INF/view/orders/ordersPage.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.cartService = new CartService();
		OrdersService ordersService = new OrdersService();
		this.pointHistoryService = new PointHistoryService();
		
		// 로그인세션 불러오기
		HttpSession session = request.getSession();
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		// 로그인되지 않은경우, 주문페이지 폼 진입 불가 -> 홈화면으로 이동
		if(loginCustomer == null) {
			response.sendRedirect(request.getContextPath()+"/Home");
			return;
		}
		System.out.println("[OrdersAdd컨트롤러 post]");

		// 값 받아오기
		Orders orders = new Orders();;	
		int addressCode = Integer.parseInt(request.getParameter("addressCode"));				
		String customerId = request.getParameter("customerId");	
		int point = Integer.parseInt(request.getParameter("point")); // 잔여 포인트 
		String[] pointCkList = request.getParameterValues("pointCk"); // 포인트 사용 goods
		
		//값 저장 
		ArrayList<HashMap<String,Object>> cartList = cartService.getCartList(loginCustomer.getCustomerId());
		orders.setAdrressCode(addressCode);		
		orders.setCustomerId(customerId);
		
		//디버깅	
		System.out.println("addressCode : " + addressCode);	
		System.out.println("customerId : " + customerId);
		for (int i = 0; i < pointCkList.length; i++) {            
			System.out.println(pointCkList[i]);   
		}
		
		// 포인트 일부사용
		
		// 포인트 전액사용(카트 모두 사용)
		int row = 0;
		if(request.getParameter("pointAll") != null) {
			// 주문add
			row = ordersService.AddOrderPoint(orders, cartList, customerId);
			
			
			// 잔여 포인트 조회
			int customerPoint = pointHistoryService.remainCustomerPoint(loginCustomer);
			loginCustomer.setPoint(customerPoint);
			System.out.println("잔여 포인트 : " + customerPoint);
		} else {
			row = ordersService.addOrder(orders, cartList, customerId);	
		}
		
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


