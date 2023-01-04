package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import vo.Review;

public class ReviewDao {
		// 리뷰추가 
	   public int insertReview(Connection conn, Review review) throws Exception {
		      int row = 0;	    
		      String sql = "INSERT INTO review(review_memo) VALUES (?)";		      
		      PreparedStatement stmt = conn.prepareStatement(sql);
		      stmt.setString(1, review.getReviewMemo());		    
		      row = stmt.executeUpdate();
		      return row;
		   }
	   // 리뷰삭제
	   public int removeReview(Connection conn, int orderCode) throws Exception {
		   int row = 0;
		   String sql = "DELETE FROM review WHERE order_code = ?";	      
		      PreparedStatement stmt = conn.prepareStatement(sql);
		      stmt.setInt(1, orderCode);		    
		      row = stmt.executeUpdate();
		      return row;
	   }
	   //리뷰수정 
	   public int modifyReview(Connection conn, int orderCode, String reviewMemo) throws Exception {
		   int row = 0;
		   String sql = "UPDATE review SET review_memo = ? WHERE order_code = ?";
		      PreparedStatement stmt = conn.prepareStatement(sql);
		      stmt.setString(1, reviewMemo);
		      stmt.setInt(2, orderCode);		    
		      row = stmt.executeUpdate();
		      return row;
	   }
}
