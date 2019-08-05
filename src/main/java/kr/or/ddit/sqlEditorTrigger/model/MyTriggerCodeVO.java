package kr.or.ddit.sqlEditorTrigger.model;

public class MyTriggerCodeVO {
	
	private String description;
	private String trigger_body;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTrigger_body() {
		return trigger_body;
	}
	public void setTrigger_body(String trigger_body) {
		this.trigger_body = trigger_body;
	}
	@Override
	public String toString() {
		return "MyTriggerCodeVO [description=" + description + ", trigger_body=" + trigger_body + "]";
	}
	public MyTriggerCodeVO() {
		
	}
	
}
