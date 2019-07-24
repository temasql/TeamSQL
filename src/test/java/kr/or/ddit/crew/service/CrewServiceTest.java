package kr.or.ddit.crew.service;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.crew.model.CrewVO;
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
* 서비스 테스트의 틀을 위한 클래스입니다.
* </pre>
*/
public class CrewServiceTest extends LogicTestEnv{

	@Resource(name = "crewService")
	private ICrewService crewService;
	
	/**
	 * 
	* Method : insertCrewTest
	* 작성자 : 김범휘
	* 변경이력 :
	* Method 설명 : 구성원 추가 테스트
	 */
	@Test
	public void insertCrewTest() {
		/***Given***/
		CrewVO crewVO = new CrewVO("testDB", "TEST_ID19");
		/***When***/
		int result = crewService.insertCrew(crewVO);
		/***Then***/
		assertEquals(result, 1);
	}

}
