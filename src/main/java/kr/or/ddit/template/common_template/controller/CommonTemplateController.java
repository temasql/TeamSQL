package kr.or.ddit.template.common_template.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.page.model.PageVo;
import kr.or.ddit.template.common_template.model.CommonTemplateVO;
import kr.or.ddit.template.common_template.service.ICommonTemplateService;

@RequestMapping("/commonTemplate")
@Controller
public class CommonTemplateController {

	private static final Logger logger = LoggerFactory.getLogger(CommonTemplateController.class);
	
	@Resource(name = "commonTemplateService")
	private ICommonTemplateService templateService;
	
	
	/**
	* Method : templateManager
	* 작성자 : 이영은
	* 변경이력 :
	* @return
	* Method 설명 : 공통 템플릿 관리 화면 요청
	*/
	@RequestMapping(path =  "/manager", method = RequestMethod.GET)
	public String templateManager() {
		return "/admin/templateMG.tiles";
	}
	
	
	/**
	* Method : templateList
	* 작성자 : 이영은
	* 변경이력 :
	* @param pageVo
	* @param templateVo
	* @param model
	* @return
	* Method 설명 : 템플릿 페이징 리스트 화면
	*/
	@RequestMapping(path =  "/templateList", method = RequestMethod.POST)
	public String templateList(PageVo pageVo, CommonTemplateVO templateVo, Model model) {
		Map<String, Object> pageMap = new HashMap<String, Object>();
		
		pageMap.put("page", pageVo.getPage());
		pageMap.put("pageSize", pageVo.getPageSize());
		
		Map<String, Object> resultMap = templateService.templatePagingList(pageMap);
		
		List<CommonTemplateVO> templateList = (List<CommonTemplateVO>) resultMap.get("templateList");
		
		int paginationSize = (int) resultMap.get("paginationSize");
		
		int startPage = ((int)Math.floor((pageVo.getPage()-1)/10)) + 1;
	    if(pageVo.getPage()==1) {
	    	startPage =1;
	    }
	      
	    if(startPage>=2) {
	    	startPage =((int)Math.floor((pageVo.getPage()-1)/10)*10) + 1;
	    }
	      
	    paginationSize = ((int)Math.floor((pageVo.getPage()-1)/10 + 1))*10;
	         
	    int lastpaginationSize= (int) resultMap.get("paginationSize");
	         
	    if(((int)Math.floor((pageVo.getPage()-1)/10 + 1))*10>lastpaginationSize) {
	    	paginationSize= lastpaginationSize;
	    }

	    model.addAttribute("lastpaginationSize", lastpaginationSize);
	    model.addAttribute("startPage", startPage);
		model.addAttribute("templateList", templateList);
		model.addAttribute("pageMap", pageMap);
		model.addAttribute("paginationSize", paginationSize);
		
		return "admin/templateListAjaxHtml";
	}
	
	
	/**
	* Method : addTemplate
	* 작성자 : 이영은
	* 변경이력 :
	* @param templateVo
	* @param model
	* @return
	* Method 설명 : 공통 템플릿 추가
	*/
	@RequestMapping(path = "/addTemplate", method = RequestMethod.POST)
	public String addTemplate(CommonTemplateVO templateVo, Model model) {

		templateVo.setCtemplate_abb(templateVo.getCtemplate_abb().toUpperCase());
		templateVo.setCtemplate_original(templateVo.getCtemplate_original().toUpperCase());
		
		templateService.addTemplate(templateVo);
		
		return "/admin/templateMG.tiles";
	}
	
	/**
	* Method : modifyTemplate
	* 작성자 : 이영은
	* 변경이력 :
	* @param templateVo
	* @param model
	* @return
	* Method 설명 : 공통 템플릿 수정
	*/
	@RequestMapping(path = "/modifyTemplate", method = RequestMethod.POST)
	public String modifyDomain(CommonTemplateVO templateVo, Model model) {
		
		templateVo.setCtemplate_abb(templateVo.getCtemplate_abb().toUpperCase());
		templateVo.setCtemplate_original(templateVo.getCtemplate_original().toUpperCase());
		
		templateService.modifyTemplate(templateVo);

		return "/admin/templateMG.tiles";

	}
	
	
	/**
	* Method : deleteTemplate
	* 작성자 : 이영은
	* 변경이력 :
	* @param ctemplate_id
	* @param model
	* @return
	* Method 설명 : 공통 도메인 삭제
	*/
	@RequestMapping(path = "/deleteTemplate", method = RequestMethod.POST)
	public String deleteDomain(int ctemplate_id, Model model) {
		templateService.deleteTemplate(ctemplate_id);
	
		return "/admin/templateMG.tiles";
	}
	

}
