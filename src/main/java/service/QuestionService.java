package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import dao.NoticeDao;
import dao.QuestionDao;
import util.DBUtil;
import vo.Notice;
import vo.Question;

public class QuestionService {
	private QuestionDao questionDao; 
	// 1) add 
	public int addQuestion(Question question) {
		int row = 0;
		Connection conn = null;
		this.questionDao = new QuestionDao();
		try {
			conn = DBUtil.getConnection();
			row = questionDao.insertQuestion(conn, question);
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
	// 2-1) 관리자
	public ArrayList<Question> getQuestionListByAdmin() {
		ArrayList<Question> list = new ArrayList<Question>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			this.questionDao = new QuestionDao();
			list = questionDao.selectQuestionByAdmin(conn);
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
	
	// 2-2) 회원용
	public ArrayList<HashMap<String, Object>> getQuestionListbyCustomer(String customerId) {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			this.questionDao = new QuestionDao();
			list = questionDao.selectQuestionBycustomer(conn, customerId);
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	// 2-3) 문의사항 one
	public ArrayList<HashMap<String, Object>> getQuestionOne(int questionCode) {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			this.questionDao = new QuestionDao();
			list = questionDao.selectQuestionOne(conn, questionCode);
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
	
	// 3) modify
	public int modifyQuestion(Question question) {
		int row = 0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			this.questionDao = new QuestionDao();
			row = questionDao.updateQuestion(conn, question);
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
	
	// 4) remove
	public int removeQuestion(int questionCode) {
		int row = 0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			this.questionDao = new QuestionDao();
			row = questionDao.deleteQuestion(conn, questionCode);
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
	
}
