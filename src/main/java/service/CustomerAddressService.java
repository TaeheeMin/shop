package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.CustomerAddressDao;
import util.DBUtil;
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
	
	// 7) 회원 주소추가
	public int addMyAddress(CustomerAddress cusAddress) {
		int addMyAddress = 0;
		Connection conn = null;
		this.customerAddressDao = new CustomerAddressDao();
		try {
			conn = DBUtil.getConnection();
			addMyAddress = customerAddressDao.addMyAddress(conn, cusAddress);
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
	
	// 8) 주소삭제
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
}
