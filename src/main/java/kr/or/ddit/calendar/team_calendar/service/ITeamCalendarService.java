package kr.or.ddit.calendar.team_calendar.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.calendar.team_calendar.model.TeamCalendarVO;


public interface ITeamCalendarService {
	
	int insertCal(TeamCalendarVO teamCalendarVO);
	
	int maxSequence();
	
	TeamCalendarVO get(String id);
	
	String readCal();
	
	Map<String, Object> map(Map<String, Object> map);
	
	int updateCal(TeamCalendarVO teamCalendarVO);
	
	int updateDropCal(TeamCalendarVO teamCalendarVO);
	
	int deleteCal(int calendar_id);
}
