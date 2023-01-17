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
import vo.Notice;

@WebServlet("/NoticeAdd")
public class NoticeAddController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		
		// 재직중인 사원이상의 직원만 공지등록 가능
		if(loginEmp == null || loginEmp.getAuthCode() == 0 || !(loginEmp.getActive()).equals("재직")) {
			System.out.println("[noticeAdd컨트롤러] 재직중인 사원이상의 직원만 접근 가능");
			String limitNotice = "accesslimit";
			response.sendRedirect(request.getContextPath()+"/NoticeList?limitNotice="+limitNotice);
			return;
		}
		
		request.getRequestDispatcher("/WEB-INF/view/notice/noticeForm.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		// 값 받아오기
		Notice notice = new Notice();
		notice.setNoticeTitle(request.getParameter("noticeTitle"));
		notice.setNoticeContent(request.getParameter("noticeContent"));
		notice.setEmpId(loginEmp.getEmpId());
		
		int row = 0;
		NoticeService noticeService = new NoticeService();
		row = noticeService.addNotice(notice);
		// 결과
		if(row == 1) {
			// 리스트로 이동
			System.out.println("입력성공");
			response.sendRedirect(request.getContextPath()+"/NoticeList"); 
		} else {
			// 폼이동
			System.out.println("입력실패");
			response.sendRedirect(request.getContextPath()+"/NoticeAdd");
		}
	}

}
