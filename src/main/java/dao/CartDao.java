package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import vo.Cart;

public class CartDao {
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
}
