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

@WebServlet("/AddCustomer")
public class CustomerAddController extends HttpServlet {
	private CustomerService customerService;
	
	// 회원가입 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인세션 불러오기
		HttpSession session = request.getSession();
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		
		// 로그인되어있을 시, 회원가입폼 진입 불가 -> 홈화면으로 이동
		if(loginCustomer != null) {
			response.sendRedirect(request.getContextPath()+"/Home");
		}
		
		// 비로그인일 경우, 회원가입폼 view로 이동
		request.getRequestDispatcher("/WEB-INF/view/customer/customerSignin.jsp").forward(request, response);
	}

	// 회원가입 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); // 한글 인코딩
		
		// 파라미터값 받아오기
		String customerId = request.getParameter("customerId");
		String customerPw = request.getParameter("customerPw");
		String customerName = request.getParameter("customerName");
		String customerPhone = request.getParameter("customerPhone");
		System.out.println("[addCustomer컨트롤러 진입]");
		System.out.println("입력받은 ID : "+customerId);
		
		// customer에 set
		Customer customer = new Customer();
		customer.setCustomerId(customerId);
		customer.setCustomerPw(customerPw);
		customer.setCustomerName(customerName);
		customer.setCustomerPhone(customerPhone);
		
		// 회원가입 service 불러오기
		this.customerService = new CustomerService();
		
		// 아이디 중복체크
		boolean checkId = customerService.customerSigninCkId(customer);
		System.out.println("아이디 중복체크 true값이 뜨면 중복 --> :"+checkId);
		
		if(checkId) {
			// 중복일 경우 회원가입폼으로 이동
			System.out.println("중복된 아이디가 존재합니다");
			response.sendRedirect(request.getContextPath()+"/AddCustomer");
			return;
		} else {
			// 중복 아닐경우 회원가입 진행
			int addCustomer = customerService.customerSignin(customer);
			if(addCustomer == 1) {
				System.out.println("회원가입 성공");
				response.sendRedirect(request.getContextPath()+"/Home");
				return;
			} else {
				System.out.println("회원가입 실패");
				response.sendRedirect(request.getContextPath()+"/AddCustomer");
			}
		}
	}

}
