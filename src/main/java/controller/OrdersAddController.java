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
import vo.PointHistory;
import service.CartService;


@WebServlet("/orders/ordersAdd")
public class OrdersAddController extends HttpServlet {
	private CustomerAddressService customerAddressService;
	private GoodsService goodsService;
	private CartService cartService;
	private PointHistoryService pointHistoryService;
	private OrdersService ordersService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1-1) 로그인세션 불러오기
		HttpSession session = request.getSession();
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");

		// 1-2) 로그인되지 않은경우, 주문페이지 폼 진입 불가 -> 장바구니로 이동 + alert출력
		if(loginCustomer == null) {
			String notLogin = "accesslimit";
			response.sendRedirect(request.getContextPath()+"/CartList?notLogin="+notLogin);
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
		
		// 3-1) 장바구니가 비어있는데 주문할 경우
		
		
		// 4) 회원주소 불러오기 (모달)
		CustomerAddress cusAddress = new CustomerAddress();
		cusAddress.setCustomerId(loginCustomer.getCustomerId());
		this.customerAddressService = new CustomerAddressService();
		ArrayList<CustomerAddress> list = customerAddressService.myAddressList(cusAddress);
		request.setAttribute("list", list);

		// 5) 주소 최대 3개 저장가능 -> 초과추가할시 alert출력
		if(request.getParameter("maxAddress") != null) {
			request.setAttribute("maxAddress", request.getParameter("maxAddress"));
		}
		request.setAttribute("myAddress", cusAddress);
		request.getRequestDispatcher("/WEB-INF/view/orders/ordersPage.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.cartService = new CartService();
		this.pointHistoryService = new PointHistoryService();
		this.ordersService = new OrdersService();

		// 로그인세션 불러오기
		HttpSession session = request.getSession();
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		
		// 로그인되지 않은경우, 주문페이지 폼 진입 불가 -> 장바구니로 이동
		if(loginCustomer == null) {
			response.sendRedirect(request.getContextPath()+"/CartList");
			return;
		}
		System.out.println("[OrdersAdd컨트롤러 post]");

		// 값 받아오기
		int addressCode = Integer.parseInt(request.getParameter("addressCode"));				
		String customerId = loginCustomer.getCustomerId();
		//System.out.println("addressCode : " + addressCode);	
		System.out.println("customerId : " + customerId);
				
		//int point = Integer.parseInt(request.getParameter("point")); // 잔여 포인트 
		//String[] pointCkList = request.getParameterValues("pointCk"); // 포인트 사용 goods
		String[] goodsCode = request.getParameterValues("goodsCode");
		String[] cartQuantity = request.getParameterValues("cartQuantity");
		String[] orderPrice = request.getParameterValues("orderPrice");
		System.out.println(orderPrice[0]);
		String sharePoint = request.getParameter("sharePoint");
		int point = Integer.parseInt(request.getParameter("point"));
		//System.out.println("sharePoint : " + sharePoint);
		//System.out.println("point : " + point);
		//System.out.println("pointAll : " + request.getParameter("pointAll"));
		//System.out.println("usePoint : " + request.getParameter("usePoint"));
		
		// 서비스 호출
		ArrayList<HashMap<String,Object>> cartList = cartService.getCartList(customerId);
		ArrayList<Orders> list = new ArrayList<Orders>();
		HashMap<String, Object> m = new HashMap<String, Object>();
		// 주문완료시 출력
		ArrayList<Orders> orderCode = new ArrayList<Orders>();
		ArrayList<HashMap<String, Object>> orderOne = new ArrayList<HashMap<String, Object>>();
		Orders orders = new Orders();
		for(int i = 0; i < orderPrice.length; i++) {
			orders.setCustomerId(customerId);
			orders.setAdrressCode(addressCode);
			orders.setGoodsCode(Integer.parseInt(goodsCode[i]));
			orders.setOrderQuantity(Integer.parseInt(cartQuantity[i]));
			orders.setOrderPrice(Integer.parseInt(orderPrice[i]));
			
			if(request.getParameter("pointAll") == null && request.getParameter("usePoint").equals("")) {
				// 포인트 미사용
				orderCode = ordersService.addOrder(orders, cartList, customerId);
			} else {
				// 포인트 사용
				orderCode = ordersService.AddOrderPoint(orders, customerId, Integer.parseInt(sharePoint), point);
			}
		}
		
		for(int i =0; i< orderCode.size(); i++) {
			System.out.println("orderCode1 : " + orderCode.get(i).getOrderCode());
			orderOne = ordersService.selectOrdersOneList(orderCode.get(i).getOrderCode());
		}
		session.setAttribute("orderOne", orderOne);
		
		int ttlCntCart = cartService.ttlCntCart(loginCustomer.getCustomerId());
		session.setAttribute("ttlCntCart", ttlCntCart);
		
		// 결과
		if(orderOne != null) {
			// 리스트로 이동
			System.out.println("구매성공");
			int orderLength = (orderCode.size());
			System.out.println(orderLength);
			response.sendRedirect(request.getContextPath()+"/orders/ordersComplete?orderLength="+orderLength+"&addressCode="+addressCode);
			// request.getRequestDispatcher("/WEB-INF/view/orders/ordersComplete.jsp").forward(request, response);
		} else {
			// 폼이동
			System.out.println("구매실패");
			response.sendRedirect(request.getContextPath()+"/orders/ordersAdd");
		}
	
	}
}


