package kr.or.ddit.worksheet.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kr.or.ddit.worksheet.dao.IWorksheetDao;
import kr.or.ddit.worksheet.dao.WorksheetDao;

@Service
public class WorksheetService implements IWorkSheetService {
	
	private IWorksheetDao worksheetDao;
	
	public WorksheetService() {
		worksheetDao = new WorksheetDao();
	}

	/**
	 * 
	* Method : selectRun
	* 작성자 : 김범휘
	* 변경이력 :
	* @param query
	* @param conn
	* @return
	* Method 설명 : 워크시트 select문 처리
	 */
	@Override
	public List<List<String>> selectRun(String query, Connection conn) {
		return worksheetDao.selectRun(query, conn);
	}

	/**
	 * 
	* Method : anotherRun
	* 작성자 : 김범휘
	* 변경이력 :
	* @param query
	* @param conn
	* @return
	* Method 설명 : 워크시트 select를 제외한 쿼리문 처리(commit, rollback제외)
	 */
	@Override
	public Map<String, Object> anotherRun(String query, Connection conn) {
		return worksheetDao.anotherRun(query, conn);
	}

	/**
	 * 
	* Method : commitRun
	* 작성자 : 김범휘
	* 변경이력 :
	* @param query
	* @param conn
	* @return
	* Method 설명 : 워크시트 commit문 처리
	 */
	@Override
	public int commitRun(String query, Connection conn) {
		return worksheetDao.commitRun(query, conn);
	}

	/**
	 * 
	* Method : rollbackRun
	* 작성자 : 김범휘
	* 변경이력 :
	* @param query
	* @param conn
	* @return
	* Method 설명 : 워크시트 rollback문 처리
	 */
	@Override
	public int rollbackRun(String query, Connection conn) {
		return worksheetDao.rollbackRun(query, conn);
	}

	/**
	 * 
	* Method : ddlRun
	* 작성자 : 김범휘
	* 변경이력 :
	* @param query
	* @param conn
	* @return
	* Method 설명 : 워크시트 DDL문 처리
	 */
	@Override
	public Map<String, String> ddlRun(String query, Connection conn) {
		return worksheetDao.ddlRun(query, conn);
	}

	/**
	 * 
	* Method : planInsert
	* 작성자 : 김범휘
	* 변경이력 :
	* @param query
	* @param conn
	* @return
	* Method 설명 : 실행계획 등록
	 */
	@Override
	public String planInsert(String query, Connection conn) {
		return worksheetDao.planInsert(query, conn);
	}

	
	/**
	 * 
	* Method : planRun
	* 작성자 : 김범휘
	* 변경이력 :
	* @param conn
	* @return
	* Method 설명 : 실행계획 실행
	 */
	@Override
	public List<String> planRun(Connection conn) {
		return worksheetDao.planRun(conn);
	}
	
	
	
}
