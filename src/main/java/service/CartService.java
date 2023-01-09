package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import util.DBUtil;
import dao.*;
import vo.*;

public class CartService {
	// 1) add cart
	public int addCart(Cart cart) {
		Connection conn = null;
		CartDao cartDao = new CartDao();
		int row = 0;
		try {
			conn = DBUtil.getConnection();
			row = cartDao.insertCart(conn, cart);
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
		return row;
	}
	
	// 2) get cart
	public ArrayList<HashMap<String, Object>> getCartList(String customerId) {
		Connection conn = null;
		CartDao cartDao = new CartDao();
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		try {
			conn = DBUtil.getConnection();
			list = cartDao.cartList(conn, customerId);
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
	
	// 3) modify cart
	public int modifyCart(int cartQuantity, int goodsCode, String customerId) {
		Connection conn = null;
		CartDao cartDao = new CartDao();
		int row = 0;
		try {
			conn = DBUtil.getConnection();
			row = cartDao.updateCart(conn, cartQuantity, goodsCode, customerId);
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
		return row;
	}
	// 4) remove cart
	public int removeCart(Cart cart) {
		Connection conn = null;
		CartDao cartDao = new CartDao();
		int row = 0;
		try {
			conn = DBUtil.getConnection();
			row = cartDao.deleteCart(conn, cart);
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
		return row;
	}
}
