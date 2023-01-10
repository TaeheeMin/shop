package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ReviewService;
import vo.Review;


@WebServlet("/review/reviewAdd")
public class ReviewAddController extends HttpServlet {	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//리뷰 작성 폼 연결 
		int orderCode = Integer.parseInt(request.getParameter("orderCode"));
		request.setAttribute("orderCode", orderCode);
		request.getRequestDispatcher("/WEB-INF/view/review/reviewForm.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		// 값 받아오기
		Review review = new Review();
		int orderCode = Integer.parseInt(request.getParameter("orderCode"));
		String reviewMemo = request.getParameter("reviewMemo");
		
		System.out.println(orderCode);
		//값 저장 
		review.setReviewMemo(reviewMemo);
		review.setOrderCode(orderCode);
		int row=0;
		int pointRow = 0;
		ReviewService reviewService = new ReviewService();
		row = reviewService.addReivew(review);
		// 결과
		if(row == 1) {
			// 리스트로 이동
			System.out.println("입력성공");
			response.sendRedirect(request.getContextPath()+"/Home");
			
			
		} else {
			// 폼이동
			System.out.println("입력실패");
			response.sendRedirect(request.getContextPath()+"/review/reviewAdd");
		}
	}

}

