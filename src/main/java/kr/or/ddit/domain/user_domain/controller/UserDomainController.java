package kr.or.ddit.domain.user_domain.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.domain.user_domain.model.UserDomainVO;
import kr.or.ddit.domain.user_domain.service.IUserDomainService;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.DataTypeUtil;

@RequestMapping("/userDomain")
@Controller
public class UserDomainController {

	@Resource(name="userDomainService")
	IUserDomainService domainService;
	
	private static final Logger logger = LoggerFactory.getLogger(UserDomainController.class);
	
	
	/**
	* Method : userDomain
	* 작성자 : 이영은
	* 변경이력 :
	* @param model
	* @param userDomainVo
	* @return
	* Method 설명 : 유저 도메인 리스트 조회
	*/
	@RequestMapping(path =  "/userDomain", method = RequestMethod.POST)
	public String userDomain(Model model, UserDomainVO userDomainVo) {
		List<UserDomainVO> domainList = domainService.userDomainList(userDomainVo);
		
		model.addAttribute("domainList", domainList);
		
		return "jsonView";
	}
	
	
	/**
	* Method : insertUserDomain
	* 작성자 : 이영은
	* 변경이력 :
	* @param model
	* @param session
	* @param userDomainVo
	* @return
	* Method 설명 : 유저 도메인 추가
	*/
	@RequestMapping(path = "/insertUserDomain", method = RequestMethod.POST)
	public String insertUserDomain(Model model, HttpSession session, UserDomainVO userDomainVo) {
		UserVO userVo = (UserVO) session.getAttribute("USER_INFO");
		
		userDomainVo.setUser_id_fk(userVo.getUser_id());
		userDomainVo.setUdomain_name(userDomainVo.getUdomain_name().toUpperCase());
		userDomainVo.setUdomain_type(userDomainVo.getUdomain_type().toUpperCase());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id_fk", userVo.getUser_id());
		map.put("udomain_name", userDomainVo.getUdomain_name());
		
		String name = domainService.getName(map);
		
		if(name != null) {
			if(name.contentEquals(userDomainVo.getUdomain_name())) {
				String msg = "존재하는 도메인";
				
				model.addAttribute("msg", msg);
			}
		}
		
		// 입력 받은 데이터 타입이 유효 여부 체크
		List<String> dataTypeList = DataTypeUtil.tableDataType();
		
		int cnt =0;
		for (String dataType : dataTypeList) {
			logger.debug("!!!!!dataType : {}",dataType);
			logger.debug("!!!!!boolean : {}", userDomainVo.getUdomain_type().toUpperCase().startsWith(dataType));
			if(userDomainVo.getUdomain_type().toUpperCase().startsWith(dataType)==true) {
				cnt++;
			}
		}
		
		if(cnt == 0) {
			String msg = "유효하지 않는 데이터 타입";
			model.addAttribute("typeMsg", msg);
			return "jsonView";
		}
		
		int result = domainService.insertUserDomain(userDomainVo);
		
		if(result > 0) {
			logger.debug("도메인 추가 성공");
		}
		return "jsonView";
	}
	
	
	/**
	* Method : updateUserDomain
	* 작성자 : 이영은
	* 변경이력 :
	* @param model
	* @param session
	* @param userDomainVo
	* @return
	* Method 설명 : 사용자가 수정하고자 하는 도메인 클릭 후 수정 버튼 클릭 시 도메인 수정화면 출력
	*/
	@RequestMapping(path = "/updateUserDomain", method = RequestMethod.POST)
	public String updateUserDomain(Model model, HttpSession session, UserDomainVO userDomainVo) {
		UserVO userVo = (UserVO) session.getAttribute("USER_INFO");
		
		userDomainVo.setUser_id_fk(userVo.getUser_id());
		userDomainVo.setUdomain_name(userDomainVo.getUdomain_name().toUpperCase());
		userDomainVo.setUdomain_type(userDomainVo.getUdomain_type().toUpperCase());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id_fk", userDomainVo.getUser_id_fk());
		map.put("udomain_id", userDomainVo.getUdomain_id());
		map.put("udomain_name", userDomainVo.getUdomain_name());
		map.put("udomain_type", userDomainVo.getUdomain_type());
		
		// DB에 있는 도메인명인지 여부 검사
		String name = "";
		name = domainService.getName(map);

		if(name == null) {
			name = "";
		}
		
		String findName = "";
		findName = domainService.findDomain(map);
		
		if(findName == null) {
			findName = "";
		}
		
		// 입력 받은 데이터 타입이 유효 여부 체크
		List<String> dataTypeList = DataTypeUtil.tableDataType();
		
		int cnt =0;
		for (String dataType : dataTypeList) {
			logger.debug("!!!!!dataType : {}",dataType);
			logger.debug("!!!!!boolean : {}", userDomainVo.getUdomain_type().toUpperCase().startsWith(dataType));
			if(userDomainVo.getUdomain_type().toUpperCase().startsWith(dataType)==true) {
				cnt++;
			}
		}
		
		if(cnt == 0) {
			String msg = "유효하지 않는 데이터 타입";
			model.addAttribute("typeMsg", msg);
			return "jsonView";
		}
		
		int result = 0;
		
		// 선택한 도메인명과 입력한 도메인명이 일치했을 때 업데이트
		
		if(findName.equals(userDomainVo.getUdomain_name())) {
			result = domainService.updateUserDomain(userDomainVo);
			return "jsonView";
		}
		
		// 입력한 도메인명이 DB에 없을 시 업데이트
		if(findName.equals("")) {
			result = domainService.updateUserDomain(userDomainVo);
		} else if (name.equals(userDomainVo.getUdomain_name())) {
			String msg = "존재하는 도메인";
			
			model.addAttribute("msg", msg);
			return "jsonView";
		} else if(!findName.equals(userDomainVo.getUdomain_name())) {
			result = domainService.updateUserDomain(userDomainVo);
		}
		return "jsonView";
	}
	
	
	/**
	* Method : deleteUserDomain
	* 작성자 : 이영은 
	* 변경이력 :
	* @param model
	* @param session
	* @param userDomainVo
	* @return
	* Method 설명 : 사용자가 선택한 도메인 삭제
	*/
	@ResponseBody
	@RequestMapping(path = "/deleteUserDomain", method = RequestMethod.POST)
	public String deleteUserDomain(Model model, HttpSession session, UserDomainVO userDomainVo) {
		UserVO userVo = (UserVO) session.getAttribute("USER_INFO");
		
		userDomainVo.setUser_id_fk(userVo.getUser_id());
		
		int result = domainService.deleteUserDomain(userDomainVo);
		
		if(result > 0) {
			logger.debug(result + "개 삭제 성공");
		}
		
		return "";
	}
	
	
	/**
	* Method : userDomainFunc
	* 작성자 : 이영은
	* 변경이력 :
	* @param model
	* @param session
	* @param userDomainVo
	* @return
	* Method 설명 : 도메인 기능 실행 시 도메인명에 해당하는 데이터 타입을 리턴
	*/
	@RequestMapping(path = "/getType", method = RequestMethod.POST)
	public String userDomainFunc(Model model, HttpSession session, UserDomainVO userDomainVo) {
		UserVO userVo = (UserVO) session.getAttribute("USER_INFO");
		
		userDomainVo.setUser_id_fk(userVo.getUser_id());
		userDomainVo.setUdomain_name(userDomainVo.getUdomain_name().toUpperCase());
		String udomain_type = domainService.getType(userDomainVo);
		
		String result = "";
		if(udomain_type == null) {
			result = "존재하지 않는 도메인 입니다.";
		} else {
			result = udomain_type;
		}
		
		model.addAttribute("result", result);
		return "jsonView";
	}

}
	
