package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import vo.*;

public class GoodsDao {
	// 1) insert
	public HashMap<String, Integer> insertGoods(Connection conn, Goods goods) throws Exception {
		String sql = "INSERT INTO goods("
				+ " goods_category"
				+ ", goods_title"
				+ ", goods_artist"
				+ ", goods_content"
				+ ", goods_price"
				+ ", soldout"
				+ ", emp_id"
				+ ", hit"
				+ ", view" 
				+ ", createdate"
				+ ") VALUES (?, ?, ?, ?, ?, 'N', ?, 0, 0, NOW())";
		PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		stmt.setString(1, goods.getGoodsCategory());
		stmt.setString(2, goods.getGoodsTitle());
		stmt.setString(3, goods.getGoodsArtist());
		stmt.setString(4, goods.getGoodsContent());
		stmt.setInt(5, goods.getGoodsPrice());
		stmt.setString(6, goods.getEmpId());
		int row = stmt.executeUpdate();
		
		ResultSet rs = stmt.getGeneratedKeys();
		int autoKey = 0;
		if(rs.next()) {
			autoKey = rs.getInt(1);
		}
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("row", row);
		map.put("autoKey", autoKey);
		return map;
	}
	
	// 2) list
	// 2-1) count
	public int selectGoodsCount(Connection conn) throws Exception {
		int row = 0;
		String sql = "SELECT COUNT(*) FROM goods";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			row = rs.getInt("COUNT(*)");
		}
		return row;
	}
	// 2-2) list
	public ArrayList<HashMap<String, Object>> selectGoodsList(Connection conn, int beginRow, int endRow, String search, String word, String category) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		String sql = "SELECT"
				+ " gd.goods_code goodsCode"
				+ ", gd.goods_category goodsCategory"
				+ ", gd.goods_title goodsTitle"
				+ ", gd.goods_artist goodsArtist"
				+ ", gd.goods_price goodsPrice"
				+ ", gd.soldout soldout"
				+ ", gd.view view"
				+ ", img.filename filename"
				+ " FROM goods gd INNER JOIN goods_img img"
				+ " ON gd.goods_code = img.goods_code"
				+ " WHERE " + search + " LIKE ?"
				+ " AND gd.goods_category LIKE ?"
				+ " ORDER BY goodsCode DESC"
				+ " LIMIT ?, ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%"+ word +"%");
		stmt.setString(2, "%"+ category +"%");
		stmt.setInt(3, beginRow);
		stmt.setInt(4, endRow);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("goodsCode", rs.getInt("goodsCode"));
			m.put("goodsCategory", rs.getString("goodsCategory"));
			m.put("goodsTitle", rs.getString("goodsTitle"));
			m.put("goodsArtist",  rs.getString("goodsArtist"));
			m.put("goodsPrice",  rs.getInt("goodsPrice"));
			m.put("soldout", rs.getString("soldout"));
			m.put("view",  rs.getInt("view"));
			m.put("filename", rs.getString("filename"));
			list.add(m);
		}
		return list;
	}
	
	// 2-2-2) ???????????? ?????? ????????????
	public ArrayList<HashMap<String, Object>> selectRecentlySongList(Connection conn) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		String sql = "SELECT"
				+ " gd.goods_code goodsCode"
				+ ", gd.goods_category goodsCategory"
				+ ", gd.goods_title goodsTitle"
				+ ", gd.goods_artist goodsArtist"
				+ ", img.filename filename"
				+ " FROM goods gd INNER JOIN goods_img img"
				+ " ON gd.goods_code = img.goods_code"
				+ " ORDER BY gd.createdate DESC";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("goodsCode", rs.getInt("goodsCode"));
			m.put("goodsCategory", rs.getString("goodsCategory"));
			m.put("goodsTitle", rs.getString("goodsTitle"));
			m.put("goodsArtist",  rs.getString("goodsArtist"));
			m.put("filename", rs.getString("filename"));
			list.add(m);
		}
		// System.out.println("[GoodsDao] list : "+list);
		return list;
	}
	
	// 2-2-3) ???????????? ?????? hit??? (best?????? 12???)
	public ArrayList<HashMap<String, Object>> selectHitSongList(Connection conn) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		String sql = "SELECT"
				+ " gd.goods_code goodsCode"
				+ ", gd.goods_category goodsCategory"
				+ ", gd.goods_title goodsTitle"
				+ ", gd.goods_artist goodsArtist"
				+ ", img.filename filename"
				+ ", gd.goods_code goodsCode"
				+ ", gd.goods_price goodsPrice"
				+ " FROM goods gd INNER JOIN goods_img img"
				+ " ON gd.goods_code = img.goods_code"
				+ " ORDER BY gd.hit DESC, gd.goods_code DESC";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("goodsCode", rs.getInt("goodsCode"));
			m.put("goodsCategory", rs.getString("goodsCategory"));
			m.put("goodsTitle", rs.getString("goodsTitle"));
			m.put("goodsArtist",  rs.getString("goodsArtist"));
			m.put("filename", rs.getString("filename"));
			m.put("goodsPrice",  rs.getInt("goodsPrice"));
			list.add(m);
		}
		// System.out.println("[GoodsDao] list : "+list);
		return list;
	}
	
	// 2-2-4) ????????? ???????????? ????????? ????????????
	public ArrayList<HashMap<String, Object>> selectPopSongList(Connection conn) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		String sql = "SELECT"
				+ " gd.goods_code goodsCode"
				+ ", gd.goods_category goodsCategory"
				+ ", gd.goods_title goodsTitle"
				+ ", gd.goods_artist goodsArtist"
				+ ", img.filename filename"
				+ ", gd.goods_code goodsCode"
				+ ", gd.goods_price goodsPrice"
				+ " FROM goods gd INNER JOIN goods_img img"
				+ " ON gd.goods_code = img.goods_code"
				+ " WHERE gd.goods_category = '?????????'"
				+ " ORDER BY gd.hit DESC, gd.goods_code DESC";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("goodsCode", rs.getInt("goodsCode"));
			m.put("goodsCategory", rs.getString("goodsCategory"));
			m.put("goodsTitle", rs.getString("goodsTitle"));
			m.put("goodsArtist",  rs.getString("goodsArtist"));
			m.put("filename", rs.getString("filename"));
			m.put("goodsPrice",  rs.getInt("goodsPrice"));
			list.add(m);
		}
		// System.out.println("[GoodsDao] ??????list : "+list);
		return list;
	}
	
	// 2-2-5) ????????? ???????????? ????????? ????????????
	public ArrayList<HashMap<String, Object>> selectIndieSongList(Connection conn) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		String sql = "SELECT"
				+ " gd.goods_code goodsCode"
				+ ", gd.goods_category goodsCategory"
				+ ", gd.goods_title goodsTitle"
				+ ", gd.goods_artist goodsArtist"
				+ ", img.filename filename"
				+ ", gd.goods_code goodsCode"
				+ ", gd.goods_price goodsPrice"
				+ " FROM goods gd INNER JOIN goods_img img"
				+ " ON gd.goods_code = img.goods_code"
				+ " WHERE gd.goods_category = '??????'"
				+ " ORDER BY gd.hit DESC, gd.goods_code DESC";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("goodsCode", rs.getInt("goodsCode"));
			m.put("goodsCategory", rs.getString("goodsCategory"));
			m.put("goodsTitle", rs.getString("goodsTitle"));
			m.put("goodsArtist",  rs.getString("goodsArtist"));
			m.put("filename", rs.getString("filename"));
			m.put("goodsPrice",  rs.getInt("goodsPrice"));
			list.add(m);
		}
		return list;
	}
	
	// 2-3) goods one
	public ArrayList<HashMap<String, Object>> selectGoodsOne(Connection conn, int goodsCode) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		// System.out.println(goodsCode);
		String sql = "SELECT"
				+ " g.goods_code goodsCode"
				+ ", g.goods_category goodsCategory"
				+ ", g.goods_title goodsTitle"
				+ ", g.goods_artist goodsArtist"
				+ ", g.goods_content goodsContent"
				+ ", g.goods_price goodsPrice"
				+ ", g.soldout soldout"
				+ ", g.hit hit"
				+ ", g.view view"
				+ ", img.filename filename"
				+ " FROM goods g INNER JOIN goods_img img"
				+ " ON g.goods_code = img.goods_code"
				+ " WHERE g.goods_code = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, goodsCode);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("goodsCode", rs.getString("goodsCode"));
			m.put("goodsCategory", rs.getString("goodsCategory"));
			m.put("goodsTitle", rs.getString("goodsTitle"));
			m.put("filename", rs.getString("filename"));
			m.put("goodsArtist",  rs.getString("goodsArtist"));
			m.put("goodsPrice",  rs.getString("goodsPrice"));
			m.put("goodsContent", rs.getString("goodsContent"));
			m.put("soldout", rs.getString("soldout"));
			m.put("hit", rs.getString("hit"));
			m.put("view",  rs.getInt("view"));
			m.put("filename", rs.getString("filename"));
			list.add(m);
		}
		return list;
	}
	
	// 2-4) goodsOne (???????????? ??????)
	public Goods GoodsOne(Connection conn, int goodsCode) throws Exception {
		Goods goodsOne = null;
		
		String sql = "SELECT * FROM goods WHERE goods_code = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, goodsCode);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			goodsOne = new Goods();
			goodsOne.setGoodsCode(rs.getInt("goods_code"));
			goodsOne.setGoodsTitle(rs.getString("goods_title"));
			goodsOne.setGoodsArtist(rs.getString("goods_artist"));
			goodsOne.setGoodsContent(rs.getString("goods_content"));
			goodsOne.setGoodsPrice(rs.getInt("goods_price"));
		}
		
		return goodsOne;
	}
	
	// 2-5) goods one review
	public ArrayList<HashMap<String, Object>> goodsReview(Connection conn, int goodsCode) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		String sql = "SELECT"
				+ " r.review_memo reviewMemo"
				+ ", r.createdate createdate"
				+ ", o.customer_id customerId"
				+ " FROM goods g INNER JOIN orders o"
				+ " ON g.goods_code = o.goods_code"
				+ " INNER JOIN review r"
				+ " ON o.order_code = r.order_code"
				+ " WHERE g.goods_code = ?"
				+ " ORDER BY r.createdate DESC";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, goodsCode);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("reviewMemo", rs.getString("reviewMemo"));
			m.put("createdate", rs.getString("createdate"));
			m.put("customerId", rs.getString("customerId"));
			list.add(m);
		}
		return list;
	}
	
	// 2-6) goods Artist ??????
	public ArrayList<Goods> goodsArtist(Connection conn) throws Exception {
		ArrayList<Goods> list = new ArrayList<Goods>();
		String sql = "SELECT DISTINCT goods_artist goodsArtist FROM goods ORDER BY hit DESC";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Goods g = new Goods();
			g.setGoodsArtist(rs.getString("goodsArtist"));
			list.add(g);
		}
		return list;
	}
	
	// 3) update
	// 3-1) view update
	public int updateGoodsView(Connection conn, int goodsCode) throws Exception {
		int row = 0;
		String sql = "UPDATE goods SET view = view+1 WHERE goods_code = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, goodsCode);
		row = stmt.executeUpdate();
		return row;
	}
	
	// 3-2) goods update
	public int updateGoods(Connection conn, Goods goods) throws Exception {
		int row = 0;
		String sql = "UPDATE goods SET"
				+ " goods_title = ?"
				+ ", goods_category = ?"
				+ ", goods_artist = ?"
				+ ", goods_content = ?"
				+ ", goods_price = ?"
				+ ", soldout = ?"
				+ ", hit = ?"
				+ " WHERE goods_code = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, goods.getGoodsTitle());
		stmt.setString(2, goods.getGoodsCategory());
		stmt.setString(3, goods.getGoodsArtist());
		stmt.setString(4, goods.getGoodsContent());
		stmt.setInt(5, goods.getGoodsPrice());
		stmt.setString(6, goods.getSoldout());
		stmt.setInt(7, goods.getHit());
		stmt.setInt(8, goods.getGoodsCode());
		row = stmt.executeUpdate();
		return row;
	}
}
