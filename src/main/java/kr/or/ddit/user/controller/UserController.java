package kr.or.ddit.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.page.model.PageVo;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.util.FindUserPwByMail;

/**
* UserController.java
*
* @author 이중석
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
* 수정자 수정내용
* ------ ------------------------
* 이중석 최초 생성
* 
* 일반회원에 관한 컨트롤러
*
* </pre>
*/
@RequestMapping("/user")
@Controller
public class UserController {

	@Resource(name = "userService")
	private IUserService userService;

	
    /**
    * Method : naverCall
    * 작성자 : 이중석
    * 변경이력 :
    * @return
    * Method 설명 : 네이버로 회원가입 시 네이버 정보를 리턴해줄 jsp로 이동
    */
    @RequestMapping("naverCallback")
    public String naverCall() {
    	return "callback";
    }
   
   /**
   * Method : signInGet
   * 작성자 : 이중석
   * 변경이력 :
   * @return
   * Method 설명 : 회원가입 페이지 요청
   */
   @RequestMapping(path = "/signIn", method = RequestMethod.GET)
   public String signInGet(UserVO userVo, Model model) { 
	   model.addAttribute("userVo", userVo);
	   return "/user/signIn"; 
   }
   
   /**
   * Method : signInPost
   * 작성자 : 이중석
   * 변경이력 :
   * @param userVo
   * @param result
   * @return
   * Method 설명 : 회원가입 페이지 응답
   */
   @RequestMapping(path = "/signIn", method = RequestMethod.POST)
   public String signInPost(@Valid UserVO userVo, BindingResult result
		   ,MultipartFile profile, Model model) {
      
      if(result.hasErrors()) {
    	  model.addAttribute("userVo", userVo);
         return "/user/signIn";
      }
      
      // 등록
      if(userService.insertUser(userVo, profile) == 1)
    	  // 로그인페이지로 이동
    	  return "/login/login.tiles";
      
      model.addAttribute("userVo", userVo);
      return "/user/signIn";
   }
   
   
   /**
	* Method : profile
	* 작성자 : 이중석
	* 변경이력 :
	* @param user_id
	* @param model
	* @return
	* Method 설명 : 사용자 프로필 사진
	*/
	@RequestMapping("/profile") 
	public String profile(String user_id, Model model) { 
		UserVO userVo = userService.getUser(user_id);
		model.addAttribute("userVo", userVo);
		return "profileView";
	}
	
	/**
	* Method : idCheck
	* 작성자 : 이중석
	* 변경이력 :
	* @param user_id
	* @param model
	* @return
	* Method 설명 : 아이디 중복 체크 버튼을 클릭 시 
	*/
	@RequestMapping("/idCheck")
	public String idCheck(UserVO userVo, Model model) {
		
		// 입력한 아이디에 해당하는 회원정보
		UserVO checkUser = userService.getUser(userVo.getUser_id());
		
		// 입력한 아이디의 정규식 검사
		Pattern pattern = Pattern.compile("^[a-zA-Z]+[a-zA-Z0-9]{4,12}");  
        Matcher match = pattern.matcher(userVo.getUser_id()); 
        boolean bool = match.matches();
        
        String msg = "형식에 맞게 아이디를 입력해주세요";
        
        // 아이디가 정규식에 맞다면
        if(bool) {
        	
        	// 입력한 아이뎅 해당하는 회원정보가 없으면
        	if(checkUser == null) {
        		msg = "사용가능한 아이디입니다.";
        		model.addAttribute("btnSignIn", "true");
        	} else {
        		msg = "이미 사용중인 아이디입니다.";
        	}
        	
        }
		model.addAttribute("msg", msg);
		model.addAttribute("userVo", userVo);
		return "/user/signIn";
	}
	@RequestMapping("/adminIdCheck")
	public String adminIdCheck(UserVO userVo, Model model) {
		
		// 입력한 아이디에 해당하는 회원정보
		UserVO checkUser = userService.getUser(userVo.getUser_id());
		
		// 입력한 아이디의 정규식 검사
		Pattern pattern = Pattern.compile("^[a-zA-Z]+[a-zA-Z0-9]{4,12}");  
		Matcher match = pattern.matcher(userVo.getUser_id()); 
		boolean bool = match.matches();
		
		String msg = "형식에 맞게 아이디를 입력해주세요";
		
		// 아이디가 정규식에 맞다면
		if(bool) {
			
			// 입력한 아이뎅 해당하는 회원정보가 없으면
			if(checkUser == null) {
				msg = "사용가능한 아이디입니다.";
				model.addAttribute("btnSignIn", "true");
			} else {
				msg = "이미 사용중인 아이디입니다.";
			}
			
		}
		model.addAttribute("msg", msg);
		model.addAttribute("userVo", userVo);
		return "/admin/adminMG/adminMGInsert.tiles";
	}
	
	/**
	* Method : findUserId
	* 작성자 : 이중석
	* 변경이력 :
	* @param userVo
	* @param model
	* @return
	* Method 설명 : 아이디 찾기 ajax
	*/
	@RequestMapping("/findUserId")
	public String findUserId(UserVO userVo, Model model) {
		model.addAttribute("user_id", userService.findUserId(userVo));
		return "jsonView";
	}
	
	/**
	* Method : findUserPw
	* 작성자 : 이중석
	* 변경이력 :
	* @param userVo
	* @param model
	* @return
	* Method 설명 : 비밀번호 찾기 ajax
	*/
	@RequestMapping("/findUserPw")
	public String findUserPw(UserVO userVo, Model model) {
		
		String findUserPw = userService.findUserPw(userVo);
		
		if (findUserPw != null) {
			userVo.setUser_pw(KISA_SHA256.encrypt(
					new FindUserPwByMail().sendMail(
							userVo.getUser_email(), userVo.getUser_id()))
					);
			userService.temporaryUpdateUserPw(userVo);
		}
		model.addAttribute("userVo", userVo);
		return "jsonView";
	}
	
	/**
	* Method : logOut
	* 작성자 : 이중석
	* 변경이력 :
	* @param session
	* @return
	* Method 설명 : header에 로그아웃 클릭 시 세션의 정보 삭제
	*/
	@RequestMapping("/logout")
	public String logOut(HttpSession session) {
		session.invalidate(); // session안에 있는 모든 정보를 삭제하는 메서드
		return "/login/login.tiles";
	}
	
	/**
	* Method : myPageMain
	* 작성자 : 이중석
	* 변경이력 :
	* @param session
	* @param model
	* @return
	* Method 설명 : 마이페이지 조회
	*/
	@RequestMapping("/mypage")
	public String myPageMain(HttpSession session, Model model) {
		
		model.addAttribute("userVo", (UserVO)session.getAttribute("USER_INFO"));
		return "/user/myPageMain.tiles";
	}
	
	/**
	* Method : modifyUserGet
	* 작성자 : 이중석
	* 변경이력 :
	* @param user_id
	* @param model
	* @return
	* Method 설명 : 회원 정보 수정 요청 화면
	*/
	@RequestMapping(path = "/modifyUser", method = RequestMethod.GET)
	public String modifyUserGet(String user_id, Model model) {
		
		UserVO userVo = userService.getUser(user_id);
		
		model.addAttribute("userVo", userVo);
		return "/user/myPageModify.tiles";
	}
	
	/**
	* Method : modifyUserPost
	* 작성자 : 이중석
	* 변경이력 :
	* @param userVo
	* @param result
	* @param model
	* @return
	* Method 설명 : 회원 정보 수정 응답 화면
	*/
	@RequestMapping(path = "/modifyUser", method = RequestMethod.POST)
	public String modifyUserPost(@Valid UserVO userVo, BindingResult result ,
			Model model, MultipartFile profile) {
		
		if(result.hasErrors()) {
	    	  model.addAttribute("userVo", userVo);
	         return "/user/myPageModify.tiles";
	      }
		if(userService.updateUser(userVo, profile) == 1) {
			// 수정된 정보 가지고 오기
			model.addAttribute("userVo", userService.getUser(userVo.getUser_id()));
			return "/user/myPageMain.tiles";
		}
		return "/user/myPageModify.tiles";
	}
	
	@RequestMapping("/deleteUser")
	public String deleteUser(UserVO userVo, Model model, HttpSession session) {
		
		userVo.setUser_pw(KISA_SHA256.encrypt(userVo.getUser_pw()));
		
		int deleteUserCount = userService.deleteUser(userVo);
		if (deleteUserCount == 1) {
			session.invalidate();
			model.addAttribute("userVo", userVo);
		}
		return "jsonView";
	}
	
	@RequestMapping(path = "/userManager", method = RequestMethod.POST)
	public String userManager(@RequestParam(name = "searchfor", defaultValue = "") String search,
			PageVo pageVo, Model model) {
		
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("search", search);
//		
//		// 페이지 번호
		pageMap.put("page", pageVo.getPage());
//		
//		// 한 페이지에 출력할 게시글 수
		pageMap.put("pageSize", pageVo.getPageSize());
//		
		Map<String, Object> resultMap = userService.userList(pageMap);
		List<UserVO>userList = (List<UserVO>) resultMap.get("userList");
		int paginationSize = (int) resultMap.get("paginationSize");
//		
		model.addAttribute("userList", userList);
		model.addAttribute("pageMap", pageMap);
		model.addAttribute("paginationSize", paginationSize);
		return "admin/userMGAjaxHtml";
	}
	
	@RequestMapping(path = "/userManager", method = RequestMethod.GET)
	public String userManager() {
		
		return "/admin/userMG.tiles";
	}
	
	@RequestMapping(path = "/adminManager", method = RequestMethod.POST)
	public String adminManager(@RequestParam(name = "searchfor", defaultValue = "") String search,
			PageVo pageVo, Model model) {
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("search", search);
//		
//		// 페이지 번호
		pageMap.put("page", pageVo.getPage());
//		
//		// 한 페이지에 출력할 게시글 수
		pageMap.put("pageSize", pageVo.getPageSize());
//		
		Map<String, Object> resultMap = userService.adminList(pageMap);
		List<UserVO>adminList = (List<UserVO>) resultMap.get("adminList");
		int paginationSize = (int) resultMap.get("paginationSize");
//		
		model.addAttribute("adminList", adminList);
		model.addAttribute("pageMap", pageMap);
		model.addAttribute("paginationSize", paginationSize);
		
		return "/admin/adminMG/adminMGAjaxHtml";
	}
	
	@RequestMapping(path = "/adminManager", method = RequestMethod.GET)
	public String adminManager() {
		return "/admin/adminMG/adminMGMain.tiles";
	}

	@RequestMapping("/deleteAdminMG")
	public String deleteAdminMG(String[] deleteCheck, Model model) {
		
		for (int i = 0; i < deleteCheck.length; i++) {
			userService.deleteUserMG(deleteCheck[i]);
		}
		return adminManager();
	}
	
   @RequestMapping(path = "/insertAdmin", method = RequestMethod.GET)
   public String insertAdminGet(UserVO userVo, Model model) { 
	   model.addAttribute("userVo", userVo);
	   return "/admin/adminMG/adminMGInsert.tiles"; 
   }
	
   @RequestMapping(path = "/insertAdmin", method = RequestMethod.POST)
   public String signInPost(@Valid UserVO userVo, BindingResult result, Model model) {
      
      if(result.hasErrors()) {
    	  model.addAttribute("userVo", userVo);
         return "/admin/adminMG/adminMGInsert.tiles";
      }
      
      // 등록
      if(userService.insertAdmin(userVo) == 1)
    	  // 로그인페이지로 이동
    	  return adminManager();
      
      model.addAttribute("userVo", userVo);
      return "/admin/adminMG/adminMGInsert.tiles";
   }
   
   
	@RequestMapping("/deleteUserMG")
	public String deleteUserMG(String[] deleteCheck, Model model) {
		
		for (int i = 0; i < deleteCheck.length; i++) {
			userService.deleteUserMG(deleteCheck[i]);
		}
		return userManager();
	}
	
}