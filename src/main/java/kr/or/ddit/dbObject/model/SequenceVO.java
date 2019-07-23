package kr.or.ddit.dbObject.model;

public class SequenceVO {
	
	private String sequence_owner;
	private String sequence_name;
	public String getSequence_owner() {
		return sequence_owner;
	}
	public void setSequence_owner(String sequence_owner) {
		this.sequence_owner = sequence_owner;
	}
	public String getSequence_name() {
		return sequence_name;
	}
	public void setSequence_name(String sequence_name) {
		this.sequence_name = sequence_name;
	}
	@Override
	public String toString() {
		return "SequenceVO [sequence_owner=" + sequence_owner + ", sequence_name=" + sequence_name + "]";
	}
	
}
