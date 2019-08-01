package kr.or.ddit.crew.model;

public class UserAndCrewVO {
	private String account_id_fk;
	private String user_id_fk;
	private String user_name;
	
	public UserAndCrewVO() {
	}

	public UserAndCrewVO(String account_id_fk, String user_id_fk, String user_name) {
		super();
		this.account_id_fk = account_id_fk;
		this.user_id_fk = user_id_fk;
		this.user_name = user_name;
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
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	@Override
	public String toString() {
		return "UserAndCrewVO [account_id_fk=" + account_id_fk + ", user_id_fk=" + user_id_fk + ", user_name="
				+ user_name + "]";
	}
}
