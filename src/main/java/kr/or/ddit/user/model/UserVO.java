package kr.or.ddit.user.model;

import javax.validation.constraints.Pattern;

/**
* UserVO.java
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
* </pre>
*/
public class UserVO {

	@Pattern(regexp = "^[a-zA-Z]+[a-zA-Z0-9]{4,12}")
	private String user_id;			// 사용자 아이디

	private String user_right;	// 계정 구분
	private String exit_right;		// 탈퇴 구분
	
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}")
	private String user_pw;				// 비밀번호
	
	@Pattern(regexp = "[가-힣]{2,4}")
	private String user_name;			// 이름
	@Pattern(regexp = "^[a-z0-9_+.-]+@([a-z0-9-]+\\.)+[a-z0-9]{2,4}$")
	private String user_email;			// 이메일
	private String user_path;			// 사진 경로
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_right() {
		return user_right;
	}
	public void setUser_right(String user_right) {
		this.user_right = user_right;
	}
	public String getExit_right() {
		return exit_right;
	}
	public void setExit_right(String exit_right) {
		this.exit_right = exit_right;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_path() {
		return user_path;
	}
	public void setUser_path(String user_path) {
		this.user_path = user_path;
	}
	@Override
	public String toString() {
		return "UserVO [user_id=" + user_id + ", user_right=" + user_right + ", exit_right=" + exit_right + ", user_pw="
				+ user_pw + ", user_name=" + user_name + ", user_email=" + user_email + ", user_path=" + user_path
				+ "]";
	}
	
	
	
}
