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
	
	TeamChatRoomVO get(String id);
	
	List<TeamChatRoomVO> list();
	
	List<TeamChatRoomVO> map(Map<String, Object> map);
	
	int update(String id);

	int delete(String id);
}
