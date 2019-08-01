/**
 * 
 */
package kr.or.ddit.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* SelectTableUtilTest.java
*
* @author 이중석
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
* 수정자 수정내용
* ------ ------------------------
* 이중석 최초 생성
*
* </pre>
*/
public class SelectTableUtilTest {

	private static final Logger logger = LoggerFactory.getLogger(SelectTableUtilTest.class);
	
	@Test
	public void test() {
		/***Given***/
		String select = SelectTableUtil.selectQuery("column", "USERS");
		/***When***/
		logger.debug("\n{}", select);
		/***Then***/
	}

}
