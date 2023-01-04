package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import vo.Orders;

public class OrdersDao {
		// OrdersList
	 public ArrayList<HashMap<String, Object>> selectOrdersList(Connection conn) throws Exception {
			ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
			String sql = "SELECT "
		               + "   FROM ";
		              
		      PreparedStatement stmt = conn.prepareStatement(sql);
		      ResultSet rs = stmt.executeQuery();
		      while(rs.next()) {
		         HashMap<String, Object> m = new HashMap<String, Object>();
		         
		         list.add(m);
		      }			
			return list;
		}
	
		// 장바구니 x 바로구매 
	   public int insertOrders(Connection conn, Orders orders) throws Exception {
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
}
