package kr.or.ddit.worksheet.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.testenv.LogicTestEnv;
import kr.or.ddit.util.DBUtilForWorksheet;

/**
* CommonsDaoTest.java
*
* @author OWNER
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
* 수정자 수정내용
* ------ ------------------------
* OWNER 최초 생성
* Dao 테스트의 틀을 위한 클래스입니다.
* </pre>
*/
public class WorksheetDaoTest extends LogicTestEnv{
	
	private static final Logger logger = LoggerFactory.getLogger(WorksheetDaoTest.class);

	private IWorksheetDao worksheetDao;
	
	public WorksheetDaoTest() {
		worksheetDao = new WorksheetDao();
	}
	
}
