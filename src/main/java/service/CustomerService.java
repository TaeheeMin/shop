package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.CustomerAddressDao;
import dao.CustomerDao;
import dao.PwHistoryDao;
import util.DBUtil;
import vo.Customer;
import vo.CustomerAddress;

public class CustomerService {
	private CustomerDao customerDao;
	private PwHistoryDao pwHistoryDao;
	private CustomerAddressDao customerAddressDao;
	
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
	
	// 4-1) 회원정보수정 비밀번호 이전내역과 중복체크
	public boolean pwHistoryCk(Customer customer) {
		boolean pwHistoryCk = false;
		Connection conn = null;
		this.pwHistoryDao = new PwHistoryDao();
		try {
			conn = DBUtil.getConnection();
			pwHistoryCk = pwHistoryDao.selectPwHistoryCk(conn, customer);
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
		return pwHistoryCk;
	}
	
	// 4-2) 회원정보 수정 (CustomerModifyController)
	public int modifyCustomerOne(Customer customerOne, Customer modifyCustomer) {
		int modifyCustomerOne = 0;
		Connection conn = null;
		this.customerDao = new CustomerDao();
		try {
			conn = DBUtil.getConnection();
			modifyCustomerOne = customerDao.modifyCustomerOne(conn, customerOne, modifyCustomer);
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
		return modifyCustomerOne;
	}
	
	// 5) 회원탈퇴 (탈퇴와 동시에 outid에 저장 - CustomerRemoveController) + pwHistory도 삭제
	public int removeCustomerOutid(Customer customerOne) {
		int removeCustomer = 0;
		int removeCustomerOutid = 0;
		int removePwHistory = 0;
		Connection conn = null;
		this.customerDao = new CustomerDao();
		this.pwHistoryDao = new PwHistoryDao();
		this.customerAddressDao = new CustomerAddressDao();
		
		try {
			conn = DBUtil.getConnection();
			// (1) 회원탈퇴 전, 회원 정보 삭제 진행 (주소 및 비밀번호이력)
			customerAddressDao.removeAllAddress(conn, customerOne);
			removePwHistory = pwHistoryDao.removePwHistoryByRemoveCustomer(conn, customerOne);
			// (2) 회원탈퇴 쿼리(dao) 진행
			removeCustomer = customerDao.removeCustomerOne(conn, customerOne);
			if(removeCustomer == 1) {
				System.out.println("탈퇴성공");
				// (3) 탈퇴한 아이디 -> outid 테이블에 추가
				removeCustomerOutid = customerDao.addOutid(conn, customerOne);
			} else {
				System.out.println("탈퇴실패");
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
		return removeCustomerOutid;
	}	
}
