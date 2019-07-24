package kr.or.ddit.invite.model;

public class InviteVO {
	private int invite_id;			// 초대아이디
	private String account_id_fk;   // 계정아이디
	private String user_id_fk;      // 사용자아이디
	public int getInvite_id() {
		return invite_id;
	}
	public void setInvite_id(int invite_id) {
		this.invite_id = invite_id;
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
	
}
