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
import service.SiteCounterService;

@WebServlet("/Home2")
public class Home2Controller extends HttpServlet {
	private GoodsService goodsService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[Home폼 진입]");
		this.goodsService = new GoodsService();
		ArrayList<HashMap<String, Object>> list = goodsService.selectRecentlySongList();
		System.out.println("최신곡 list : "+list);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/view/home2.jsp").forward(request, response);
	}
}
