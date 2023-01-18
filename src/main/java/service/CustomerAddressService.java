package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.CustomerAddressDao;
import util.DBUtil;
import vo.Customer;
import vo.CustomerAddress;

public class CustomerAddressService {
	private CustomerAddressDao customerAddressDao;
	
	// 6) 회원 주소목록
	public ArrayList<CustomerAddress> myAddressList(CustomerAddress cusAddress) {
		ArrayList<CustomerAddress> list = null;
		Connection conn = null;
		this.customerAddressDao = new CustomerAddressDao();
		try {
			conn = DBUtil.getConnection();
			list = customerAddressDao.myAddressList(conn, cusAddress);
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	// 6-1) 배송 주소 불러오기 (1개)
	public String myAddress(int addressCode) {
		String myAddress = null;
		Connection conn = null;
		this.customerAddressDao = new CustomerAddressDao();
		try {
			conn = DBUtil.getConnection();
			myAddress = customerAddressDao.myAddress(conn, addressCode);
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return myAddress;
	}
	
	// 7) 회원 주소추가 (주소 총갯수와 트랜잭션 3개 초과 생성 불가하게끔)
	public int addMyAddress(CustomerAddress cusAddress) {
		int addMyAddress = 0;
		int ttlCntAddress = 0;
		Connection conn = null;
		this.customerAddressDao = new CustomerAddressDao();
		try {
			conn = DBUtil.getConnection();
			ttlCntAddress = customerAddressDao.ttlCntAddress(conn, cusAddress);
			System.out.println("회원님의 저장된 주소갯수 : "+ttlCntAddress);
			if(ttlCntAddress > 2) {
				System.out.println("주소는 최대 3개까지 저장 가능합니다");
			} else {
				System.out.println("주소 3개미만으로 추가 가능합니다");
				addMyAddress = customerAddressDao.addMyAddress(conn, cusAddress);
			}
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return addMyAddress;
	}
	
	// 8) 주소삭제 (하나삭제)
	public int removeAddress(CustomerAddress cusAddress) {
		int removeAddress = 0;
		Connection conn = null;
		this.customerAddressDao = new CustomerAddressDao();
		try {
			conn = DBUtil.getConnection();
			removeAddress = customerAddressDao.removeAddress(conn, cusAddress);
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return removeAddress;
	}
	
	// 9) 주소 총갯수
	public int ttlCntAddress(CustomerAddress cusAddress) {
		int ttlCntAddress = 0;
		Connection conn = null;
		this.customerAddressDao = new CustomerAddressDao();
		try {
			conn = DBUtil.getConnection();
			ttlCntAddress = customerAddressDao.ttlCntAddress(conn, cusAddress);
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ttlCntAddress;
	}
}
