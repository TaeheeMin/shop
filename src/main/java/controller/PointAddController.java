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
}
