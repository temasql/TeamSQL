package kr.or.ddit.dbObject.model;

public class IndexVO {
	
	private String owner;
	private String index_name;
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getIndex_name() {
		return index_name;
	}
	public void setIndex_name(String index_name) {
		this.index_name = index_name;
	}
	@Override
	public String toString() {
		return "IndexVO [owner=" + owner + ", index_name=" + index_name + "]";
	}
}
