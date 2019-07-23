package kr.or.ddit.account.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.account.dao.IAccountDao;
import kr.or.ddit.account.model.AccountVO;
import kr.or.ddit.dbObject.model.FuncProceVO;
import kr.or.ddit.dbObject.model.IndexVO;
import kr.or.ddit.dbObject.model.SequenceVO;
import kr.or.ddit.dbObject.model.TableVO;
import kr.or.ddit.dbObject.model.TriggerVO;
import kr.or.ddit.dbObject.model.ViewVO;

@Service
public class AccountService implements IAccountService{

	@Resource(name = "accountDao")
	private IAccountDao accountDao;

	/**
	 * 
	* Method : insertAccount
	* 작성자 : 김범휘
	* 변경이력 :
	* @param accountVo
	* @return
	* Method 설명 : DB계정 추가
	 */
	@Override
	public int insertAccount(AccountVO accountVo) {
		return accountDao.insertAccount(accountVo);
	}
	
	/**
	 * 
	* Method : getAccountList
	* 작성자 : 김범휘
	* 변경이력 :
	* @param user_id
	* @return
	* Method 설명 : user_id를 통해 DB계정 리스트 정보 가져오기
	 */
	@Override
	public List<AccountVO> getAccountList(String user_id) {
		return accountDao.getAccountList(user_id);
	}
	
	/**
	 * 
	* Method : createAccount
	* 작성자 : 김범휘
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 : DB계정 '실제' 생성
	 */
	@Override
	public int createAccount(Map<String, String> map) {
		return accountDao.createAccount(map);
	}

	/**
	 * 
	* Method : grantAccount
	* 작성자 : 김범휘
	* 변경이력 :
	* @param name
	* @return
	* Method 설명 : DB계정 '실제' 권한주기
	 */
	@Override
	public int grantAccount(Map<String, String> map) {
		return accountDao.grantAccount(map);
	}

	/**
	 * 
	* Method : getAccountAllTable
	* 작성자 : 김범휘
	* 변경이력 :
	* @param account_id
	* @return
	* Method 설명 : DB계정의 테이블 모두 가져오기
	 */
	@Override
	public List<TableVO> getAccountAllTable(String account_id) {
		return accountDao.getAccountAllTable(account_id);
	}

	/**
	 * 
	* Method : getAccountAllView
	* 작성자 : 김범휘
	* 변경이력 :
	* @param account_id
	* @return
	* Method 설명 : DB계정의 뷰 모두 가져오기
	 */
	@Override
	public List<ViewVO> getAccountAllView(String account_id) {
		return accountDao.getAccountAllView(account_id);
	}

	/**
	 * 
	* Method : getAccountAllIndex
	* 작성자 : 김범휘
	* 변경이력 :
	* @param account_id
	* @return
	* Method 설명 : DB계정의 인덱스 모두 가져오기
	 */
	@Override
	public List<IndexVO> getAccountAllIndex(String account_id) {
		return accountDao.getAccountAllIndex(account_id);
	}

	/**
	 * 
	* Method : getAccountAllTrigger
	* 작성자 : 김범휘
	* 변경이력 :
	* @param account_id
	* @return
	* Method 설명 : DB계정의 인덱스 모두 가져오기
	 */
	@Override
	public List<TriggerVO> getAccountAllTrigger(String account_id) {
		return accountDao.getAccountAllTrigger(account_id);
	}

	/**
	 * 
	* Method : getAccountAllSequence
	* 작성자 : 김범휘
	* 변경이력 :
	* @param account_id
	* @return
	* Method 설명 : DB계정의 시퀀스 모두 가져오기
	 */
	@Override
	public List<SequenceVO> getAccountAllSequence(String account_id) {
		return accountDao.getAccountAllSequence(account_id);
	}

	/**
	 * 
	* Method : getAccountAllFunction
	* 작성자 : 김범휘
	* 변경이력 :
	* @param account_id
	* @return
	* Method 설명 : DB계정 함수 모두 가져오기
	 */
	@Override
	public List<FuncProceVO> getAccountAllFunction(String account_id) {
		return accountDao.getAccountAllFunction(account_id);
	}

	/**
	 * 
	* Method : getAccountAllProcedureTest
	* 작성자 : 김범휘
	* 변경이력 :
	* Method 설명 : DB계정의 프로시저 모두 가져오기 테스트
	 */
	@Override
	public List<FuncProceVO> getAccountAllProcedure(String account_id) {
		return accountDao.getAccountAllProcedure(account_id);
	}

}
