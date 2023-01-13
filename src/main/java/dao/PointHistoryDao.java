package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import vo.Customer;
import vo.Orders;
import vo.PointHistory;
import vo.Review;

public class PointHistoryDao {
	
	// 1) 포인트 추가 (적립)
	public int AddPoint(Connection conn, Review review) throws Exception {
		int pointRow = 0;	    
		String sql = "INSERT INTO point_history(order_code,point_kind,point,createdate) VALUES (?,'적립','500',NOW())";		      
	    PreparedStatement stmt = conn.prepareStatement(sql);	
	    stmt.setInt(1, review.getOrderCode());
	    pointRow = stmt.executeUpdate();
	    return pointRow;
	}
	
	// 포인트(사용)
	public int addPointHistory(Connection conn, String customerId, ArrayList<PointHistory> list) throws Exception {
		int row = 0;
		String sql = "INSERT INTO point_history("
				+ " order_code"
				+ ", point_kind"
				+ ", POINT"
				+ ", createdate"
				+ ") VALUES(?, '사용', (SELECT point FROM customer WHERE customer_id= ?), NOW())";
		for(int i = 0; i < list.size(); i++) {
			PreparedStatement stmt = conn.prepareStatement(sql);	
			stmt.setInt(1, list.get(i).getOrderCode());
			stmt.setString(2,customerId);
			row = stmt.executeUpdate();
		}
	    return row;
	}
	
	// 2) 아이디별 적립,사용 포인트총합 확인 
	public HashMap<String, Object> selectPoint(Connection conn, Customer loginCustomer) throws Exception {
		HashMap<String, Object> map = null; 
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
	    if(rs.next()) {
		       map = new HashMap<String, Object>();
		       map.put("importPoint", rs.getInt("importPoint"));
		       map.put("exportPoint", rs.getInt("exportPoint"));
		       map.put("createdate", rs.getString("createdate"));		
	    }
		return map;	
	}
	
	// 3) customer포인트에 누적포인트 덮어쓰기
	public int modifyCustomerPoint(Connection conn, Customer loginCustomer, int point) throws Exception {
		int row = 0;
		
		String sql = "UPDATE customer SET point = ? WHERE customer_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, point);
		stmt.setString(2, loginCustomer.getCustomerId());
		row = stmt.executeUpdate();
		
		return row;
	}
}
