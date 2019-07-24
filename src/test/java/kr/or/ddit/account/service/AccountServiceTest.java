package kr.or.ddit.account.service;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.account.model.AccountVO;
import kr.or.ddit.dbObject.model.FuncProceVO;
import kr.or.ddit.dbObject.model.IndexVO;
import kr.or.ddit.dbObject.model.SequenceVO;
import kr.or.ddit.dbObject.model.TableVO;
import kr.or.ddit.dbObject.model.TriggerVO;
import kr.or.ddit.dbObject.model.ViewVO;
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
public class AccountServiceTest extends LogicTestEnv{

	@Resource(name = "accountService")
	private IAccountService accountService;
	
	/**
	 * 
	* Method : getAccountListTest
	* 작성자 : 김범휘
	* 변경이력 :
	* Method 설명 : user_id를 통해 DB계정 리스트 정보 가져오기 테스트
	 */
	@Test
	public void getAccountListTest() {
		/***Given***/

		/***When***/
		List<AccountVO> accountList = accountService.getAccountList("TEST_ID20");
		/***Then***/
		assertNotNull(accountList);
		assertEquals(accountList.get(0).getAccount_id(), "testDB");
		assertEquals(1, accountList.size());
	}
	
	/**
	 * 
	* Method : insertAccountTest
	* 작성자 : 김범휘
	* 변경이력 :
	* Method 설명 : DB계정 추가 테스트
	 */
	@Test
	public void insertAccountTest() {
		/***Given***/
		AccountVO accountVO = new AccountVO("testDBDB", "TEST_ID2", "java");
		/***When***/
		int result = accountService.insertAccount(accountVO);
		/***Then***/
		assertEquals(result, 1);
	}

	/**
	 * 
	* Method : createAccountTest
	* 작성자 : 김범휘
	* 변경이력 :
	* Method 설명 : DB계정 '실제' 생성 테스트
	 */
	@Test
	public void createAccountTest() {
		/***Given***/
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "bumper1");
		map.put("pass", "java");
		/***When***/
		int result = accountService.createAccount(map);
		
		/***Then***/
		assertEquals(0, result);
	}
	
	/**
	 * 
	* Method : grantAccountTest
	* 작성자 : 김범휘
	* 변경이력 :
	* Method 설명 : DB계정 '실제' 권한주기
	 */
	@Test
	public void grantAccountTest() {
		/***Given***/
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "bumper1");
		/***When***/
		int result = accountService.grantAccount(map);
		/***Then***/
		assertEquals(0, result);
	}
	
	/**
	 * 
	* Method : getAccountAllTableTest
	* 작성자 : 김범휘
	* 변경이력 :
	* Method 설명 : DB계정의 테이블 모두 가져오기 테스트
	 */
	@Test
	public void getAccountAllTableTest() {
		/***Given***/
		String account_id = "BUM_TEST_ID20";
		/***When***/
		List<TableVO> tableList = accountService.getAccountAllTable(account_id);
		/***Then***/
		assertEquals(5, tableList.size());
	}
	
	/**
	 * 
	* Method : getAccountAllView
	* 작성자 : 김범휘
	* 변경이력 :
	* Method 설명 : DB계정의 테이블 모두 가져오기 테스트
	 */
	@Test
	public void getAccountAllView() {
		/***Given***/
		String account_id = "BUM_TEST_ID20";
		/***When***/
		List<ViewVO> viewList = accountService.getAccountAllView(account_id);
		/***Then***/
		assertEquals(viewList.size(), 1);
	}
	
	/**
	 * 
	* Method : getAccountAllIndex
	* 작성자 : PC06
	* 변경이력 :
	* Method 설명 : DB계정의 인덱스 모두 가져오기 테스트
	 */
	@Test
	public void getAccountAllIndex() {
		/***Given***/
		String account_id = "BUM_TEST_ID20";
		/***When***/
		List<IndexVO> indexList = accountService.getAccountAllIndex(account_id);
		/***Then***/
		assertEquals(indexList.size(), 1);
	}
	
	/**
	 * 
	* Method : getAccountAllTriggerTest
	* 작성자 : 김범휘
	* 변경이력 :
	* Method 설명 : DB계정의 트리거 모두 가져오기 테스트
	 */
	@Test
	public void getAccountAllTriggerTest() {
		/***Given***/
		String account_id = "BUM_TEST_ID20";
		/***When***/
		List<TriggerVO> indexList = accountService.getAccountAllTrigger(account_id);
		/***Then***/
		assertEquals(indexList.size(), 1);
	}
	
	
	/**
	 * 
	* Method : getAccountAllSequenceTest
	* 작성자 : 김범휘
	* 변경이력 :
	* Method 설명 : DB계정의 트리거 모두 가져오기 테스트
	 */
	@Test
	public void getAccountAllSequenceTest() {
		/***Given***/
		String account_id = "BUM_TEST_ID20";
		/***When***/
		List<SequenceVO> sequenceList = accountService.getAccountAllSequence(account_id);
		/***Then***/
		assertEquals(sequenceList.size(), 1);
	}
	
	/**
	 * 
	* Method : getAccountAllFunctionTest
	* 작성자 : 김범휘
	* 변경이력 :
	* Method 설명 : DB계정의 함수 모두 가져오기 테스트
	 */
	@Test
	public void getAccountAllFunctionTest() {
		/***Given***/
		String account_id = "BUM_TEST_ID20";
		/***When***/
		List<FuncProceVO> functionList = accountService.getAccountAllFunction(account_id);
		/***Then***/
		assertEquals(functionList.size(), 1);
	}
	
	/**
	 * 
	* Method : getAccountAllProcedureTest
	* 작성자 : 김범휘
	* 변경이력 :
	* Method 설명 : DB계정의 프로시저 모두 가져오기 테스트
	 */
	@Test
	public void getAccountAllProcedureTest() {
		/***Given***/
		String account_id = "BUM_TEST_ID20";
		/***When***/
		List<FuncProceVO> procedureList = accountService.getAccountAllProcedure(account_id);
		/***Then***/
		assertEquals(procedureList.size(), 1);
	}
	
	/**
	 * 
	* Method : getAccountCntTest
	* 작성자 : 김범휘
	* 변경이력 :
	* Method 설명 : DB계정이름으로 등록된 계정 수 얻어오기 테스트
	 */
	@Test
	public void getAccountCntTest() {
		/***Given***/
		String account_id = "testDB";
		/***When***/
		int result = accountService.getAccountCnt(account_id);
		/***Then***/
		assertEquals(1, result);
	}
	
	/**
	 * 
	* Method : getAccountOneTest
	* 작성자 : 김범휘
	* 변경이력 :
	* Method 설명 : DB계정 한개의 정보 조회
	 */
	@Test
	public void getAccountOneTest() {
		/***Given***/
		String account_id = "testDB";
		/***When***/
		AccountVO accountVO = accountService.getAccountOne(account_id);
		/***Then***/
		assertNotNull(accountVO);
	}
	
	/**
	 * 
	* Method : deleteAccountByTableTest
	* 작성자 : 김범휘
	* 변경이력 :
	* Method 설명 : DB계정 테이블에서 삭제
	 */
	@Test
	public void deleteAccountByTableTest() {
		/***Given***/
		String account_id = "testDB";
		/***When***/
		int result = accountService.deleteAccountByTable(account_id);
		/***Then***/
		assertEquals(result, 1);
	}
	
	/**
	 * 
	* Method : updateAccountTest
	* 작성자 : 김범휘
	* 변경이력 :
	* Method 설명 : DB계정 비밀번호 수정
	 */
	@Test
	public void updateAccountTest() {
		/***Given***/
		String value = "ALTER USER KIM_TEST_ID20 IDENTIFIED by test";
		/***When***/
		int result = accountService.updateAccount(value);
		/***Then***/
		assertEquals(result, 0);
	}
	
	/**
	 * 
	* Method : updateAccountByTableTest
	* 작성자 : 김범휘
	* 변경이력 :
	* Method 설명 : DB계정 테이블에서 비밀번호 수정
	 */
	@Test
	public void updateAccountByTableTest() {
		/***Given***/
		AccountVO accountVO = new AccountVO();
		accountVO.setAccount_id("testDB");
		accountVO.setAccount_pw("test");
		/***When***/
		int result = accountService.updateAccountByTable(accountVO);
		/***Then***/
		assertEquals(result, 1);
	}
	
}
