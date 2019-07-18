package kr.or.ddit.chat.team_chat_room.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.ddit.chat.team_chat_room.dao.ITeamChatRoomDao;
import kr.or.ddit.chat.team_chat_room.model.TeamChatRoomVO;

//@Service
public class TeamChatRoomService implements ITeamChatRoomService{

	@Resource(name = "teamChatRoomDao")
	private ITeamChatRoomDao teamChatRoomDao;

	@Override
	public int insert(TeamChatRoomVO teamChatRoomVo) {
		return teamChatRoomDao.insert(teamChatRoomVo);
	}
	
	@Override
	public TeamChatRoomVO get(String id) {
		return teamChatRoomDao.get(id);
	}
	
	@Override
	public List<TeamChatRoomVO> list() {
		return teamChatRoomDao.list();
	}

	@Override
	public Map<String, Object> map(Map<String, Object> map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<TeamChatRoomVO> mapList =  teamChatRoomDao.map(map);
		return resultMap;
	}

	@Override
	public int update(String id) {
		return teamChatRoomDao.update(id);
	}
	
	@Override
	public int delete(String id) {
		return teamChatRoomDao.delete(id);
	}

}
