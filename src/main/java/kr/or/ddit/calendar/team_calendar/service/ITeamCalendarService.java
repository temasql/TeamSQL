package kr.or.ddit.calendar.team_calendar.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.calendar.team_calendar.model.TeamCalendarVO;


public interface ITeamCalendarService {
	
	int insert(TeamCalendarVO teamCalnedarVo);
	TeamCalendarVO get(String id);
	
	String list();
	
	Map<String, Object> map(Map<String, Object> map);
	
	int update(String id);
	
	int delete(String id);
}
