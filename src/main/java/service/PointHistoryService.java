package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.PointHistoryDao;
import util.DBUtil;
import vo.PointHistory;


public class PointHistoryService {
	private PointHistoryDao pointHistoryDao;
	public int AddPoint(int orderCode) {
		int result = 0;
		Connection conn = null; 
		pointHistoryDao = new PointHistoryDao();		
			try {
				conn = DBUtil.getConnection();
				result = pointHistoryDao.AddPoint(conn, orderCode);
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
