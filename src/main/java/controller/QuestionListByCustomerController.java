package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.QuestionService;
import vo.Customer;
import vo.Question;

@WebServlet("/QuestionListByCustomer")
public class QuestionListByCustomerController extends HttpServlet {
	private QuestionService questionService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		// 로그인이 안되어있을경우 진입 불가 -> 홈으로 이동
		if(loginCustomer == null) {
			System.out.println("로그인 필요");
			response.sendRedirect(request.getContextPath()+"/Home");
			return;
		}
		
		String customerId = loginCustomer.getCustomerId();
		this.questionService = new QuestionService();
		ArrayList<Question> list = new ArrayList<Question>();
		list = questionService.getQuestionListbyCustomer(customerId);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/view/question/questionListByCustomer.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
