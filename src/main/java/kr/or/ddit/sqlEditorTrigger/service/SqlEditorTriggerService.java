package kr.or.ddit.sqlEditorTrigger.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.sqlEdiotTable.dao.ISqlEditorTableDao;
import kr.or.ddit.sqlEditorTrigger.dao.ISqlEditorTriggerDao;

import kr.or.ddit.sqlEditorTrigger.model.MyTriggerVO;

import kr.or.ddit.sqlEditorTrigger.model.MyTriggerCodeVO;
import kr.or.ddit.sqlEditorTrigger.model.MyTriggerVO;
import kr.or.ddit.sqlEditorTrigger.model.TriggerDetailVO;

import kr.or.ddit.util.CreateTableUtil;
import kr.or.ddit.util.SelectTableUtil;
import kr.or.ddit.util.TriggerUtil;

@Service
public class SqlEditorTriggerService implements ISqlEditorTriggerService {

	@Resource(name = "sqlEditorTriggerDao")
	private ISqlEditorTriggerDao sqlEditorTriggerDao;
	
	@Override
	public String createTrigger(MyTriggerVO triggerVO, Connection conn) {
		String query = new TriggerUtil().getCreateTriggerSql(triggerVO);
		boolean result = sqlEditorTriggerDao.createTrigger(query, conn);
		if(result == true)
			return query;
		else
			return "";
	}


	@Override
	public List<MyTriggerCodeVO> getTriggerCode(Map<String, String> map, Connection conn) {
		return sqlEditorTriggerDao.getTriggerCode(map, conn);
	}

	@Override
	public List<TriggerDetailVO> triggerDetail(Map<String, String> map) {
		return sqlEditorTriggerDao.triggerDetail(map);
	}

	@Override
	public int deleteTrigger(String triggerName) {
		return sqlEditorTriggerDao.deleteTrigger(triggerName);
	}

}
