package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.EmpService;
import vo.Emp;

@WebServlet("/EmpAuthCode")
public class EmpAuthCodeController extends HttpServlet {
	private EmpService empService;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파라미터 값 받기
		String empId = request.getParameter("empId");
		int authCode = Integer.parseInt(request.getParameter("authCode"));
		System.out.println("[EmpAuthCode컨트롤러 진입]");
		System.out.println("authCode : "+authCode);
		
		// Emp에 set
		Emp emp = new Emp();
		emp.setEmpId(empId);
		emp.setAuthCode(authCode);
		
		this.empService = new EmpService();
		int modifyEmpAuthCode = empService.modifyEmpAuthCode(emp);
		if(modifyEmpAuthCode == 1) {
			System.out.println("활성화 변경 성공");
			response.sendRedirect(request.getContextPath()+"/EmpList");
			return;
		} else {
			System.out.println("활성화 변경 실패");
			response.sendRedirect(request.getContextPath()+"/EmpList");
		}
	}

}
