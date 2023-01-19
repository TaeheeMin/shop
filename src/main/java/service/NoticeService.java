package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.*;
import util.DBUtil;
import vo.*;

public class NoticeService {
	private NoticeDao noticeDao;
	// 1) select
	// 1-0) count
	public int getNoticeListCount() {
		int row = 0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			this.noticeDao = new NoticeDao();
			row = noticeDao.selectNoticeCount(conn);
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
	// 1-1) notice list
	public ArrayList<Notice> getNoticeList(int currentPage, int rowPerPage) {
		ArrayList<Notice> notice = new ArrayList<Notice>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			noticeDao = new NoticeDao();
			// 페이징
			int beginRow = (currentPage - 1) * rowPerPage; // 시작 행
			// System.out.println("beginRow : " + beginRow);
			notice = noticeDao.selectNoticeList(conn, beginRow, rowPerPage);
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
		return notice;
	}
	
	// 1-2) notice one
	public Notice getNoticeOne(int noticeCode) {
		Connection conn = null;
		Notice notice = new Notice();
		try {
			conn = DBUtil.getConnection();
			noticeDao = new NoticeDao();
			notice = noticeDao.selectNoticeOne(conn, noticeCode);
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
		return notice;
	}

	
	// 2) notice add
	public int addNotice(Notice notice) {
		int row = 0;
		Connection conn = null;
		noticeDao = new NoticeDao();
		try {
			conn = DBUtil.getConnection();
			row = noticeDao.inesrtNotice(conn, notice);
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.out.println("예외발생");
			}
			e.printStackTrace();
			
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	
	// 3) modify
	public int modifyNotice(Notice notice) {
		int row = 0;
		Connection conn = null;
		noticeDao = new NoticeDao();;
		try {
			conn = DBUtil.getConnection();
			row = noticeDao.updateNotice(conn, notice);
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.out.println("예외발생");
			}
			e.printStackTrace();
			
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	
	// 4) remove
	public int removeNotice(int noticeCode) {
		int row = 0;
		Connection conn = null;
		noticeDao = new NoticeDao();
		try {
			conn = DBUtil.getConnection();
			row = noticeDao.deleteBoard(conn, noticeCode);
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	
}
