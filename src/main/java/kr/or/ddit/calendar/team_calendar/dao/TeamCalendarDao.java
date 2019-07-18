package kr.or.ddit.calendar.team_calendar.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;

import kr.or.ddit.calendar.team_calendar.model.TeamCalendarVO;

//@Repository
public class TeamCalendarDao implements ITeamCalendarDao{

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	@Override
	public int insert(TeamCalendarVO TeamCalendarVO) {
		return sqlSession.insert("teamCalendar.insert", TeamCalendarVO);
	}
	
	@Override
	public TeamCalendarVO get(String id) {
		return sqlSession.selectOne("teamCalendar.get", id);
	}
	
	@Override
	public List<TeamCalendarVO> list() {
		return sqlSession.selectList("teamCalendar.list");
	}

	@Override
	public List<TeamCalendarVO> map(Map<String, Object> map) {
		return sqlSession.selectList("teamCalendar.map", map);
	}
	
	@Override
	public int update(String id) {
		return sqlSession.update("teamCalendar.update", id);
	}

	@Override
	public int delete(String id) {
		return sqlSession.delete("teamCalendar.delete", id);
	}

	

}
