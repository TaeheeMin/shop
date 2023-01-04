package service;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import dao.*;
import util.DBUtil;
import vo.*;

public class GoodsService {
	// 1) add goods
	public int addItem(Goods goods, GoodsImg goodsImg, String dir) {
		Connection conn = null;
		GoodsDao goodsDao = new GoodsDao();
		GoodsImgDao goodsImgDao = new GoodsImgDao(); 
		int row = 0;
		try {
			conn = DBUtil.getConnection();
			HashMap<String, Integer> map = goodsDao.insertGoods(conn, goods);
			goodsImg.setGoodsCode(map.get("autoKey")); // item테이블에 입력이 성공됐으면 itemNo 생성 이걸 받아와서 item_img에 넣어줌
			row = goodsImgDao.insertGoodsImg(conn, goodsImg); // item_img에 넣어주는 dao
			
			// 두개 모두 성공하면 커밋됨
			conn.commit();
			
		} catch (Exception e) {
			try {
				conn.rollback();
				// db작업 실패시 이미 업로드된 파일 삭제
				File f = new File(dir+"\\"+goodsImg.getFilename());
				if(f.exists()) {
					f.delete();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return row;
	}
	
	// 2) list
	public ArrayList<HashMap<String, Object>> getGoodsList() {
		Connection conn = null;
		GoodsDao goodsDao = new GoodsDao();
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		try {
			conn = DBUtil.getConnection();
			list = goodsDao.selectGoodsList(conn);
			conn.commit();
			
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
