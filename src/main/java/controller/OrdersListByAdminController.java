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
		if(loginEmp == null || !(loginEmp.getActive()).equals("재직")) {
			System.out.println("재직중인 직원만 접근 가능합니다");
			String orderEmp = "accesslimit";
			response.sendRedirect(request.getContextPath()+"/LoginEmp?orderEmp="+orderEmp);
			return;
		}	
		
		// 1) 카테고리선택
		String category = request.getParameter("category"); 
		if(category == null) {
			category = "";
		}
		//System.out.println("category : " + category);
		
		// 2) 검색부분, 검색어
		String search = request.getParameter("search");
		if(search == null) {
			search = "order_state";
		}
		//System.out.println("search : " + search);
		String word = request.getParameter("word");
		if(word == null) {
			word = "";
		}
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
		
		
		ordersService = new OrdersService();
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		list = ordersService.selectOrdersListByAdmin(currentPage, rowPerPage, search, word, category);
		request.setAttribute("list", list);
		request.setAttribute("beginPage", beginPage);
		request.setAttribute("page", page);
		request.setAttribute("endPage", endPage);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("rowPerPage", rowPerPage);
		request.getRequestDispatcher("/WEB-INF/view/orders/ordersListByAdmin.jsp").forward(request, response);
	}

}
