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


@WebServlet("/CustomerRemove")
public class CustomerRemoveController extends HttpServlet {
	private CustomerService customerService;
	
	// 회원탈퇴 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인세션 불러오기
		HttpSession session = request.getSession();
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		
		// 로그인되지 않은경우, 회원탈퇴 폼 진입 불가 -> 홈화면으로 이동
		if(loginCustomer == null) {
			response.sendRedirect(request.getContextPath()+"/Home");
			return;
		}
		
		request.getRequestDispatcher("/WEB-INF/view/customer/customerRemove.jsp").forward(request, response);	
	}

	// 회원탈퇴 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); // 인코딩
		
		// 파라미터값 받기
		String customerId = request.getParameter("customerId");
		String customerPw = request.getParameter("customerPw");
		
		Customer customer = new Customer();
		customer.setCustomerId(customerId);
		customer.setCustomerPw(customerPw);
		
		System.out.println("[customerRemove컨트롤러 진입]");
		this.customerService = new CustomerService();
		
		// 회원탈퇴 및 outid테이블에 탈퇴id 저장을 동시 진행
		int removeCustomerOutid = customerService.removeCustomerOutid(customer);
		if(removeCustomerOutid == 1) {
			System.out.println("회원탈퇴 성공");
			response.sendRedirect(request.getContextPath()+"/Logout");
			return;
		} else {
			System.out.println("회원탈퇴 실패 - 비밀번호 불일치");
			response.sendRedirect(request.getContextPath()+"/CustomerRemove");
		}
		
	}

}
