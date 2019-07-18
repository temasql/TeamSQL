package kr.or.ddit.blacklist.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class BlackListVO {
	
	private int black_id;               // 블랙리스트아이디
	private String user_id_fk;          // 사용자아이디
	private String reason;              // 사유
	
	@DateTimeFormat(pattern = "yyyy-MM-dd a hh:mm:ss")
	private Date register_dt;           // 등록일시
	private String reg_user_id_fk;      // 등록자아이디
	
	@DateTimeFormat(pattern = "yyyy-MM-dd a hh:mm:ss")
	private Date cancel_dt;             // 해지일시
	private String cancel_user_id_fk;   // 해지자아이디
	
	public int getBlack_id() {
		return black_id;
	}
	public void setBlack_id(int black_id) {
		this.black_id = black_id;
	}
	public String getUser_id_fk() {
		return user_id_fk;
	}
	public void setUser_id_fk(String user_id_fk) {
		this.user_id_fk = user_id_fk;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Date getRegister_dt() {
		return register_dt;
	}
	public void setRegister_dt(Date register_dt) {
		this.register_dt = register_dt;
	}
	public String getReg_user_id_fk() {
		return reg_user_id_fk;
	}
	public void setReg_user_id_fk(String reg_user_id_fk) {
		this.reg_user_id_fk = reg_user_id_fk;
	}
	public Date getCancel_dt() {
		return cancel_dt;
	}
	public void setCancel_dt(Date cancel_dt) {
		this.cancel_dt = cancel_dt;
	}
	public String getCancel_user_id_fk() {
		return cancel_user_id_fk;
	}
	public void setCancel_user_id_fk(String cancel_user_id_fk) {
		this.cancel_user_id_fk = cancel_user_id_fk;
	}
	
	@Override
	public String toString() {
		return "BlackListVO [black_id=" + black_id + ", user_id_fk=" + user_id_fk + ", reason=" + reason
				+ ", register_dt=" + register_dt + ", reg_user_id_fk=" + reg_user_id_fk + ", cancel_dt=" + cancel_dt
				+ ", cancel_user_id_fk=" + cancel_user_id_fk + "]";
	}
}
