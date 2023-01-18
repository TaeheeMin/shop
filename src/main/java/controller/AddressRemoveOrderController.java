package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CustomerAddressService;
import vo.CustomerAddress;


@WebServlet("/AddressRemoveOrder")
public class AddressRemoveOrderController extends HttpServlet {
	private CustomerAddressService customerAddressService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[주소삭제컨트롤러 진입]");
		// 파라미터값 받아서
		String customerId = request.getParameter("customerId");
		String address = request.getParameter("address");
		// int goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
		System.out.println("customerId : "+customerId);
		
		// cusAddress에 set
		CustomerAddress cusAddress = new CustomerAddress();
		cusAddress.setCustomerId(customerId);
		cusAddress.setAddress(address);
		System.out.println("cusAddress.getCustomerId : "+cusAddress.getCustomerId());
		System.out.println("cusAddress.getAddress : "+cusAddress.getAddress());
		
		// 삭제 service 진행
		this.customerAddressService = new CustomerAddressService();
		int removeAddress = customerAddressService.removeAddress(cusAddress);
		if(removeAddress == 1) {
			System.out.println("주소삭제 성공");
			response.sendRedirect(request.getContextPath()+"/orders/ordersAdd");
			return;
		} else {
			System.out.println("주소삭제 실패");
			response.sendRedirect(request.getContextPath()+"/orders/ordersAdd");
		}
	}
}
