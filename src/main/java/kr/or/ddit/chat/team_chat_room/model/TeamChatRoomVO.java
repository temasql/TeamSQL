package kr.or.ddit.chat.team_chat_room.model;

public class TeamChatRoomVO {
	private int chat_room_id;		// 채팅방아이디
	private String account_id_fk;   // 계정아이디
	private String user_id_fk;      // 개설자아이디
	private String chat_room_name;  // 채팅방명
	
	
	public int getChat_room_id() {
		return chat_room_id;
	}
	public void setChat_room_id(int chat_room_id) {
		this.chat_room_id = chat_room_id;
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
	public String getChat_room_name() {
		return chat_room_name;
	}
	public void setChat_room_name(String chat_room_name) {
		this.chat_room_name = chat_room_name;
	}
	@Override
	public String toString() {
		return "TeamChatRoomVO [chat_room_id=" + chat_room_id + ", account_id_fk=" + account_id_fk + ", user_id_fk="
				+ user_id_fk + ", chat_room_name=" + chat_room_name + "]";
	}
	
	
}
