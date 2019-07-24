package kr.or.ddit.crew.model;

public class CrewVO {
	private String account_id_fk;   // 계정아이디
	private String user_id_fk;		// 사용자아이디
	
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
		return "CrewVO [account_id_fk=" + account_id_fk + ", user_id_fk=" + user_id_fk + "]";
	}
	public CrewVO(String account_id_fk, String user_id_fk) {
		super();
		this.account_id_fk = account_id_fk;
		this.user_id_fk = user_id_fk;
	}
	public CrewVO() {
		
	}
}
