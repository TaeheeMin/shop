package controller;

import java.io.IOException;
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
		
		// 로그인되어있을 시, 로그인폼 진입 불가 -> 홈화면으로 이동
		if(loginCustomer == null) {
			response.sendRedirect(request.getContextPath()+"/Home");
		}
		
		// 값 받아오기
		request.setCharacterEncoding("utf-8");
		System.out.println(request.getParameter("goodsCode"));
		Cart cart = new Cart();
		cart.setGoodsCode(Integer.parseInt(request.getParameter("goodsCode")));
		cart.setCustomerId(loginCustomer.getCustomerId());
		
		this.cartService = new CartService();
		int row = cartService.addCart(cart);
		
		// 결과
		if(row == 1) {
			System.out.println("담기성공");
			response.sendRedirect(request.getContextPath()+"/GoodList");
		} else {
			System.out.println("담기실패");
			response.sendRedirect(request.getContextPath()+"/GoodList");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/goods/goodsList.jsp").forward(request, response);
	}
}
