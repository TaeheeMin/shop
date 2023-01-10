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

import service.QuestionService;
import vo.Customer;
import vo.Question;


@WebServlet("/QuestionModify")
public class QuestionModifyController extends HttpServlet {
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
		
		int questionCode = Integer.parseInt(request.getParameter("questionCode"));
		this.questionService = new QuestionService();
		ArrayList<HashMap<String, Object>> list = questionService.getQuestionOne(questionCode);
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/WEB-INF/view/question/questionModify.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		// 로그인이 안되어있을경우 진입 불가 -> 홈으로 이동
		if(loginCustomer == null) {
			System.out.println("로그인 필요");
			response.sendRedirect(request.getContextPath()+"/Home");
			return;
		}
		
		// 값 받아오기
		request.setCharacterEncoding("utf-8");
		Question question = new Question();
		question.setCategory(request.getParameter("category"));
		question.setQuestionTitle(request.getParameter("questionTitle"));
		question.setQuestionMemo(request.getParameter("qusetionMemo"));
		question.setQuestionCode(Integer.parseInt(request.getParameter("questionCode")));
		System.out.println("category : " + question.getCategory());
		System.out.println("questionTitle : " + question.getQuestionCode());
		System.out.println("qusetionMemo : " + question.getQuestionMemo());
		System.out.println("questionCode : " + question.getQuestionTitle());
		int row = 0;
		this.questionService = new QuestionService();
		row = questionService.modifyQuestion(question);
		
		// 결과
		if(row == 1) {
			// 리스트로 이동
			System.out.println("수정성공");
			response.sendRedirect(request.getContextPath()+"/QuestionListByCustomer"); 
		} else {
			// 폼이동
			System.out.println("수정실패");
			response.sendRedirect(request.getContextPath()+"/QuestionModify?questionCode="+question.getQuestionCode());
		}
	}

}
