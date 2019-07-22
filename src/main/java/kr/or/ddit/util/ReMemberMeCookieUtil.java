/**
 * 
 */
package kr.or.ddit.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class ReMemberMeCookieUtil {
	
	/**
	* Method : rememberMeCookie
	* 작성자 : 이중석
	* 변경이력 :
	* @param userId
	* @param rememberme
	* @param response
	* Method 설명 : rememberme 쿠키를 응답으로 생성
	*/
	public static void rememberMeCookie(String user_id, String rememberme, HttpServletResponse response) {

		// rememberme체크가 되어있으면
		if (rememberme != null) { 
			
			// 쿠키 잔여 시간을 담을 변수
			int cookieMaxAge = 0;
			
			// 쿠키의 잔여시간을 담는 변수의 값을 30일로 설정
			cookieMaxAge = 60*60*24*30;
			
			// user_id라는 이름의 쿠키를 생성하고 입력받은 사용자 아이디를 저장
			Cookie userIdCookie = new Cookie("user_id", user_id);
			
			// user_id쿠키의 잔여시간을 30일로 설정
			userIdCookie.setMaxAge(cookieMaxAge);
			
			// rememberme라는 이름의 쿠키를 생성하고 쿠키의 값을 문자열 "true"로 저장
			Cookie rememberMeCookie = new Cookie("rememberme", "true");
			
			// remeberme쿠키의 잔여시간을 30일로 설정
			rememberMeCookie.setMaxAge(cookieMaxAge);
			
			// 쿠키를 만들어 응답
			response.addCookie(userIdCookie);
			response.addCookie(rememberMeCookie);
		}
	}
}
