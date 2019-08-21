package kr.or.ddit.post.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class PostVO {
	private int post_id;            // 게시글아이디
	private int parent_id_fk;       // 부모게시글아이디
	private String user_id_fk;      // 작성자아이디
	private int board_id_fk;        // 게시판아이디
	private String post_title;      // 게시글제목
	private String post_content;    // 게시글내용
	@DateTimeFormat(pattern = "yyyy-MM-dd a hh:mm:ss")
	private Date post_dt;           // 게시글작성일시
	private String post_use;        // 게시글사용여부
	private int post_group_seq;		// 그룹시퀀스
	private int lv;		// 레벨
	private int reply_cnt;		// 댓글 갯수
	private int view_cnt;		// 조회수
	


	public PostVO() {	}
	
	public PostVO(int post_id, int parent_id_fk, String user_id_fk, int board_id_fk, String post_title,
			String post_content, String post_use, int post_group_seq) {
		super();
		this.post_id = post_id;
		this.parent_id_fk = parent_id_fk;
		this.user_id_fk = user_id_fk;
		this.board_id_fk = board_id_fk;
		this.post_title = post_title;
		this.post_content = post_content;
		this.post_use = post_use;
		this.post_group_seq = post_group_seq;
	}

	public PostVO(int post_id, String user_id_fk, int board_id_fk, String post_title
								, String post_content, int post_group_seq) {
		this.post_id = post_id;
		this.user_id_fk = user_id_fk;
		this.board_id_fk = board_id_fk;
		this.post_title = post_title;
		this.post_content = post_content;
		this.post_group_seq = post_group_seq;
	}

	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	public int getParent_id_fk() {
		return parent_id_fk;
	}

	public void setParent_id_fk(int parent_id_fk) {
		this.parent_id_fk = parent_id_fk;
	}

	public String getUser_id_fk() {
		return user_id_fk;
	}

	public void setUser_id_fk(String user_id_fk) {
		this.user_id_fk = user_id_fk;
	}

	public int getBoard_id_fk() {
		return board_id_fk;
	}

	public void setBoard_id_fk(int board_id_fk) {
		this.board_id_fk = board_id_fk;
	}

	public String getPost_title() {
		return post_title;
	}

	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}

	public String getPost_content() {
		return post_content;
	}

	public void setPost_content(String post_content) {
		this.post_content = post_content;
	}

	public Date getPost_dt() {
		return post_dt;
	}

	public void setPost_dt(Date post_dt) {
		this.post_dt = post_dt;
	}

	public String getPost_use() {
		return post_use;
	}

	public void setPost_use(String post_use) {
		this.post_use = post_use;
	}

	public int getPost_group_seq() {
		return post_group_seq;
	}

	public void setPost_group_seq(int post_group_seq) {
		this.post_group_seq = post_group_seq;
	}

	public int getLv() {
		return lv;
	}

	public void setLv(int lv) {
		this.lv = lv;
	}

	public int getReply_cnt() {
		return reply_cnt;
	}
	
	public void setReply_cnt(int reply_cnt) {
		this.reply_cnt = reply_cnt;
	}

	public int getView_cnt() {
		return view_cnt;
	}
	
	public void setView_cnt(int view_cnt) {
		this.view_cnt = view_cnt;
	}

	@Override
	public String toString() {
		return "PostVO [post_id=" + post_id + ", parent_id_fk=" + parent_id_fk + ", user_id_fk=" + user_id_fk
				+ ", board_id_fk=" + board_id_fk + ", post_title=" + post_title + ", post_content=" + post_content
				+ ", post_dt=" + post_dt + ", post_use=" + post_use + ", post_group_seq=" + post_group_seq + ", lv="
				+ lv + "]";
	}

	
}
