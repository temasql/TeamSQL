package kr.or.ddit.dbObject.model;

public class ViewVO {
	
	private String owner;
	private String view_name;
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getView_name() {
		return view_name;
	}
	public void setView_name(String view_name) {
		this.view_name = view_name;
	}
	@Override
	public String toString() {
		return "ViewVO [owner=" + owner + ", view_name=" + view_name + "]";
	}
}
