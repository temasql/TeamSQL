package kr.or.ddit.board.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class BoardVO {
	private int board_id;       // 게시판아이디
	private String user_id_fk;  // 생성자아이디
	private String board_name;  // 게시판이름
	private String board_use;   // 게시판사용여부
	
	@DateTimeFormat(pattern = "yyyy-MM-dd a hh:mm:ss")
	private Date board_dt;		// 게시판생성일시

	public int getBoard_id() {
		return board_id;
	}
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}
	public String getUser_id_fk() {
		return user_id_fk;
	}
	public void setUser_id_fk(String user_id_fk) {
		this.user_id_fk = user_id_fk;
	}
	public String getBoard_name() {
		return board_name;
	}
	public void setBoard_name(String board_name) {
		this.board_name = board_name;
	}
	public String getBoard_use() {
		return board_use;
	}
	public void setBoard_use(String board_use) {
		this.board_use = board_use;
	}
	public Date getBoard_dt() {
		return board_dt;
	}
	public void setBoard_dt(Date board_dt) {
		this.board_dt = board_dt;
	}
	@Override
	public String toString() {
		return "BoardVO [board_id=" + board_id + ", user_id_fk=" + user_id_fk + ", board_name=" + board_name
				+ ", board_use=" + board_use + ", board_dt=" + board_dt + "]";
	}
}
