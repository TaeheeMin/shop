package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import vo.PointHistory;

public class PointHistoryDao {
		//포인트 추가
	   public int AddPoint(Connection conn, int orderCode) throws Exception {
		      int pointRow = 0;	    
		      String sql = "INSERT INTO point_history(order_code,point_kind,point) VALUES (?,'적립',500)";		      
		      PreparedStatement stmt = conn.prepareStatement(sql);		    	    
		      pointRow = stmt.executeUpdate();
		      stmt.setInt(1, orderCode);
		      return pointRow;
		   }

}
