package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.NoticeService;


@WebServlet("/NoticeRemove")
public class NoticeRemoveController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
