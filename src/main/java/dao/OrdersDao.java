package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import vo.Cart;
import vo.Orders;

public class OrdersDao {
		// 주문목록
	 public ArrayList<HashMap<String, Object>> selectOrdersList(Connection conn) throws Exception {
			ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
			String sql = "SELECT "
					+ "	o.order_code orderCode"
					+ "	, g.goods_title goodsTitle"
					+ "	, gi.filename filename"
					+ "	, o.createdate createdate"
					+ "	, o.order_state orderState"
					+ "	, o.order_price orderPrice"
					+ "	FROM orders o INNER JOIN goods g ON o.goods_code = g.goods_code"
					+ "	INNER JOIN goods_img gi ON g.goods_code = gi.goods_code";		              
		      PreparedStatement stmt = conn.prepareStatement(sql);
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
		// 장바구니 구매 
		   public int AddCartOrders(Connection conn, Cart cart,Orders orders) throws Exception {
			      int row = 0;	    
			      String sql = "INSERT INTO orders(goods_code, customer_id, address_code , order_quantity ,order_price , order_state) VALUES (?,?,?,?,?,'결제')";		      
			      PreparedStatement stmt = conn.prepareStatement(sql);
			      stmt.setInt(1, orders.getGoodsCode());
			      stmt.setString(2, orders.getCustomerId());
			      stmt.setInt(3, orders.getAdrressCode());
			      stmt.setInt(4, orders.getOrderQuantity());
			      stmt.setInt(5, orders.getOrderPrice());		    
			      row = stmt.executeUpdate();
			      return row;
			   }
		   
		   
		
		// 장바구니 x 바로구매 
	   public int AddOrders(Connection conn, Orders orders) throws Exception {
		      int row = 0;	    
		      String sql = "INSERT INTO orders(goods_code, customer_id, address_code , order_quantity ,order_price , order_state) VALUES (?,?,?,?,?,'결제')";		      
		      PreparedStatement stmt = conn.prepareStatement(sql);
		      stmt.setInt(1, orders.getGoodsCode());
		      stmt.setString(2, orders.getCustomerId());
		      stmt.setInt(3, orders.getAdrressCode());
		      stmt.setInt(4, orders.getOrderQuantity());
		      stmt.setInt(5, orders.getOrderPrice());		    
		      row = stmt.executeUpdate();
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
		   String sql = "update orders SET order_state =? WHERE customer_id = ? AND order_code=?";     
		      PreparedStatement stmt = conn.prepareStatement(sql);
		      stmt.setString(1, orders.getOrderState());
		      stmt.setString(2, orders.getCustomerId());
		      stmt.setInt(3, orders.getOrderCode());
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
}
