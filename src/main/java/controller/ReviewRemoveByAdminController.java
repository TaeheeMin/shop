package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.NoticeService;
import service.ReviewService;

/**
 * Servlet implementation class ReviewRemoveController
 */
@WebServlet("/review/reviewRemoveByAdmin")
public class ReviewRemoveByAdminController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 값 받아오기
		int orderCode = Integer.parseInt(request.getParameter("orderCode"));
		
		ReviewService reviewService = new ReviewService();
		int row = reviewService.removeByAdminReview(orderCode);
		
		// 결과
		if(row == 1) {
			// 리스트 이동
			System.out.println("삭제성공");
			response.sendRedirect(request.getContextPath()+"/review/reviewList"); 
		} else {
			// 리스트 이동
			System.out.println("삭제실패");
			response.sendRedirect(request.getContextPath()+"/review/reviewList?orderCode="+orderCode);
		}
	}
}


