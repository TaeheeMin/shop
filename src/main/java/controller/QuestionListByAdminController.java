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

import service.GoodsService;
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
		this.questionService = new QuestionService();
		this.questionCommentService = new QuestionCommentService();
		
		// 로그인 세션 불러오기
		HttpSession session = request.getSession();
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		
		if(loginEmp == null) {
			System.out.println("관리자만 사용 가능");
			response.sendRedirect(request.getContextPath()+"/Home");
			return;
		}
		
		// 1) 페이징
		// 1-1) currentPage		
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		// System.out.println("currentPage : " + currentPage);
		
		// 1-2) rowPerPage
		int rowPerPage = 10;
		if(request.getParameter("rowPerPage") != null) {
			rowPerPage = Integer.parseInt(request.getParameter("rowPerPage"));
		}
		System.out.println("rowPerPage : " + rowPerPage);
		
		// 1-3) 전체 페이지
		int count = questionService.getQuestionListCount();
		//System.out.println("count : " + count);
		int page = 5; // 페이징 목록 개수
		int beginPage = ((currentPage - 1)/page) * page + 1; // 시작 페이지
		//System.out.println("beginPage : "+beginPage);
		int endPage = beginPage + page - 1; // 페이징 목록 끝
		//System.out.println("endPage : "+endPage);
		int lastPage = (int)Math.ceil((double)count / (double)rowPerPage); // 마지막 페이지
		// System.out.println("lastPage : " + lastPage);
		if(endPage > lastPage) {
			endPage = lastPage;
		}
		// System.out.println("endPage : " + endPage);
		
		// 2) 검색
		String category = request.getParameter("category");
		if(category == null) {
			category = "";
		}
		// System.out.println("category : " +  category);
	
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		ArrayList<HashMap<String, Object>> comment = new ArrayList<HashMap<String, Object>>();
		list = questionService.getQuestionListByAdmin(currentPage, rowPerPage, category);
		comment = questionCommentService.getAllQuestionComment();
		
		request.setAttribute("comment", comment);
		request.setAttribute("list", list);
		request.setAttribute("beginPage", beginPage);
		request.setAttribute("page", page);
		request.setAttribute("endPage", endPage);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("rowPerPage", rowPerPage);
		request.setAttribute("category", category);
		request.getRequestDispatcher("/WEB-INF/view/question/questionListByAdmin.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}