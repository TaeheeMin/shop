package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.GoodsService;
import service.SiteCounterService;


@WebServlet("/Home")
public class HomeController extends HttpServlet {
	private SiteCounterService siteCounterService;
	private GoodsService goodsService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 홈 View
		this.goodsService = new GoodsService();
		
		// (누적,오늘,현재) 접속자수 불러오기
		this.siteCounterService = new SiteCounterService();
		int todayCount = siteCounterService.selectTodayCount();
		int totalCount = siteCounterService.selectTotalCount();
		request.setAttribute("todayCount", todayCount);
		request.setAttribute("totalCount", totalCount);
		
		// 최신앨범 목록 불러오기
		ArrayList<HashMap<String, Object>> rlist = goodsService.selectRecentlySongList();
		// System.out.println("최신곡 list : "+rlist);
		request.setAttribute("rlist", rlist);
				
		// 히트앨범 목록 불러오기
		ArrayList<HashMap<String, Object>> hlist = goodsService.selectHitSongList();
		request.setAttribute("hlist", hlist);
		
		request.getRequestDispatcher("/WEB-INF/view/home.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
}
 
