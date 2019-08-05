package kr.or.ddit.sqlEdiotTable.model;

public class SqlEditorTableVO {
	
	private String pk;
	private String column_name;
	private String data_type;
	private int data_length;
	private String nullable;
	private String data_default;
	private String comments;
	
	
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
				+ ", comments=" + comments + "]";
	}
	
}
