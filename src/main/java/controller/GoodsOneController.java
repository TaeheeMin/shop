package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.GoodsService;
import vo.Review;


@WebServlet("/GoodsOne")
public class GoodsOneController extends HttpServlet {
	private GoodsService goodsService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 값 받아오기
		System.out.println("[goodsOne 진입]");
		System.out.println(request.getParameter("goodsCode"));
		
		int goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
		this.goodsService = new GoodsService();
		ArrayList<HashMap<String, Object>> goodsOne = goodsService.getGoodsOne(goodsCode);
		ArrayList<HashMap<String, Object>> review = goodsService.getGoodsReview(goodsCode);
		
		// 상품 상세보기
		request.setAttribute("goodsOne", goodsOne);
		request.setAttribute("review", review);
		request.getRequestDispatcher("/WEB-INF/view/goods/goodsOne.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
