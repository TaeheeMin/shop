package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CustomerAddressService;
import service.CustomerService;
import vo.Customer;
import vo.CustomerAddress;


@WebServlet("/CustomerOne")
public class CustomerOneController extends HttpServlet {
	private CustomerService customerService;
	private CustomerAddressService customerAddressService;
	
	// 회원정보 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인세션 불러오기
		HttpSession session = request.getSession();
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		
		// 로그인되지 않은경우, 회원정보 폼 진입 불가 -> 홈화면으로 이동
		if(loginCustomer == null) {
			response.sendRedirect(request.getContextPath()+"/Home");
			return;
		}
		
		System.out.println("[customerOne컨트롤러 진입]");
		this.customerService = new CustomerService();
		this.customerAddressService = new CustomerAddressService();
		
		// 로그인된 경우, 회원정보 불러오기
		Customer customer = new Customer();
		customer.setCustomerId(loginCustomer.getCustomerId());
		System.out.println("customer.getCustomerId : "+customer.getCustomerId());
		Customer customerOne = customerService.customerOne(customer);
		
		// 회원주소목록 불러오기
		CustomerAddress cusAddress = new CustomerAddress();
		cusAddress.setCustomerId(loginCustomer.getCustomerId());
		System.out.println("cusAddress.getCustomerId : "+cusAddress.getCustomerId());
		ArrayList<CustomerAddress> list = customerAddressService.myAddressList(cusAddress);

		request.setAttribute("list", list);
		request.setAttribute("customerOne", customerOne);
		request.getRequestDispatcher("/WEB-INF/view/customer/customerOne.jsp").forward(request, response);
	}
}
