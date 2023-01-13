package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import org.mariadb.jdbc.client.socket.impl.PacketReader;

import vo.Cart;
import vo.Customer;
import vo.Orders;

public class OrdersDao {
	// 월 상품별 취소수,금액 
	public ArrayList<HashMap<String, Object>> selectOrdersCancelByGoods(Connection conn) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		String sql = "SELECT"
				+ " YEAR(o.createdate) year"
				+ ", MONTH(o.createdate) month"
				+ ", g.goods_title goodsTitle"
				+ ", SUM(o.order_quantity) AS cancelCount"
				+ ", SUM(o.order_price) AS cancelPrice"
				+ "	FROM orders o INNER JOIN goods g"
				+ "	ON o.goods_code = g.goods_code"
				+ "	WHERE o.order_state = '취소'"
				+ "	GROUP BY g.goods_title";	              
	      PreparedStatement stmt = conn.prepareStatement(sql);	   
	      ResultSet rs = stmt.executeQuery();
	      while(rs.next()) {
	         HashMap<String, Object> m = new HashMap<String, Object>();
	         m.put("cancelCount", rs.getInt("cancelCount"));
	         m.put("cancelPrice", rs.getInt("cancelPrice"));
	         m.put("year", rs.getString("year")); 
	         m.put("month", rs.getString("month"));
	         m.put("goodsTitle", rs.getString("goodsTitle")); 
	         list.add(m);
	      }			
		return list;
	}
	
	
	// 월 상품별 판매수,금액 
	public ArrayList<HashMap<String, Object>> selectOrdersByGoods(Connection conn) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		String sql = "SELECT"
				+ " YEAR(o.createdate) year"
				+ ", MONTH(o.createdate) month"
				+ ", g.goods_title goodsTitle"
				+ ", SUM(o.order_quantity) AS orderCount"
				+ ", SUM(o.order_price) AS orderPrice"
				+ "	FROM orders o INNER JOIN goods g"
				+ "	ON o.goods_code = g.goods_code"
				+ "	GROUP BY g.goods_title";	              
	      PreparedStatement stmt = conn.prepareStatement(sql);	   
	      ResultSet rs = stmt.executeQuery();
	      while(rs.next()) {
	         HashMap<String, Object> m = new HashMap<String, Object>();
	         m.put("orderCount", rs.getInt("orderCount"));
	         m.put("orderPrice", rs.getInt("orderPrice"));
	         m.put("year", rs.getString("year")); 
	         m.put("month", rs.getString("month")); 
	         m.put("goodsTitle", rs.getString("goodsTitle"));
	         list.add(m);
	      }			
		return list;
	}
	
	// 월별 총주문 건수
	public ArrayList<HashMap<String, Object>> selectOrdersCountByMonth(Connection conn) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		String sql = "SELECT YEAR(createdate) year,MONTH(createdate) month, COUNT(*) AS count FROM orders GROUP BY MONTH(createdate)";	              
	      PreparedStatement stmt = conn.prepareStatement(sql);	   
	      ResultSet rs = stmt.executeQuery();
	      while(rs.next()) {
	         HashMap<String, Object> m = new HashMap<String, Object>();
	         m.put("count", rs.getInt("count"));
	         m.put("year", rs.getString("year")); 
	         m.put("month", rs.getString("month")); 
	         list.add(m);
	      }			
		return list;
	}
	
	
	// 월별 총 취소 list 
	public ArrayList<HashMap<String, Object>> selectOrdersCancelListByMonth(Connection conn) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		String sql = "SELECT"
				+ " YEAR(createdate) year"
				+ ", MONTH(createdate) month"
				+ ", SUM(if(order_state='취소',order_price, NULL)) AS cancelPrice"
				+ "	FROM orders"
				+ "	GROUP BY MONTH(createdate)";	              
	      PreparedStatement stmt = conn.prepareStatement(sql);	   
	      ResultSet rs = stmt.executeQuery();
	      while(rs.next()) {
	         HashMap<String, Object> m = new HashMap<String, Object>();
	         m.put("cancelPrice", rs.getInt("cancelPrice"));
	         m.put("year", rs.getString("year"));
	         m.put("month", rs.getString("month"));  
	         list.add(m);
	      }			
		return list;
	}	
	
	// 월별 총 매출 list
	public ArrayList<HashMap<String, Object>> selectOrdersListByMonth(Connection conn) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		String sql = "SELECT"
				+ " YEAR(createdate) year"
				+ ", MONTH(createdate) month"
				+ ", SUM(order_price) AS orderPrice"
				+ "	FROM orders"
				+ "	GROUP BY MONTH(createdate)";	              
	      PreparedStatement stmt = conn.prepareStatement(sql);	   
	      ResultSet rs = stmt.executeQuery();
	      while(rs.next()) {
	         HashMap<String, Object> m = new HashMap<String, Object>();
	         m.put("orderPrice", rs.getInt("orderPrice"));
	         m.put("year", rs.getString("year"));
	         m.put("month", rs.getString("month"));  
	         list.add(m);
	      }			
		return list;
	}
	// 년도별 총 취소금액 List
	public ArrayList<HashMap<String, Object>> selectOrdersCancelListByYear(Connection conn) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		String sql = "SELECT"
				+ "YEAR(createdate) year"
				+ ", SUM(if(order_state='취소',order_price, NULL)) AS cancelPrice"
				+ "	FROM orders";	              
	      PreparedStatement stmt = conn.prepareStatement(sql);	   
	      ResultSet rs = stmt.executeQuery();
	      while(rs.next()) {
	         HashMap<String, Object> m = new HashMap<String, Object>();
	         m.put("cancelPrice", rs.getInt("cancelPrice"));
	         m.put("year", rs.getString("year"));        
	         list.add(m);
	      }			
		return list;
	}	
	
	// 년도별 총 매출액 List 
	public ArrayList<HashMap<String, Object>> selectOrdersListByYear(Connection conn) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		String sql = "SELECT"
				+ "YEAR(createdate) year"
				+ ", SUM(order_price) AS orderPrice"
				+ "	FROM orders";	              
	      PreparedStatement stmt = conn.prepareStatement(sql);	   
	      ResultSet rs = stmt.executeQuery();
	      while(rs.next()) {
	         HashMap<String, Object> m = new HashMap<String, Object>();
	         m.put("orderPrice", rs.getInt("orderPrice"));
	         m.put("year", rs.getString("year"));        
	         list.add(m);
	      }			
		return list;
	}
	
	
	// 주문목록 페이징
	public int selectOrdersCount(Connection conn) throws Exception {
		int row = 0;
		String sql = "SELECT COUNT(*) FROM orders";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			row = rs.getInt("COUNT(*)");
		}
		return row;
	}

	
	// 주문목록 관리자용 
 public ArrayList<HashMap<String, Object>> selectOrdersListByAdmin(Connection conn, int beginRow, int rowPerPage,String search, String word, String category) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		String sql = "SELECT "
				+ "	o.order_code orderCode"
				+ "	, g.goods_title goodsTitle"
				+ "	, gi.filename filename"
				+ "	, o.createdate createdate"
				+ "	, o.order_state orderState"
				+ "	, o.order_price orderPrice"								
				+ "	FROM orders o INNER JOIN goods g ON o.goods_code = g.goods_code"
				+ "	INNER JOIN goods_img gi ON g.goods_code = gi.goods_code"
				+ " WHERE g.goods_title LIKE ?"
				+ " AND o.order_state LIKE ?"
				+ "	LIMIT ?,?";
	              
	      PreparedStatement stmt = conn.prepareStatement(sql);
	      stmt.setString(1, "%"+ word +"%");
	      stmt.setString(2, "%"+ category +"%");
	      stmt.setInt(3, beginRow);
	      stmt.setInt(4, rowPerPage);
	      ResultSet rs = stmt.executeQuery();
	      while(rs.next()) {
	         HashMap<String, Object> m = new HashMap<String, Object>();
	         m.put("orderCode", rs.getInt("orderCode"));
	         m.put("goodsTitle", rs.getString("goodsTitle"));
	         m.put("filename", rs.getString("filename"));
	         m.put("createdate", rs.getString("createdate"));
	         m.put("orderState", rs.getString("orderState"));
	         m.put("orderPrice", rs.getString("orderPrice"));	         
	         list.add(m);
	      }			
		return list;
	}
		// 주문목록 본인용 
	 public ArrayList<HashMap<String, Object>> selectOrdersList(Connection conn, int beginRow, int rowPerPage, Customer loginCustomer) throws Exception {
			ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
			String sql = "SELECT "
					+ "	o.order_code orderCode"
					+ "	, g.goods_title goodsTitle"
					+ "	, gi.filename filename"
					+ "	, o.createdate createdate"
					+ "	, o.order_state orderState"
					+ "	, o.order_price orderPrice"
					+ "	FROM orders o INNER JOIN goods g ON o.goods_code = g.goods_code"
					+ "	INNER JOIN goods_img gi ON g.goods_code = gi.goods_code"
					+ "	WHERE o.customer_id = ?"
					+ " LIMIT ?,?";		              
		      PreparedStatement stmt = conn.prepareStatement(sql);
		      stmt.setString(1, loginCustomer.getCustomerId());
		      stmt.setInt(2, beginRow);
		      stmt.setInt(3, rowPerPage);
		      ResultSet rs = stmt.executeQuery();
		      while(rs.next()) {
		         HashMap<String, Object> m = new HashMap<String, Object>();
		         m.put("orderCode", rs.getInt("orderCode"));
		         m.put("goodsTitle", rs.getString("goodsTitle"));
		         m.put("filename", rs.getString("filename"));
		         m.put("createdate", rs.getString("createdate"));
		         m.put("orderState", rs.getString("orderState"));
		         m.put("orderPrice", rs.getString("orderPrice"));
		         list.add(m);
		      }			
			return list;
		}
	 	//주문내역 상세보기 	
		public ArrayList<HashMap<String, Object>> selectOrdersOne(Connection conn, int orderCode) throws Exception {
			ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
			String sql = "SELECT "
					+ "o.order_code orderCode"
					+ ",o.order_quantity orderQuantity"
					+ ",o.order_price orderPrice"
					+ ",o.order_state orderState"
					+ ",o.createdate createdate"
					+ ",o.customer_id customerId"
					+ ",g.goods_title goodsTitle"
					+ ",g.goods_price goodsPrice"
					+ ",cu.customer_name customerName"
					+ ",cu.customer_phone customerPhone"
					+ ",ca.address address"
					+ ",gi.filename filename"
					+ "	FROM goods g inner JOIN orders o"
					+ "	ON g.goods_code = o.goods_code"
					+ "	RIGHT JOIN goods_img gi"
					+ "	ON g.goods_code = gi.goods_code"
					+ "	inner JOIN customer cu"
					+ "	ON o.customer_id = cu.customer_id"
					+ "	RIGHT JOIN customer_address ca"
					+ "	ON o.address_code = ca.address_code"
					+ "	WHERE order_code = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, orderCode);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				HashMap<String, Object> m = new HashMap<String, Object>();
				m.put("orderCode", rs.getInt("orderCode"));
				m.put("orderQuantity", rs.getInt("orderQuantity"));
				m.put("orderPrice", rs.getInt("orderPrice"));
				m.put("orderState", rs.getString("orderState"));
				m.put("createdate", rs.getString("createdate"));
				m.put("goodsTitle", rs.getString("goodsTitle"));
				m.put("goodsPrice", rs.getInt("goodsPrice"));
				m.put("customerName", rs.getString("customerName"));
				m.put("customerPhone", rs.getString("customerPhone"));
				m.put("address", rs.getString("address"));
				m.put("filename", rs.getString("filename"));
				m.put("customerId", rs.getString("customerId"));
				list.add(m);
			}
			return list;
			
		}	
		
		// 구매 
	   public int AddOrders(Connection conn, Orders orders, ArrayList<HashMap<String, Object>> cartList) throws Exception {
	      int row = 0;
	      for(HashMap<String, Object> c : cartList) {
		      String sql = "INSERT INTO orders(goods_code, customer_id, address_code , order_quantity ,order_price , order_state) VALUES (?,?,?,?,?,'결제')";		      
		      PreparedStatement stmt = conn.prepareStatement(sql);
		      stmt.setInt(1, (int) c.get("goodsCode"));
		      stmt.setString(2, orders.getCustomerId());
		      stmt.setInt(3, orders.getAdrressCode());
		      stmt.setInt(4, (int) c.get("cartQuantity"));
		      stmt.setInt(5, (int) c.get("goodsPrice"));		    
		      row = stmt.executeUpdate();
	      }
	      return row;
	   }
	   // 주문내역 목록에서 삭제  
	   public int removeOrders(Connection conn, Orders orders)throws Exception {
		   int row = 0;
		   String sql = "DELETE FROM orders WHERE order_code = ? AND customer_id = ?";      
		      PreparedStatement stmt = conn.prepareStatement(sql);
		      stmt.setInt(1, orders.getOrderCode());
		      stmt.setString(2, orders.getCustomerId());
		      row = stmt.executeUpdate();
		      return row;
	   }
	   // 주문상태 수정 (고객용)
	   public int modifyOrders(Connection conn, Orders orders)throws Exception {
		   int row = 0;
		   String sql = "update orders SET order_state =? WHERE order_code=?";     
		      PreparedStatement stmt = conn.prepareStatement(sql);
		      stmt.setString(1, orders.getOrderState());
		      stmt.setInt(2, orders.getOrderCode());
		      row = stmt.executeUpdate();
		      return row;
	   } 
	   // 주문상태 수정 (관리자용)
	   public int modifyByAdminOrders(Connection conn, Orders orders)throws Exception {
		   int row = 0;
		   String sql = "update orders SET order_state =? WHERE order_code=?";     
		      PreparedStatement stmt = conn.prepareStatement(sql);
		      stmt.setString(1, orders.getOrderState());
		      stmt.setInt(2, orders.getOrderCode());
		      row = stmt.executeUpdate();
		      return row;
	   }  
	   
	   // 포인트 사용시 order Price수정
	   public int updateOrderPrice(Connection conn, Orders orders, int point, int orderCode) throws Exception {
		   int row = 0;
		   String sql = "UPDATE orders SET order_price = ? where order_code = ?";
		   PreparedStatement stmt = conn.prepareStatement(sql);
		   stmt.setInt(1, orders.getOrderPrice()-point);
		   stmt.setInt(2, orderCode);
		   row = stmt.executeUpdate();
		  return row;
	   }
}
