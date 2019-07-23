package kr.or.ddit.user.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		
		UserVO checkUser = userService.getUser(userVo.getUser_id());
		Pattern pattern = Pattern.compile("^[a-zA-Z]+[a-zA-Z0-9]{4,12}");  
        Matcher match = pattern.matcher(userVo.getUser_id()); 
        boolean bool = match.matches();
        String msg = "형식에 맞게 아이디를 입력해주세요";
        
        if(bool) {
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
}