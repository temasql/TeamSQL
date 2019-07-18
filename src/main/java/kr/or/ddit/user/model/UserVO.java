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

	private String account_right;	// 계정 구분
	private String user_right;		// 탈퇴 구분
	
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}")
	private String pw;				// 비밀번호
	
	@Pattern(regexp = "[가-힣]{2,4}")
	private String name;			// 이름
	@Pattern(regexp = "^[a-z0-9_+.-]+@([a-z0-9-]+\\.)+[a-z0-9]{2,4}$")
	private String email;			// 이메일
	private String path;			// 사진 경로
	
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getAccount_right() {
		return account_right;
	}
	public void setAccount_right(String account_right) {
		this.account_right = account_right;
	}
	public String getUser_right() {
		return user_right;
	}
	public void setUser_right(String user_right) {
		this.user_right = user_right;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Override
	public String toString() {
		return "UserVO [user_id=" + user_id + ", account_right=" + account_right + ", user_right=" + user_right
				+ ", pw=" + pw + ", name=" + name + ", email=" + email + ", path=" + path + "]";
	}
	
}
