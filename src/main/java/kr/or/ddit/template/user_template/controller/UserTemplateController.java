package kr.or.ddit.template.user_template.controller;

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

import kr.or.ddit.template.user_template.model.UserTemplateVO;
import kr.or.ddit.template.user_template.service.IUserTemplateService;
import kr.or.ddit.user.model.UserVO;

@RequestMapping("/userTemplate")
@Controller
public class UserTemplateController {
	private static final Logger logger = LoggerFactory.getLogger(UserTemplateController.class);
	
	@Resource(name="userTemplateService")
	IUserTemplateService service;

	/**
	* Method : userTemplate
	* 작성자 : 손주형
	* 변경이력 :
	* @param model
	* @param userTemplateVO
	* @return
	* Method 설명 : 유저의 템플릿 리스트를 조회 하는 메서드
	*/
	@RequestMapping(path =  "/userTemplate", method = RequestMethod.POST)
	public String userTemplate(Model model, UserTemplateVO userTemplateVO) {
		logger.debug("여기 userTemplateVO : {}", userTemplateVO);
		
		List<UserTemplateVO> templateList = service.userTemplateList(userTemplateVO);
		
		logger.debug("유저 템플릿 리스트 : {}", templateList);
		
		model.addAttribute("templateList", templateList);
		
		return "jsonView";
	}
	
	/**
	* Method : insertUserTemplate
	* 작성자 : 손주형
	* 변경이력 :
	* @param model
	* @param session
	* @param userTemplateVO
	* @return
	* Method 설명 : 사용자가 템플릿 추가하는 메서드
	*/
	@RequestMapping(path =  "/insertUserTemplate", method = RequestMethod.POST)
	public String insertUserTemplate(Model model,HttpSession session, UserTemplateVO userTemplateVO) {
		logger.debug("유저템플릿 등록 : {}", userTemplateVO);
		
		UserVO userVO = (UserVO) session.getAttribute("USER_INFO");
		
		userTemplateVO.setUser_id_fk(userVO.getUser_id());
		userTemplateVO.setUtemplate_abb(userTemplateVO.getUtemplate_abb().toUpperCase());
		userTemplateVO.setUtemplate_original(userTemplateVO.getUtemplate_original().toUpperCase());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id_fk", userVO.getUser_id());
		map.put("utemplate_abb", userTemplateVO.getUtemplate_abb());
		
		String abb = service.getAbb(map);
		
		if(abb != null) {
			if(abb.equals(userTemplateVO.getUtemplate_abb())) {
				String msg = "존재하는 약어";
				
				model.addAttribute("msg", msg);
				
				return "jsonView";
			}
		}
		
		
		int result = service.insertUserTemplate(userTemplateVO);
		
		if(result>0) {
			logger.debug("등록 성공");
		}
		
		return "jsonView";
	}
	
	/**
	* Method : readTemplate
	* 작성자 : 손주형
	* 변경이력 :
	* @return
	* Method 설명 : 사용자가 수정하고자 하는 템플릿 클릭 후 수정버튼 클릭 시
	* 		  	     해당 템플릿을 수정화면에 출력하고는 하는 메서드
	*/
	@RequestMapping(path = "/updateUserTemplate", method=RequestMethod.POST)
	public String updateUserTemplate(Model model,HttpSession session, UserTemplateVO userTemplateVO) {
		logger.debug("수정화면에서 컨트롤러 보내기 : {}", userTemplateVO);
		
		UserVO userVO = (UserVO) session.getAttribute("USER_INFO");
		
		userTemplateVO.setUser_id_fk(userVO.getUser_id());
		userTemplateVO.setUtemplate_abb(userTemplateVO.getUtemplate_abb().toUpperCase());
		userTemplateVO.setUtemplate_original(userTemplateVO.getUtemplate_original().toUpperCase());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id_fk", userTemplateVO.getUser_id_fk());
		map.put("utemplate_abb", userTemplateVO.getUtemplate_abb());
		
		String abb = service.getAbb(map);
		
		if(abb != null) {
			if(abb.equals(userTemplateVO.getUtemplate_abb())) {
				String msg = "존재하는 약어";
				
				model.addAttribute("msg", msg);
				
				return "jsonView";
			}
		}
		
		int result = service.updateUserTemplate(userTemplateVO);
		
		if(result > 0) {
			logger.debug(result + "수정 성공");
		}
		
		return "jsonView";
	}
	
	/**
	* Method : deleteUserTemplate
	* 작성자 : 손주형
	* 변경이력 :
	* @param model
	* @param userTemplateVO
	* @return
	* Method 설명 : 사용자가 선택한 템플릿을 삭제하는 메서드
	*/
	@ResponseBody
	@RequestMapping(path = "/deleteUserTemplate", method = RequestMethod.POST)
	public String deleteUserTemplate(Model model, HttpSession session, UserTemplateVO userTemplateVO) {
		UserVO userVO = (UserVO) session.getAttribute("USER_INFO");
		
		userTemplateVO.setUser_id_fk(userVO.getUser_id());
		
		logger.debug("삭제 VO : {}", userTemplateVO);
		
		int result = service.deleteUserTemplate(userTemplateVO);
		
		if(result>0)
			logger.debug(result + "개 삭제 성공");
		
		return "";
	}
	
	
	/**
	* Method : userTemplateFunc
	* 작성자 : 손주형
	* 변경이력 :
	* @return
	* Method 설명 : 사용자가 템플릿 기능을 실했했을 때
	* 			사용자가 원하는 약어를 가져와서
	* 			DB에있는 약어와 일치하는 원문을
	* 			리턴
	*/
	@RequestMapping(path="/getOriginal", method=RequestMethod.POST)
	public String userTemplateFunc(Model model, HttpSession session, UserTemplateVO userTemplateVO) {
		logger.debug("사용자 약어 utemplate_abb : {}", userTemplateVO);
		
		UserVO userVO = (UserVO) session.getAttribute("USER_INFO");
		userTemplateVO.setUser_id_fk(userVO.getUser_id());
		userTemplateVO.setUtemplate_abb(userTemplateVO.getUtemplate_abb().toUpperCase());
		String utemplate_original = service.getOriginal(userTemplateVO);
		
		String result = "";
		if(utemplate_original == null) {
			result = "없는 약어입니다.";
		}else {
			result = utemplate_original;
		}
		
		model.addAttribute("result", result);
		
		return "jsonView";
	}
}
