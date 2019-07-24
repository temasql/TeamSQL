package kr.or.ddit.calendar.team_calendar.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import kr.or.ddit.calendar.team_calendar.dao.ITeamCalendarDao;
import kr.or.ddit.calendar.team_calendar.model.TeamCalendarVO;

@Service
public class TeamCalendarService implements ITeamCalendarService{
	private static final Logger logger = LoggerFactory.getLogger(TeamCalendarService.class);

	@Resource(name = "teamCalendarDao")
	private ITeamCalendarDao teamCalendarDao;

	@Override
	public int insertCal(TeamCalendarVO teamCalendarVO) {
		return teamCalendarDao.insertCal(teamCalendarVO);
	}
	
	@Override
	public TeamCalendarVO get(String id) {
		return teamCalendarDao.get(id);
	}
	
	@Override
	public String readCal() {
      List<TeamCalendarVO> list = teamCalendarDao.readCal();
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
	
	@Override
	public int deleteCal(String calendar_id) {
		return teamCalendarDao.deleteCal(calendar_id);
	}
}
