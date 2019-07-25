package kr.or.ddit.worksheet.service;

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
	* Method : selectQuery
	* 작성자 : 김범휘
	* 변경이력 :
	* @param value
	* @return
	* Method 설명 : select에 관한 쿼리문 실행
	 */
	@Override
	public List<Map<String, String>> selectQuery(String value) {
		return worksheetDao.selectQuery(value);
	}
	
}
