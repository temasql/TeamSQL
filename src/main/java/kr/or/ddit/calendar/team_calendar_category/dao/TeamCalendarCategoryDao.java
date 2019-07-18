package kr.or.ddit.calendar.team_calendar_category.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;

import kr.or.ddit.calendar.team_calendar_category.model.TeamCalendarCategoryVO;

//@Repository
public class TeamCalendarCategoryDao implements ITeamCalendarCategoryDao{

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	@Override
	public int insert(TeamCalendarCategoryVO teamCalendarCategoryVo) {
		return sqlSession.insert("teamCalendarCategory.insert", teamCalendarCategoryVo);
	}
	
	@Override
	public TeamCalendarCategoryVO get(String id) {
		return sqlSession.selectOne("teamCalendarCategory.get", id);
	}
	
	@Override
	public List<TeamCalendarCategoryVO> list() {
		return sqlSession.selectList("teamCalendarCategory.list");
	}

	@Override
	public List<TeamCalendarCategoryVO> map(Map<String, Object> map) {
		return sqlSession.selectList("teamCalendarCategory.map", map);
	}
	
	@Override
	public int update(String id) {
		return sqlSession.update("teamCalendarCategory.update", id);
	}

	@Override
	public int delete(String id) {
		return sqlSession.delete("teamCalendarCategory.delete", id);
	}

	

}
