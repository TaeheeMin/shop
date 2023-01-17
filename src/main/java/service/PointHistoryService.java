package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import dao.PointHistoryDao;
import dao.ReviewDao;
import util.DBUtil;
import vo.Customer;
import vo.PointHistory;


public class PointHistoryService {
	private PointHistoryDao pointHistoryDao;
	// 포인트 환불 
	public int refundPoint(int orderCode) {
		int row = 0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			pointHistoryDao = new PointHistoryDao();
			row = pointHistoryDao.refundPoint(conn, orderCode);
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
	
	
	// 1) 포인트 적립,사용 리스트
	public HashMap<String, Object> selectPoint(Customer loginCustomer) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			pointHistoryDao = new PointHistoryDao();
			map = pointHistoryDao.selectPoint(conn, loginCustomer);
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
		return map;
	}
	
	// 2) customer의 point 누적 덮어쓰기
	public int modifyCustomerPoint(Customer loginCustomer) {
		int row = 0;
		Connection conn = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		int point = 0;
		try {
			conn = DBUtil.getConnection();
			pointHistoryDao = new PointHistoryDao();
			map = pointHistoryDao.selectPoint(conn, loginCustomer);
			point = (int)(map.get("importPoint"))-(int)(map.get("exportPoint"))+100;
			row = pointHistoryDao.modifyCustomerPoint(conn, loginCustomer.getCustomerId(), point);
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
		return row;
	}
	
	// 2-1) customer 잔여포인트 불러오기
	public int remainCustomerPoint(Customer loginCustomer) {
		int row = 0;
		Connection conn = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		int point = 0;
		try {
			conn = DBUtil.getConnection();
			pointHistoryDao = new PointHistoryDao();
			map = pointHistoryDao.selectPoint(conn, loginCustomer);
			point = (int)(map.get("importPoint"))-(int)(map.get("exportPoint"))+100;
			row = pointHistoryDao.modifyCustomerPoint(conn, loginCustomer.getCustomerId(), point);
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
		return point;
	}
}
