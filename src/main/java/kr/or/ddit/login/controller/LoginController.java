package kr.or.ddit.login.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.invite.model.InviteVO;
import kr.or.ddit.invite.service.IInviteService;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.util.ReMemberMeCookieUtil;

@Controller
public class LoginController {
	
	@Resource(name = "userService")
	private IUserService userService;
	@Resource(name = "inviteService")
	private IInviteService inviteService;
	
	/**
	* Method : userLoginGet
	* 작성자 : OWNER
	* 변경이력 :
	* @return
	* Method 설명 : 로그인 화면 요청
	*/
	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String loginGet(String user_id, String user_pw, Model model) {
		
		model.addAttribute("user_id", user_id);
		model.addAttribute("user_pw", user_pw);
		return "/login/login.tiles";
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
	public String loginPost(UserVO userVo, String rememberme, 
			HttpServletResponse response, HttpSession session, Model model, 
			RedirectAttributes redirectAttributes) {
		
		// 입력받은 아이디에 해당하는 사용자 VO
		UserVO loginUserVo = userService.getUser(userVo.getUser_id());
		
		// 요청 받은 비밀번호를 암호화
		String encryptPassword = KISA_SHA256.encrypt(userVo.getUser_pw());
		
		// 입력받은 아이디에 해당하는 사용자 VO객체가 존재하고 VO에 비밀번호와 입력받은 비밀번호가 일치할 때
		if(loginUserVo != null &&loginUserVo.getUser_pw().equals(encryptPassword)) {
			
			// 로그인한 아이디에 해당하는 초대장 리스트
			List<InviteVO> inviteList = inviteService.getInviteList(loginUserVo.getUser_id());
			
			// 초대장 리스트에 초대장이 있으면
			if (inviteList.size() > 0) {
				model.addAttribute("inviteList", inviteList);
			}
			
			// remeberme 쿠키를 생성하는 유틸클래스
			ReMemberMeCookieUtil.rememberMeCookie(userVo.getUser_id(), rememberme, response);
			
			// 로그인한 사용자의 정보를 세션에 저장
			session.setAttribute("USER_INFO", loginUserVo);
			return "main.tiles";
		}
		redirectAttributes.addAttribute("user_id", userVo.getUser_id());
		redirectAttributes.addAttribute("user_pw", userVo.getUser_pw());
		return "redirect:/login";
		
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
		return "/login/login.tiles";
	}
	
}
