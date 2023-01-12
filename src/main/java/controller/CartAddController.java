package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CartService;
import service.NoticeService;
import vo.Cart;
import vo.Customer;
import vo.Notice;


@WebServlet("/CartAdd")
public class CartAddController extends HttpServlet {
	private CartService cartService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		int row = 0;
		System.out.println("[CartAddController진입]");
		
		// 1) 로그인 -> cart 추가
		if(loginCustomer != null) {
			// 값 받아오기
			// System.out.println(request.getParameter("goodsCode"));
			Cart cart = new Cart();
			cart.setGoodsCode(Integer.parseInt(request.getParameter("goodsCode")));
			cart.setCustomerId(loginCustomer.getCustomerId());
			
			this.cartService = new CartService();
			row = cartService.addCart(cart);
			
			// 결과
			if(row != 1) {
				System.out.println("담기실패");
			}
			response.sendRedirect(request.getContextPath()+"/GoodsList");
			return;
		} 
		
		// 2) 비로그인시 => 세션에 저장
		if(loginCustomer == null) {
			System.out.println("[CartAddController - 비로그인상태]");
			ArrayList<HashMap<String, Object>> list = null;
			ArrayList<HashMap<String, Object>> cart = (ArrayList<HashMap<String,Object>>)session.getAttribute("cart");
			System.out.println("cart : "+cart);
			if(cart == null) {
				System.out.println("장바구니 null");
				list = new ArrayList<HashMap<String, Object>>();
			} else {
				list = cart;
			}
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("goodsCode", request.getParameter("goodsCode"));
			m.put("cartQuantity", request.getParameter("cartQuantity"));
			m.put("filename", request.getParameter("filename"));
			m.put("goodsPrice", request.getParameter("goodsPrice"));
			list.add(m);
			session.setAttribute("cart", list);
			response.sendRedirect(request.getContextPath()+"/GoodsList");
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/goods/goodsList.jsp").forward(request, response);
	}
}
