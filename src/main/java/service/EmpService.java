package service;

import java.sql.Connection;
import java.sql.SQLException;

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
}
