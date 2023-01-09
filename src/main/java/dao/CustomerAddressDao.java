package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.CustomerAddress;

public class CustomerAddressDao {
	// 6) 회원주소목록 (내주소)
	public ArrayList<CustomerAddress> myAddressList(Connection conn, CustomerAddress cusAddress) throws Exception {
		ArrayList<CustomerAddress> list = new ArrayList<CustomerAddress>();
		
		// 주소 불러오기
		String sql = "SELECT address_code, customer_id, address FROM customer_address WHERE customer_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, cusAddress.getCustomerId());
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			CustomerAddress a = new CustomerAddress();
			a.setAddressCode(rs.getInt("address_code"));
			a.setCustomerId(rs.getString("customer_id"));
			a.setAddress(rs.getString("address"));
			list.add(a);
		}
		
		return list;
	}
	
	// 7) 주소추가
	public int addMyAddress(Connection conn, CustomerAddress cusAddress) throws Exception {
		int addMyAddress = 0;
		
		String sql = "INSERT INTO customer_address("
				+ "customer_id, address, createdate"
				+ ") VALUES (?,?,NOW())"; // point는 기본값 100포인트 부여
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, cusAddress.getCustomerId());
		stmt.setString(2, cusAddress.getAddress());
		
		addMyAddress = stmt.executeUpdate();
		
		return addMyAddress;
	}
	
	// 8) 주소삭제
	public int removeAddress(Connection conn, CustomerAddress cusAddress) throws Exception {
		int removeAddress = 0;
		
		String sql = "DELETE from customer_address WHERE customer_id = ? AND address = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, cusAddress.getCustomerId());
		stmt.setString(2, cusAddress.getAddress());
		
		removeAddress = stmt.executeUpdate();
				
		return removeAddress;
	}
}
