package kr.or.ddit.user.controller;

import java.sql.Connection;
import java.sql.SQLException;
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
	   
	   // 회원가입 실패 후 다시 돌아왔을때 등록한 정보가 form에 남아있게 하기위한 작업
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
    	  model.addAttribute("btnSignIn", "true");
         return "/user/signIn";
      }
      
      // 등록
      if(userService.insertUser(userVo, profile) == 1)
    	  // 로그인페이지로 이동
    	  return "/login/login";
      
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
	
		// application-context에서 빈으로 등록한 profileView클래스로 사용자 정보를 가지고 이동 
		return "profileView";
	}
	
	/**
	* Method : idCheck
	* 작성자 : 이중석
	* 변경이력 :
	* @param user_id
	* @param model
	* @return
	* Method 설명 : 사용자 아이디 중복 체크 버튼을 클릭 시 
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
	
	/**
	* Method : adminIdCheck
	* 작성자 : 이중석
	* 변경이력 :
	* @param userVo
	* @param model
	* @return
	* Method 설명 : 관리자 아이디 중복체크
	*/
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
		Connection conn =  (Connection) session.getAttribute("conn");
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		session.invalidate(); // session안에 있는 모든 정보를 삭제하는 메서드
		return "/login/login";
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
			Model model, MultipartFile profile, HttpSession session) {
		
		if(result.hasErrors()) {
	    	  model.addAttribute("userVo", userVo);
	         return "/user/myPageModify.tiles";
	      }
		if(userService.updateUser(userVo, profile) == 1) {
			// 수정된 정보 가지고 오기
			model.addAttribute("userVo", userService.getUser(userVo.getUser_id()));
			session.setAttribute("USER_INFO", userService.getUser(userVo.getUser_id()));
			return "/user/myPageMain.tiles";
		}
		return "/user/myPageModify.tiles";
	}
	
	/**
	* Method : deleteUser
	* 작성자 : 이중석
	* 변경이력 :
	* @param userVo
	* @param model
	* @param session
	* @return
	* Method 설명 : 사용자 삭제
	*/
	@RequestMapping("/deleteUser")
	public String deleteUser(UserVO userVo, Model model, HttpSession session) {
		
		// 삭제 전 사용자의 비밀번호를 재입력 받음
		userVo.setUser_pw(KISA_SHA256.encrypt(userVo.getUser_pw()));
		
		// 사용자의 아이디와 입력한 비밀번호가 일치하면 삭제
		int deleteUserCount = userService.deleteUser(userVo);
		if (deleteUserCount == 1) {
			
			// 삭제 후 세션을 비움
			session.invalidate();
			
			// userVo의 Null여부에 따라 ajax에서 응답하는 메시지가 달라짐
			model.addAttribute("userVo", userVo);
		}
		return "jsonView";
	}
	
	
	/**
	* Method : userManager
	* 작성자 : 이중석
	* 변경이력 :
	* @return
	* Method 설명 : 관리자 회원 관리 메인 요청 화면
	*/
	@RequestMapping(path = "/userManager", method = RequestMethod.GET)
	public String userManager() {
		
		return "/admin/userMG.tiles";
	}
	
	/**
	* Method : userManager
	* 작성자 : 이중석
	* 변경이력 :
	* @param search
	* @param pageVo
	* @param model
	* @return
	* Method 설명 : 관리자 회원관리 메인 페이징 처리 응답 화면
	*/
	@RequestMapping(path = "/userManager", method = RequestMethod.POST)
	public String userManager(@RequestParam(name = "searchfor", defaultValue = "") String search,
			PageVo pageVo, Model model) {
		
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("search", search);
		
		pageMap.put("page", pageVo.getPage());
		
		pageMap.put("pageSize", pageVo.getPageSize());
		Map<String, Object> resultMap = userService.userList(pageMap);
		
		List<UserVO>userList = (List<UserVO>) resultMap.get("userList");
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
	         
		model.addAttribute("userList", userList);
		model.addAttribute("pageMap", pageMap);
		model.addAttribute("lastpaginationSize", lastpaginationSize);
		model.addAttribute("startPage", startPage);
		model.addAttribute("paginationSize", paginationSize);
		return "admin/userMGAjaxHtml";
	}
	
	/**
	* Method : deleteUserMG
	* 작성자 : 이중석
	* 변경이력 :
	* @param deleteCheck
	* @param model
	* @return
	* Method 설명 : 관리자 회원 삭제 
	*/
	@RequestMapping("/deleteUserMG")
	public String deleteUserMG(String[] deleteCheck, Model model) {
		
		for (int i = 0; i < deleteCheck.length; i++) {
			userService.deleteUserMG(deleteCheck[i]);
		}
		return userManager();
	}
	
	/**
	* Method : adminManager
	* 작성자 : 이중석
	* 변경이력 :
	 * @param model 
	* @return
	* Method 설명 : 관리자 관리 메인 요청 화면
	*/
	@RequestMapping(path = "/adminManager", method = RequestMethod.GET)
	public String adminManager(Model model) {
		return "/admin/adminMG/adminMGMain.tiles";
	}
	
	/**
	* Method : adminManager
	* 작성자 : 이중석
	* 변경이력 :
	* @param search
	* @param pageVo
	* @param model
	* @return
	* Method 설명 : 관리자 관리 메인 페이징 처리 응답화면
	*/
	@RequestMapping(path = "/adminManager", method = RequestMethod.POST)
	public String adminManager(@RequestParam(name = "searchfor", defaultValue = "") String search,
			PageVo pageVo, Model model) {
		
		Map<String, Object> pageMap = new HashMap<String, Object>();

		pageMap.put("search", search);
		pageMap.put("page", pageVo.getPage());
		pageMap.put("pageSize", pageVo.getPageSize());

		Map<String, Object> resultMap = userService.adminList(pageMap);
		List<UserVO>adminList = (List<UserVO>) resultMap.get("adminList");
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
		model.addAttribute("adminList", adminList);
		model.addAttribute("pageMap", pageMap);
		model.addAttribute("paginationSize", paginationSize);
		
		return "/admin/adminMG/adminMGAjaxHtml";
	}

	/**
	* Method : insertAdminGet
	* 작성자 : 이중석
	* 변경이력 :
	* @param userVo
	* @param model
	* @return
	* Method 설명 : 관리자 추가 요청화면
	*/
	@RequestMapping(path = "/insertAdmin", method = RequestMethod.GET)
	public String insertAdminGet(UserVO userVo, Model model) { 
		model.addAttribute("userVo", userVo);
		return "/admin/adminMG/adminMGInsert.tiles"; 
	}
	
	/**
	 * Method : insertAdminPost
	 * 작성자 : 이중석
	 * 변경이력 :
	 * @param userVo
	 * @param result
	 * @param model
	 * @return
	 * Method 설명 : 관리자 추가 응답화면
	 */
	@RequestMapping(path = "/insertAdmin", method = RequestMethod.POST)
	public String insertAdminPost(@Valid UserVO userVo, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			model.addAttribute("userVo", userVo);
			return "/admin/adminMG/adminMGInsert.tiles";
		}
		
		// 등록
		if(userService.insertAdmin(userVo) == 1) {
			model.addAttribute("msg", "관리자" + userVo.getUser_id() + "를 추가했습니다.");
			// 로그인페이지로 이동
			return adminManager(model);
		}
		model.addAttribute("userVo", userVo);
		return "/admin/adminMG/adminMGInsert.tiles";
	}

	/**
	* Method : deleteAdminMG
	* 작성자 : 이중석
	* 변경이력 :
	* @param deleteCheck
	* @param model
	* @return
	* Method 설명 : 관리자 삭제
	*/
	@RequestMapping("/deleteAdminMG")
	public String deleteAdminMG(String[] deleteCheck, Model model, HttpSession session) {
		
		UserVO userVo =  (UserVO) session.getAttribute("USER_INFO");
		
		for (int i = 0; i < deleteCheck.length; i++) {
			if (deleteCheck[i].equals(userVo.getUser_id())) {
				model.addAttribute("msg", "자기 자신은 삭제 시킬 수 없습니다.");
				return adminManager(model);
			}
			userService.deleteUserMG(deleteCheck[i]);
			model.addAttribute("msg", "관리자 [" + deleteCheck[i] + "] 을 삭제했습니다.");
		}
		return adminManager(model);
	}
	
}