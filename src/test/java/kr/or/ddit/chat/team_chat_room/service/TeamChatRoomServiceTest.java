package kr.or.ddit.chat.team_chat_room.service;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.chat.team_chat_room.model.TeamChatRoomVO;
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
public class TeamChatRoomServiceTest extends LogicTestEnv{

	@Resource(name = "teamChatRoomService")
	private ITeamChatRoomService teamChatRoomService;
	
	/**
	 * 
	* Method : insertTeamChatRoomTest
	* 작성자 : 김범휘
	* 변경이력 :
	* Method 설명 : 채팅방 추가 테스트
	 */
	@Test
	public void insertTeamChatRoomTest() {
		/***Given***/
		TeamChatRoomVO teamChatRoomVo = new TeamChatRoomVO("testDB", "TEST_ID20", "TEST CHAT ROOM");
		/***When***/
		int result = teamChatRoomService.insertTeamChatRoom(teamChatRoomVo);
		/***Then***/
		assertEquals(result, 1);
	}

}
