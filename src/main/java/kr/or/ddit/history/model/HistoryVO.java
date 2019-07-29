package kr.or.ddit.history.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class HistoryVO {
	
	private Date exec_dtm;          // 변경일시
	private String username;        // 실행DB계정명
	private String action_event;    // 발생이벤트
	private String object_type;     // 객체타입
	private String object_owner;    // DB계정명
	private String object_name;     // 객체명
	private String program;         // 프로그램
	private String machine;         // 머신
	private String ip_address;      // ip주소
	private String osuser;          // 운영체제유저
	private String sql_id;          // sql아이디
	private String sql_text;		// sql내용
	public Date getExec_dtm() {
		return exec_dtm;
	}
	public void setExec_dtm(Date exec_dtm) {
		this.exec_dtm = exec_dtm;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAction_event() {
		return action_event;
	}
	public void setAction_event(String action_event) {
		this.action_event = action_event;
	}
	public String getObject_type() {
		return object_type;
	}
	public void setObject_type(String object_type) {
		this.object_type = object_type;
	}
	public String getObject_owner() {
		return object_owner;
	}
	public void setObject_owner(String object_owner) {
		this.object_owner = object_owner;
	}
	public String getObject_name() {
		return object_name;
	}
	public void setObject_name(String object_name) {
		this.object_name = object_name;
	}
	public String getProgram() {
		return program;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	public String getMachine() {
		return machine;
	}
	public void setMachine(String machine) {
		this.machine = machine;
	}
	public String getIp_address() {
		return ip_address;
	}
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}
	public String getOsuser() {
		return osuser;
	}
	public void setOsuser(String osuser) {
		this.osuser = osuser;
	}
	public String getSql_id() {
		return sql_id;
	}
	public void setSql_id(String sql_id) {
		this.sql_id = sql_id;
	}
	public String getSql_text() {
		return sql_text;
	}
	public void setSql_text(String sql_text) {
		this.sql_text = sql_text;
	}
	@Override
	public String toString() {
		return "HistoryVO [exec_dtm=" + exec_dtm + ", username=" + username + ", action_event=" + action_event
				+ ", object_type=" + object_type + ", object_owner=" + object_owner + ", object_name=" + object_name
				+ ", program=" + program + ", machine=" + machine + ", ip_address=" + ip_address + ", osuser=" + osuser
				+ ", sql_id=" + sql_id + ", sql_text=" + sql_text + "]";
	}
	
	
}
