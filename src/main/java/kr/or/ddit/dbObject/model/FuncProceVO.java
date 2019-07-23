package kr.or.ddit.dbObject.model;

public class FuncProceVO {
	
	private String owner;
	private String object_name;
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getObject_name() {
		return object_name;
	}
	public void setObject_name(String object_name) {
		this.object_name = object_name;
	}
	@Override
	public String toString() {
		return "FuncProceVO [owner=" + owner + ", object_name=" + object_name + "]";
	}
}
