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
import vo.Emp;
import vo.Question;
import vo.QuestionComment;

@WebServlet("/QuestionCommentAdd")
public class QuestionCommentAddController extends HttpServlet {
	private QuestionCommentService questionCommentService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 로그인 세션 불러오기
		HttpSession session = request.getSession();
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		
		if(loginEmp == null) {
			System.out.println("관리자만 사용 가능");
			response.sendRedirect(request.getContextPath()+"/Home");
			return;
		}
		
		// 값 받아오기
		QuestionComment questionComment = new QuestionComment();
		questionComment.setCommentMemo(request.getParameter("commentMemo"));
		questionComment.setQuestionCode(Integer.parseInt(request.getParameter("questionCode")));
		//System.out.println(questionComment.getQuestionCode());
		//System.out.println(questionComment.getCommentMemo());
		int row = 0;
		this.questionCommentService = new QuestionCommentService();
		row = questionCommentService.addQuestionComment(questionComment);
		
		// 결과
		if(row == 1) {
			// 리스트로 이동
			System.out.println("입력성공");
			response.sendRedirect(request.getContextPath()+"/QuestionListByAdmin"); 
		} else {
			// 폼이동
			System.out.println("입력실패");
			response.sendRedirect(request.getContextPath()+"/QuestionOne?questionCode=?"+questionComment.getQuestionCode());
		}
		
	}

}
