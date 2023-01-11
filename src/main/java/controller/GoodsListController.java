package controller;

import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.GoodsDao;
import service.*;
import vo.Customer;

@WebServlet("/GoodsList")
public class GoodsListController extends HttpServlet {
	private GoodsService goodsService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		
		// 1) 카테고리
		String category = request.getParameter("category"); 
		if(category == null) {
			category = "gd.goods_title";
		}
		// System.out.println("category : " + category);
		
		// 2) 검색어
		String word = request.getParameter("word");
		if(word == null) {
			word = "";
			// System.out.println("word : " + word);
		}
		
		// 3) 페이징
		// 3-1) currentPage		
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		// System.out.println("currentPage : " + currentPage);
		
		// 3-2) rowPerPage
		int rowPerPage = 10;
		if(request.getParameter("rowPerPage") != null) {
			rowPerPage = Integer.parseInt(request.getParameter("rowPerPage"));
		}
		// System.out.println("rowPerPage : " + rowPerPage);
		
		// 3-3) 전체 페이지
		this.goodsService = new GoodsService();
		int count = goodsService.getGoodsListCount();
		// System.out.println("count : " + count);
		int page = 5; // 페이징 목록 개수
		int beginPage = ((currentPage - 1)/page) * page + 1; // 시작 페이지

		System.out.println("beginPage : "+beginPage);
		// System.out.println("beginPage : " + beginPage);
		int endPage = beginPage + page - 1; // 페이징 목록 끝
		System.out.println("endPage : "+endPage);
		int lastPage = (int)Math.ceil((double)count / (double)rowPerPage); // 마지막 페이지
		// System.out.println("lastPage : " + lastPage);
		if(endPage > lastPage) {
			endPage = lastPage;
		}
		// System.out.println("endPage : " + endPage);
		
		ArrayList<HashMap<String, Object>> list = goodsService.getGoodsList(currentPage, rowPerPage, category, word);
		request.setAttribute("list", list);
		request.setAttribute("beginPage", beginPage);
		request.setAttribute("page", page);
		request.setAttribute("endPage", endPage);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("rowPerPage", rowPerPage);
		request.setAttribute("category", category);
		request.setAttribute("word", word);
		request.getRequestDispatcher("/WEB-INF/view/goods/goodsList.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
