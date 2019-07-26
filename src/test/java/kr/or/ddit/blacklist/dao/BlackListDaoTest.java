package kr.or.ddit.blacklist.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.blacklist.model.BlackListVO;
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
public class BlackListDaoTest extends LogicTestEnv{

	@Resource(name = "blackListDao")
	private IBlackListDao blackListDao;
	
	/**
	* Method : blackListTest
	* 작성자 : 이중석
	* 변경이력 :
	* Method 설명 : 블랙리스트 조회 테스트
	*/
	@Test
	public void blackListTest() {
		/***Given***/
		/***When***/
//		List<BlackListVO> blackList = blackListDao.blackList();
		/***Then***/
//		assertEquals(2, blackList.size());
	}

}
