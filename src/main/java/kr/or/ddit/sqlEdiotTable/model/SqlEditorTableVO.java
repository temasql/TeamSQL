package kr.or.ddit.sqlEdiotTable.model;

public class SqlEditorTableVO {
	
	private String pk;
	private String column_name;
	private String data_type;
	private int data_length;
	private String nullable;
	private String data_default;
	private String comments;
	private String DDL;
	private String CONSTRAINT_NAME;
	private String TRIGGER_NAME;
	private String VIEW_NAME;
	private String INDEX_NAME;
	
	
	public String getCONSTRAINT_NAME() {
		return CONSTRAINT_NAME;
	}
	public void setCONSTRAINT_NAME(String cONSTRAINT_NAME) {
		CONSTRAINT_NAME = cONSTRAINT_NAME;
	}
	public String getTRIGGER_NAME() {
		return TRIGGER_NAME;
	}
	public void setTRIGGER_NAME(String tRIGGER_NAME) {
		TRIGGER_NAME = tRIGGER_NAME;
	}
	public String getVIEW_NAME() {
		return VIEW_NAME;
	}
	public void setVIEW_NAME(String vIEW_NAME) {
		VIEW_NAME = vIEW_NAME;
	}
	public String getINDEX_NAME() {
		return INDEX_NAME;
	}
	public void setINDEX_NAME(String iNDEX_NAME) {
		INDEX_NAME = iNDEX_NAME;
	}
	public String getTABLE_NAME() {
		return CONSTRAINT_NAME;
	}
	public void setTABLE_NAME(String tABLE_NAME) {
		CONSTRAINT_NAME = tABLE_NAME;
	}
	public String getDDL() {
		return DDL;
	}
	public void setDDL(String dDL) {
		DDL = dDL;
	}
	public String getPk() {
		return pk;
	}
	public void setPk(String pk) {
		this.pk = pk;
	}
	public String getColumn_name() {
		return column_name;
	}
	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}
	public String getData_type() {
		return data_type;
	}
	public void setData_type(String data_type) {
		this.data_type = data_type;
	}
	public int getData_length() {
		return data_length;
	}
	public void setData_length(int data_length) {
		this.data_length = data_length;
	}
	public String getNullable() {
		return nullable;
	}
	public void setNullable(String nullable) {
		this.nullable = nullable;
	}
	public String getData_default() {
		return data_default;
	}
	public void setData_default(String data_default) {
		this.data_default = data_default;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	@Override
	public String toString() {
		return "SqlEditorTableVO [pk=" + pk + ", column_name=" + column_name + ", data_type=" + data_type
				+ ", data_length=" + data_length + ", nullable=" + nullable + ", data_default=" + data_default
				+ ", comments=" + comments + ", DDL=" + DDL + ", CONSTRAINT_NAME=" + CONSTRAINT_NAME + ", TRIGGER_NAME="
				+ TRIGGER_NAME + ", VIEW_NAME=" + VIEW_NAME + ", INDEX_NAME=" + INDEX_NAME + "]";
	}
	
}
