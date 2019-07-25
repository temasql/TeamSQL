package kr.or.ddit.calendar.team_calendar.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.calendar.team_calendar.model.TeamCalendarVO;
import kr.or.ddit.calendar.team_calendar.service.ITeamCalendarService;
import kr.or.ddit.crew.model.CrewVO;
import kr.or.ddit.user.model.UserVO;

//@RequestMapping("/teamCalendar")
//@ResponseBody
@Controller
public class TeamCalendarController {
	private static final Logger logger = LoggerFactory.getLogger(TeamCalendarController.class);
	
	@Resource(name="teamCalendarService")
	private ITeamCalendarService service;

	/**
	* Method : calendar
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 캘린더 첫 접속 시 메서드
	*/
	@RequestMapping("/cal")
	public String calendar(HttpSession session, Model model, String account_id) {
		logger.debug("account_id : {}", account_id);
		
		UserVO userVO = (UserVO) session.getAttribute("USER_INFO");
		
		CrewVO crewVO = new CrewVO();
		crewVO.setAccount_id_fk(account_id+"_"+userVO.getUser_id());
		logger.debug("crewVO account_id : {}", crewVO.getAccount_id_fk());
		
		crewVO.setUser_id_fk(userVO.getUser_id());
		session.setAttribute("CREW_INFO", crewVO);
		
		//해당 DB에 포함되어있는 사용자 리스트 반환하는 메서드
		List<UserVO> userList = service.getUserNameList(crewVO);
		logger.debug("userList : {}", userList.get(0));
		
		model.addAttribute("userList", userList);
		return "calendar/calendar.jsp?name="+crewVO.getUser_id_fk();
	}
	
	/**
	* Method : readCal
	* 작성자 : PC19
	* 변경이력 :
	* @param model
	* @return
	* Method 설명 : 캘린더 전체 일정 조회 메서드
	*/
//	@ResponseBody
	@RequestMapping("/readCal")
	public String readCal(Model model, HttpSession session) {
		CrewVO crewVO = (CrewVO) session.getAttribute("CREW_INFO");
		String calList = service.readCal(crewVO);
		
		model.addAttribute("list", calList);
		return "jsonView";
	}
	
	/**
	* Method : insertCal
	* 작성자 : PC19
	* 변경이력 :
	* @param model
	* @param map
	* @return
	* Method 설명 : 일정 등록 메서드
	*/
	@ResponseBody
	@RequestMapping(path = "/insertCal", method = RequestMethod.POST)
	public int insertCal(Model model, @RequestBody Map<String, Object> map, HttpSession session) {
		logger.debug("map : {}", map);
		TeamCalendarVO vo = new TeamCalendarVO();
		CrewVO crewVO = (CrewVO) session.getAttribute("CREW_INFO");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		//-------------------필 히 바꿀것!!!-------------------
		vo.setUser_id_fk(crewVO.getUser_id_fk());
		//-------------------필 히 바꿀것!!!-------------------
		
		logger.debug("변환 전 start : {}", (String) map.get("start"));
		logger.debug("변환 전 end : {}", (String) map.get("end"));
		
		String start = (String) map.get("start");
		String end = (String) map.get("end");
		
		logger.debug("start : {}", start);
		logger.debug("end : {}", end);
		
		Date start_1 = null;
		Date end_1 = null;
		try {
			start_1 = sdf.parse(start);
			end_1 = sdf.parse(end);
			
			logger.debug("start : {}", start_1);
			logger.debug("end : {}", end_1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		vo.setCalendar_start_dt(start_1);
		vo.setCalendar_end_dt(end_1);
		
		vo.setUser_id_fk((String) map.get("username"));
		vo.setAccount_id_fk(crewVO.getAccount_id_fk());
		vo.setCalendar_title((String) map.get("title"));
		vo.setCalendar_content((String) map.get("description"));
		vo.setCalendar_background((String) map.get("backgroundColor"));
		vo.setCalendar_type((String) map.get("type"));
		
		logger.debug("teamCalendar : {}", vo);
		
		int result = service.insertCal(vo);
		int maxSequence = service.maxSequence();
		if(result>0) {
			logger.debug("등록 성공");
			logger.debug("시퀀스 값 : {}", result);
		}
		return maxSequence;
	}
	
	@ResponseBody
	@RequestMapping(path = "/deleteCal", method = RequestMethod.POST)
	public int deleteCal(int id) {
		logger.debug("삭제 int : {}", id);
		int result = service.deleteCal(id);
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping(path = "updateCal", method = RequestMethod.POST)
	public int updateCal(@RequestBody Map<String, Object> map) {
		logger.debug("map update: {}", map);
		
		String dataInt = (String) map.get("id");
		int id = Integer.parseInt(dataInt);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date start = null;
		Date end = null;
		try {
			start = sdf.parse((String) map.get("start"));
			end = sdf.parse((String) map.get("end"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		TeamCalendarVO tcVO = new TeamCalendarVO();
		tcVO.setCalendar_title((String) map.get("title"));
		tcVO.setCalendar_id(id);
		tcVO.setCalendar_start_dt(start);
		tcVO.setCalendar_end_dt(end);
		tcVO.setCalendar_type((String) map.get("type"));
		tcVO.setCalendar_background((String) map.get("backgroundColor"));
		
		int result = service.updateCal(tcVO);
		
		return result;
	}
	
	/**
	* Method : updateDropCal
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 기간만 수정하는 메서드
	*/
	@ResponseBody
	@RequestMapping(path="/updateDropCal", method = RequestMethod.POST)
	public int updateDropCal(@RequestBody Map<String, Object> map) {
		logger.debug("drop map : {}", map);
//		logger.debug("drop int : {}", id);
		TeamCalendarVO tcVO = new TeamCalendarVO();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		
		String id_1 = (String) map.get("id");
		int id = Integer.parseInt(id_1);
		
		logger.debug("map id : {}", id);
		
		Date start = null;
		Date end = null;
		try {
			start = sdf.parse((String) map.get("start"));
			end = sdf.parse((String) map.get("end"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		tcVO.setCalendar_start_dt(start);
		tcVO.setCalendar_end_dt(end);
		tcVO.setCalendar_id(id);
		
		logger.debug("tcVO : {}", tcVO);
		
		int result = service.updateDropCal(tcVO);
		return result;
	}
}
