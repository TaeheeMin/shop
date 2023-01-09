package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import dao.PointHistoryDao;
import util.DBUtil;
import vo.Customer;
import vo.PointHistory;


public class PointHistoryService {
	private PointHistoryDao pointHistoryDao;
	// 포인트 적립,사용 리스트
	public ArrayList<HashMap<String, Object>> selectPoint(Customer loginCustomer) {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			pointHistoryDao = new PointHistoryDao();
			list = pointHistoryDao.selectPoint(conn, loginCustomer);
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
	

}
