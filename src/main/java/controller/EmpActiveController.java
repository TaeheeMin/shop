package controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.EmpService;
import vo.Emp;


@WebServlet("/EmpActive")
public class EmpActiveController extends HttpServlet {
	private EmpService empService;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파라미터 값 받기
		String empId = request.getParameter("empId");
		String active = request.getParameter("active");
		String word =request.getParameter("word");
		System.out.println("word : "+word);
		String rowPerPage = request.getParameter("rowPerPage");
		
		// Emp에 set
		Emp emp = new Emp();
		emp.setEmpId(empId);
		emp.setActive(active);
		
		this.empService = new EmpService();
		int modifyEmpActive = empService.modifyEmpActive(emp);
		if(modifyEmpActive == 1) {
			System.out.println("활성화 변경 성공");
			response.sendRedirect(request.getContextPath()+"/EmpList?word="+URLEncoder.encode(word,"utf-8")+"&rowPerPage="+rowPerPage);
			return;
		} else {
			System.out.println("활성화 변경 실패");
			response.sendRedirect(request.getContextPath()+"/EmpList?word="+URLEncoder.encode(word,"utf-8")+"&rowPerPage="+rowPerPage);
		}	
	}

}
