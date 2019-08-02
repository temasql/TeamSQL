package kr.or.ddit.calendar.team_calendar.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.calendar.team_calendar.model.TeamCalendarVO;
import kr.or.ddit.crew.model.CrewVO;
import kr.or.ddit.user.model.UserVO;

@Repository
public class TeamCalendarDao implements ITeamCalendarDao{

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	/**
	* Method : insert
	* 작성자 : 손주형
	* 변경이력 :
	* @param teamCalendarVO
	* @return
	* Method 설명 : DB에 일정을 추가
	*/
	@Override
	public int insertCal(TeamCalendarVO teamCalendarVO) {
		return sqlSession.insert("teamCalendar.insertCal", teamCalendarVO);
	}
	
	/**
	* Method : maxSequence
	* 작성자 : 손주형
	* 변경이력 :
	* @return
	* Method 설명 : insert 후 sequence 최댓값을 반환하는 메서드
	*/
	@Override
	public int maxSequence() {
		return sqlSession.selectOne("teamCalendar.maxSequence");
	}
	
	@Override
	public TeamCalendarVO get(String id) {
		return sqlSession.selectOne("teamCalendar.get", id);
	}
	
	/**
	* Method : readCal
	* 작성자 : 손주형
	* 변경이력 :
	* @return
	* Method 설명 : DB의 모든 일정을 조회
	*/
	@Override
	public List<TeamCalendarVO> readCal(CrewVO crewVO) {
		return sqlSession.selectList("teamCalendar.readCal", crewVO);
	}

	@Override
	public List<TeamCalendarVO> map(Map<String, Object> map) {
		return sqlSession.selectList("teamCalendar.map", map);
	}
	
	/**
	* Method : update
	* 작성자 : 손주형
	* 변경이력 :
	* @param id
	* @return
	* Method 설명 : DB의 일정을 수정하는 메서드
	*/
	@Override
	public int updateCal(TeamCalendarVO teamCalendarVO) {
		return sqlSession.update("teamCalendar.updateCal", teamCalendarVO);
	}
	
	/**
	 * Method : updateDropCal
	 * 작성자 : 손주형
	 * 변경이력 :
	 * @param teamCalendarVO
	 * @return
	 * Method 설명 : 기간에 해당하는 부분만 수정하는 메서드
	 */
	@Override
	public int updateDropCal(TeamCalendarVO teamCalendarVO) {
		return sqlSession.update("teamCalendar.updateDropCal", teamCalendarVO);
	}

	/**
	* Method : delete
	* 작성자 : 손주형
	* 변경이력 :
	* @param id
	* @return
	* Method 설명 : 클릭한 일정의 id를 받아서 DB에서 해당일정을 삭제하는 메서드
	*/
	@Override
	public int deleteCal(int calendar_id) {
		return sqlSession.delete("teamCalendar.deleteCal", calendar_id);
	}

	/**
	* Method : getUserNameList
	* 작성자 : 손주형
	* 변경이력 :
	* @param crewVO
	* @return
	* Method 설명 : DB계정명을 입력 받아서 해당 DB계정에 있는 모든 사용자를 반환 하는 메서드 
	*/
	@Override
	public List<UserVO> getUserNameList(CrewVO crewVO) {
		return sqlSession.selectList("teamCalendar.getUserNameList", crewVO);
	}
}
