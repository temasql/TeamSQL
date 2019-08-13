package kr.or.ddit.sqlEditorIndex.model;

/**
* IndexColVO.java
*
* @author 강호길
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
* 수정자 수정내용
* ------ ------------------------
* 강호길 최초 생성
*
* </pre>
*/
public class IndexColVO {
	private String index_owner;		// DB계정명
	private String index_name;		// 인덱스명
	private String table_owner;		// 테이블 계정명
	private String table_name;		// 테이블명
	private String column_name;		// 컬럼명
	private String column_position;	// 컬럼 위치
	private String descend;			// 정렬
	
	public String getIndex_owner() {
		return index_owner;
	}
	public void setIndex_owner(String index_owner) {
		this.index_owner = index_owner;
	}
	public String getIndex_name() {
		return index_name;
	}
	public void setIndex_name(String index_name) {
		this.index_name = index_name;
	}
	public String getTable_owner() {
		return table_owner;
	}
	public void setTable_owner(String table_owner) {
		this.table_owner = table_owner;
	}
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	public String getColumn_name() {
		return column_name;
	}
	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}
	public String getColumn_position() {
		return column_position;
	}
	public void setColumn_position(String column_position) {
		this.column_position = column_position;
	}
	public String getDescend() {
		return descend;
	}
	public void setDescend(String descend) {
		this.descend = descend;
	}
	
	@Override
	public String toString() {
		return "IndexColVO [index_owner=" + index_owner + ", index_name=" + index_name + ", table_owner=" + table_owner
				+ ", table_name=" + table_name + ", column_name=" + column_name + ", column_position=" + column_position
				+ ", descend=" + descend + "]";
	}
	
	
	
}
