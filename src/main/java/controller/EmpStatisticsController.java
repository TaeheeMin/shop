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

import service.OrdersService;
import vo.Emp;

@WebServlet("/EmpStatistics")
public class EmpStatisticsController extends HttpServlet {
	private OrdersService ordersService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				// 직원로그인세션 불러오기 - 직원이상만 가능
				HttpSession session = request.getSession();
				Emp loginEmp = (Emp)session.getAttribute("loginEmp");
				
				// 직원로그인이 안되어있을경우 진입 불가 -> 홈으로 인동
				if(loginEmp == null) {
					System.out.println("사원이상의 재직자만 접근 가능합니다");
					String empAdd = "accesslimit";
					response.sendRedirect(request.getContextPath()+"/Home?emp="+empAdd);
					return;
				}				
		
				// model 
				ordersService = new OrdersService();
				// 월별 총 매출 
				ArrayList<HashMap<String, Object>> orderMonth = new ArrayList<HashMap<String, Object>>(); 
				orderMonth = ordersService.selectOrdersListByMonth();
				request.setAttribute("orderMonth", orderMonth);
				// 월별 총 주문건수 
				ArrayList<HashMap<String, Object>> orderCnt = new ArrayList<HashMap<String, Object>>();
				orderCnt = ordersService.selectOrdersCountByMonth(); 
				request.setAttribute("orderCnt", orderCnt);
				// 년도별 총 매출
				ArrayList<HashMap<String, Object>> orderYear = new ArrayList<HashMap<String, Object>>(); 
				orderYear = ordersService.selectOrdersListByYear();
				request.setAttribute("orderYear", orderYear);
				
				request.getRequestDispatcher("/WEB-INF/view/emp/goodsStatistics.jsp").forward(request, response);	
	}


}
