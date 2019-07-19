package kr.or.ddit.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SessionCheckInterceptor extends HandlerInterceptorAdapter{

	/**
	* Method : preHandle
	* 작성자 : PC25
	* 변경이력 :
	* @param request
	* @param response
	* @param handler
	* @return
	* @throws Exception
	* Method 설명 : 로그인 한 사용자만 Controller접근이 가능하도록 체크
	*/
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("USER_INFO") != null)
			return true;
		else {
			response.sendRedirect(request.getContextPath() + "/login");
			return false;
		}
	}

}
