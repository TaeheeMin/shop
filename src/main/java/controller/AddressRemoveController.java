package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CustomerService;
import vo.CustomerAddress;

@WebServlet("/AddressRemove")
public class AddressRemoveController extends HttpServlet {
	private CustomerService customerService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[주소삭제컨트롤러 진입]");
		
		// 파라미터값 받아서
		String customerId = request.getParameter("customerId");
		String address = request.getParameter("address");
		System.out.println("customerId : "+customerId);
		
		// cusAddress에 set
		CustomerAddress cusAddress = new CustomerAddress();
		cusAddress.setCustomerId(customerId);
		cusAddress.setAddress(address);
		System.out.println("cusAddress.getCustomerId : "+cusAddress.getCustomerId());
		System.out.println("cusAddress.getAddress : "+cusAddress.getAddress());
		
		// 삭제 service 진행
		this.customerService = new CustomerService();
		int removeAddress = customerService.removeAddress(cusAddress);
		if(removeAddress == 1) {
			System.out.println("주소삭제 성공");
			response.sendRedirect(request.getContextPath()+"/CustomerOne");
			return;
		} else {
			System.out.println("주소삭제 실패");
			response.sendRedirect(request.getContextPath()+"/CustomerOne");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
