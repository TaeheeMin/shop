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
				+ " goods_code"
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
	
	// 2-2) cartlistOne
	public ArrayList<HashMap<String, Object>> cartOne(Connection conn, String customerId, int goodsCode) throws Exception {
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
				+ " WHERE c.customer_id = ? AND g.goodsCode = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, customerId);
		stmt.setInt(2, goodsCode);
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
	public int updateCart(Connection conn, int cartQuantity, int goodsCode, String customerId) throws Exception {
		int row = 0;
		String sql = "UPDATE cart SET cart_quantity = ? WHERE goods_code = ? AND customer_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, cartQuantity);
		stmt.setInt(2, goodsCode);
		stmt.setString(3, customerId);
		row = stmt.executeUpdate();
		return row;
	}
	
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
	
	// 4-2) 주문시 cart delete
	public int clearCart(Connection conn, String customerId) throws Exception {
		int row = 0;
		String sql = "DELETE FROM cart WHERE customer_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, customerId);
		row = stmt.executeUpdate();
		return row;
	}
}
