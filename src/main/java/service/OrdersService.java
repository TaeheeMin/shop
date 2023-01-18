package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import dao.CartDao;
import dao.GoodsDao;
import dao.OrdersDao;
import dao.PointHistoryDao;
import dao.ReviewDao;
import util.DBUtil;
import vo.Customer;
import vo.Orders;
import vo.PointHistory;
import vo.Review;


public class OrdersService {
	private OrdersDao ordersDao;
	private CartDao cartDao;
	private PointHistoryDao pointHistoryDao;
	// 년도별 총 매출액,취소액 List 
	public ArrayList<HashMap<String, Object>> selectOrdersListByYear() {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		Connection conn = null;
		try {			
			conn = DBUtil.getConnection();
			ordersDao = new OrdersDao();
			list = ordersDao.selectOrdersListByYear(conn);
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

	
	// 월별 총 매출,취소 list
	public ArrayList<HashMap<String, Object>> selectOrdersListByMonth() {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		Connection conn = null;
		try {			
			conn = DBUtil.getConnection();
			ordersDao = new OrdersDao();
			list = ordersDao.selectOrdersListByMonth(conn);
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

	
	// 월별 총주문,취소 건수
	public ArrayList<HashMap<String, Object>> selectOrdersCountByMonth() {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		Connection conn = null;
		try {			
			conn = DBUtil.getConnection();
			ordersDao = new OrdersDao();
			list = ordersDao.selectOrdersCountByMonth(conn);
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
	// 월 상품별 판매수,금액 
	public ArrayList<HashMap<String, Object>> selectOrdersByGoods() {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		Connection conn = null;
		try {			
			conn = DBUtil.getConnection();
			ordersDao = new OrdersDao();
			list = ordersDao.selectOrdersByGoods(conn);
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
	//월 상품별 취소수,금액 List
	public ArrayList<HashMap<String, Object>> selectOrdersCancelByGoods() {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		Connection conn = null;
		try {			
			conn = DBUtil.getConnection();
			ordersDao = new OrdersDao();
			list = ordersDao.selectOrdersCancelByGoods(conn);
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
	
	
	// 주문 리스트 관리자용
	public ArrayList<HashMap<String, Object>> selectOrdersListByAdmin(int currentPage, int rowPerPage, String search, String word, String category) {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		Connection conn = null;
		try {
			int beginRow = (currentPage - 1) * rowPerPage; // 시작 행
			conn = DBUtil.getConnection();
			ordersDao = new OrdersDao();
			list = ordersDao.selectOrdersListByAdmin(conn, beginRow, rowPerPage, search, word, category);
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
	
	// 주문 리스트 페이징
	public int getOrdersListCount() {
		int row = 0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			ordersDao = new OrdersDao();
			row = ordersDao.selectOrdersCount(conn);
			conn.commit(); // DBUtil setAutoCommit false설정
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
		return row;
	}
	
	// 주문 리스트 고객용
	public ArrayList<HashMap<String, Object>> selectOrdersList(Customer loginCustomer,int currentPage, int rowPerPage) {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			int beginRow = (currentPage - 1) * rowPerPage; // 시작 행
			ordersDao = new OrdersDao();
			list = ordersDao.selectOrdersList(conn, beginRow, rowPerPage, loginCustomer);
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
	public ArrayList<HashMap<String, Object>> completeOrdersList(Customer loginCustomer, int orderLength) {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			ordersDao = new OrdersDao();
			list = ordersDao.completeOrderList(conn, loginCustomer, orderLength);
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
	
	// 1) 주문
	// 1-1) 포인트 사용 주문
	public ArrayList<Orders> AddOrderPoint(Orders orders, String customerId, int sharePoint, int point) {
		int result = 0;
		Connection conn = null; 
		ordersDao = new OrdersDao();
		cartDao = new CartDao();
		pointHistoryDao = new PointHistoryDao();
		ArrayList<Orders> list = new ArrayList<Orders>();
		try {
			conn = DBUtil.getConnection();
			//order 추가하면서 oderCode 받아오기
			list = ordersDao.AddOrdersPoint(conn, orders);
			// pointHistory 추가
			result = pointHistoryDao.addPointHistory(conn, list, sharePoint);
			System.out.println("포인트히스토리 결과 : " + result);
			// 고객 정보 수정
			//result = pointHistoryDao.modifyCustomerPoint(conn, customerId, point);
			//System.out.println("고객 포인트 수정 결과 : " + result);
			// 장바구니 비우기
			cartDao.clearCart(conn, customerId);
			//디버깅
			if(result ==1) {
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
		return list;
	}
	
	// 1-2) 포인트 미사용 주문
	public ArrayList<Orders> addOrder(Orders orders,ArrayList<HashMap<String, Object>> cartList, String customerId) {
		ArrayList<Orders> list = new ArrayList<Orders>();
		list = null;
		Connection conn = null; 
		ordersDao = new OrdersDao();
		cartDao = new CartDao();
		pointHistoryDao = new PointHistoryDao();
		System.out.println("[orderService 진입]");
			try {
				conn = DBUtil.getConnection();
				list = ordersDao.AddOrders(conn, orders, cartList);
				if(list != null) {
					// 장바구니 비우기
					int clearCart = cartDao.clearCart(conn, customerId);
					System.out.println(clearCart);
					System.out.println("구매성공 -> 장바구니를 비웁니다");
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
			return list;
		}
}
