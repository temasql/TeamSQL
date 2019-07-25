package kr.or.ddit.calendar.team_calendar.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.calendar.team_calendar.model.TeamCalendarVO;
import kr.or.ddit.crew.model.CrewVO;
import kr.or.ddit.user.model.UserVO;

public interface ITeamCalendarDao {

	int insertCal(TeamCalendarVO teamCalendarVo);
	
	int maxSequence();
	
	TeamCalendarVO get(String id);
	
	List<TeamCalendarVO> readCal(CrewVO crewVO);
	
	List<TeamCalendarVO> map(Map<String, Object> map);
	
	int updateCal(TeamCalendarVO teamCalendarVO);
	
	int updateDropCal(TeamCalendarVO teamCalendarVO);

	int deleteCal(int calendar_id);
	
	List<UserVO> getUserNameList(CrewVO crewVO);
}
