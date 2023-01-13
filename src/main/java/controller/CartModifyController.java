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
		HttpSession session = request.getSession();
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		int row = 0;
		System.out.println("[CartModify컨트롤러 post 진입]");
		int cartQuantity =Integer.parseInt(request.getParameter("cartQuantity"));
		int goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
		
		// 1) 로그인 -> cart 수정
		if(loginCustomer != null) {
			// 파라미터값 받아오기
			System.out.println("cartQtt : "+request.getParameter("cartQuantity"));
			System.out.println("goodsCode : "+request.getParameter("goodsCode"));

			// 수량수정 service
			String customerId = loginCustomer.getCustomerId();
			this.cartService = new CartService();
			row = cartService.modifyCart(cartQuantity, goodsCode, customerId);
			
			// 결과
			if(row != 1) {
				System.out.println("수정실패");
			}
		} else {
			// 2) 비로그인 -> session cart 수정
			System.out.println("---비회원 장바구니상품수량변경---");
			ArrayList<HashMap<String, Object>> cart = (ArrayList<HashMap<String, Object>>)session.getAttribute("list");
			for(HashMap<String,Object> c : cart) {
				int thisGoodsCode = Integer.parseInt(String.valueOf(c.get("goodsCode")));
				System.out.println("확인할 상품코드 : "+thisGoodsCode+" / 변경할 상품코드 : "+goodsCode);
				if(thisGoodsCode==goodsCode) {
					c.put("cartQuantity", cartQuantity);
					System.out.println("코드 일치, 수량변경완료 --> "+c.get("cartQuantity"));
				} else {
					System.out.println("코드 불일치, 변경값없음");
				}
			}
			System.out.println("----------------------");
			session.setAttribute("cart", cart); // 로그인시, 불러오기 위한 cart
			session.setAttribute("list", cart); // 장바구니 view에서 불러오기 위한 cart
		}
		response.sendRedirect(request.getContextPath()+"/CartList");
		return;
	}
}
