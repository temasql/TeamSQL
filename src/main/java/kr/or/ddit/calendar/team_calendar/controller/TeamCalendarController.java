package kr.or.ddit.calendar.team_calendar.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.calendar.team_calendar.model.TeamCalendarVO;
import kr.or.ddit.calendar.team_calendar.service.ITeamCalendarService;

//@RequestMapping("/teamCalendar")
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
	public String calendar() {
		
		
		return "calendar/calendar.jsp?name=정연";
	}
	
//	@ResponseBody
	@RequestMapping("/list")
	public String viewPost(Model model) {
		logger.debug("===============리스트===============");
		String calList = service.list();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a hh:mm");
		
//		String start = sdf.format(calList.get(0).getCalendar_start_dt());
//		String end = sdf.format(calList.get(0).getCalendar_end_dt());
		
//		logger.debug("date : {}", start);
//		logger.debug("date : {}", end);
		
		model.addAttribute("list", calList);
		return "jsonView";
	}
}
