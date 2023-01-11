package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

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
					+ ") VALUES (?,PASSWORD(?),?,'재직',0,NOW())"; 
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
			loginEmp.setActive(rs.getString("active"));
			loginEmp.setAuthCode(rs.getInt("auth_code"));

		}
		
		return loginEmp;
	}
	
	// 3) 직원목록(검색기능추가된) - 사원이상만 조회 가능
	// 3-1) 직원목록 - 사원이상만 조회 가능
	public ArrayList<HashMap<String,Object>> allEmpList(Connection conn, int beginRow, int rowPerPage, String col, String sort, String word) throws Exception {
		ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
		
		String sql = "SELECT"
					+ " e.emp_id empId"
					+ ", e.emp_name empName"
					+ ", e.active active"
					+ ", e.createdate createdate"
					+ ", a.auth_memo authMemo"
					+ " FROM emp e"
					+ " INNER JOIN auth_info a ON e.auth_code = a.auth_code"
					+ " WHERE e.emp_name LIKE ?"
					+ " ORDER BY "+col+" "+sort
					+ " LIMIT ?, ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%"+word+"%");
		stmt.setInt(2, beginRow);
		stmt.setInt(3, rowPerPage);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String,Object> m = new HashMap<String,Object>();
			m.put("empId", rs.getString("empId"));
			m.put("empName", rs.getString("empName"));
			m.put("active", rs.getString("active"));
			m.put("createdate", rs.getString("createdate"));
			m.put("authMemo", rs.getString("authMemo"));
			list.add(m);
		}
		return list;		
	}
	
	// 4-1) 직원 재직현황
	public int modifyEmpActive(Connection conn, Emp emp) throws Exception {
		int modifyEmpActive = 0;
		
		String sql = "UPDATE emp SET active = ? WHERE emp_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, emp.getActive());
		stmt.setString(2, emp.getEmpId());
		modifyEmpActive = stmt.executeUpdate();
		
		return modifyEmpActive;
	}
	
	// 4-2) 직원 직위변경
	public int modifyEmpAuthCode(Connection conn, Emp emp) throws Exception {
		int modifyEmpAuthCode = 0;
		
		String sql = "UPDATE emp SET auth_code = ? WHERE emp_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, emp.getAuthCode());
		stmt.setString(2, emp.getEmpId());
		modifyEmpAuthCode = stmt.executeUpdate();
		
		return modifyEmpAuthCode;
	}
	
	// 5) 직원 해고(삭제)
	public int fireEmp(Connection conn, Emp emp) throws Exception {
		int fireEmp = 0;
		String sql = "DELETE FROM emp WHERE emp_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, emp.getEmpId());
		fireEmp = stmt.executeUpdate();
		
		return fireEmp;
	}
	
	// 6) 직원 총원
	public int ttlCntEmp(Connection conn) throws Exception {
		int ttlCntEmp = 0;
		
		String sql = "SELECT COUNT(*) FROM emp";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			ttlCntEmp = rs.getInt("COUNT(*)");
		}
		return ttlCntEmp;
	}	
	
	// 7) 검색된 직원 
	public int ttlCntSearchEmp(Connection conn, String word) throws Exception {
		int ttlCntSearchEmp = 0;
		
		String sql = "SELECT COUNT(*) FROM emp WHERE emp_name LIKE ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%"+word+"%");
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			ttlCntSearchEmp = rs.getInt("COUNT(*)");
		}
		
		return ttlCntSearchEmp;
	}
}
