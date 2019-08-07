package kr.or.ddit.template.user_template.controller;

import java.util.List;

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
		
		model.addAttribute("templateList", templateList);
		
		return "jsonView";
	}
	
	@ResponseBody
	@RequestMapping(path =  "/insertUserTemplate", method = RequestMethod.POST)
	public int insertUserTemplate(Model model,HttpSession session, UserTemplateVO userTemplateVO) {
		logger.debug("유저템플릿 등록 : {}", userTemplateVO);
		
		UserVO userVO = (UserVO) session.getAttribute("USER_INFO");
		
		userTemplateVO.setUser_id_fk(userVO.getUser_id());
		
		userTemplateVO.setUtemplate_abb(userTemplateVO.getUtemplate_abb().toUpperCase());
		userTemplateVO.setUtemplate_original(userTemplateVO.getUtemplate_original().toUpperCase());
		
		int result = service.insertUserTemplate(userTemplateVO);
		
		
		
		return result;
	}
}
