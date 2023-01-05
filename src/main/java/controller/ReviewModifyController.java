package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.ReviewService;
import vo.Orders;
import vo.Review;
@WebServlet("/review/reviewModify")
public class ReviewModifyController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//리뷰 변경 폼 연결 
		request.getRequestDispatcher("/WEB-INF/view/review/reviewModify.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 값 받아오기
		Review review = new Review();
		//리뷰 작성자 Id 설정 
		Orders orders = new Orders();
		int orderCode = Integer.parseInt(request.getParameter("orderCode"));
		String reviewMemo = request.getParameter("reviewMemo");
		
		//로그인정보 추후 세션으로 변경예정
		String customerId = request.getParameter("customerId");					
		
		//디버깅 
		 System.out.println(customerId);
		 System.out.println(orderCode);
		 System.out.println(reviewMemo);
		//값 저장 
		review.setReviewMemo(reviewMemo);
		review.setOrderCode(orderCode);
		orders.setCustomerId(customerId);
		
		int row = 0;
		ReviewService reviewService = new ReviewService();
		row = reviewService.modifyReview(review, orders);
		// 결과
		if(row == 1) {
			// 리스트로 이동 추후 오더리스트로 변경예정
			System.out.println("입력성공");
			response.sendRedirect(request.getContextPath()+"/orders/orderList");  
		} else {
			// 폼이동
			System.out.println("입력실패");
			response.sendRedirect(request.getContextPath()+"/review/reviewModify");
		}
	}

}
