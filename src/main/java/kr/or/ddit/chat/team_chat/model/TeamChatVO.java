package kr.or.ddit.chat.team_chat.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class TeamChatVO {
	private int chat_id;			// 팀채팅아이디
	private int chat_room_id_fk;    // 채팅방아이디
	private String account_id_fk;   // 계정아이디
	private String user_id_fk;      // 사용자아이디
	private String chat_content;    // 팀채팅내용
	@DateTimeFormat(pattern = "yyyy-MM-dd a hh:mm:ss")
	private Date chat_dt;           // 팀채팅일시
	
	
	public int getChat_id() {
		return chat_id;
	}
	public void setChat_id(int chat_id) {
		this.chat_id = chat_id;
	}
	public int getChat_room_id_fk() {
		return chat_room_id_fk;
	}
	public void setChat_room_id_fk(int chat_room_id_fk) {
		this.chat_room_id_fk = chat_room_id_fk;
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
	public String getChat_content() {
		return chat_content;
	}
	public void setChat_content(String chat_content) {
		this.chat_content = chat_content;
	}
	public Date getChat_dt() {
		return chat_dt;
	}
	public void setChat_dt(Date chat_dt) {
		this.chat_dt = chat_dt;
	}
	@Override
	public String toString() {
		return "TeamChatVO [chat_id=" + chat_id + ", chat_room_id_fk=" + chat_room_id_fk + ", account_id_fk="
				+ account_id_fk + ", user_id_fk=" + user_id_fk + ", chat_content=" + chat_content + ", chat_dt="
				+ chat_dt + "]";
	}
	
}
