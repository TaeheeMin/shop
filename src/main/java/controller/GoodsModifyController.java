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
import service.NoticeService;
import vo.Emp;
import vo.Goods;
import vo.Notice;

@WebServlet("/GoodsModify")
public class GoodsModifyController extends HttpServlet {
	private GoodsService goodsService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		
		// 직원로그인이 안되어있을경우, 직원등록폼 진입 불가 -> 홈으로 인동
		if(loginEmp == null) {
			System.out.println("직원만 접근 가능");
			response.sendRedirect(request.getContextPath()+"/Home");
			return;
		}
		// 값 받아오기
		request.setCharacterEncoding("utf-8");
		int goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
		this.goodsService = new GoodsService();
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		list = goodsService.getGoodsOne(goodsCode);
		
		// view와 공유할 모델 데이터 성정
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/view/goods/goodsModify.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 값 받아오기
		request.setCharacterEncoding("utf-8");
		Goods goods = new Goods();
		goods.setGoodsCode(Integer.parseInt(request.getParameter("goodsCode")));
		goods.setGoodsCategory(request.getParameter("goodsCategory"));
		goods.setGoodsTitle(request.getParameter("goodsTitle"));
		goods.setGoodsArtist(request.getParameter("goodsArtist"));
		goods.setGoodsContent(request.getParameter("goodsContent"));
		goods.setGoodsPrice(Integer.parseInt(request.getParameter("goodsPrice")));
		goods.setSoldout(request.getParameter("soldout"));
		int row = 0;
		this.goodsService = new GoodsService();
		row = goodsService.modifyGoods(goods);
		
		// 결과
		if(row == 1) {
			// 리스트로 이동
			System.out.println("수정성공");
			response.sendRedirect(request.getContextPath()+"/GoodsList"); 
		} else {
			// 폼이동
			System.out.println("수정실패");
			response.sendRedirect(request.getContextPath()+"/GoodsModify?goodsCode="+goods.getGoodsCode());
		}
	}
}
