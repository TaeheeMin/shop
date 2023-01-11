package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import dao.CartDao;
import dao.OrdersDao;
import dao.PointHistoryDao;
import dao.ReviewDao;
import util.DBUtil;
import vo.Customer;
import vo.Orders;
import vo.Review;


public class OrdersService {
	private OrdersDao ordersDao;
	private CartDao cartDao;
	private PointHistoryDao pointHistoryDao;
	// 주문 리스트 관리자용
	public ArrayList<HashMap<String, Object>> selectOrdersListByAdmin() {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			ordersDao = new OrdersDao();
			list = ordersDao.selectOrdersListByAdmin(conn);
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
	
	// 주문 리스트 고객용
	public ArrayList<HashMap<String, Object>> selectOrdersList(Customer loginCustomer) {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			ordersDao = new OrdersDao();
			list = ordersDao.selectOrdersList(conn, loginCustomer);
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
	// 주문 
	public int AddOrderPoint(Orders orders,ArrayList<HashMap<String, Object>> cartList, String customerId) {
		int result = 0;
		int clearCart = 0;
		int point = 0;
		Connection conn = null; 
		ordersDao = new OrdersDao();
		cartDao = new CartDao();
		pointHistoryDao = new PointHistoryDao();
			try {
				conn = DBUtil.getConnection();
				result = ordersDao.AddOrders(conn, orders, cartList);
				if(result == 1) {
					point = pointHistoryDao.addPointHistory(conn, customerId);
					clearCart = cartDao.clearCart(conn, customerId);
					//디버깅
					System.out.println("구매성공");
				}
				conn.commit();
			} catch (Exception e) {
				try {System.out.println("구매실패");
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
	public int addOrder(Orders orders,ArrayList<HashMap<String, Object>> cartList, String customerId) {
		int result = 0;
		int clearCart = 0;
		Connection conn = null; 
		ordersDao = new OrdersDao();
		cartDao = new CartDao();
		pointHistoryDao = new PointHistoryDao();
			try {
				conn = DBUtil.getConnection();
				result = ordersDao.AddOrders(conn, orders, cartList);
				if(result == 1) {
					clearCart = cartDao.clearCart(conn, customerId);
					//디버깅
					System.out.println("구매성공");
				}
				conn.commit();
			} catch (Exception e) {
				try {System.out.println("구매실패");
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
