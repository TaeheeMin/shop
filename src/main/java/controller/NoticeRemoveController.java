package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.NoticeService;
import vo.Emp;


@WebServlet("/NoticeRemove")
public class NoticeRemoveController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		
		// 직원로그인이 안되어있을경우, 직원등록폼 진입 불가 -> 홈으로 인동
		if(loginEmp == null) {
			System.out.println("직원만 접근 가능");
			response.sendRedirect(request.getContextPath()+"/Home");
			return;
		}
		// 값 받아오기
		int noticeCode = Integer.parseInt(request.getParameter("noticeCode"));
		
		NoticeService noticeService = new NoticeService();
		int row = noticeService.removeNotice(noticeCode);
		
		// 결과
		if(row == 1) {
			// one으로 이동
			System.out.println("삭제성공");
			response.sendRedirect(request.getContextPath()+"/NoticeList"); 
		} else {
			// 폼이동
			System.out.println("삭제실패");
			response.sendRedirect(request.getContextPath()+"/NoticeOne?noticeCode="+noticeCode);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
