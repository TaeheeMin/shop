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


@WebServlet("/GoodsOne")
public class GoodsOneController extends HttpServlet {
	private GoodsService goodsService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 값 받아오기
		System.out.println(request.getParameter("goodsCode"));
		int goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
		this.goodsService = new GoodsService();
		ArrayList<HashMap<String, Object>> goodsOne = goodsService.getGoodsOne(goodsCode);
		
		// view와 공유할 모델 데이터 성정
		request.setAttribute("goodsOne", goodsOne);
		request.getRequestDispatcher("/WEB-INF/view/goods/goodsOne.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
