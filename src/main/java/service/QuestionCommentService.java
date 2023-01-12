package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import dao.QuestionCommentDao;
import dao.QuestionDao;
import util.DBUtil;
import vo.*;

public class QuestionCommentService {
	private QuestionCommentDao questionCommentDao;
	// 1) add
	public int addQuestionComment(QuestionComment questionComment) {
		int row = 0;
		Connection conn = null;
		this.questionCommentDao = new QuestionCommentDao();
		try {
			conn = DBUtil.getConnection();
			row = questionCommentDao.isertComment(conn, questionComment);
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
	// 2) get
	public ArrayList<HashMap<String, Object>> getQuestionComment(int questionCode) {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();;
		Connection conn = null;
		this.questionCommentDao = new QuestionCommentDao();
		try {
			conn = DBUtil.getConnection();
			list = questionCommentDao.selectQuestionComment(conn, questionCode);
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
		return list;
	}
	
	// 3) list
	public ArrayList<HashMap<String, Object>> getAllQuestionComment() {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();;
		Connection conn = null;
		this.questionCommentDao = new QuestionCommentDao();
		try {
			conn = DBUtil.getConnection();
			list = questionCommentDao.selectAllQuestionComment(conn);
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
		return list;
	}
}
