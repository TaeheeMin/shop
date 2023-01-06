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
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		int row = 0;
		
		// 로그인 -> cart 추가
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
			
		} else { // 비로그인시 => 세션에 저장
			ArrayList<HashMap<String, Object>> clist = new ArrayList<HashMap<String, Object>>();
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("goodsCode", request.getParameter("goodsCode"));
			m.put("cartQuantity", request.getParameter("cartQuantity"));
			m.put("filename", request.getParameter("filename"));
			m.put("goodsPrice", request.getParameter("goodsPrice"));
			clist.add(m);
			
			session.setAttribute("clist",clist);
		}
		response.sendRedirect(request.getContextPath()+"/GoodsList");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/goods/goodsList.jsp").forward(request, response);
	}
}
