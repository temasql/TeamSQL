package kr.or.ddit.sqlEditorTrigger.model;

public class MyTriggerVO {
	
	private String schema_id;
	private String trigger_name;
	private String table_name;
	private String timing;
	private String event;
	private String column;
	
	public String getSchema_id() {
		return schema_id;
	}
	public void setSchema_id(String schema_id) {
		this.schema_id = schema_id;
	}
	public String getTrigger_name() {
		return trigger_name;
	}
	public void setTrigger_name(String trigger_name) {
		this.trigger_name = trigger_name;
	}
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	public String getTiming() {
		return timing;
	}
	public void setTiming(String timing) {
		this.timing = timing;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	
	public MyTriggerVO() {
		
	}
	
	public MyTriggerVO(String schema_id, String trigger_name, String table_name, String timing, String event,
			String column) {
		super();
		this.schema_id = schema_id;
		this.trigger_name = trigger_name;
		this.table_name = table_name;
		this.timing = timing;
		this.event = event;
		this.column = column;
	}
	@Override
	public String toString() {
		return "MyTriggerVO [schema_id=" + schema_id + ", trigger_name=" + trigger_name + ", table_name=" + table_name
				+ ", timing=" + timing + ", event=" + event + ", column=" + column + "]";
	}
	
	

}
