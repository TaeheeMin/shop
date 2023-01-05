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
				+ ", createdate"
				+ ") VALUES (?, ?, ?, ?, 'N', ?, 0, NOW())";
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
	public ArrayList<HashMap<String, Object>> selectGoodsList(Connection conn) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		String sql = "SELECT"
				+ " gd.goods_code goodsCode"
				+ ", gd.goods_title goodsTitle"
				+ ", gd.goods_artist goodsArtist"
				+ ", gd.goods_price goodsPrice"
				+ ", gd.soldout soldout"
				+ ", img.filename filename"
				+ " FROM goods gd INNER JOIN goods_img img"
				+ " ON gd.goods_code = img.goods_code";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("goodsCode", rs.getString("goodsCode"));
			m.put("goodsTitle", rs.getString("goodsTitle"));
			m.put("goodsArtist",  rs.getString("goodsArtist"));
			m.put("goodsPrice",  rs.getInt("goodsPrice"));
			m.put("soldout", rs.getString("soldout"));
			m.put("filename", rs.getString("filename"));
			list.add(m);
		}
		return list;
	}
}
