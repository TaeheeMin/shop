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
	public int addGoods(Goods goods, GoodsImg goodsImg, String dir) {
		Connection conn = null;
		GoodsDao goodsDao = new GoodsDao();
		GoodsImgDao goodsImgDao = new GoodsImgDao(); 
		int row = 0;
		try {
			conn = DBUtil.getConnection();
			HashMap<String, Integer> map = goodsDao.insertGoods(conn, goods);
			goodsImg.setGoodsCode(map.get("autoKey")); 
			row = goodsImgDao.insertGoodsImg(conn, goodsImg);
			
			conn.commit();
			
		} catch (Exception e) {
			try {
				conn.rollback();
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
