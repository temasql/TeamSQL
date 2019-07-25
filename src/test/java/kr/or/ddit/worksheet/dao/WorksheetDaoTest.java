package kr.or.ddit.worksheet.dao;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.testenv.LogicTestEnv;

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
	
	/**
	 * 
	* Method : selectQueryTest
	* 작성자 : 김범휘
	* 변경이력 :
	* Method 설명 : select에 관한 쿼리문 실행
	 */
	@Test
	public void selectQueryTest() {
		/***Given***/
		String value = "select * from users";
		/***When***/
		List<Map<String, String>> resultList = worksheetDao.selectQuery(value);
		/***Then***/
		assertNotNull(resultList);
		logger.debug("resultList : {}", resultList);
		logger.debug("resultList.get(0) : {}", resultList.get(0));
		Set<String> set = resultList.get(0).keySet();
		logger.debug("set : {}", set);
		Iterator<String> iterator = set.iterator();
		logger.debug("iterator : {}", iterator);
		logger.debug("iterator.next() : {}", iterator.next());
	}

}
