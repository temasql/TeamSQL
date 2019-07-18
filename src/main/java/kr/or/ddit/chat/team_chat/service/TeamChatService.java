package kr.or.ddit.chat.team_chat.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.ddit.chat.team_chat.dao.ITeamChatDao;
import kr.or.ddit.chat.team_chat.model.TeamChatVO;

//@Service
public class TeamChatService implements ITeamChatService{

	@Resource(name = "teamChatDao")
	private ITeamChatDao teamChatDao;

	@Override
	public int insert(TeamChatVO teamChatVo) {
		return teamChatDao.insert(teamChatVo);
	}
	
	@Override
	public TeamChatVO get(String id) {
		return teamChatDao.get(id);
	}
	
	@Override
	public List<TeamChatVO> list() {
		return teamChatDao.list();
	}

	@Override
	public Map<String, Object> map(Map<String, Object> map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<TeamChatVO> mapList =  teamChatDao.map(map);
		return resultMap;
	}

	@Override
	public int update(String id) {
		return teamChatDao.update(id);
	}
	
	@Override
	public int delete(String id) {
		return teamChatDao.delete(id);
	}

}
