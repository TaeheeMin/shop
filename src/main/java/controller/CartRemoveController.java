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

@WebServlet("/CartRemove")
public class CartRemoveController extends HttpServlet {
	private CartService cartService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		// System.out.println(request.getParameter("goodsCode"));
		int goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
		
		// 로그인되어있을 시
		if(loginCustomer != null) {
			Cart cart = new Cart();
			cart.setGoodsCode(goodsCode);
			cart.setCustomerId(loginCustomer.getCustomerId());
			
			this.cartService = new CartService();
			int row = cartService.removeCart(cart);
			
			// 결과
			if(row == 1) {
				System.out.println("삭제성공");	
			} else {
				System.out.println("삭제실패");
			}
			
		} else {
			// 비회원 장바구니 삭제하기
			ArrayList<HashMap<String, Object>> cart = null;
			if(session.getAttribute("cart") != null) {
				cart = (ArrayList<HashMap<String, Object>>)session.getAttribute("cart");
				for(HashMap<String,Object> c : cart) {
					int thisGoodsCode = Integer.parseInt(String.valueOf(c.get("goodsCode")));
					System.out.println("확인할 상품코드 : "+thisGoodsCode+" / 삭제할 상품코드 : "+goodsCode);
					if(thisGoodsCode==goodsCode) {
						cart.remove(c);
						System.out.println("==> 코드 일치, 품목 삭제 성공");
						break;
					} else {
						System.out.println("==> 코드 불일치, 변경값없음");
					}
				}
				session.setAttribute("cart", cart);
			}
		}
		response.sendRedirect(request.getContextPath()+"/CartList");
	}
}
