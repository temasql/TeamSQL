package kr.or.ddit.manager.model;

public class ManagerVO {
	private String manager_case;	// 쿼리매니저케이스
	private String manager_hint;    // 쿼리매니저힌트
	
	public String getManager_case() {
		return manager_case;
	}
	public void setManager_case(String manager_case) {
		this.manager_case = manager_case;
	}
	public String getManager_hint() {
		return manager_hint;
	}
	public void setManager_hint(String manager_hint) {
		this.manager_hint = manager_hint;
	}
	@Override
	public String toString() {
		return "ManagerVO [manager_case=" + manager_case + ", manager_hint=" + manager_hint + "]";
	}
}