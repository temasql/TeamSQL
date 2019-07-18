package kr.or.ddit.filtering.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class FilteringVO {
	private int filtering_id;       // 필터링아이디
	private String user_id_fk;      // 등록자아이디
	@DateTimeFormat(pattern = "yyyy-MM-dd a hh:mm:ss")
	private Date filtering_dt;      // 등록일시
	private String filtering_word;	// 필터링단어

	
	public int getFiltering_id() {
		return filtering_id;
	}
	public void setFiltering_id(int filtering_id) {
		this.filtering_id = filtering_id;
	}
	public String getUser_id_fk() {
		return user_id_fk;
	}
	public void setUser_id_fk(String user_id_fk) {
		this.user_id_fk = user_id_fk;
	}
	public Date getFiltering_dt() {
		return filtering_dt;
	}
	public void setFiltering_dt(Date filtering_dt) {
		this.filtering_dt = filtering_dt;
	}
	public String getFiltering_word() {
		return filtering_word;
	}
	public void setFiltering_word(String filtering_word) {
		this.filtering_word = filtering_word;
	}
	@Override
	public String toString() {
		return "FilteringVO [filtering_id=" + filtering_id + ", user_id_fk=" + user_id_fk + ", filtering_dt="
				+ filtering_dt + ", filtering_word=" + filtering_word + "]";
	}
	
}
