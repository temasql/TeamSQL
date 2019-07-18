package kr.or.ddit.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
* ApplicationListener.java
*
* @author PC25
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
* 수정자 수정내용
* ------ ------------------------
* PC25 최초 생성
* jsp파일에서 
* ${PageContext.request.ContextPath}를 ${cp}로 줄여주기 위한 클래스 입니다.
*
* </pre>
*/
public class ApplicationListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext application = sce.getServletContext();
		application.setAttribute("cp", application.getContextPath());
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
