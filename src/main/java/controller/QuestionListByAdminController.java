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

import service.QuestionCommentService;
import service.QuestionService;
import vo.Emp;
import vo.Question;
import vo.QuestionComment;

@WebServlet("/QuestionListByAdmin")
public class QuestionListByAdminController extends HttpServlet {
	private QuestionService questionService;
	private QuestionCommentService questionCommentService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 세션 불러오기
		HttpSession session = request.getSession();
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		
		if(loginEmp == null) {
			System.out.println("관리자만 사용 가능");
			response.sendRedirect(request.getContextPath()+"/Home");
			return;
		}
		this.questionService = new QuestionService();
		this.questionCommentService = new QuestionCommentService();
		
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		ArrayList<HashMap<String, Object>> comment = new ArrayList<HashMap<String, Object>>();
		list = questionService.getQuestionListByAdmin();
		comment = questionCommentService.getAllQuestionComment();
		request.setAttribute("comment", comment);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/view/question/questionListByAdmin.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}