package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.CustomerDao;
import util.DBUtil;
import vo.Customer;

public class CustomerService {
	private CustomerDao customerDao;
	
	// 1) 회원가입 - 중복체크
	public boolean customerSigninCkId(Customer customer) {
		boolean customerSigninCkId = false;
		Connection conn = null;
		this.customerDao = new CustomerDao();
		try {
			conn = DBUtil.getConnection();
			customerSigninCkId = customerDao.customerSigninCkId(conn, customer);
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
		return customerSigninCkId;
	}
	
	// 1-1) 회원가입 - 가입
	public int customerSignin(Customer customer) {
		int customerSignin = 0;
		Connection conn = null;
		this.customerDao = new CustomerDao();
		try {
			conn = DBUtil.getConnection();
			customerSignin = customerDao.customerSignin(conn, customer);
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
		return customerSignin;
	}
	
	// 2) 로그인
	public Customer customerLogin(Customer customer) {
		Customer loginCustomer = null;
		Connection conn = null;
		this.customerDao = new CustomerDao();
		try {
			conn = DBUtil.getConnection();
			loginCustomer = customerDao.customerLogin(conn, customer);
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
		return loginCustomer;
	}
	
}
