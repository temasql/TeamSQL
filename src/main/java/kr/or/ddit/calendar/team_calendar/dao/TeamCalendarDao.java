package kr.or.ddit.calendar.team_calendar.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.calendar.team_calendar.model.TeamCalendarVO;

@Repository
public class TeamCalendarDao implements ITeamCalendarDao{

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	/**
	* Method : insert
	* 작성자 : PC19
	* 변경이력 :
	* @param teamCalendarVO
	* @return
	* Method 설명 : DB에 일정을 추가
	*/
	@Override
	public int insertCal(TeamCalendarVO teamCalendarVO) {
		return sqlSession.insert("teamCalendar.insertCal", teamCalendarVO);
	}
	
	@Override
	public TeamCalendarVO get(String id) {
		return sqlSession.selectOne("teamCalendar.get", id);
	}
	
	/**
	* Method : readCal
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : DB의 모든 일정을 조회
	*/
	@Override
	public List<TeamCalendarVO> readCal() {
		return sqlSession.selectList("teamCalendar.readCal");
	}

	@Override
	public List<TeamCalendarVO> map(Map<String, Object> map) {
		return sqlSession.selectList("teamCalendar.map", map);
	}
	
	/**
	* Method : update
	* 작성자 : PC19
	* 변경이력 :
	* @param id
	* @return
	* Method 설명 : DB의 일정을 수정하는 메서드
	*/
	@Override
	public int updateCal(TeamCalendarVO teamCalendarVO) {
		return sqlSession.update("teamCalendar.update", teamCalendarVO);
	}

	/**
	* Method : delete
	* 작성자 : PC19
	* 변경이력 :
	* @param id
	* @return
	* Method 설명 :
	*/
	@Override
	public int deleteCal(String calendar_id) {
		return sqlSession.delete("teamCalendar.delete", calendar_id);
	}

	

}
