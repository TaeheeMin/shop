package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.CustomerDao;
import util.DBUtil;
import vo.Customer;
import vo.CustomerAddress;

public class CustomerService {
	private CustomerDao customerDao;
	
	// 1) 회원가입 - 중복체크 (CustomerAddController)
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
	
	// 1-1) 회원가입 - 가입 (CustomerAddController)
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
	
	// 2) 로그인 (CustomerLoginController)
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
	
	// 3) 회원정보 불러오기 (CustomerOneController)
	public Customer customerOne(Customer customer) {
		Customer customerOne = null;
		Connection conn = null;
		this.customerDao = new CustomerDao();
		
		try {
			conn = DBUtil.getConnection();
			customerOne = customerDao.customerOne(conn, customer);
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
		
		return customerOne;
	}
	
	// 4) 회원정보 수정
	
	// 5) 회원탈퇴 (탈퇴와 동시에 outid에 저장)
	
	// 6) 회원 주소목록
	public ArrayList<CustomerAddress> MyAddressList(CustomerAddress cusAddress) {
		ArrayList<CustomerAddress> list = null;
		Connection conn = null;
		this.customerDao = new CustomerDao();
		try {
			conn = DBUtil.getConnection();
			list = customerDao.MyAddressList(conn, cusAddress);
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
		this.customerDao = new CustomerDao();
		try {
			conn = DBUtil.getConnection();
			addMyAddress = customerDao.addMyAddress(conn, cusAddress);
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
		this.customerDao = new CustomerDao();
		try {
			conn = DBUtil.getConnection();
			removeAddress = customerDao.removeAddress(conn, cusAddress);
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
