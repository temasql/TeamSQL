package kr.or.ddit.domain.common_domain.controller;

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

import kr.or.ddit.domain.common_domain.model.CommonDomainVO;
import kr.or.ddit.domain.common_domain.service.ICommonDomainService;
import kr.or.ddit.page.model.PageVo;
import kr.or.ddit.util.DataTypeUtil;

@RequestMapping("/commonDomain")
@Controller
public class CommonDomainController {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonDomainController.class);
	
	@Resource(name = "commonDomainService")
	private ICommonDomainService domainService;

	
	/**
	* Method : domainManager
	* 작성자 : 이영은
	* 변경이력 :
	* @return
	* Method 설명 : 공통 도메인 관리 화면 요청
	*/
	@RequestMapping(path =  "/manager", method = RequestMethod.GET)
	public String domainManager() {
		return "/admin/domainMG.tiles";
	}
	
	
	/**
	* Method : domainList
	* 작성자 : 이영은
	* 변경이력 :
	* @param pageVo
	* @param domainVo
	* @param model
	* @return
	* Method 설명 : 도메인 페이징 리스트 화면
	*/
	@RequestMapping(path = "/domainList", method = RequestMethod.POST)
	public String domainList(PageVo pageVo, CommonDomainVO domainVo, Model model) {
		Map<String, Object> pageMap = new HashMap<String, Object>();
		
		pageMap.put("page", pageVo.getPage());
		pageMap.put("pageSize", pageVo.getPageSize());

		Map<String, Object> resultMap = domainService.domainPagingList(pageMap);
		
		List<CommonDomainVO> domainList = (List<CommonDomainVO>) resultMap.get("domainList");
		
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
		model.addAttribute("domainList", domainList);
		model.addAttribute("pageMap", pageMap);
		model.addAttribute("paginationSize", paginationSize);
		
		return "admin/domainListAjaxHtml";
	}
	
	
	/**
	* Method : addDomain
	* 작성자 : 이영은
	* 변경이력 :
	* @param cdomain_name
	* @param cdomain_type
	* @param model
	* @return
	* Method 설명 : 공통 도메인 추가
	*/
	@RequestMapping(path = "/insertCommonDomain", method = RequestMethod.POST)
	public String addDomain(CommonDomainVO domainVo, Model model) {
		domainVo.setCdomain_name(domainVo.getCdomain_name().toUpperCase());
		domainVo.setCdomain_type(domainVo.getCdomain_type().toUpperCase());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cdomain_name", domainVo.getCdomain_name());
		
		String name = domainService.getName(map);
		
		logger.debug("!!!! domainVo : {}", domainVo);
		logger.debug("!!!! domainVo : {}",domainVo.getCdomain_type().toUpperCase());
		
		
		if(name != null) {
			if(name.contentEquals(domainVo.getCdomain_name())) {
				String msg = "존재하는 도메인";
				
				model.addAttribute("msg", msg);
				return "jsonView";
			}
		}

		// 입력 받은 데이터 타입이 유효 여부 체크
		List<String> dataTypeList = DataTypeUtil.tableDataType();
		
		int cnt =0;
		for (String dataType : dataTypeList) {
			logger.debug("!!!!!dataType : {}",dataType);
			logger.debug("!!!!!boolean : {}",domainVo.getCdomain_type().toUpperCase().startsWith(dataType));
			if(domainVo.getCdomain_type().toUpperCase().startsWith(dataType)==true) {
				cnt++;
			}
		}
		if(cnt ==0) {
			String msg = "유효하지 않는 데이터 타입";
			model.addAttribute("typeMsg", msg);
			return "jsonView";
		}
				
		int result = domainService.addDomain(domainVo);
		
		if(result > 0) {
			logger.debug("도메인 추가 성공");
		}
		
		return "jsonView";
	}
	
	
	/**
	* Method : modifyDomain
	* 작성자 : 이영은
	* 변경이력 :
	* @param domainVo
	* @param model
	* @return
	* Method 설명 : 공통 도메인 수정
	*/
	@RequestMapping(path = "/updateCommonDomain", method = RequestMethod.POST)
	public String modifyDomain(CommonDomainVO domainVo, Model model) {
		logger.debug("updatedomain domainVo: {}", domainVo);
		
		domainVo.setCdomain_name(domainVo.getCdomain_name().toUpperCase());
		domainVo.setCdomain_type(domainVo.getCdomain_type().toUpperCase());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cdomain_id", domainVo.getCdomain_id());
		map.put("cdomain_name", domainVo.getCdomain_name());
		map.put("cdomain_type", domainVo.getCdomain_type());
		
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
			logger.debug("!!!!!boolean : {}", domainVo.getCdomain_type().toUpperCase().startsWith(dataType));
			if(domainVo.getCdomain_type().toUpperCase().startsWith(dataType)==true) {
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
		if(findName.equals(domainVo.getCdomain_name())) {
			result = domainService.modifyDomain(domainVo);
			return "jsonView";
		}
		
		// 입력한 도메인명이 DB에 없을 시 업데이트
		if(findName.equals("")) {
			result = domainService.modifyDomain(domainVo);
		} else if (name.equals(domainVo.getCdomain_name())) {
			String msg = "존재하는 도메인";
			
			model.addAttribute("msg", msg);
			return "jsonView";
		} else if(!findName.equals(domainVo.getCdomain_name())) {
			result = domainService.modifyDomain(domainVo);
		}
		return "jsonView";
	}
	
	
	/**
	* Method : deleteDomain
	* 작성자 : 이영은
	* 변경이력 :
	* @param cdomain_id
	* @param model
	* @return
	* Method 설명 : 공통 도메인 삭제
	*/
	@RequestMapping(path = "/deleteDomain", method = RequestMethod.POST)
	public String deleteDomain(int cdomain_id, Model model) {
		domainService.deleteDomain(cdomain_id);
	
		return "/admin/domainMG.tiles";
	}
	
}
