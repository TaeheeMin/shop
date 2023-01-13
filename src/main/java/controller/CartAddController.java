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
			// 파라미터값 받아오기
			int goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
			int cartQuantity = Integer.parseInt(request.getParameter("cartQuantity"));
			String customerId = loginCustomer.getCustomerId();
			// System.out.println(request.getParameter("goodsCode"));
			// System.out.println(request.getParameter("cartQuantity"));

			Cart cart = new Cart();
			cart.setGoodsCode(goodsCode);
			cart.setCustomerId(customerId);
			cart.setCartQuantity(cartQuantity);
			this.cartService = new CartService();
			
			// cart내에 중복체크 (이미 장바구니 담긴 상품일시, 상품수량 추가)
			boolean cartListCk = cartService.cartListCk(cart);
			if(cartListCk) {
				System.out.println("중복된 상품이 존재하여 수량만 추가합니다");
				// 수량업데이트 service진행
				int newCartQtt = cartQuantity + cart.getCartQuantity();
				int modifyCart = cartService.modifyCart(newCartQtt, goodsCode, customerId);
				if(modifyCart == 1) {
					System.out.println("수량 추가 완료!");
				}
			} else {
				System.out.println("새롭게 추가된 장바구니상품입니다");
				row = cartService.addCart(cart);
				if(row != 1) {
					System.out.println("장바구니 담기 실패");
				}
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
			m.put("goodsTitle", request.getParameter("goodsTitle"));
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
