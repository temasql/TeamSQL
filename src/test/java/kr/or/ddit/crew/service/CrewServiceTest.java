package kr.or.ddit.crew.service;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	/**
	* Method : crewListTest
	* 작성자 : 이중석
	* 변경이력 :
	* Method 설명 : 구성원 페이징 처리 테스트
	* 				
	*/
	@Test
	public void crewListTest() {
		/***Given***/
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("search", "");
		pageMap.put("page", 1);
		pageMap.put("pageSize", 10);
		pageMap.put("user_id", "TEST_ID20");
		pageMap.put("account_id_fk", "testDB");
		/***When***/
		Map<String, Object> resultMap = crewService.crewList(pageMap);
		List<CrewVO> crewList = (List<CrewVO>) resultMap.get("crewList");
		int paginationSize = (int) resultMap.get("paginationSize");
		
		/***Then***/
		assertEquals(1, crewList.size());
		assertEquals(1, paginationSize);
	}
	
	/**
	 * Method : crewSelectListTest
	 * 작성자 : 이중석
	 * 변경이력 :
	 * Method 설명 : 해당 계정의 팀 리스트 테스트
	 */
	@Test
	public void crewSelectListTest() {
		/***Given***/
		/***When***/
		List<CrewVO> crewList = crewService.crewSelectList("TEST_ID20");
		/***Then***/
		assertEquals(1, crewList.size());
	}
	
	/**
	 * Method : deleteCrewTest
	 * 작성자 : 이중석
	 * 변경이력 :
	 * Method 설명 : 구성원 삭제 테스트
	 */
	@Test
	public void deleteCrewTest() {
		/***Given***/
		CrewVO crewVo = new CrewVO("testDB", "TEST_ID20");
		/***When***/
		int deleteCrewCount = crewService.deleteCrew(crewVo);
		/***Then***/
		assertEquals(1, deleteCrewCount);
	}

}
