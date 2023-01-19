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

		// 1) 페이징
		// 1-1) currentPage		
		int currentPage = 1;
		//System.out.println("currentPage : " + request.getParameter("currentPage"));
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		// 1-2) rowPerPage
		int rowPerPage = 5;
		// System.out.println("rowPerPage : " + rowPerPage);
		
		// 1-3) 전체 페이지
		int count = noticeService.getNoticeListCount();
		//System.out.println("count : " + count);
		int page = 5; // 페이징 목록 개수
		int beginPage = ((currentPage - 1)/page) * page + 1; // 시작 페이지
		//System.out.println("beginPage : "+beginPage);
		int endPage = beginPage + page - 1; // 페이징 목록 끝
		//System.out.println("endPage : "+endPage);
		int lastPage = (int)Math.ceil((double)count / (double)rowPerPage); // 마지막 페이지
		//System.out.println("lastPage : " + lastPage);
		if(endPage > lastPage) {
			endPage = lastPage;
		}
		//System.out.println("endPage : " + endPage);
		
		notice = noticeService.getNoticeList(currentPage, rowPerPage);
		System.out.println("notic컨트롤러진입");
		System.out.println("limitNotice : " + request.getParameter("limitNotice"));
		
		// 재직중인 사원이상의 직원이 아닌 사람이 공지등록을 할 경우, alert출력
		if(request.getParameter("limitNotice") != null) {
			System.out.println("[noticeList컨트롤러] 재직중인 사원이상의 직원만 접근 가능");
			request.setAttribute("limitNotice", request.getParameter("limitNotice"));
			System.out.println(request.getAttribute("limitNotice"));
		}
		
		request.setAttribute("notice", notice);
		request.setAttribute("beginPage", beginPage);
		request.setAttribute("page", page);
		request.setAttribute("endPage", endPage);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("rowPerPage", rowPerPage);
		request.getRequestDispatcher("/WEB-INF/view/notice/noticeList.jsp").forward(request, response);
	}
}
