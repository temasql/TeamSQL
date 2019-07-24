package kr.or.ddit.account.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.account.model.AccountVO;
import kr.or.ddit.dbObject.model.FuncProceVO;
import kr.or.ddit.dbObject.model.IndexVO;
import kr.or.ddit.dbObject.model.SequenceVO;
import kr.or.ddit.dbObject.model.TableVO;
import kr.or.ddit.dbObject.model.TriggerVO;
import kr.or.ddit.dbObject.model.ViewVO;

public interface IAccountService {
	
	/**
	 * 
	* Method : insertAccount
	* 작성자 : 김범휘
	* 변경이력 :
	* @param accountVO
	* @return
	* Method 설명 : DB계정 추가
	 */
	int insertAccount(AccountVO accountVo);
	
	/**
	 * 
	* Method : getAccountList
	* 작성자 : 김범휘
	* 변경이력 :
	* @param user_id
	* @return
	* Method 설명 : user_id를 통해 DB계정 리스트 정보 가져오기
	 */
	List<AccountVO> getAccountList(String user_id);
	
	/**
	 * 
	* Method : createAccount
	* 작성자 : 김범휘
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 : DB계정 '실제' 생성
	 */
	int createAccount(Map<String, String> map);
	
	/**
	 * 
	* Method : grantAccount
	* 작성자 : 김범휘
	* 변경이력 :
	* @param name
	* @return
	* Method 설명 : DB계정 '실제' 권한주기
	 */
	int grantAccount(Map<String, String> map);

	/**
	 * 
	* Method : getAccountAllTable
	* 작성자 : 김범휘
	* 변경이력 :
	* @param account_id
	* @return
	* Method 설명 : DB계정의 테이블 모두 가져오기
	 */
	List<TableVO> getAccountAllTable(String account_id);

	/**
	 * 
	* Method : getAccountAllView
	* 작성자 : 김범휘
	* 변경이력 :
	* @param account_id
	* @return
	* Method 설명 : DB계정의 뷰 모두 가져오기
	 */
	List<ViewVO> getAccountAllView(String account_id);

	/**
	 * 
	* Method : getAccountAllIndex
	* 작성자 : 김범휘
	* 변경이력 :
	* @param account_id
	* @return
	* Method 설명 : DB계정의 인덱스 모두 가져오기
	 */
	List<IndexVO> getAccountAllIndex(String account_id);

	/**
	 * 
	* Method : getAccountAllTrigger
	* 작성자 : 김범휘
	* 변경이력 :
	* @param account_id
	* @return
	* Method 설명 : DB계정의 인덱스 모두 가져오기
	 */
	List<TriggerVO> getAccountAllTrigger(String account_id);

	/**
	 * 
	* Method : getAccountAllSequence
	* 작성자 : 김범휘
	* 변경이력 :
	* @param account_id
	* @return
	* Method 설명 : DB계정의 시퀀스 모두 가져오기
	 */
	List<SequenceVO> getAccountAllSequence(String account_id);

	/**
	 * 
	* Method : getAccountAllFunction
	* 작성자 : 김범휘
	* 변경이력 :
	* @param account_id
	* @return
	* Method 설명 : DB계정 함수 모두 가져오기
	 */
	List<FuncProceVO> getAccountAllFunction(String account_id);

	/**
	 * 
	* Method : getAccountAllProcedureTest
	* 작성자 : 김범휘
	* 변경이력 :
	* Method 설명 : DB계정의 프로시저 모두 가져오기 테스트
	 */
	List<FuncProceVO> getAccountAllProcedure(String account_id);

	/**
	 * 
	* Method : getAccountCnt
	* 작성자 : 김범휘
	* 변경이력 :
	* @param account_id
	* @return
	* Method 설명 : DB계정이름으로 등록된 계정 수 얻어오기 
	 */
	int getAccountCnt(String account_id);

	/**
	 * 
	* Method : getAccountOne
	* 작성자 : 김범휘
	* 변경이력 :
	* @param account_id
	* @return
	* Method 설명 : DB계정 한개의 정보 조회
	 */
	AccountVO getAccountOne(String account_id);

	/**
	 * 
	* Method : deleteAccount
	* 작성자 : 김범휘
	* 변경이력 :
	* @param account_id
	* @return
	* Method 설명 : DB계정 삭제
	 */
	int deleteAccount(String account_id);

	/**
	 * 
	* Method : deleteAccountByTable
	* 작성자 : 김범휘
	* 변경이력 :
	* @param account_id
	* @return
	* Method 설명 : DB계정 테이블에서 삭제
	 */
	int deleteAccountByTable(String account_id);
	
}
