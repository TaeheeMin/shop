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

@WebServlet("/NoticeModify")
public class NoticeModifyController extends HttpServlet {
	
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
		// System.out.println(request.getParameter("noticeCode"));
		int noticeCode = Integer.parseInt(request.getParameter("noticeCode"));
		NoticeService noticeService = new NoticeService();
		Notice noticeOne = noticeService.getNoticeOne(noticeCode);
		
		// view와 공유할 모델 데이터 성정
		request.setAttribute("noticeOne", noticeOne);
		request.getRequestDispatcher("/WEB-INF/view/notice/noticeModify.jsp").forward(request, response);
				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 값 받아오기
		Notice notice = new Notice();
		notice.setNoticeCode(Integer.parseInt(request.getParameter("noticeCode")));
		notice.setNoticeTitle(request.getParameter("title"));
		notice.setNoticeContent(request.getParameter("content"));
		
		int row = 0;
		NoticeService noticeService = new NoticeService();
		row = noticeService.modifyNotice(notice);
		
		// 결과
		if(row == 1) {
			// 리스트로 이동
			System.out.println("수정성공");
			response.sendRedirect(request.getContextPath()+"/NoticeList"); 
		} else {
			// 폼이동
			System.out.println("수정실패");
			response.sendRedirect(request.getContextPath()+"/NoticeModify?noticeCode="+notice.getNoticeCode());
		}
	
	}

}
