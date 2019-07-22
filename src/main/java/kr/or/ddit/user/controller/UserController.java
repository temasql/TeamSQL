package kr.or.ddit.user.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IUserService;

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
	
   private static final Logger logger = LoggerFactory.getLogger(UserController.class);

   /**
   * Method : signInGet
   * 작성자 : 이중석
   * 변경이력 :
   * @return
   * Method 설명 : 회원가입 페이지 요청
   */
   @RequestMapping(path = "/signIn", method = RequestMethod.GET)
   public String signInGet() { return "/user/signIn"; }
   
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
		   ,MultipartFile profile) {
      
      if(result.hasErrors()) {
         return "/user/signIn";
      }
      
      // 등록
      if(userService.insertUser(userVo, profile) == 1)
    	  // 로그인페이지로 이동
    	  return "/login/login.tiles";
      
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
}