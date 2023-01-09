package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Home")
public class HomeController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 홈 View
		request.getRequestDispatcher("/WEB-INF/view/home.jsp").forward(request, response);
		
		// 드라이브 로딩
    	try {
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("BootListener 드라이브 로딩 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이브 로딩 실패");
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
}
