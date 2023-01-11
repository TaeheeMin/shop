package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import dao.CustomerDao;
import dao.EmpDao;
import util.DBUtil;
import vo.Emp;

public class EmpService {
	private EmpDao empDao;
	// 1) 직원등록 (가입)
	public int addEmployee(Emp emp) {
		int addEmployee = 0;
		Connection conn = null;
		this.empDao = new EmpDao();
		try {
			conn = DBUtil.getConnection();
			addEmployee = empDao.addEmployee(conn, emp);
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
		return addEmployee;
	}
	
	// 2) 직원로그인
	public Emp loginEmployee(Emp emp) {
		Emp loginEmployee = null;
		Connection conn = null;
		this.empDao = new EmpDao();
		try {
			conn = DBUtil.getConnection();
			loginEmployee = empDao.loginEmployee(conn, emp);
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
		return loginEmployee;
	}
	
	// 3) 직원목록(검색기능 추가)
	// 3) 직원목록
	public ArrayList<HashMap<String,Object>> allEmpList(int beginRow, int rowPerPage, String col, String sort) {
		ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
		Connection conn = null;
		this.empDao = new EmpDao();
		try {
			conn = DBUtil.getConnection();
			list = empDao.allEmpList(conn, beginRow, rowPerPage, col, sort);
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
	
	// 4-1) 직원 활성화/비활성화
	public int modifyEmpActive(Emp emp) {
		int modifyEmpActive = 0;
		Connection conn = null;
		this.empDao = new EmpDao();
		try {
			conn = DBUtil.getConnection();
			modifyEmpActive = empDao.modifyEmpActive(conn, emp);
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
		return modifyEmpActive;
	}
	
	// 4-2) 직원 직위변경
	public int modifyEmpAuthCode(Emp emp) {
		int modifyEmpAuthCode = 0;
		Connection conn = null;
		this.empDao = new EmpDao();
		try {
			conn = DBUtil.getConnection();
			modifyEmpAuthCode = empDao.modifyEmpAuthCode(conn, emp);
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
		return modifyEmpAuthCode;
	}
	
	// 4-3) 직원 해고(삭제)
	
	// 5) 직원 총원
	public int ttlCntEmp() {
		int ttlCntEmp = 0;
		Connection conn = null;
		this.empDao = new EmpDao();
		try {
			conn = DBUtil.getConnection();
			ttlCntEmp = empDao.ttlCntEmp(conn);
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
		return ttlCntEmp;
	}
}
