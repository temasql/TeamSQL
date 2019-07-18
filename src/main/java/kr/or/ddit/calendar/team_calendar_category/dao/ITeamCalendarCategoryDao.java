package kr.or.ddit.calendar.team_calendar_category.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.calendar.team_calendar_category.model.TeamCalendarCategoryVO;

public interface ITeamCalendarCategoryDao {

	int insert(TeamCalendarCategoryVO teamCalendarCategoryVo);
	
	TeamCalendarCategoryVO get(String id);
	
	List<TeamCalendarCategoryVO> list();
	
	List<TeamCalendarCategoryVO> map(Map<String, Object> map);
	
	int update(String id);

	int delete(String id);
}
