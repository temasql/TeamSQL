/**
 * 
 */
package kr.or.ddit.invite.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.invite.model.InviteVO;
import kr.or.ddit.testenv.LogicTestEnv;

public class InviteServiceTest extends LogicTestEnv{

	@Resource(name = "inviteService")
	private IInviteService inviteService;
	
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
		List<InviteVO> inviteList = inviteService.getInviteList("TEST_ID19");
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
		List<InviteVO> inviteList = inviteService.getInviteList("TEST_ID19");
		int invite_id = inviteList.get(0).getInvite_id();
		/***When***/
		int deleteCount = inviteService.deleteInvite(invite_id);
		/***Then***/
		assertEquals(1, deleteCount);
	}
}
