package kr.or.ddit.calendar.team_calendar.dao;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.calendar.team_calendar.model.TeamCalendarVO;
import kr.or.ddit.crew.model.CrewVO;
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
	
	/**
	* Method : calendarList
	* 작성자 : PC19
	* 변경이력 :
	* Method 설명 : 캘린더 전체 일정 조회 테스트 메서드
	*/
	@Test
	public void readCalTest() {
		/***Given***/
		CrewVO crewVO = new CrewVO();
		crewVO.setAccount_id_fk("테스트 계정");

		/***When***/
		List<TeamCalendarVO> list = dao.readCal(crewVO);

		/***Then***/
		assertNotNull(list);
		assertEquals(11, list.size());
	}
	
	/**
	* Method : insertCal
	* 작성자 : PC19
	* 변경이력 :
	* @throws ParseException
	* Method 설명 : 일정 등록 테스트 메서드
	*/
	@Test
	public void insertCalTest() throws ParseException {
		/***Given***/
		TeamCalendarVO tcVO = new TeamCalendarVO();
		String dt = "2019-01-01 09:30";
		String start_dt = "2019-02-02 08:30";
		String end_dt = "2019-03-03 10:30";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date date = sdf.parse(dt);
		Date start = sdf.parse(start_dt);
		Date end = sdf.parse(end_dt);

		/***When***/
		tcVO.setAccount_id_fk("테스트 계정");
		tcVO.setCalendar_background("이건 뭐가들어가도 상관없음");
		tcVO.setCalendar_content("이것도 내용이기 때문에 상관없음");
		tcVO.setCalendar_dt(date);
		tcVO.setCalendar_end_dt(end);
		tcVO.setCalendar_start_dt(start);
		tcVO.setCalendar_title("아무 제목");
		tcVO.setCalendar_type("구분짓는다");
		tcVO.setUser_id_fk("TEST_ID1");

		int result = dao.insertCal(tcVO);
		
		/***Then***/
		assertEquals(1, result);
	}
	
	/**
	* Method : deleteCalTest
	* 작성자 : PC19
	* 변경이력 :
	* Method 설명 : max시퀀스값에 해당하는 일정을 삭제하는 테스트 메서드
	*/
	@Test
	public void deleteCalTest() {
		/***Given***/
		

		/***When***/
		int maxSequence = dao.maxSequence();
		int result = dao.deleteCal(maxSequence);

		/***Then***/
		assertEquals(1, result);
	}
	
	/**
	* Method : updateCal
	* 작성자 : PC19
	* 변경이력 :
	* @throws ParseException
	* Method 설명 : max시퀀스값에 해당하는 일정의 모든 값을 수정하는 테스트 메서드
	*/
	@Test
	public void updateCalTest() throws ParseException {
		/***Given***/
		String start_dt = "2019-06-06 08:30";
		String end_dt = "2019-08-08 10:30";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date start = sdf.parse(start_dt);
		Date end = sdf.parse(end_dt);

		/***When***/
		TeamCalendarVO tcVO = new TeamCalendarVO();
		
		int maxSequence = dao.maxSequence();
		
		tcVO.setCalendar_background("이건 뭐가 들어가도 상관없어요");
		tcVO.setAccount_id_fk("테스트 계정");
		tcVO.setCalendar_content("수정이니깐요");
		tcVO.setCalendar_end_dt(end);
		tcVO.setCalendar_id(maxSequence);
		tcVO.setCalendar_start_dt(start);
		tcVO.setCalendar_title("수정 중입니다.");
		tcVO.setCalendar_type("수정 제목");
		tcVO.setUser_id_fk("TEST_ID1");

		int result = dao.updateCal(tcVO);
		
		/***Then***/
		assertEquals(1, result);
	}
	
	/**
	* Method : updateDropCalTest
	* 작성자 : PC19
	* 변경이력 :
	* Method 설명 : max시퀀스에 해당하는 일정의 기간만 수정하는 테스트 메서드
	 * @throws ParseException 
	*/
	@Test
	public void updateDropCalTest() throws ParseException {
		/***Given***/
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		int maxSequence = dao.maxSequence();
		String start_dt = "2019-06-06 08:30";
		String end_dt = "2019-08-08 10:30";
		Date start = sdf.parse(start_dt);
		Date end = sdf.parse(end_dt);

		/***When***/
		TeamCalendarVO tcVO = new TeamCalendarVO();
		tcVO.setCalendar_start_dt(start);
		tcVO.setCalendar_end_dt(end);
		tcVO.setCalendar_id(maxSequence);

		int result = dao.updateDropCal(tcVO);
		/***Then***/
		assertEquals(1, result);
	}
}
