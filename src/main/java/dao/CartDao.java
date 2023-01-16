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
				+ ") VALUES (?, ?, ?, NOW())";
		PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		stmt.setInt(1, cart.getGoodsCode());
		stmt.setString(2, cart.getCustomerId());
		stmt.setInt(3, cart.getCartQuantity());
		row = stmt.executeUpdate();
		return row;
	}
	
	// 1.5) cart 중복체크
	public boolean cartListCk(Connection conn, Cart cart) throws Exception {
		boolean cartListCk = false;
		
		String sql = "SELECT goods_code"
					+ " FROM cart"
					+ " WHERE goods_code = ? AND customer_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, cart.getGoodsCode());
		stmt.setString(2, cart.getCustomerId());
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) { // 중복 장바구니상품일경우, true반환
			cartListCk = true;
		}
		
		return cartListCk;
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
	
	// 2-3) cartOne (1개의 물품 수량정보만 불러오기)
	public int cartOneQty(Connection conn, Cart cart) throws Exception {
		int cartOneQty = 0;
		
		String sql = "SELECT cart_quantity FROM cart"
					+ " WHERE goods_code = ? AND customer_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, cart.getGoodsCode());
		stmt.setString(2, cart.getCustomerId());
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			cartOneQty = rs.getInt("cart_quantity");
		}
		
		return cartOneQty;
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
	
	// 4-3) 장바구니 굿즈갯수
	public int ttlCntCart(Connection conn) throws Exception {
		int ttlCntCart = 0;
		
		return ttlCntCart;
	}
}
