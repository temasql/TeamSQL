package kr.or.ddit.calendar.team_calendar_category.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.ddit.calendar.team_calendar_category.dao.ITeamCalendarCategoryDao;
import kr.or.ddit.calendar.team_calendar_category.model.TeamCalendarCategoryVO;

//@Service
public class TeamCalendarCategoryService implements ITeamCalendarCategoryService{

	@Resource(name = "teamCalendarCategoryDao")
	private ITeamCalendarCategoryDao teamCalendarCategoryDao;

	@Override
	public int insert(TeamCalendarCategoryVO teamCalendarCategoryVo) {
		return teamCalendarCategoryDao.insert(teamCalendarCategoryVo);
	}
	
	@Override
	public TeamCalendarCategoryVO get(String id) {
		return teamCalendarCategoryDao.get(id);
	}
	
	@Override
	public List<TeamCalendarCategoryVO> list() {
		return teamCalendarCategoryDao.list();
	}

	@Override
	public Map<String, Object> map(Map<String, Object> map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<TeamCalendarCategoryVO> mapList =  teamCalendarCategoryDao.map(map);
		return resultMap;
	}

	@Override
	public int update(String id) {
		return teamCalendarCategoryDao.update(id);
	}
	
	@Override
	public int delete(String id) {
		return teamCalendarCategoryDao.delete(id);
	}

}
