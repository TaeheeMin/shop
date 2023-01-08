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
				+ " goods_title"
				+ ", goods_artist"
				+ ", goods_content"
				+ ", goods_price"
				+ ", soldout"
				+ ", emp_id"
				+ ", hit"
				+ ", view" 
				+ ", createdate"
				+ ") VALUES (?, ?, ?, ?, 'N', ?, 0, 0, NOW())";
		PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		stmt.setString(1, goods.getGoodsTitle());
		stmt.setString(2, goods.getGoodsArtist());
		stmt.setString(3, goods.getGoodsContent());
		stmt.setInt(4, goods.getGoodsPrice());
		stmt.setString(5, goods.getEmpId());
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
	public ArrayList<HashMap<String, Object>> selectGoodsList(Connection conn, int beginRow, int endRow, String category, String word) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		String sql = "SELECT"
				+ " gd.goods_code goodsCode"
				+ ", gd.goods_title goodsTitle"
				+ ", gd.goods_artist goodsArtist"
				+ ", gd.goods_price goodsPrice"
				+ ", gd.soldout soldout"
				+ ", img.filename filename"
				+ " FROM goods gd INNER JOIN goods_img img"
				+ " ON gd.goods_code = img.goods_code"
				+ " WHERE " + category + " LIKE ?"
				+ " LIMIT ?, ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%"+ word +"%");
		stmt.setInt(2, beginRow);
		stmt.setInt(3, endRow);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("goodsCode", rs.getInt("goodsCode"));
			m.put("goodsTitle", rs.getString("goodsTitle"));
			m.put("goodsArtist",  rs.getString("goodsArtist"));
			m.put("goodsPrice",  rs.getInt("goodsPrice"));
			m.put("soldout", rs.getString("soldout"));
			m.put("filename", rs.getString("filename"));
			list.add(m);
		}
		return list;
	}
	
	// 3) goods one
	public ArrayList<HashMap<String, Object>> selectGoodsOne(Connection conn, int goodsCode) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		System.out.println(goodsCode);
		String sql = "SELECT"
				+ " g.goods_code goodsCode"
				+ ", g.goods_title goodsTitle"
				+ ", g.goods_artist goodsArtist"
				+ ", g.goods_content goodsContent"
				+ ", g.goods_price goodsPrice"
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
			m.put("goodsTitle", rs.getString("goodsTitle"));
			m.put("filename", rs.getString("filename"));
			m.put("goodsArtist",  rs.getString("goodsArtist"));
			m.put("goodsPrice",  rs.getString("goodsPrice"));
			m.put("goodsContent", rs.getString("goodsContent"));
			m.put("filename", rs.getString("filename"));
			list.add(m);
		}
		return list;
	}
}
