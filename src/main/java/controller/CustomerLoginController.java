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
import service.CustomerService;
import vo.Cart;
import vo.Customer;
import vo.Emp;

@WebServlet("/LoginCustomer")
public class CustomerLoginController extends HttpServlet {
	private CustomerService customerService;
	private CartService cartService;
	
	// 로그인 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 세션 불러오기
		HttpSession session = request.getSession();
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		Emp loginEmp = (Emp)session.getAttribute("loginEmp"); 
		
		// 로그인되어있을 시, 로그인폼 진입 불가 -> 홈화면으로 이동
		if(loginCustomer != null || loginEmp != null) {
			response.sendRedirect(request.getContextPath()+"/Home");
			return;
		}
		
		// 비로그인일 경우, 로그인폼 view로 이동
		request.getRequestDispatcher("/WEB-INF/view/customer/customerLogin.jsp").forward(request, response);
	}

	// 로그인 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); // 한글 인코딩
		
		// 파라미터값 받아오기
		String customerId = request.getParameter("customerId");
		String customerPw = request.getParameter("customerPw");
		System.out.println("[loginCustomer컨트롤러 진입]");
		System.out.println("입력받은 ID : "+customerId);
		
		// customer에 set
		Customer customer = new Customer();
		customer.setCustomerId(customerId);
		customer.setCustomerPw(customerPw);
		
		// 로그인 service불러오기
		this.customerService = new CustomerService();
		Customer loginCustomer = customerService.customerLogin(customer);
		
		// 로그인 실패 시, -> 로그인 get으로 돌아가기
		if(loginCustomer == null) {
			System.out.println("로그인 실패 -> 로그인컨트롤러 get으로 이동");
			String msg = "login fail"; // 아이디 혹은 비밀번호가 맞지 않습니다
			response.sendRedirect(request.getContextPath()+"/LoginCustomer?msg="+msg);
			return;
		}
		
		// 로그인 성공 시, 세션에 로그인정보 저장 후 홈으로
		System.out.println("로그인 성공 -> 홈컨트롤러로 이동");
		HttpSession session = request.getSession();
		session.setAttribute("loginCustomer",loginCustomer);
		
		this.cartService = new CartService();
		Cart cart = new Cart();
		cart.setCustomerId(customerId);
		
		if(session.getAttribute("cart") != null) {
			// 비로그인상태에서 장바구니에 담은 물건이 있을 경우, 회원 장바구니로 이동해주기

			// 비로그인에서 담은 cartlist 불러오기
			ArrayList<HashMap<String, Object>> cartlist = (ArrayList<HashMap<String,Object>>)session.getAttribute("cart");
			int addCart = 0;
			
			System.out.println("---- 비로그인에서 담은 장바구니를 회원장바구니로 이동합니다 ----");
			// foreach 문으로 각각의 goodsCode 및 cartQuantity를 불러와 cartDB에 추가해주기
			for(HashMap<String,Object> c : cartlist) { 

				// 기존 회원장바구니에 중복된 품목인지 확인 -> 중복일경우 수량만 추가
				int thisGoodsCode = Integer.parseInt(String.valueOf(c.get("goodsCode")));
				System.out.println("(1) "+thisGoodsCode+"번 품목 중복검사 시행중");
				cart.setGoodsCode(thisGoodsCode);
				boolean cartListCk = cartService.cartListCk(cart);
				
				if(cartListCk) {
					System.out.println("(2)중복된 품목 -> 수량만 조정");
					int addQty = Integer.parseInt(String.valueOf(c.get("cartQuantity"))); // 비회원 cart에 저장된 해당 품목의 수량
					int oldQty = cartService.cartOneQty(addCart, cart); // 기존 db에 저장된 해당 품목의 수량
					int newQty = addQty + oldQty;
					System.out.println("(3)old/add/new -> "+oldQty+"/"+addQty+"/"+newQty);
					int modifyQty = cartService.modifyCart(newQty, thisGoodsCode, customerId);
					if(modifyQty == 1) {
						System.out.println("(4)중복품목 수량 추가 성공");
					}
				} else {
					System.out.println("(2)중복X -> 기존 장바구니에 새롭게 추가");
					cart.setCartQuantity(Integer.parseInt(String.valueOf(c.get("cartQuantity"))));
					System.out.println("(3)대입하려는 goodsCode : "+cart.getGoodsCode());
					System.out.println("(4)대입하려는 cartQty : "+cart.getCartQuantity());
					addCart = cartService.addCart(cart);
					if(addCart == 1) {
						System.out.println("(5)"+Integer.parseInt(String.valueOf(c.get("goodsCode")))+"번 상품 장바구니로 이동 성공");
					}
				}
			}
			System.out.println("------------ 이동완료 ------------");
		}
		response.sendRedirect(request.getContextPath()+"/Home");
	}

}
