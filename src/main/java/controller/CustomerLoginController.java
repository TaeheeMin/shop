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
		if(session.getAttribute("cart") != null) {
			// 비로그인상태에서 장바구니에 담은 물건이 있을 경우, 회원 장바구니로 이동해주기
			this.cartService = new CartService();
			Cart cart = new Cart();
			cart.setCustomerId(customerId);
			// 비로그인에서 담은 cartlist 불러오기
			ArrayList<HashMap<String, Object>> cartlist = (ArrayList<HashMap<String,Object>>)session.getAttribute("cart");
			int addCart = 0;
			for(HashMap<String,Object> c : cartlist) { 
				// foreach 문으로 각각의 goodsCode를 불러와 cart에대입하여 cart에 추가해주기
				cart.setGoodsCode(Integer.parseInt(String.valueOf(c.get("goodsCode"))));
				addCart = cartService.addCart(cart);
				if(addCart == 1) {
					System.out.println(Integer.parseInt(String.valueOf(c.get("goodsCode")))+"번 상품 장바구니로 이동 성공");
				}
			}
		}
		response.sendRedirect(request.getContextPath()+"/Home");
	}

}
