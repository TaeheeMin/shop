package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.CustomerDao;
import dao.PwHistoryDao;
import util.DBUtil;
import vo.Customer;
import vo.PwHistory;

public class PwHistoryService {
	private PwHistoryDao pwHistoryDao;
	
	// pw이력에 pw추가 및 최대3개 넘을 시, 가장오래된 pw 자동삭제
	// 발동조건 2가지 경우 -> 회원가입 시, 비밀번호 수정 시
	public int operatePwHistory(PwHistory pwHistory) {
		int operatePwHistory = 0;
		Connection conn = null;
		this.pwHistoryDao = new PwHistoryDao();
		int ttlCntPwHistory = 0;
		int removePwHistory = 0;
		PwHistory selectOldestPw = new PwHistory();
		System.out.println("[pwHistoryService]");
		try {
			conn = DBUtil.getConnection();
			
			// 총갯수
			ttlCntPwHistory = pwHistoryDao.ttlCntPwHistory(conn, pwHistory);
			System.out.println("(1)비밀번호내역 총 갯수 : "+ttlCntPwHistory);
			
			if(ttlCntPwHistory < 3) { // 3개 미만일 경우, 이력추가만 진행
				System.out.println("(2)내역 3개미만");
				operatePwHistory = pwHistoryDao.addPwHistory(conn, pwHistory);
				
			} else { // 3개 이상일 경우, 가장오래된 pw 삭제 후, 이력추가진행
				System.out.println("(2)내역 3개이상 -> 오래된 비밀번호 삭제");
				// 삭제할 비밀번호내역 하나 불러오기 (가장 오래된 비밀번호)
				selectOldestPw = pwHistoryDao.selectOldestPw(conn, pwHistory);
				System.out.println("(3)삭제할 pw : "+selectOldestPw.getPw());
				System.out.println("(3)삭제할 id : "+selectOldestPw.getCustomerId());

				// 오래된 비밀번호내역 삭제
				removePwHistory = pwHistoryDao.removePwHistory(conn, selectOldestPw);
				if(removePwHistory == 1) {
					System.out.println("(4)old 비밀번호내역 삭제 성공");
					// 수정된 비밀번호 추가
					operatePwHistory = pwHistoryDao.addPwHistory(conn, pwHistory);
				} else {
					System.out.println("(4)old 비밀번호내역 삭제 실패"	);
				}
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
		return operatePwHistory;
	}
}
