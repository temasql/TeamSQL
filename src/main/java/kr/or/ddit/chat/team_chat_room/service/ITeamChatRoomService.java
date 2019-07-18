package kr.or.ddit.chat.team_chat_room.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.chat.team_chat_room.model.TeamChatRoomVO;

public interface ITeamChatRoomService {
	
	int insert(TeamChatRoomVO teamChatRoomVo);
	
	TeamChatRoomVO get(String id);
	
	List<TeamChatRoomVO> list();
	
	Map<String, Object> map(Map<String, Object> map);
	
	int update(String id);
	
	int delete(String id);
}
