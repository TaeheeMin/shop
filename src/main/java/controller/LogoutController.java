package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Logout")
public class LogoutController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션 종료 (invalidate)
		request.getSession().invalidate();
		
		// 사직처리된 직원아이디로 로그인할시 자동로그아웃 + alert출력
		if(request.getParameter("outEmp") != null) {
			System.out.println("사직처리된 직원 로그아웃");
			String outEmp =request.getParameter("outEmp");
			response.sendRedirect(request.getContextPath()+"/LoginEmp?outEmp="+outEmp);
			return;
		}
		
		// 홈으로 다시 이동
		response.sendRedirect(request.getContextPath()+"/Home");
	}
}
