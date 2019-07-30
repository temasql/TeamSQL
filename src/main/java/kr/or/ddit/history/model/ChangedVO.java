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
	public String getObject_ownerSlice() {
		
		// 구분자 체크
		int idx = object_owner.indexOf("_"); 
        
		// _ 앞부분을 추출
        // substring은 첫번째 지정한 인덱스는 포함하지 않는다.
        // 아래의 경우는 첫번째 문자열인 a 부터 추출된다.
		return  object_owner.substring(0, idx);
	}
	public ChangedVO(String object_owner, Date exec_dtm, String user_name) {
		super();
		this.object_owner = object_owner;
		this.exec_dtm = exec_dtm;
		this.user_name = user_name;
	}
	
	
	

}
