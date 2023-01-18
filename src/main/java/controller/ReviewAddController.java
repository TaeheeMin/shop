package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.ReviewService;
import vo.Customer;
import vo.Review;


@WebServlet("/review/reviewAdd")
public class ReviewAddController extends HttpServlet {	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//리뷰 작성 폼 연결 
		int orderCode = Integer.parseInt(request.getParameter("orderCode"));
		// 디버깅
		System.out.println("orderCode : "+orderCode);
		request.setAttribute("orderCode", orderCode);
		ReviewService reviewService = new ReviewService();
		// 리뷰 중복 msg 출력
		if(request.getParameter("msg") != null) {
			request.setAttribute("overlap", request.getParameter("msg"));
		}		
		
		if(reviewService.reviewCheck(orderCode)) {
			String msg = "already";
			response.sendRedirect(request.getContextPath()+"/orders/ordersOne?orderCode="+orderCode+"&msg="+msg);
			return;			
		} else {
			request.getRequestDispatcher("/WEB-INF/view/review/reviewForm.jsp").forward(request, response);
			}

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		
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
		row = reviewService.addReivew(review, loginCustomer);

		// 결과
		
		System.out.print(reviewService.addReivew(review, loginCustomer));
			
		
		if(row == 1) {
			// 리스트로 이동
			System.out.println("리뷰작성성공");
			response.sendRedirect(request.getContextPath()+"/Home?msg="+1);
			return;	
			
		} else {
			// 폼이동		
			System.out.println("리뷰작성실패");
			response.sendRedirect(request.getContextPath()+"/review/reviewAdd?orderCode="+orderCode);
			}
		}
}



