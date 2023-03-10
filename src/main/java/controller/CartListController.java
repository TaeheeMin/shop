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
import service.*;
import vo.*;

@WebServlet("/CartList")
public class CartListController extends HttpServlet {
	private CartService cartService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		
		// 로그인시 db cart 목록 출력
		if(loginCustomer != null) { 
			//response.sendRedirect(request.getContextPath()+"/Home");
			this.cartService = new CartService();
			ArrayList<HashMap<String, Object>> list = cartService.getCartList(loginCustomer.getCustomerId());
			System.out.println("장바구니에 담긴 상품 갯수 : "+list.size());
			request.setAttribute("list", list);
		} else {
			// 비회원일 시, 세션에 저장된 cart 목록 출력
			ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>)session.getAttribute("cart");
			request.getSession().setAttribute("list", list);
			System.out.println("비회원 list : "+list);
		}
		
		if(request.getParameter("notLogin") != null) {
			request.setAttribute("notLogin", request.getParameter("notLogin"));
		}
		
		request.getRequestDispatcher("/WEB-INF/view/cart/cartList.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
