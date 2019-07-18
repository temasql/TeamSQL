package kr.or.ddit.calendar.team_calendar_category.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.calendar.team_calendar_category.model.TeamCalendarCategoryVO;

public interface ITeamCalendarCategoryService {
	
	int insert(TeamCalendarCategoryVO teamCalendarCategoryVo);
	
	TeamCalendarCategoryVO get(String id);
	
	List<TeamCalendarCategoryVO> list();
	
	Map<String, Object> map(Map<String, Object> map);
	
	int update(String id);
	
	int delete(String id);
}
