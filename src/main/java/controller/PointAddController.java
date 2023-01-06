package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.PointHistoryService;
import service.ReviewService;
import vo.Review;

/**
 * Servlet implementation class PointAddController
 */
@WebServlet("/point/pointAdd")
public class PointAddController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/point/pointOne.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 값 받아오기		
		int orderCode = Integer.parseInt(request.getParameter("orderCode"));		
		int row = 0;
		PointHistoryService pointHistoryService = new PointHistoryService();
		row = pointHistoryService.AddPoint(orderCode);
		// 결과
		if(row == 1) {
			// 리스트로 이동
			System.out.println("입력성공");
			response.sendRedirect(request.getContextPath()+"/review/reviewOne");
			
		} else {
			// 폼이동
			System.out.println("입력실패");
			response.sendRedirect(request.getContextPath()+"/point/pointAdd");
		}
	}	

}
