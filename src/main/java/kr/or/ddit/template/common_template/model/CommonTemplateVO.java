package kr.or.ddit.template.common_template.model;

public class CommonTemplateVO {
	private int ctemplate_id;           // 템플릿아이디
	private String ctemplate_abb;       // 템플릿약어
	private String ctemplate_original;	// 템플릿쿼리원문
	
	public CommonTemplateVO(int ctemplate_id, String ctemplate_abb, String ctemplate_original) {
		super();
		this.ctemplate_id = ctemplate_id;
		this.ctemplate_abb = ctemplate_abb;
		this.ctemplate_original = ctemplate_original;
	}
	
	public CommonTemplateVO() {
	}
	
	public int getCtemplate_id() {
		return ctemplate_id;
	}
	public void setCtemplate_id(int ctemplate_id) {
		this.ctemplate_id = ctemplate_id;
	}
	public String getCtemplate_abb() {
		return ctemplate_abb;
	}
	public void setCtemplate_abb(String ctemplate_abb) {
		this.ctemplate_abb = ctemplate_abb;
	}
	public String getCtemplate_original() {
		return ctemplate_original;
	}
	public void setCtemplate_original(String ctemplate_original) {
		this.ctemplate_original = ctemplate_original;
	}
	@Override
	public String toString() {
		return "CommonTemplateVO [ctemplate_id=" + ctemplate_id + ", ctemplate_abb=" + ctemplate_abb
				+ ", ctemplate_original=" + ctemplate_original + "]";
	}
	
	
	
}
