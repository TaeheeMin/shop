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


@WebServlet("/CustomerModify")
public class CustomerModifyController extends HttpServlet {
	private CustomerService customerService;
	private PwHistoryService pwHistoryService;
	
	// 회원정보수정 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인세션 불러오기
		HttpSession session = request.getSession();
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		
		// 로그인되지 않은경우, 회원정보수정 폼 진입 불가 -> 홈화면으로 이동
		if(loginCustomer == null) {
			response.sendRedirect(request.getContextPath()+"/Home");
			return;
		}
		
		request.getRequestDispatcher("/WEB-INF/view/customer/customerModify.jsp").forward(request, response);
	}
	
	// 회원정보수정 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); // 인코딩
		
		// 로그인세션 불러오기
		HttpSession session = request.getSession();
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		
		// 파라미터값 받기
		String customerPw = request.getParameter("customerPw");
		String customerNewPw = request.getParameter("customerNewPw");
		String customerName = request.getParameter("customerName");
		String customerPhone = request.getParameter("customerPhone");
		System.out.println("[회원정보수정액션컨트롤러 진입]");
		System.out.println("(1)받아온 pw값 : "+customerPw+"/newPw : "+customerNewPw);
		
		// 두개의 customer에 set
		// 기존정보
		Customer customerOne = new Customer();
		customerOne.setCustomerId(loginCustomer.getCustomerId());
		customerOne.setCustomerPw(customerPw);
		System.out.println("(2)기존 로그인아이디값 : "+customerOne.getCustomerId());
		
		// 바뀔정보
		Customer modifyCustomer = new Customer();
		modifyCustomer.setCustomerId(loginCustomer.getCustomerId());
		modifyCustomer.setCustomerPw(customerNewPw);
		modifyCustomer.setCustomerName(customerName);
		modifyCustomer.setCustomerPhone(customerPhone);
		System.out.println("(3)바뀔 이름/번호 : "+modifyCustomer.getCustomerName()+"/"+modifyCustomer.getCustomerPhone());
		
		// pwHistory에 set
		PwHistory pwHistory = new PwHistory();
		pwHistory.setCustomerId(loginCustomer.getCustomerId());
		pwHistory.setPw(customerNewPw);
		
		this.customerService = new CustomerService();
		this.pwHistoryService = new PwHistoryService();
		
		// 이전에 사용한 비밀번호들과의 중복체크 (최근 3개의 비밀번호)
		boolean pwHistoryCk = customerService.pwHistoryCk(modifyCustomer);
		System.out.println("비밀번호이력 중복체크 true값이 뜨면 중복 --> :"+pwHistoryCk);
		
		if(pwHistoryCk) {
			System.out.println("최근 변경했던 비밀번호들과 중복됩니다");
			response.sendRedirect(request.getContextPath()+"/CustomerModify");
			return;
		}
		
		int modifyCustomerOne = customerService.modifyCustomerOne(customerOne, modifyCustomer);
		if(modifyCustomerOne == 1) {
			System.out.println("(4)회원정보 수정 성공");
			// 수정된 비밀번호는 pwHistory(비밀번호내역)에 추가하기 
			int operatePwHistory = pwHistoryService.operatePwHistory(pwHistory);
			if(operatePwHistory == 1) {
				System.out.println("(5)비밀번호 pwHistory에 추가완료");
			} else {
				System.out.println("(5)비밀번호 pwHistory에 추가실패");
			}
			loginCustomer.setCustomerName(customerName);
			loginCustomer.setCustomerPhone(customerPhone);
			response.sendRedirect(request.getContextPath()+"/CustomerOne");
			return;
		} else {
			System.out.println("(4)회원정보 수정 실패");
			response.sendRedirect(request.getContextPath()+"/CustomerModify");
		}
	}
}
