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

import service.GoodsService;
import service.OrdersService;
import vo.Customer;
import vo.Orders;


@WebServlet("/orders/ordersList")
public class OrdersListController extends HttpServlet {
	private OrdersService ordersService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		
		// 3) 페이징
		// 3-1) currentPage		
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		// System.out.println("currentPage : " + currentPage);
		
		// 3-2) rowPerPage
		int rowPerPage = 10;
		if(request.getParameter("rowPerPage") != null) {
			rowPerPage = Integer.parseInt(request.getParameter("rowPerPage"));
		}
		System.out.println("rowPerPage : " + rowPerPage);
		
		// 3-3) 전체 페이지
		this.ordersService = new OrdersService();
		int count = ordersService.getOrdersListCount();
		System.out.println("count : " + count);
		int page = 5; // 페이징 목록 개수
		int beginPage = ((currentPage - 1)/page) * page + 1; // 시작 페이지

		System.out.println("beginPage : "+beginPage);
		// System.out.println("beginPage : " + beginPage);
		int endPage = beginPage + page - 1; // 페이징 목록 끝
		System.out.println("endPage : "+endPage);
		int lastPage = (int)Math.ceil((double)count / (double)rowPerPage); // 마지막 페이지
		// System.out.println("lastPage : " + lastPage);
		if(endPage > lastPage) {
			endPage = lastPage;
		}
		// System.out.println("endPage : " + endPage);
		
		
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		list = ordersService.selectOrdersList(loginCustomer, currentPage, rowPerPage);
		request.setAttribute("list", list);
		request.setAttribute("beginPage", beginPage);
		request.setAttribute("page", page);
		request.setAttribute("endPage", endPage);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("rowPerPage", rowPerPage);
		request.getRequestDispatcher("/WEB-INF/view/orders/ordersList.jsp").forward(request, response);
	
	}
}


