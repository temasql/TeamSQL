package kr.or.ddit.sqlEditorTrigger.service;

import java.sql.Connection;
import java.util.List;

import kr.or.ddit.sqlEditorTrigger.model.MyTriggerVO;

public interface ISqlEditorTriggerService {
	
	String createTrigger(MyTriggerVO triggerVO, Connection conn);
	
}
