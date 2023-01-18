package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CustomerAddressService;
import vo.CustomerAddress;

@WebServlet("/AddressAddByOrder")
public class AddressAddByOrderController extends HttpServlet {

	private CustomerAddressService customerAddressService;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[배송지추가orderPage]");
		
		// 파라미타값 받아오기
		String customerId = request.getParameter("customerId");
		String address = request.getParameter("addressAdd");
		System.out.println("입력한 주소값 : "+address);
		
		// cusAddress에 set
		CustomerAddress cusAddress = new CustomerAddress();
		cusAddress.setCustomerId(customerId);
		cusAddress.setAddress(address);
		
		// service
		this.customerAddressService = new CustomerAddressService();
		int addMyAddress = customerAddressService.addMyAddress(cusAddress);
		if(addMyAddress == 1) {
			System.out.println("주소추가 완료");
			response.sendRedirect(request.getContextPath()+"/orders/ordersAdd");
			return;
		} else {
			System.out.println("주소추가 실패");
			String maxAddress = "over";
			response.sendRedirect(request.getContextPath()+"/orders/ordersAdd?maxAddress="+maxAddress);
		}
	}

}
