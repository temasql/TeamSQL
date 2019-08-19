package kr.or.ddit.chat.team_chat_room.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.chat.team_chat_room.model.TeamChatRoomVO;

public interface ITeamChatRoomDao {

	/**
	 * 
	* Method : insertTeamChatRoom
	* 작성자 : 김범휘
	* 변경이력 :
	* @param teamChatRoomVo
	* @return
	* Method 설명 : 채팅방 추가
	 */
	int insertTeamChatRoom(TeamChatRoomVO teamChatRoomVo);
	
	/**
	* Method : getChatRoomId
	* 작성자 : 손주형
	* 변경이력 :
	* @param id
	* @return
	* Method 설명 : 유저 아이디로 채팅방 아이디, 채팅방명 얻기
	*/
	TeamChatRoomVO getChatRoomId(String accountId);
	
	/**
	* Method : chatRoomList
	* 작성자 : 손주형
	* 변경이력 :
	* @return
	* Method 설명 : 채팅방 리스트 반환
	*/
	List<TeamChatRoomVO> chatRoomList(String userId);
	
	List<TeamChatRoomVO> map(Map<String, Object> map);
	
	int update(String id);

	int delete(String id);
	
	/**
	* Method : getChatRoomNM
	* 작성자 : 손주형
	* 변경이력 :
	* @param account_id
	* @return
	* Method 설명 : 채팅방 번호로 채팅방 ID을 조회하는 메서드
	*/
	String getAccount_id_fk(int chat_room_id);
}
