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

import service.OrdersService;
import service.QuestionService;
import vo.Customer;
import vo.Emp;
import vo.Question;

@WebServlet("/QuestionAdd")
public class QuestionAddController extends HttpServlet {
	private OrdersService ordersService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		// 로그인이 안되어있을경우 진입 불가 -> 홈으로 이동
		if(loginCustomer == null) {
			System.out.println("로그인 필요");
			response.sendRedirect(request.getContextPath()+"/Home");
			return;
		}
		
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
		
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		this.ordersService = new OrdersService();
		list = ordersService.selectOrdersList(loginCustomer, currentPage, rowPerPage);
		session.setAttribute("list", list);
		
		request.getRequestDispatcher("/WEB-INF/view/question/questionAdd.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		System.out.println(request.getParameter("ordersCode"));
		System.out.println(request.getParameter("category"));
		System.out.println(request.getParameter("questionTitle"));
		System.out.println(request.getParameter("questionMemo"));
		// 값 받아오기
		Question question = new Question();
		question.setOrdersCode(Integer.parseInt(request.getParameter("ordersCode")));
		question.setCategory(request.getParameter("category"));
		question.setQuestionTitle(request.getParameter("questionTitle"));
		question.setQuestionMemo(request.getParameter("questionMemo"));
		
		int row = 0;
		QuestionService questionService = new QuestionService();
		row = questionService.addQuestion(question);
		
		// 결과
		if(row == 1) {
			// 리스트로 이동
			System.out.println("입력성공");
			response.sendRedirect(request.getContextPath()+"/QuestionListByCustomer"); 
		} else {
			// 폼이동
			System.out.println("입력실패");
			response.sendRedirect(request.getContextPath()+"/QuestionListByCustomer");
		}
	}
}
