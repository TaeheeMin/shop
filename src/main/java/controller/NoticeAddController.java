package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.NoticeService;
import vo.Notice;

@WebServlet("/NoticeAdd")
public class NoticeAddController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/notice/noticeForm.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 값 받아오기
		Notice notice = new Notice();
		notice.setNoticeTitle(request.getParameter("noticeTitle"));
		notice.setNoticeContent(request.getParameter("noticeContent"));
		notice.setEmpId(request.getParameter("empId"));
		
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
