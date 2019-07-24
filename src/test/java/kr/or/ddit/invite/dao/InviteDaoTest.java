/**
 * 
 */
package kr.or.ddit.invite.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.invite.model.InviteVO;
import kr.or.ddit.testenv.LogicTestEnv;

public class InviteDaoTest extends LogicTestEnv{

	@Resource(name = "inviteDao")
	private IInviteDao inviteDao;
	
	/**
	* Method : getInviteListTest
	* 작성자 : 이중석
	* 변경이력 :
	* Method 설명 : 사용자 아이디에 해당하는 초대장 리스트 테스트
	*/
	@Test
	public void getInviteListTest() {
		/***Given***/
		/***When***/
		List<InviteVO> inviteList = inviteDao.getInviteList("TEST_ID19");
		/***Then***/
		assertEquals(1, inviteList.size());
	}
	
	/**
	* Method : deleteInviteTest
	* 작성자 : 이중석
	* 변경이력 :
	* Method 설명 : 초대장 번호에 해당하는 초대장을 삭제 테스트
	*/
	@Test
	public void deleteInviteTest() {
		/***Given***/
		List<InviteVO> inviteList = inviteDao.getInviteList("TEST_ID19");
		int invite_id = inviteList.get(0).getInvite_id();
		/***When***/
		int deleteCount = inviteDao.deleteInvite(invite_id);
		/***Then***/
		assertEquals(1, deleteCount);
	}

}
