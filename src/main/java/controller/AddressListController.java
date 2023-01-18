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
import vo.Customer;
import vo.CustomerAddress;

@WebServlet("/AddressList")
public class AddressListController extends HttpServlet {

	private CustomerAddressService customerAddressService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 주소목록 불러오기
		HttpSession session = request.getSession();
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		
		CustomerAddress cusAddress = new CustomerAddress();
		cusAddress.setCustomerId(loginCustomer.getCustomerId());
		this.customerAddressService = new CustomerAddressService();
		
		ArrayList<CustomerAddress> list = customerAddressService.myAddressList(cusAddress);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/view/customer/customerAddressList.jsp").forward(request, response);
	}
}
