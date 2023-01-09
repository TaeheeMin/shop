package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CustomerService;
import service.PwHistoryService;
import vo.Customer;
import vo.PwHistory;

@WebServlet("/AddCustomer")
public class CustomerAddController extends HttpServlet {
	private CustomerService customerService;
	private PwHistoryService pwHistoryService;
	
	// 회원가입 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인세션 불러오기
		HttpSession session = request.getSession();
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		
		// 로그인되어있을 시, 회원가입폼 진입 불가 -> 홈화면으로 이동
		if(loginCustomer != null) {
			response.sendRedirect(request.getContextPath()+"/Home");
			return;
		}
		
		// 비로그인일 경우, 회원가입폼 view로 이동
		request.getRequestDispatcher("/WEB-INF/view/customer/customerSignin.jsp").forward(request, response);
	}

	// 회원가입 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
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
		
		// pwHistory에 set
		PwHistory pwHistory = new PwHistory();
		pwHistory.setCustomerId(customerId);
		pwHistory.setPw(customerPw);
		
		// 회원가입 service 불러오기
		this.customerService = new CustomerService();
		this.pwHistoryService = new PwHistoryService();
		
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
				// 회원가입한 비밀번호는 pwHistory(비밀번호내역)에 추가하기 
				int operatePwHistory = pwHistoryService.operatePwHistory(pwHistory);
				if(operatePwHistory == 1) {
					System.out.println("비밀번호 pwHistory에 추가완료");
				}
				response.sendRedirect(request.getContextPath()+"/Home");
				return;
			} else {
				System.out.println("회원가입 실패");
				response.sendRedirect(request.getContextPath()+"/AddCustomer");
			}
		}
	}
}
