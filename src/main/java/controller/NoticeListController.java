 package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.NoticeService;
import vo.Notice;

@WebServlet("/NoticeList")
public class NoticeListController extends HttpServlet {
	private NoticeService noticeService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.noticeService = new NoticeService();
		ArrayList<Notice> notice = new ArrayList<Notice>();
		notice = noticeService.getNoticeList();
		System.out.println("notic컨트롤러진입");
		System.out.println(request.getParameter("limitNotice"));
		
		// 재직중인 사원이상의 직원이 아닌 사람이 공지등록을 할 경우, alert출력
		if(request.getParameter("limitNotice") != null) {
			System.out.println("[noticeList컨트롤러] 재직중인 사원이상의 직원만 접근 가능");
			request.setAttribute("limitNotice", request.getParameter("limitNotice"));
			System.out.println(request.getAttribute("limitNotice"));
		}
		
		request.setAttribute("notice", notice);
		request.getRequestDispatcher("/WEB-INF/view/notice/noticeList.jsp").forward(request, response);
	}
}
