package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import vo.PointHistory;

public class PotintHistoryDao {
		//포인트 추가
	   public int insertPoint(Connection conn, PointHistory pointHistory) throws Exception {
		      int row = 0;	    
		      String sql = "INSERT INTO point_history(review_memo) VALUES ()";	
		      
		      PreparedStatement stmt = conn.prepareStatement(sql);
		    	    
		      row = stmt.executeUpdate();
		      return row;
		   }

}
