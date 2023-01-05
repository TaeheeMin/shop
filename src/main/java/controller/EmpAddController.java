package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.EmpService;
import vo.Emp;


@WebServlet("/AddEmp")
public class EmpAddController extends HttpServlet {
	private EmpService empService;
	
	// 직원등록 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 직원로그인세션 불러오기 - 직원이상만 가능
		HttpSession session = request.getSession();
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		
		// 직원로그인이 안되어있을경우, 직원등록폼 진입 불가 -> 홈으로 인동
		if(loginEmp == null) {
			System.out.println("직원만 접근 가능합니다");
			response.sendRedirect(request.getContextPath()+"/Home");
			return;
		}
		
		System.out.println("로그인된 직원 : "+loginEmp.getEmpId());
		// 인턴이상의 직원아이디가 로그인되어있을 경우, 직원등록 가능 -> 직원등록폼 view로 이동
		request.getRequestDispatcher("/WEB-INF/view/emp/empAdd.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); // 한글 인코딩
		
		// 파라미터값 받아오기
		String empId = request.getParameter("empId");
		String empPw = request.getParameter("empPw");
		String empName = request.getParameter("empName");
		System.out.println("[addEmp컨트롤러 진입]");
		System.out.println("입력받은 ID : "+empId);
		
		// emp에 set
		Emp emp = new Emp();
		emp.setEmpId(empId);
		emp.setEmpPw(empPw);
		emp.setEmpName(empName);
		System.out.println("입력받은 emp Id : "+emp.getEmpId());
		System.out.println("입력받은 emp Pw : "+emp.getEmpPw());
		System.out.println("입력받은 emp Name : "+emp.getEmpName());
		
		// 직원등록 service 불러오기
		this.empService = new EmpService();
		
		// 중복체크 --> 추후업데이트예정
		// 직원등록폼
		int addEmp = empService.addEmployee(emp);
		if(addEmp == 1) {
			System.out.println("직원등록 성공");
			response.sendRedirect(request.getContextPath()+"/Home");
			return;
		} else {
			System.out.println("직원등록 실패");
			response.sendRedirect(request.getContextPath()+"/AddEmp");
		}
	}

}
