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
		
		// 파라미터값 받아오기
		int goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
		int cartQuantity = Integer.parseInt(request.getParameter("cartQuantity"));
		// System.out.println(request.getParameter("goodsCode"));
		// System.out.println(request.getParameter("cartQuantity"));
		
		// 1) 로그인 -> cart 추가
		if(loginCustomer != null) {
			String customerId = loginCustomer.getCustomerId();
			System.out.println("[로그인상태]");
			Cart cart = new Cart();
			cart.setGoodsCode(goodsCode);
			cart.setCustomerId(customerId);
			cart.setCartQuantity(cartQuantity);
			this.cartService = new CartService();
			
			// cart내에 중복체크 (이미 장바구니 담긴 상품일시, 상품수량 추가)
			boolean cartListCk = cartService.cartListCk(cart);
			if(cartListCk) {
				System.out.println("(1)중복된 상품이 존재하여 수량만 추가합니다");
				// 수량업데이트 service진행
				int newCartQtt = cartQuantity + cart.getCartQuantity();
				System.out.println("(2)변경 할 상품코드 : "+cart.getGoodsCode());
				System.out.println("(3)변경 전 상품수량 : "+cart.getCartQuantity());
				System.out.println("(4)변경 후 상품수량 : "+newCartQtt);
				int modifyCart = cartService.modifyCart(newCartQtt, goodsCode, customerId);
				if(modifyCart == 1) {
					System.out.println("(5)수량 추가 완료!");
				}
			} else {
				System.out.println("(1)새롭게 추가된 장바구니상품입니다");
				row = cartService.addCart(cart);
				if(row != 1) {
					System.out.println("장바구니 담기 실패");
				}
			}
			
			int ttlCntCart = cartService.ttlCntCart(loginCustomer.getCustomerId());
			session.setAttribute("ttlCntCart", ttlCntCart);
			response.sendRedirect(request.getContextPath()+"/GoodsList");
			return;
		}
		
		// 2) 비로그인시 => 세션에 저장
		if(loginCustomer == null) {
			System.out.println("[CartAddController - 비로그인상태]");
			ArrayList<HashMap<String, Object>> list = null;
			ArrayList<HashMap<String, Object>> cart = (ArrayList<HashMap<String,Object>>)session.getAttribute("cart");
			// System.out.println("cart : "+cart);
			
			if(cart == null) {
				System.out.println("장바구니 null");
				list = new ArrayList<HashMap<String, Object>>();
			} else {
				// session cart내 중복체크
				for(HashMap<String,Object> c : cart) {
					int thisGoodsCode = Integer.parseInt(String.valueOf(c.get("goodsCode")));	
					if(thisGoodsCode == goodsCode) {
						// session 장바구니에 이미 있는 품목일 경우 수량만 변경
						System.out.println("(1)중복상품이라 수량만 추가합니다");
						int newCartQtt = cartQuantity + Integer.parseInt(String.valueOf(c.get("cartQuantity")));
						System.out.println("(2)중복상품이름 : "+c.get("goodsTitle"));
						System.out.println("(3)상품수량변경 (이전 : "+c.get("cartQuantity")+" / 이후 : "+newCartQtt+")");
						c.put("cartQuantity", newCartQtt);
						session.setAttribute("cart", cart);
						response.sendRedirect(request.getContextPath()+"/GoodsList");
						return;
					} else {
						System.out.println("(1)새로운상품 입니다");
					}
				}
				list = cart; // session cart를 list에 저장
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
