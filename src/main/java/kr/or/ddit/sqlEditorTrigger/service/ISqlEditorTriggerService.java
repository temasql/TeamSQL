package kr.or.ddit.sqlEditorTrigger.service;

import java.sql.Connection;
import java.util.List;


import kr.or.ddit.sqlEditorTrigger.model.MyTriggerVO;

import java.util.Map;

import kr.or.ddit.sqlEditorTrigger.model.MyTriggerCodeVO;
import kr.or.ddit.sqlEditorTrigger.model.MyTriggerVO;
import kr.or.ddit.sqlEditorTrigger.model.TriggerDetailVO;


public interface ISqlEditorTriggerService {
	
	String createTrigger(MyTriggerVO triggerVO, Connection conn);
	

	List<MyTriggerCodeVO> getTriggerCode(Map<String, String> map, Connection conn);
	
	List<TriggerDetailVO> triggerDetail(String object_name);
	
	int deleteTrigger(String triggerName);

}
