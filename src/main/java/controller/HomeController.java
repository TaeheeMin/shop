package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.SiteCounterService;


@WebServlet("/Home")
public class HomeController extends HttpServlet {
	private SiteCounterService siteCounterService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 홈 View
		
		// (누적,오늘,현재) 접속자수 불러오기
		this.siteCounterService = new SiteCounterService();
		int todayCount = siteCounterService.selectTodayCount();
		int totalCount = siteCounterService.selectTotalCount();
		request.setAttribute("todayCount", todayCount);
		request.setAttribute("totalCount", totalCount);
		
		request.getRequestDispatcher("/WEB-INF/view/home.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
}
 
