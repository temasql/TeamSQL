package kr.or.ddit.util;

import kr.or.ddit.sqlEditorTrigger.model.MyTriggerVO;

public class TriggerUtil {
	
	public String getCreateTriggerSql(MyTriggerVO triggerVO) {
		String query = "";
		String event = "";
		
		query += "CREATE OR REPLACE TRIGGER " + triggerVO.getTrigger_name() + " \n";
		
		if(triggerVO.getEvent().contains(",")) {
			String[] events = triggerVO.getEvent().split(",");
			for (int i = 0; i < events.length; i++) {
				if(i == events.length-1) {
					if(triggerVO.getColumn() == null) {
						event += events[i] + " ON ";
					}else {
						event += events[i] + " OF " + triggerVO.getColumn() + " ON ";
					}
				}
				else {
					event += events[i] + " OR ";
				}
			}
		}else {
			if(triggerVO.getEvent().contains("UPDATE")) {
				if(triggerVO.getColumn() != null) {
					event += triggerVO.getEvent() + " OF " + triggerVO.getColumn() + " ON ";
				}else {
					event += triggerVO.getEvent() + " ON ";
				}
			}else {
				event += triggerVO.getEvent() + " ON ";
			}
		}
		
		query += triggerVO.getTiming() + " " + event + triggerVO.getTable_name() + " \n";
		query += "BEGIN \n  NULL; \nEND;";
		
		return query;
	}
	
}
