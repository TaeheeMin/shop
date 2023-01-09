package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import vo.PointHistory;
import vo.Review;

public class PointHistoryDao {
	//포인트 추가
	public int AddPoint(Connection conn, Review review) throws Exception {
		int pointRow = 0;	    
		String sql = "INSERT INTO point_history(order_code,point_kind,point,createdate) VALUES (?,'적립','500',NOW())";		      
	    PreparedStatement stmt = conn.prepareStatement(sql);	
	    stmt.setInt(1, review.getOrderCode());
	    pointRow = stmt.executeUpdate();
	    return pointRow;
	}
	
}
