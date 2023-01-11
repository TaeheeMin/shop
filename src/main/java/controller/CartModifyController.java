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

import service.CartService;
import vo.Cart;
import vo.Customer;

@WebServlet("/CartModify")
public class CartModifyController extends HttpServlet {
	private CartService cartService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		System.out.println(loginCustomer.getCustomerId());
		// 로그인시 db cart 목록 출력
		if(loginCustomer != null) { 
			//response.sendRedirect(request.getContextPath()+"/Home");
			this.cartService = new CartService();
			ArrayList<HashMap<String, Object>> list = cartService.getCartList(loginCustomer.getCustomerId());
			request.setAttribute("list", list);
		} else {
			ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>)session.getAttribute("cart");
			request.getSession().setAttribute("list", list);
		}
		request.getRequestDispatcher("/WEB-INF/view/cart/cartModify.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		int row = 0;
		
		// 1) 로그인 -> cart 추가
		if(loginCustomer != null) {
			// 값 받아오기
			int cartQuantity =Integer.parseInt(request.getParameter("cartQuantity"));
			System.out.println(cartQuantity);
			int goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
			System.out.println(goodsCode);
			String customerId = loginCustomer.getCustomerId();
			this.cartService = new CartService();
			row = cartService.modifyCart(cartQuantity, goodsCode, customerId);
			
			// 결과
			if(row != 1) {
				System.out.println("수정실패");
			}
			response.sendRedirect(request.getContextPath()+"/CartList");
			return;
		} 
	}
}
