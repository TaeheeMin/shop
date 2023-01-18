package controller;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CartService;
import service.GoodsService;
import service.SiteCounterService;
import vo.Customer;
import vo.Emp;
import vo.Goods;


@WebServlet("/Home")
public class HomeController extends HttpServlet {
	private SiteCounterService siteCounterService;
	private GoodsService goodsService;
	private CartService cartService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 홈 View
		this.goodsService = new GoodsService();
		System.out.println("[Home컨트롤러]");
		
		// (누적,오늘,현재) 접속자수 불러오기
		this.siteCounterService = new SiteCounterService();
		int todayCount = siteCounterService.selectTodayCount();
		int totalCount = siteCounterService.selectTotalCount();
		request.setAttribute("todayCount", todayCount);
		request.setAttribute("totalCount", totalCount);
		
		// 직원로그인세션 불러오기 - 직원이상만 가능
		HttpSession session = request.getSession();
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		
		// 직원등록(인턴이나 휴직 및 사직 직원이 접근시, 접근불가 alert출력)
		if(request.getParameter("empAdd") != null) {
			request.setAttribute("empAdd", request.getParameter("empAdd"));
		}
		
		// 리뷰관리(재직자 외 접근불가 alert출력)
		if(request.getParameter("reviewLimit") != null) {
			request.setAttribute("reviewLimit", request.getParameter("reviewLimit"));
		}
		
		// 고객 로그인세션 불러오기
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		if(loginCustomer != null) {
			this.cartService = new CartService();
			int ttlCntCart = cartService.ttlCntCart(loginCustomer.getCustomerId());
			session.setAttribute("ttlCntCart", ttlCntCart);
		}
		
		System.out.println("==갯수== : "+session.getAttribute("ttlCntCart"));

		// 최신앨범 목록 불러오기
		ArrayList<HashMap<String, Object>> rlist = goodsService.selectRecentlySongList();
		// System.out.println("최신곡 list : "+rlist);
		request.setAttribute("rlist", rlist);
				
		// 히트앨범 목록 불러오기
		ArrayList<HashMap<String, Object>> hlist = goodsService.selectHitSongList();
		request.setAttribute("hlist", hlist);
		
		// 추천 artist
		ArrayList<Goods> alist = goodsService.getGoodsArtist();
		request.setAttribute("alist", alist);
		
		request.getRequestDispatcher("/WEB-INF/view/home.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
}
 
