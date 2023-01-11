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
import vo.Cart;
import vo.Customer;
import vo.CustomerAddress;
import vo.Goods;
import vo.Orders;
import service.CartService;


@WebServlet("/orders/ordersAdd")
public class OrdersAddController extends HttpServlet {
	private CustomerAddressService customerAddressService;
	private GoodsService goodsService;
	private CartService cartService;
	private PointHistoryService pointHistoryService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인세션 불러오기
		HttpSession session = request.getSession();
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		
		// 로그인되지 않은경우, 주문페이지 폼 진입 불가 -> 홈화면으로 이동
		if(loginCustomer == null) {
			response.sendRedirect(request.getContextPath()+"/Home");
			return;
		}
		System.out.println("[OrdersAdd컨트롤러 진입]");
		System.out.println(request.getParameter("goodsCode"));
		// 파라미타값 받아오기
		// String goodsTitle = request.getParameter("goodsTitle");
		int goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
		System.out.println("(1)goodsCode : "+goodsCode);
		this.goodsService = new GoodsService();
		int orderQuantity = Integer.parseInt(request.getParameter("cartQuantity"));
		Cart cart = new Cart();
		// 주문할 상품목록 불러오기 
		this.cartService = new CartService();
		
		ArrayList<HashMap<String,Object>> cartList = cartService.getCartList(loginCustomer.getCustomerId());
		request.setAttribute("cartList", cartList);
		request.setAttribute("orderQuantity", orderQuantity);
		// request.setAttribute("goodsTitle", goodsTitle);
		// int orderPrice = Integer.parseInt(request.getParameter("cartPrice"));
		
		this.customerAddressService = new CustomerAddressService();
		
		/* 주소 추가입력 있을 시,
		if(request.getParameter("address") != null) {
			System.out.println("주소추가입력값 :::: "+request.getParameter("address"));
			CustomerAddress insertAddress = new CustomerAddress();
			insertAddress.setCustomerId(loginCustomer.getCustomerId());
			insertAddress.setAddress(request.getParameter("address"));
			int addMyaddress = customerAddressService.addMyAddress(insertAddress);
			if(addMyaddress == 1) {
				System.out.println("[OrdersAdd컨트롤러] 주소추가 완료");
				response.sendRedirect(request.getContextPath()+"/orders/ordersAdd?goodsCode="+goodsCode);
				return;
			} else {
				System.out.println("[OrdersAdd컨트롤러] 주소추가 실패");
				response.sendRedirect(request.getContextPath()+"/orders/ordersAdd?goodsCode="+goodsCode);
			}
		}
		*/
		// 회원 포인트 불러오기
		this.pointHistoryService = new PointHistoryService();
		int customerPoint = pointHistoryService.modifyCustomerPoint(loginCustomer);
		if(customerPoint == 1) {
			System.out.println("고객 잔여 포인트 로딩");
		}
		
		// 회원주소목록 불러오기
		CustomerAddress cusAddress = new CustomerAddress();
		cusAddress.setCustomerId(loginCustomer.getCustomerId());
		ArrayList<CustomerAddress> addressList = customerAddressService.myAddressList(cusAddress);
		request.setAttribute("addressList", addressList);
		
		request.getRequestDispatcher("/WEB-INF/view/orders/ordersPage.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인세션 불러오기
		HttpSession session = request.getSession();
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		// 로그인되지 않은경우, 주문페이지 폼 진입 불가 -> 홈화면으로 이동
		if(loginCustomer == null) {
			response.sendRedirect(request.getContextPath()+"/Home");
			return;
		}
		
		request.setCharacterEncoding("utf-8");
		System.out.println("[OrdersAdd컨트롤러 post]");

		// 값 받아오기
		Orders orders = new Orders();;	
		int addressCode = Integer.parseInt(request.getParameter("addressCode"));				
		String customerId = request.getParameter("customerId");	
		
		//값 저장 
		ArrayList<HashMap<String,Object>> cartList = cartService.getCartList(loginCustomer.getCustomerId());
		
		orders.setAdrressCode(addressCode);		
		orders.setCustomerId(customerId);
		this.cartService = new CartService();
		
		//디버깅	
		System.out.println("addressCode : "+addressCode);	
		System.out.println("customerId : "+customerId);
		
		int row = 0;
		OrdersService ordersService = new OrdersService();
		
		// 체크값이 null이면 포인트 사용안함
		// System.out.println("포인트 체크: " + request.getParameter("pointCk"));
		if(request.getParameter("pointCk") != null) {
			row = ordersService.AddOrderPoint(orders, cartList, customerId);
		} else {
			row = ordersService.addOrder(orders, cartList, customerId);
			int customerPoint = pointHistoryService.remainCustomerPoint(loginCustomer);
			loginCustomer.setPoint(customerPoint);
			System.out.println("잔여 포인트 : " + customerPoint);
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


