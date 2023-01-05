package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Customer;
import vo.CustomerAddress;

public class CustomerDao {
	// 1-1) 회원가입 - 중복체크
	public boolean customerSigninCkId(Connection conn, Customer customer) throws Exception {
		boolean customerAddCheckId = false;
		
		// 중복확인 쿼리 진행 (탈퇴한 아이디와, customerId Union)
		String sql = "SELECT t.checkId"
					+ " FROM"
					+ "		(SELECT customer_id checkId FROM customer"
					+ "		union"
					+ "		SELECT id checkId FROM outid)t"
					+ " WHERE t.checkId = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, customer.getCustomerId());
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) { // ID 중복값이 존재할 경우, true 반환
			customerAddCheckId = true;
		}
		
		return customerAddCheckId;
	}
	
	// 1-2) 회원가입 - 가입
	public int customerSignin(Connection conn, Customer customer) throws Exception {
		int customerSignin = 0;
		
		// 가입쿼리 진행
		String sql = "INSERT INTO customer("
					+ "customer_id, customer_pw, customer_name, customer_phone, point, createdate"
					+ ") VALUES (?,PASSWORD(?),?,?,100,NOW())"; // point는 기본값 100포인트 부여
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, customer.getCustomerId());
		stmt.setString(2, customer.getCustomerPw());
		stmt.setString(3, customer.getCustomerName());
		stmt.setString(4, customer.getCustomerPhone());
		
		customerSignin = stmt.executeUpdate();

		return customerSignin;
	}
	
	// 2) 로그인
	public Customer customerLogin(Connection conn, Customer customer) throws Exception {
		Customer loginCustomer = null;
		
		// 로그인쿼리 진행
		String sql = "SELECT * FROM customer WHERE customer_id = ? AND customer_pw = PASSWORD(?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, customer.getCustomerId());
		stmt.setString(2, customer.getCustomerPw());
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			loginCustomer = new Customer();
			loginCustomer.setCustomerId(rs.getString("customer_id"));
			loginCustomer.setCustomerName(rs.getString("customer_name"));
			loginCustomer.setCustomerPhone(rs.getString("point"));
		}
		
		return loginCustomer;
	}
	
	// 3) 회원정보 불러오기 (주소포함)
	public Customer customerOne(Connection conn, Customer customer) throws Exception {
		Customer customerOne = null;
		
		// 내정보 불러오기 (customerAddress와 조인)
		String sql = "SELECT * FROM customer WHERE customer_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, customer.getCustomerId());
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			customerOne = new Customer();
			customerOne.setCustomerId(rs.getString("customer_id"));
			customerOne.setCustomerPw(rs.getString("customer_pw")); // 회원정보수정 및 탈퇴 시 패스워드 대조하기 위함
			customerOne.setCustomerName(rs.getString("customer_name"));
			customerOne.setCustomerPhone(rs.getString("customer_phone"));
			customerOne.setPoint(rs.getInt("point"));
			customerOne.setCreatedate(rs.getString("createdate"));
		}
		return customerOne;
	}
	
	// 4) 회원정보 수정
	public int modifyCustomerOne(Connection conn, Customer customerOne, Customer modifyCustomer) throws Exception {
		int modifyCustomerOne = 0;
		
		// 정보수정 쿼리 (비밀번호 및 이름 및 휴대폰번호 변경가능)
		String sql = "UPDATE customer"
					+ " SET customer_pw = PASSWORD(?), customer_name = ?, customer_phone = ?"
					+ " WHERE customer_id = ? AND customer_pw = PASSWORD(?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, modifyCustomer.getCustomerPw());
		stmt.setString(2, modifyCustomer.getCustomerName());
		stmt.setString(3, modifyCustomer.getCustomerPhone());
		stmt.setString(4, customerOne.getCustomerId());
		stmt.setString(5, customerOne.getCustomerPw());
		
		return modifyCustomerOne;
	}
	
	// 4-1) 비밀번호 수정에 따른 pw_history로 넘기기
	
	// 5) 회원탈퇴
	public int removeCustomerOne(Connection conn, Customer customerOne) throws Exception {
		int removeCustomerOne = 0;
		
		String sql = "DELETE from customer WHERE customer_id = ? AND customer_pw = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, customerOne.getCustomerId());
		stmt.setString(2, customerOne.getCustomerPw());
		
		removeCustomerOne = stmt.executeUpdate();
				
		return removeCustomerOne;
	}
	
	// 5-1) 탈퇴한 회원 outid 테이블에 데이터 저장하기
	public int addOutid(Connection conn, String outid) throws Exception {
		int addOutid = 0;
		
		String sql = "INSERT INTO outid("
				+ "id, createdate"
				+ ") VALUES (?,NOW())"; // point는 기본값 100포인트 부여
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, outid);
	
		addOutid = stmt.executeUpdate();
		
		return addOutid;
	}
	
	// 6) 회원주소목록 (내주소)
	public ArrayList<CustomerAddress> myAddressList(Connection conn, CustomerAddress cusAddress) throws Exception {
		ArrayList<CustomerAddress> list = new ArrayList<CustomerAddress>();
		
		// 주소 불러오기
		String sql = "SELECT customer_id, address FROM customer_address WHERE customer_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, cusAddress.getCustomerId());
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			CustomerAddress a = new CustomerAddress();
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
