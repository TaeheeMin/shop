package controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import service.*;
import vo.*;


@WebServlet("/GoodsAdd")
public class GoodsAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/goods/goodsAddForm.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파일저장 경로 및 설정	
		String dir = request.getServletContext().getRealPath("goodsimg");
		int maxFileSize = 1024 * 1024 * 100; // 100Mb까지(1kb = 1024) 
		DefaultFileRenamePolicy fp = new DefaultFileRenamePolicy();
		MultipartRequest mreq = new MultipartRequest(request, dir, maxFileSize, "utf-8", fp);
		
		// 파일 형식 확인
		String contentType = mreq.getContentType("goodsFile");
		System.out.println("contentType : " + contentType);
		if(contentType.equals("image/gif") || contentType.equals("image/png") || contentType.equals("image/jpg") || contentType.equals("image/jpeg")) {
			
			// 값 받아오기
			Goods goods = new Goods();
			goods.setGoodsName(mreq.getParameter("goodsName"));
			goods.setGoodsPrice(Integer.parseInt(mreq.getParameter("goodsPrice")));
			System.out.println("goodsName : " + goods.getGoodsName());
			System.out.println("goodsPrice : " + goods.getGoodsPrice());
			
			GoodsImg goodsImg = new GoodsImg();
			goodsImg.setFilename(mreq.getFilesystemName("goodsFile"));
			goodsImg.setOriginName(mreq.getOriginalFileName("goodsFile"));
			goodsImg.setContentType(contentType);
			System.out.println("fileName : " + goodsImg.getFilename());
			System.out.println("originName : " + goodsImg.getOriginName());
			
			// 서비스
			GoodsService goodsService = new GoodsService();
			goodsService.addGoods(goods, goodsImg, dir);
			System.out.println("등록 완료");
		} else {
			System.out.println("jsp, gif, png파일만 업로드 가능");
			File f = new File(dir+"\\"+mreq.getFilesystemName("fileName"));
			if(f.exists()) {
				f.delete();
			}
		}
		
		response.sendRedirect(request.getContextPath()+"/GoodsAdd");
	}

}
