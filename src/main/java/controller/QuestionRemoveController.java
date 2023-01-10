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

@WebServlet("/QuestionRemove")
public class QuestionRemoveController extends HttpServlet {
	private QuestionService questionService; 
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
		int questionCode = Integer.parseInt(request.getParameter("questionCode"));
		System.out.println("questionCode : " + questionCode);
		this.questionService = new QuestionService();
		int row = questionService.removeQuestion(questionCode);
		
		// 결과
		if(row == 1) {
			// one으로 이동
			System.out.println("삭제성공");
			response.sendRedirect(request.getContextPath()+"/QuestionListByCustomer"); 
		} else {
			// 폼이동
			System.out.println("삭제실패");
			response.sendRedirect(request.getContextPath()+"/QuestionOne?questionCode="+questionCode);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
