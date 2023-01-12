package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.*;
import vo.*;

@WebServlet("/QuestionOne")
public class QuestionOneController extends HttpServlet {
	private QuestionService questionService;
	private QuestionCommentService questionCommentService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 값 받아오기
		//System.out.println(request.getParameter("questionCode"));
		int questionCode = Integer.parseInt(request.getParameter("questionCode"));
		this.questionService = new QuestionService();
		this.questionCommentService = new QuestionCommentService();
		ArrayList<HashMap<String, Object>> list = questionService.getQuestionOne(questionCode);
		ArrayList<HashMap<String, Object>> comment = questionCommentService.getQuestionComment(questionCode);
		// view와 공유할 모델 데이터 성정
		request.setAttribute("list", list);
		request.setAttribute("comment", comment);
		
		request.getRequestDispatcher("/WEB-INF/view/question/questionOne.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
