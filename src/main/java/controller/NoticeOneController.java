package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.*;
import vo.*;


@WebServlet("/NoticeOne")
public class NoticeOneController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 값 받아오기
		System.out.println(request.getParameter("noticeCode"));
		int noticeCode = Integer.parseInt(request.getParameter("noticeCode"));
		NoticeService noticeService = new NoticeService();
		Notice noticeOne = noticeService.getNoticeOne(noticeCode);
		
		// view와 공유할 모델 데이터 성정
		request.setAttribute("noticeOne", noticeOne);
		
		request.getRequestDispatcher("/WEB-INF/view/notice/noticeOne.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
