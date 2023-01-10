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
import vo.Emp;
import vo.Question;

@WebServlet("/QuestionListByAdmin")
public class QuestionListByAdminController extends HttpServlet {
	private QuestionService questionService;
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
		
		ArrayList<Question> list = new ArrayList<Question>();
		list = questionService.getQuestionListByAdmin();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/view/question/questionListByAdmin.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}