package kr.or.ddit.calendar.team_calendar.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.calendar.team_calendar.model.TeamCalendarVO;
import kr.or.ddit.testenv.LogicTestEnv;

/**
* CommonsDaoTest.java
*
* @author OWNER
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
* 수정자 수정내용
* ------ ------------------------
* OWNER 최초 생성
* Dao 테스트의 틀을 위한 클래스입니다.
* </pre>
*/
public class TeamCalendarDaoTest extends LogicTestEnv{

	@Resource(name = "teamCalendarDao")
	private ITeamCalendarDao dao;
	
	@Test
	public void daoTest() {
		/***Given***/

		/***When***/
		
		/***Then***/
		assertEquals("", "");
	}
	
	@Test
	public void calendarList() {
		/***Given***/
		

		/***When***/
		List<TeamCalendarVO> list = dao.readCal();

		/***Then***/
		assertNotNull(list);
		assertEquals(1, list.size());
	}
}
