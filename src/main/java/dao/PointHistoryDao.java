package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import vo.Customer;
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
	// 아이디별 적립,사용 포인트 확인 
	public ArrayList<HashMap<String, Object>> selectPoint(Connection conn, Customer loginCustomer) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>(); 
		String sql = "SELECT"
				+ "	ifnull(SUM(IF(ph.point_kind = '적립', ph.point, NULL)), 0) importPoint"
				+ ", ifnull(sum(IF(ph.point_kind = '사용', ph.point, NULL)), 0) exportPoint"
				+ ", ph.createdate createdate"
				+ "	FROM point_history ph INNER JOIN orders o"
				+ "	ON ph.order_code = o.order_code"
				+ "	INNER JOIN customer cu"
				+ "	ON o.customer_id = cu.customer_id"
				+ "	WHERE cu.customer_id = ?";		      
	    PreparedStatement stmt = conn.prepareStatement(sql);	
	    stmt.setString(1, loginCustomer.getCustomerId());
	    ResultSet rs = stmt.executeQuery();
	    while(rs.next()) {
		       HashMap<String, Object> m = new HashMap<String, Object>();
		       m.put("importPoint", rs.getInt("importPoint"));
		       m.put("exportPoint", rs.getInt("exportPoint"));
		       m.put("createdate", rs.getString("createdate"));
		       list.add(m);
		
	    }
		return list;	
	}
	
	
}
