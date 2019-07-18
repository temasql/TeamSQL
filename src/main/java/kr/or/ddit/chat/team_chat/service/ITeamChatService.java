package kr.or.ddit.chat.team_chat.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.chat.team_chat.model.TeamChatVO;

public interface ITeamChatService {
	
	int insert(TeamChatVO teamChatVo);
	
	TeamChatVO get(String id);
	
	List<TeamChatVO> list();
	
	Map<String, Object> map(Map<String, Object> map);
	
	int update(String id);
	
	int delete(String id);
}
