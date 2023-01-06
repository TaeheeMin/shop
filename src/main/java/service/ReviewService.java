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
		int pointRow = 0;
		Connection conn = null;
		pointHistoryDao = new PointHistoryDao();
		reviewDao = new ReviewDao();
		try {
			conn = DBUtil.getConnection();
			row = reviewDao.addReview(conn, review);
			if(pointRow == 1) {
				System.out.println("리뷰작성성공");
				pointRow = pointHistoryDao.AddPoint(conn, review.getOrderCode());
				} else {
					System.out.println("리뷰작성실패");	
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
		return pointRow;
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
