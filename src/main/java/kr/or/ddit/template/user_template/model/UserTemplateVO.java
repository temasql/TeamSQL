package kr.or.ddit.template.user_template.model;

public class UserTemplateVO {
	private int utemplate_id;           // 템플릿아이디
	private String user_id_fk;          // 사용자아이디
	private String utemplate_abb;       // 템플릿약어
	private String utemplate_original;  // 템플릿쿼리원문
	private String utemplate_use;		// 템플릿사용여부
	
	public int getUtemplate_id() {
		return utemplate_id;
	}
	public void setUtemplate_id(int utemplate_id) {
		this.utemplate_id = utemplate_id;
	}
	public String getUser_id_fk() {
		return user_id_fk;
	}
	public void setUser_id_fk(String user_id_fk) {
		this.user_id_fk = user_id_fk;
	}
	public String getUtemplate_abb() {
		return utemplate_abb;
	}
	public void setUtemplate_abb(String utemplate_abb) {
		this.utemplate_abb = utemplate_abb;
	}
	public String getUtemplate_original() {
		return utemplate_original;
	}
	public void setUtemplate_original(String utemplate_original) {
		this.utemplate_original = utemplate_original;
	}
	public String getUtemplate_use() {
		return utemplate_use;
	}
	public void setUtemplate_use(String utemplate_use) {
		this.utemplate_use = utemplate_use;
	}
	@Override
	public String toString() {
		return "UserTemplateVO [utemplate_id=" + utemplate_id + ", user_id_fk=" + user_id_fk + ", utemplate_abb="
				+ utemplate_abb + ", utemplate_original=" + utemplate_original + ", utemplate_use=" + utemplate_use
				+ "]";
	}
	
	
}
