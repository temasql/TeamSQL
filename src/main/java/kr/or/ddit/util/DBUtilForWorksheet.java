package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// JDBC드라이버를 로딩하고 Connection객체를 생성하는 메서드로 구성된 class 작성

public class DBUtilForWorksheet {
	
	private static final Logger logger = LoggerFactory.getLogger(DBUtilForWorksheet.class);
	
	static{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			logger.debug("DB로딩 실패");
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(String name, String pass, HttpSession session){
		try {
			Connection conn = (Connection) session.getAttribute("conn");
			
			if(conn != null) 
				return conn;
			else {
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", name, pass);
				session.setAttribute("conn", conn);
				return conn;
			}
			
		} catch (SQLException e) {
			logger.debug("오라클 연결 실패");
			return null;
		}
	}
}