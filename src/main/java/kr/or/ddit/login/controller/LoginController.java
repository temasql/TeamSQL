package kr.or.ddit.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.user.model.UserVO;

@Controller
public class LoginController {
	
	/**
	* Method : userLoginGet
	* 작성자 : OWNER
	* 변경이력 :
	* @return
	* Method 설명 : 로그인 화면 요청
	*/
	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String loginGet() {
		return "tiles.login";
	}
	
	/**
	* Method : userLoginPost
	* 작성자 : OWNER
	* 변경이력 :
	* @param userVo
	* @param result
	* @return
	* Method 설명 : 로그인 화면 응답
	*/
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String loginPost(@Valid UserVO userVo, BindingResult result) {
		
		// 유효성 체크 검증이 실패했을 경우
		if(result.hasErrors()) {
			return "tiles.login"; 
		}
		
		String encryptPassword = KISA_SHA256.encrypt("");
		
		return"tiles.main";
		
	}
	
	/**
	* Method : logOut
	* 작성자 : OWNER
	* 변경이력 :
	* @param request
	* @return
	* Method 설명 : 로그아웃 처리
	*/
	@RequestMapping(path = "/logOut")
	public String logOut(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		// session 모든 정보 삭제
		session.invalidate();
		return "tiles.login";
	}
	
	@RequestMapping(path = "/main")
	public String main() {
		return "tiles.main";
	}
}
