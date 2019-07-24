package kr.or.ddit.calendar.team_calendar.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class TeamCalendarVO {
	private int calendar_id;			 // 캘린더아이디
	private int category_id_fk;          // 일정아이디
	private String account_id_fk;        // 계정아이디
	private String user_id_fk;           // 사용자아이디
	
	@DateTimeFormat(pattern = "yyyy-MM-dd a hh:mm")
	private Date calendar_start_dt;      // 일정시작일시
	@DateTimeFormat(pattern = "yyyy-MM-dd a hh:mm")
	private Date calendar_end_dt;        // 일정종료일시
	@DateTimeFormat(pattern = "yyyy-MM-dd a hh:mm")
	private Date calendar_dt;            // 캘린더등록일시
	private String calendar_title;       // 캘린더제목
	private String calendar_content;     // 캘린더내용
	private String calendar_background;  // 캘린더배경색
	private String calendar_type;        // 캘린더구분
	
	
	public String getEnd() {
	      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
	      return sdf.format(calendar_end_dt);
	 }
	
	public int getCalendar_id() {
		return calendar_id;
	}
	public void setCalendar_id(int calendar_id) {
		this.calendar_id = calendar_id;
	}
	public int getCategory_id_fk() {
		return category_id_fk;
	}
	public void setCategory_id_fk(int category_id_fk) {
		this.category_id_fk = category_id_fk;
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
	public Date getCalendar_start_dt() {
		return calendar_start_dt;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd a hh:mm")
	public void setCalendar_start_dt(Date calendar_start_dt) {
		this.calendar_start_dt = calendar_start_dt;
	}
	public Date getCalendar_end_dt() {
		return calendar_end_dt;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd a hh:mm")
	public void setCalendar_end_dt(Date calendar_end_dt) {
		this.calendar_end_dt = calendar_end_dt;
	}
	public Date getCalendar_dt() {
		return calendar_dt;
	}
	public void setCalendar_dt(Date calendar_dt) {
		this.calendar_dt = calendar_dt;
	}
	public String getCalendar_title() {
		return calendar_title;
	}
	public void setCalendar_title(String calendar_title) {
		this.calendar_title = calendar_title;
	}
	public String getCalendar_content() {
		return calendar_content;
	}
	public void setCalendar_content(String calendar_content) {
		this.calendar_content = calendar_content;
	}
	public String getCalendar_background() {
		return calendar_background;
	}
	public void setCalendar_background(String calendar_background) {
		this.calendar_background = calendar_background;
	}
	public String getCalendar_type() {
		return calendar_type;
	}
	public void setCalendar_type(String calendar_type) {
		this.calendar_type = calendar_type;
	}
	
	public String getStart() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		return sdf.format(calendar_start_dt);
	}
	
	@Override
	public String toString() {
		return "TeamCalendarVO [calendar_id=" + calendar_id + ", category_id_fk=" + category_id_fk + ", account_id_fk="
				+ account_id_fk + ", user_id_fk=" + user_id_fk + ", calendar_start_dt=" + calendar_start_dt
				+ ", calendar_end_dt=" + calendar_end_dt + ", calendar_dt=" + calendar_dt + ", calendar_title="
				+ calendar_title + ", calendar_content=" + calendar_content + ", calendar_background="
				+ calendar_background + ", calendar_type=" + calendar_type + "]";
	}
}
