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

import service.EmpService;
import vo.Emp;


@WebServlet("/EmpList")
public class EmpListController extends HttpServlet {
	private EmpService empService;
	
	// 직원목록 view
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) 직원로그인세션 불러오기 - 사원이상만 가능
		HttpSession session = request.getSession();
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		
		// 2) 직원로그인이 안되어있거나 인턴일 경우, 직원목록 페이지 조회불가 -> 홈으로 인동
		if(loginEmp == null) {
			System.out.println("직원만 접근 가능합니다");
			response.sendRedirect(request.getContextPath()+"/Home");
			return;
		}
		
		this.empService = new EmpService();
		int ttlEmp = empService.ttlCntEmp();
		
		// 2.5) 검색어
		String word = request.getParameter("word");
		if(word == null) {
			word = "";
		} else {
			// 검색값 총 갯수
			ttlEmp = empService.ttlCntSearchEmp(word);
		}
		System.out.println("검색값 : "+word);
		System.out.println("직원 총원 : "+ttlEmp);
				
		// 3) 페이징 작업
		// 3-1) currentPage 현재페이지
		int currentPage = 1; // 현재페이지 초기값 
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		System.out.println("currentPage: "+currentPage);
		
		// 3-2) rowPerPage, beginRow
		int rowPerPage = 10; // 페이지 당 출력할 사원 수
		if(request.getParameter("rowPerPage") != null) {
			rowPerPage = Integer.parseInt(request.getParameter("rowPerPage"));
		}
		System.out.println("rowPerPage : "+rowPerPage);
		int beginRow = (currentPage-1) * rowPerPage;
		
		// 3-3) 전체 페이지
		int pageCnt = 5; // 한번에 보일 페이지 갯수

		int beginPage = ((currentPage-1)/pageCnt)*pageCnt + 1; // 페이지 목록 시작 값
		System.out.println("beginPage : "+beginPage);
		int endPage = beginPage + pageCnt - 1;
		System.out.println("endPage : "+endPage);
		
		int lastPage = (int)Math.ceil((double)ttlEmp / (double)rowPerPage);

		if(endPage > lastPage) {
			endPage = lastPage;
		}
		
		// 4) 내림차순 오름차순 초기값
		String col = "e.createdate";
		String sort = "desc";
		if(request.getParameter("col") != null) {
			col = request.getParameter("col");
		}
		if(request.getParameter("sort") != null) {
			sort = request.getParameter("sort");
		}
		System.out.println("sort : "+sort);
		String paramSort = "asc";
		if(sort.equals("asc")) {
			paramSort = "desc";
		}
				
		// 6) 목록불러오기 service
		ArrayList<HashMap<String,Object>> list = empService.allEmpList(beginRow, rowPerPage, col, sort, word);
				
		request.setAttribute("word", word);
		request.setAttribute("paramSort", paramSort);
		request.setAttribute("list", list);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("rowPerPage", rowPerPage);
		request.setAttribute("pageCnt", pageCnt);
		request.setAttribute("beginPage", beginPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("lastPage", lastPage);
		request.getRequestDispatcher("/WEB-INF/view/emp/empList.jsp").forward(request, response);		
	}
}
