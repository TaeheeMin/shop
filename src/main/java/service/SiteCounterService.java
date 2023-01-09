package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.SiteCounterDao;
import util.DBUtil;

public class SiteCounterService {
	private SiteCounterDao siteCounterDao;
	
	// 접속자 추가 (첫 접속자)
	public void insertCount() {
		Connection conn = null;
		this.siteCounterDao = new SiteCounterDao();
		try {
			conn = DBUtil.getConnection();
			siteCounterDao.insertCount(conn);
			conn.commit();
		} catch(Exception e) {
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
	}
	
	// 접속자 업데이트 (첫 접속자 아닐때)
	public void updateCount() {
		Connection conn = null;
		this.siteCounterDao = new SiteCounterDao();
		try {
			conn = DBUtil.getConnection();
			siteCounterDao.updateCount(conn);
			conn.commit();
		} catch(Exception e) {
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
	}
	
	// 오늘 접속자 수 확인 select
	public int selectTodayCount() {
		int todayCount = 0;
		Connection conn = null;
		this.siteCounterDao = new SiteCounterDao();
		try {
			conn = DBUtil.getConnection();
			todayCount = siteCounterDao.selectTodayCount(conn);
			conn.commit();
		} catch(Exception e) {
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
		return todayCount;
	}
	
	// 오늘 접속자 수 확인 select
	public int selectTotalCount() {
		int totalCount = 0;
		Connection conn = null;
		this.siteCounterDao = new SiteCounterDao();
		try {
			conn = DBUtil.getConnection();
			totalCount = siteCounterDao.selectTotalCount(conn);
			conn.commit();
		} catch(Exception e) {
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
		return totalCount;
	}
}
