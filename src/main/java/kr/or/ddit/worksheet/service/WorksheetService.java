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

	@Override
	public List<List<String>> selectRun(String query, Connection conn) {
		return worksheetDao.selectRun(query, conn);
	}

	@Override
	public int anotherRun(String query, Connection conn) {
		return worksheetDao.anotherRun(query, conn);
	}
	
	
	
}
