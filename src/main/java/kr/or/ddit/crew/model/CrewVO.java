package kr.or.ddit.crew.model;

public class CrewVO {
	private String account_id_fk;   // 계정아이디
	private String user_id_fk;		// 사용자아이디
	private String user_name;
	private String user_email;
	private String user_path;
	
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
	public String getAccount_id_fk() {
		return account_id_fk;
	}
	public void setAccount_id_fk(String account_id_fk) {
		this.account_id_fk = account_id_fk;
	}
	public String getUser_id_fk() {
		return user_id_fk;
	}
	public void setUser_id_fk(String user_id_fk) {
		this.user_id_fk = user_id_fk;
	}
	
	@Override
	public String toString() {
		return "CrewVO [account_id_fk=" + account_id_fk + ", user_id_fk=" + user_id_fk + ", user_name=" + user_name
				+ ", user_email=" + user_email + ", user_path=" + user_path + "]";
	}
	public CrewVO(String account_id_fk, String user_id_fk) {
		super();
		this.account_id_fk = account_id_fk;
		this.user_id_fk = user_id_fk;
	}
	public CrewVO() {
	}
	
	
}
