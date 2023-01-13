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
	
	// 1.5) cart 중복체크
	public boolean cartListCk(Cart cart) {
		boolean cartListCk = false;
		Connection conn = null;
		CartDao cartDao = new CartDao();
		try {
			conn = DBUtil.getConnection();
			cartListCk = cartDao.cartListCk(conn, cart);
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
		return cartListCk;
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
	// 2-1) cartOne (1개 품목 수량정보)
	public int cartOneQty(int cartQuantity, Cart cart) {
		Connection conn = null;
		CartDao cartDao = new CartDao();
		int cartOneQty = 0;
		try {
			conn = DBUtil.getConnection();
			cartOneQty = cartDao.cartOneQty(conn, cart);
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
		return cartOneQty;
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
	
	//4-2) 주문시 카트 비우기
	public int clearCart(String customerId) {
		Connection conn = null;
		CartDao cartDao = new CartDao();
		int row = 0;
		try {
			conn = DBUtil.getConnection();
			row = cartDao.clearCart(conn, customerId);
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
