package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.EmpService;
import vo.Emp;


@WebServlet("/EmpList")
public class EmpListController extends HttpServlet {
	private EmpService empService;
	
	// 직원목록 view
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 직원로그인세션 불러오기 - 사원이상만 가능
		HttpSession session = request.getSession();
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		
		// 직원로그인이 안되어있거나 인턴일 경우, 직원목록 페이지 조회불가 -> 홈으로 인동
		if(loginEmp == null || loginEmp.getAuthCode() == 0) {
			System.out.println("사원 이상의 직급만 접근 가능합니다");
			response.sendRedirect(request.getContextPath()+"/Home");
			return;
		}
		
		// 페이징 작업
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		System.out.println("currentPage: "+currentPage);
		int rowPerPage = 10; // 페이지 당 출력할 사원 수
		if(request.getParameter("rowPerPage") != null) {
			rowPerPage = Integer.parseInt(request.getParameter("rowPerPage"));
		}
		int beginRow = (currentPage-1) * rowPerPage;
		
		
		// 현
		this.empService = new EmpService();
		ArrayList<HashMap<String,Object>> list = empService.allEmpList(beginRow, rowPerPage);
				
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/view/emp/empList.jsp").forward(request, response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
