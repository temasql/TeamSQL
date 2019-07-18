package kr.or.ddit.calendar.team_calendar.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.ddit.calendar.team_calendar.dao.ITeamCalendarDao;
import kr.or.ddit.calendar.team_calendar.model.TeamCalendarVO;

//@Service
public class TeamCalendarService implements ITeamCalendarService{

	@Resource(name = "teamCalendarDao")
	private ITeamCalendarDao teamCalendarDao;

	@Override
	public int insert(TeamCalendarVO teamCalendarVo) {
		return teamCalendarDao.insert(teamCalendarVo);
	}
	
	@Override
	public TeamCalendarVO get(String id) {
		return teamCalendarDao.get(id);
	}
	
	@Override
	public List<TeamCalendarVO> list() {
		return teamCalendarDao.list();
	}

	@Override
	public Map<String, Object> map(Map<String, Object> map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<TeamCalendarVO> mapList =  teamCalendarDao.map(map);
		return resultMap;
	}

	@Override
	public int update(String id) {
		return teamCalendarDao.update(id);
	}
	
	@Override
	public int delete(String id) {
		return teamCalendarDao.delete(id);
	}

}
