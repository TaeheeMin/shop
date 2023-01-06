package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import dao.OrdersDao;
import dao.ReviewDao;
import util.DBUtil;
import vo.Orders;
import vo.Review;


public class OrdersService {
	private OrdersDao ordersDao;
	// 주문 리스트
	public ArrayList<HashMap<String, Object>> selectOrdersList() {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			ordersDao = new OrdersDao();
			list = ordersDao.selectOrdersList(conn);
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	//주문상세 리스트 
	public ArrayList<HashMap<String, Object>> selectOrdersOneList(int orderCode) {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			ordersDao = new OrdersDao();
			list = ordersDao.selectOrdersOne(conn, orderCode);
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	//주문삭제 
	public int removeByCustomerOrder(int orderCode,Orders orders) {
		int result = 0;
		Connection conn = null; 
		ordersDao = new OrdersDao();		
			try {
				conn = DBUtil.getConnection();
				result = ordersDao.removeOrders(conn, orders);
				conn.commit();
			} catch (Exception e) {
				try {
					conn.rollback(); 
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}	
			return result;
		}
	//주문 상태변경(고객용) 
	public int modifyByCustomerOrders(int orderCode, Orders orders) {
		int result = 0;
		Connection conn = null;
		ordersDao = new OrdersDao();
			try {
				conn = DBUtil.getConnection();		
				result = ordersDao.modifyOrders(conn, orders);
				conn.commit(); 
			} catch (Exception e) {
				try {
					conn.rollback(); 
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					}
				}
			return result;	
		}
	//주문 상태변경(관리자용) 
	public int modifyByAdminOrders(int orderCode, Orders orders) {
		int result = 0;
		Connection conn = null;
		ordersDao = new OrdersDao();
			try {
				conn = DBUtil.getConnection();		
				result = ordersDao.modifyByAdminOrders(conn, orders);
				conn.commit(); 
			} catch (Exception e) {
				try {
					conn.rollback(); 
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					}
				}
			return result;	
		}
}
