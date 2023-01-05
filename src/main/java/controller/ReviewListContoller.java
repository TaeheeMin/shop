package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.ReviewService;
import vo.Review;




@WebServlet("/review/reviewList")
public class ReviewListContoller extends HttpServlet {	
	private ReviewService reviewService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 관리자 로그인 후에만 진입가능 		
		
		// 페이징 작업 
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		int rowPerPage = 10;
		if(request.getParameter("rowPerPage") != null) {
			rowPerPage = Integer.parseInt(request.getParameter("rowPerPage"));
		}		
	
		this.reviewService = new ReviewService();
		ArrayList<Review> list = reviewService.selectReivewList();
		request.setAttribute("reviewList", list); //view에서 필요
		request.setAttribute("currentPage", currentPage); // view에서 필요
		request.setAttribute("rowPerPage", rowPerPage); // view에서 필요

		request.getRequestDispatcher("/WEB-INF/view/review/reviewOne.jsp").forward(request, response);
	}
}

