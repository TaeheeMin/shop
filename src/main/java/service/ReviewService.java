package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.PointHistoryDao;
import dao.ReviewDao;
import util.DBUtil;
import vo.Orders;
import vo.Review;


public class ReviewService {
	private ReviewDao reviewDao;
	private PointHistoryDao pointHistoryDao;
	// 리뷰 리스트
	public ArrayList<Review> selectReivewList() {
		ArrayList<Review> review = new ArrayList<Review>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			reviewDao = new ReviewDao();
			review = reviewDao.selectReviewList(conn);
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
		return review;
	}
	
	// 리뷰추가 동시에 포인트적립
	public int addReivew(Review review) {
		int row = 0;		
		int addReviewRow = 0;
		Connection conn = null;		
		reviewDao = new ReviewDao();
		pointHistoryDao = new PointHistoryDao();
		try {
			conn = DBUtil.getConnection();
			addReviewRow = reviewDao.addReview(conn, review);	
			if(addReviewRow == 1) {
				System.out.println("[ReviewService]리뷰 추가완료");
				row = pointHistoryDao.AddPoint(conn, review);
				if(row == 1) {
					System.out.println("[ReviewService]포인트 적립 완료");
				} else {
					System.out.println("[ReviewService]포인트 적립 실패");
				}
			} else {
				System.out.println("[ReviewService]리뷰 추가실패");
			}
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
	// 리뷰삭제 관리자용
	public int removeByAdminReview(int orderCode) {
		int result = 0;
		Connection conn = null; 
		reviewDao = new ReviewDao();		
			try {
				conn = DBUtil.getConnection();
				result = reviewDao.removeReview(conn, orderCode);
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
	// 리뷰삭제 본인용 
	public int removeByCustomerReview(int orderCode,Orders orders) {
		int result = 0;
		Connection conn = null; 
		reviewDao = new ReviewDao();		
			try {
				conn = DBUtil.getConnection();
				result = reviewDao.removeReview(conn, orderCode, orders);
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
	//리뷰수정 
	public int modifyReview(Review review, Orders orders) {
		int result = 0;
		Connection conn = null;
		reviewDao = new ReviewDao();
			try {
				conn = DBUtil.getConnection();		
				result = reviewDao.modifyReview(conn, review, orders);
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
