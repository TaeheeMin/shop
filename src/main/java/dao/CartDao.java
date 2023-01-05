package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import vo.Cart;

public class CartDao {
	// 1) cart insert
	public int insertCart(Connection conn, Cart cart) throws Exception {
		int row = 0;
		String sql = "INSERT INTO cart("
				+ " goods_title"
				+ ", customer_id"
				+ ", cart_quantity"
				+ ", createdate"
				+ ") VALUES (?, ?, 1, NOW())";
		PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		stmt.setInt(1, cart.getGoodsCode());
		stmt.setString(2, cart.getCustomerId());
		row = stmt.executeUpdate();
		return row;
	}
	
	// 2) cart list
	public ArrayList<HashMap<String, Object>> cartList(Connection conn, String customerId) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		String sql = "SELECT "
				+ " g.goods_code goodsCode"
				+ ", g.goods_title goodsTitle"
				+ ", g.goods_price goodsPrice"
				+ ", c.cart_quantity cartQuantity"
				+ ", img.filename filename"
				+ " FROM cart c INNER JOIN goods g"
				+ " ON c.goods_code = g.goods_code"
				+ " INNER JOIN goods_img img"
				+ " ON g.goods_code = img.goods_code"
				+ " WHERE c.customer_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, customerId);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("goodsCode",  rs.getInt("goodsCode"));
			m.put("goodsTitle", rs.getString("goodsTitle"));
			m.put("goodsPrice",  rs.getInt("goodsPrice"));
			m.put("cartQuantity", rs.getInt("cartQuantity"));
			m.put("filename", rs.getString("filename"));
			list.add(m);
		}
		return list;
	}
	
	
	// 3) cart update
	
	
	// 4) cart delete
	public int deleteCart(Connection conn, Cart cart) throws Exception {
		int row = 0;
		String sql = "DELETE FROM cart WHERE goods_code = ? AND customer_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, cart.getGoodsCode());
		stmt.setString(2, cart.getCustomerId());
		row = stmt.executeUpdate();
		return row;
	}
}
