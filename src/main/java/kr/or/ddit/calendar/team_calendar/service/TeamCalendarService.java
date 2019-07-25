package kr.or.ddit.calendar.team_calendar.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import kr.or.ddit.calendar.team_calendar.dao.ITeamCalendarDao;
import kr.or.ddit.calendar.team_calendar.model.TeamCalendarVO;
import kr.or.ddit.crew.model.CrewVO;
import kr.or.ddit.user.model.UserVO;

@Service
public class TeamCalendarService implements ITeamCalendarService{
	private static final Logger logger = LoggerFactory.getLogger(TeamCalendarService.class);

	@Resource(name = "teamCalendarDao")
	private ITeamCalendarDao teamCalendarDao;

	/**
	* Method : insertCal
	* 작성자 : PC19
	* 변경이력 :
	* @param teamCalendarVO
	* @return
	* Method 설명 : 일정 등록 메서드
	*/
	@Override
	public int insertCal(TeamCalendarVO teamCalendarVO) {
		return teamCalendarDao.insertCal(teamCalendarVO);
	}
	
	/**
	* Method : maxSequence
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : insert 후 sequence 최댓값을 반환하는 메서드
	*/
	@Override
	public int maxSequence() {
		return teamCalendarDao.maxSequence();
	}
	
	/**
	* Method : get
	* 작성자 : PC19
	* 변경이력 :
	* @param id
	* @return
	* Method 설명 : 
	*/
	@Override
	public TeamCalendarVO get(String id) {
		return teamCalendarDao.get(id);
	}
	
	/**
	* Method : readCal
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 현재 모든 리스트를 반환 - 수정 예정(해당 DB계정에 대해서만 가져올 예정)
	*/
	@Override
	public String readCal(CrewVO crewVO) {
      List<TeamCalendarVO> list = teamCalendarDao.readCal(crewVO);
      logger.debug("start : {}", list.get(0).getStart());
      logger.debug("End : {}", list.get(0).getEnd());
      String jsonData = "[";
      for(TeamCalendarVO calVO : list) {
         jsonData += "{\"_id\"" + ":"+ calVO.getCalendar_id()  + ","
                + "\"title\"" + ":" + "\"" + calVO.getCalendar_title() + "\"" + "," 
                + "\"description\"" + ":" + "\"" + calVO.getCalendar_content() + "\"" + "," 
                + "\"start\"" + ":" + "\"" + calVO.getStart() + "\"" + ","
                + "\"end\"" + ":" + "\"" + calVO.getEnd() + "\"" + ","
                + "\"username\"" + ":" + "\"" + calVO.getUser_id_fk() + "\"" + ","
                + "\"type\"" + ":" + "\"" + calVO.getCalendar_type() + "\"" + ","
                + "\"textColor\"" + ":" + "\"" + "#ffffff" + "\"" + ","
                + "\"backgroundColor\"" + ":" + "\"" + calVO.getCalendar_background() + "\"" + ","
         		+ "\"allDay\"" + ":" + "false" + "},";
         logger.debug("start : {}", calVO.getStart());
         logger.debug("end : {}", calVO.getEnd());
      }
      jsonData = jsonData.substring(0, jsonData.lastIndexOf(","));
      jsonData += "]";
      
      logger.debug("jsonData : {}", jsonData);
      
      return jsonData;
	}

	@Override
	public Map<String, Object> map(Map<String, Object> map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<TeamCalendarVO> mapList =  teamCalendarDao.map(map);
		return resultMap;
	}

	@Override
	public int updateCal(TeamCalendarVO teamCalendarVO) {
		return teamCalendarDao.updateCal(teamCalendarVO);
	}
	
	/**
	* Method : updateDropCal
	* 작성자 : PC19
	* 변경이력 :
	* @param teamCalendarVO
	* @return
	* Method 설명 : 일정만 수정 하는 메서드 (제목, 내용, 배경색, 구분 제외)
	*/
	@Override
	public int updateDropCal(TeamCalendarVO teamCalendarVO) {
		return teamCalendarDao.updateDropCal(teamCalendarVO);
	}
	
	@Override
	public int deleteCal(int calendar_id) {
		return teamCalendarDao.deleteCal(calendar_id);
	}

	/**
	* Method : getUserNameList
	* 작성자 : PC19
	* 변경이력 :
	* @param crewVO
	* @return
	* Method 설명 : DB계정명을 입력 받아서 해당 DB계정에 있는 모든 사용자를 반환 하는 메서드 
	*/
	@Override
	public List<UserVO> getUserNameList(CrewVO crewVO) {
		return teamCalendarDao.getUserNameList(crewVO);
	}
}
