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
				+ " goods_name"
				+ ", goods_price"
				+ ", soldout"
				+ ", emp_id"
				+ ", hit"
				+ ", createdate"
				+ ") VALUES (?, ?, 'N', 1, 0, NOW())";
		PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		stmt.setString(1, goods.getGoodsName());
		stmt.setInt(2, goods.getGoodsPrice());
		int row = stmt.executeUpdate();
		
		// stmt.executeUpdate()에서 생성된 오토키값을 가져오는 
		ResultSet rs = stmt.getGeneratedKeys();
		int autoKey = 0;
		if(rs.next()) {
			autoKey = rs.getInt(1);
		}
		
		// hashMap에 결과 넣어서 반환 => item테이블 결과와 item_no 반환
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("row", row);
		map.put("autoKey", autoKey);
		return map;
	}
	
	// 2) list
	public ArrayList<HashMap<String, Object>> selectGoodsList(Connection conn) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		String sql = "SELECT"
				+ " gd.goods_name goodsName"
				+ ", gd.goods_price goodsPrice"
				+ ", gd.soldout soldout"
				+ ", img.filename filename"
				+ " FROM goods gd INNER JOIN goods_img img"
				+ " ON gd.goods_code = img.goods_code";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("goodsName", rs.getString("goodsName"));
			m.put("goodsPrice",  rs.getInt("goodsPrice"));
			m.put("soldout", rs.getString("soldout"));
			m.put("filename", rs.getString("filename"));
			list.add(m);
		}
		return list;
	}
}
