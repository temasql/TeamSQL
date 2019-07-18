package kr.or.ddit.chat.team_chat.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.chat.team_chat.model.TeamChatVO;

public interface ITeamChatDao {

	int insert(TeamChatVO teamChatVo);
	
	TeamChatVO get(String id);
	
	List<TeamChatVO> list();
	
	List<TeamChatVO> map(Map<String, Object> map);
	
	int update(String id);

	int delete(String id);
}
