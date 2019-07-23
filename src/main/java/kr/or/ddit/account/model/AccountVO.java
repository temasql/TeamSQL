package kr.or.ddit.account.model;

import javax.validation.constraints.Pattern;

public class AccountVO {
	
	private String account_id; // 계정아이디
	private String user_id_fk; // 사용자아이디
	private String account_pw; // 계정비밀번호
	
	public String getAccount_id() {
		return account_id;
	}
	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}
	public String getUser_id_fk() {
		return user_id_fk;
	}
	public void setUser_id_fk(String user_id_fk) {
		this.user_id_fk = user_id_fk;
	}
	public String getAccount_pw() {
		return account_pw;
	}
	public void setAccount_pw(String account_pw) {
		this.account_pw = account_pw;
	}
	@Override
	public String toString() {
		return "AccountVO [account_id=" + account_id + ", user_id_fk=" + user_id_fk + ", account_pw=" + account_pw
				+ "]";
	}
	public AccountVO(String account_id, String user_id_fk, String account_pw) {
		super();
		this.account_id = account_id;
		this.user_id_fk = user_id_fk;
		this.account_pw = account_pw;
	}
	public AccountVO() {
		
	}
	
	
}
