package kr.or.ddit.reply.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ReplyVO {
	private int reply_id;           // 댓글아이디
	private String user_id_fk;      // 작성자아이디
	private int post_id_fk;         // 게시글아이디
	private String reply_content;   // 댓글내용
	private String reply_use;       // 댓글사용여부
	@DateTimeFormat(pattern = "yyyy-MM-dd a hh:mm:ss")
	private Date reply_dt;			// 댓글작성일시

	public ReplyVO() {
	}

	
	public ReplyVO(int reply_id, String user_id_fk, int post_id_fk, String reply_content, String reply_use) {
		super();
		this.reply_id = reply_id;
		this.user_id_fk = user_id_fk;
		this.post_id_fk = post_id_fk;
		this.reply_content = reply_content;
		this.reply_use = reply_use;
	}


	public int getReply_id() {
		return reply_id;
	}
	public void setReply_id(int reply_id) {
		this.reply_id = reply_id;
	}
	public String getUser_id_fk() {
		return user_id_fk;
	}
	public void setUser_id_fk(String user_id_fk) {
		this.user_id_fk = user_id_fk;
	}
	public int getPost_id_fk() {
		return post_id_fk;
	}
	public void setPost_id_fk(int post_id_fk) {
		this.post_id_fk = post_id_fk;
	}
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	public String getReply_use() {
		return reply_use;
	}
	public void setReply_use(String reply_use) {
		this.reply_use = reply_use;
	}
	public Date getReply_dt() {
		return reply_dt;
	}
	public void setReply_dt(Date reply_dt) {
		this.reply_dt = reply_dt;
	}
	@Override
	public String toString() {
		return "ReplyVO [reply_id=" + reply_id + ", user_id_fk=" + user_id_fk + ", post_id_fk=" + post_id_fk
				+ ", reply_content=" + reply_content + ", reply_use=" + reply_use + ", reply_dt=" + reply_dt + "]";
	}
	
}
