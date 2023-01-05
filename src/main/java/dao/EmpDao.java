package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vo.Customer;
import vo.Emp;

public class EmpDao {
	// auth Code 목록 (0:인턴, 1:사원, 99:매니저)
	
	// 1) 직원등록 - 사원이상의 직원만 가능
	// 1-1) 중복체크
	
	// 1-2) 직원등록(직원가입)
	public int addEmployee(Connection conn, Emp emp) throws Exception {
		int addEmployee = 0;
		
		// 가입쿼리 진행 - 기본값 비활성화 및 0(인턴)레벨 -> 추후 매니저가 활성화 및 등급조정하게끔
		String sql = "INSERT INTO emp("
					+ "emp_id, emp_pw, emp_name, active, auth_code, createdate"
					+ ") VALUES (?,PASSWORD(?),?,'N',0,NOW())"; 
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, emp.getEmpId());
		stmt.setString(2, emp.getEmpPw());
		stmt.setString(3, emp.getEmpName());
		
		addEmployee = stmt.executeUpdate();

		return addEmployee;
	}
	
	// 2) 직원로그인
	public Emp loginEmployee(Connection conn, Emp emp) throws Exception {
		Emp loginEmp = null;
		
		// 로그인쿼리 진행
		String sql = "SELECT * FROM emp WHERE emp_id = ? AND emp_pw = PASSWORD(?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, emp.getEmpId());
		stmt.setString(2, emp.getEmpPw());
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			loginEmp = new Emp();
			loginEmp.setEmpId(rs.getString("emp_id"));
			loginEmp.setAuthCode(rs.getInt("auth_code"));

		}
		
		return loginEmp;
	}
}
