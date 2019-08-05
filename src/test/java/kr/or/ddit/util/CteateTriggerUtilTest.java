package kr.or.ddit.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.sqlEditorTrigger.model.MyTriggerVO;

public class CteateTriggerUtilTest {

	private static final Logger logger = LoggerFactory.getLogger(CteateTriggerUtilTest.class);
	@Test
	public void getQueryTest() {
		/***Given***/
		MyTriggerVO triggerVO = new MyTriggerVO("BB_kkk123", "TRIGER1", "TABLE1", "BEFORE", "DELETE,UPDATE", "AA,BB");
		/***When***/
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
		/***Then***/
		logger.debug("query : {}", query);
	}

}
