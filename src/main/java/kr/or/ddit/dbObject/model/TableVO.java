package kr.or.ddit.dbObject.model;

public class TableVO {
	
	private String owner;
	private String table_name;
	
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	
	@Override
	public String toString() {
		return "TableVO [owner=" + owner + ", table_name=" + table_name + "]";
	}
	
}
