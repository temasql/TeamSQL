package kr.or.ddit.history.model;

import java.util.Date;

public class ChangedVO {
	String object_owner;	// DB계정명	
	Date exec_dtm;			// 마지막 변경일시
	String user_name;		// 생성자 이름
	
	public String getObject_owner() {
		return object_owner;
	}
	public Date getExec_dtm() {
		return exec_dtm;
	}
	public String getUser_name() {
		return user_name;
	}
	
	public ChangedVO(String object_owner, Date exec_dtm, String user_name) {
		super();
		this.object_owner = object_owner;
		this.exec_dtm = exec_dtm;
		this.user_name = user_name;
	}
	
	
	

}
