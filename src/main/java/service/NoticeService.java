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
	// 1-1) notice list
	public Notice getNoticeList() {
		Notice notice = new Notice();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			noticeDao = new NoticeDao();
			notice = noticeDao.selectNoticeList(conn);
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
	
	// 1-2) notice add
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
}
