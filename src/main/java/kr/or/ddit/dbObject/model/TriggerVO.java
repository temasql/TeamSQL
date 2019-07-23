package kr.or.ddit.dbObject.model;

public class TriggerVO {
	
	private String owner;
	private String trigger_name;
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getTrigger_name() {
		return trigger_name;
	}
	public void setTrigger_name(String trigger_name) {
		this.trigger_name = trigger_name;
	}
	@Override
	public String toString() {
		return "TriggerVO [owner=" + owner + ", trigger_name=" + trigger_name + "]";
	}
	
}
