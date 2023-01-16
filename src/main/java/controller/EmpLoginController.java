package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CustomerService;
import service.EmpService;
import vo.Customer;
import vo.Emp;

@WebServlet("/LoginEmp")
public class EmpLoginController extends HttpServlet {
	private EmpService empService;
	
	// 사원 로그인 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 세션 불러오기
		HttpSession session = request.getSession();
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		
		// 사원로그인 되어있을 시, 로그인 폼 진입 불가 -> 홈화면 이동
		if(loginEmp != null || loginCustomer != null) {
			response.sendRedirect(request.getContextPath()+"/Home2");
			return;
		}
		
		// 아이디나 비밀번호 잘못된경우, msg 출력하게끔하기
		if(request.getParameter("empMsg") != null) {
			request.setAttribute("wrongEmp", request.getParameter("empMsg"));
		}
				
		// 비로그인일경우, 사원로그인폼 view로 이동
		request.getRequestDispatcher("/WEB-INF/view/emp/empLogin.jsp").forward(request, response);
	}

	// 사원 로그인 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); // 한글 인코딩
		
		// 파라미터값 받아오기
		String empId = request.getParameter("empId");
		String empPw = request.getParameter("empPw");
		System.out.println("[loginEmp컨트롤러 진입]");
		System.out.println("입력받은 ID : "+empId);
		System.out.println("입력받은 Pw : "+empPw);
		
		// emp에 set
		Emp emp = new Emp();
		emp.setEmpId(empId);
		emp.setEmpPw(empPw);
		System.out.println("emp Id : "+emp.getEmpId());
		System.out.println("emp Pw : "+emp.getEmpPw());
		
		// 로그인 service불러오기
		this.empService = new EmpService();
		Emp loginEmp = empService.loginEmployee(emp);
		
		// 로그인 실패 시, -> 로그인 get으로 돌아가기
		if(loginEmp == null) {
			System.out.println("로그인 실패 -> 사원로그인컨트롤러 get으로 이동");
			String empMsg = "login fail"; // 아이디 혹은 비밀번호가 맞지 않습니다
			response.sendRedirect(request.getContextPath()+"/LoginEmp?empMsg="+empMsg);
			return;
		}
		
		// 로그인 성공 시, 세션에 사원로그인정보 저장 후 홈으로
		System.out.println("로그인 성공 -> 홈컨트롤러로 이동");
		HttpSession session = request.getSession();
		session.setAttribute("loginEmp",loginEmp);
		response.sendRedirect(request.getContextPath()+"/Home2");
	}

}
