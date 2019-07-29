package kr.or.ddit.history.model;

import java.util.Date;

public class ChangedVO {
	String object_owner;	// DB계정명	
	Date EXEC_DTM;			// 마지막 변경일시
	String user_name;		// 생성자 이름
	
	public String getObject_owner() {
		return object_owner;
	}
	public Date getEXEC_DTM() {
		return EXEC_DTM;
	}
	public String getUser_name() {
		return user_name;
	}
	@Override
	public String toString() {
		return "ChangedVO [object_owner=" + object_owner + ", EXEC_DTM=" + EXEC_DTM + ", user_name=" + user_name + "]";
	}

}
