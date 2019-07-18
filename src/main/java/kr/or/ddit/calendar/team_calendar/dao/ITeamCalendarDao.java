package kr.or.ddit.calendar.team_calendar.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.calendar.team_calendar.model.TeamCalendarVO;

public interface ITeamCalendarDao {

	int insert(TeamCalendarVO teamCalendarVo);
	
	TeamCalendarVO get(String id);
	
	List<TeamCalendarVO> list();
	
	List<TeamCalendarVO> map(Map<String, Object> map);
	
	int update(String id);

	int delete(String id);
}
