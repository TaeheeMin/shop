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
import vo.Customer;
import vo.CustomerAddress;
import vo.Goods;
import vo.Orders;


@WebServlet("/orders/ordersAdd")
public class OrdersAddController extends HttpServlet {
	private CustomerAddressService customerAddressService;
	private GoodsService goodsService;
	
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
		
		//값받아서 넘겨주기
		String goodsTitle = request.getParameter("goodsTitle");
		int goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
		this.goodsService = new GoodsService();
		int orderQuantity = Integer.parseInt(request.getParameter("cartQuantity"));
		Goods goodsOne = goodsService.goodsOne(goodsCode);
		request.setAttribute("goodsOne", goodsOne);
		request.setAttribute("orderQuantity", orderQuantity);
		request.setAttribute("goodsTitle", goodsTitle);
		int orderPrice = Integer.parseInt(request.getParameter("cartPrice"));
		
		this.customerAddressService = new CustomerAddressService();
		
		// 주소 추가입력 있을 시,
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
		
		// 회원주소목록 불러오기
		CustomerAddress cusAddress = new CustomerAddress();
		cusAddress.setCustomerId(loginCustomer.getCustomerId());
		ArrayList<CustomerAddress> list = customerAddressService.myAddressList(cusAddress);
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/WEB-INF/view/orders/ordersPage.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		System.out.println("[OrdersAdd컨트롤러 post]");

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
		//디버깅
		System.out.println(goodsCode); 
		System.out.println(addressCode);
		System.out.println(orderQuantity);
		System.out.println(orderPrice);
		System.out.println(customerId);
	
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


