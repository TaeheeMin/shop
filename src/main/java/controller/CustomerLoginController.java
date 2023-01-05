package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CustomerService;
import vo.Customer;
import vo.Emp;

@WebServlet("/LoginCustomer")
public class CustomerLoginController extends HttpServlet {
	private CustomerService customerService;
	
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
		response.sendRedirect(request.getContextPath()+"/Home");
	}

}
