package kr.or.ddit.account.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.account.model.AccountVO;
import kr.or.ddit.dbObject.model.FuncProceVO;
import kr.or.ddit.dbObject.model.IndexVO;
import kr.or.ddit.dbObject.model.SequenceVO;
import kr.or.ddit.dbObject.model.TableVO;
import kr.or.ddit.dbObject.model.TriggerVO;
import kr.or.ddit.dbObject.model.ViewVO;

@Repository
public class AccountDao implements IAccountDao{

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;
	
	/**
	 * 
	* Method : insertAccount
	* 작성자 : 김범휘
	* 변경이력 :
	* @param accountVO
	* @return
	* Method 설명 : DB계정 추가
	 */
	@Override
	public int insertAccount(AccountVO accountVO) {
		return sqlSession.insert("account.insertAccount", accountVO);
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
		return sqlSession.selectList("account.getAccountList", user_id);
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
		return sqlSession.update("account.createAccount", map);
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
		return sqlSession.update("account.grantAccount", map);
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
		return sqlSession.selectList("account.getAccountAllTable", account_id);
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
		return sqlSession.selectList("account.getAccountAllView", account_id);
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
		return sqlSession.selectList("account.getAccountAllIndex", account_id);
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
		return sqlSession.selectList("account.getAccountAllTrigger", account_id);
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
		return sqlSession.selectList("account.getAccountAllSequence", account_id);
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
		return sqlSession.selectList("account.getAccountAllFunction", account_id);
	}

	/**
	 * 
	* Method : getAccountAllProcedure
	* 작성자 : 김범휘
	* 변경이력 :
	* @param account_id
	* @return
	* Method 설명 : DB계정 프로시저 모두 가져오기
	 */
	@Override
	public List<FuncProceVO> getAccountAllProcedure(String account_id) {
		return sqlSession.selectList("account.getAccountAllProcedure", account_id);
	}

}
