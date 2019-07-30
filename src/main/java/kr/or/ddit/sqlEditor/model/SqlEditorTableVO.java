/**
 * 
 */
package kr.or.ddit.sqlEditor.model;

/**
* TableVO.java
*
* @author 이중석
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
* 수정자 수정내용
* ------ ------------------------
* 이중석 최초 생성
* 
* 테이블 패키지에서 우클릭 후 테이블 생성을 할때 name값으로 변수를 한번에 받기 위한 VO클래스
* </pre>
*/
public class SqlEditorTableVO {
	private String table_name;
	private String colPKChecked;
	private String colName;
	private String colDataType;
	private int colSize;
	private String colNullCheck;
	private int colDefaultVal;
	private String colComment;

	public SqlEditorTableVO() {	}
	
	public SqlEditorTableVO(String table_name, String colPKChecked, String colName, String colDataType, int colSize,
			String colNullCheck, int colDefaultVal, String colComment) {
		this.table_name = table_name;
		this.colPKChecked = colPKChecked;
		this.colName = colName;
		this.colDataType = colDataType;
		this.colSize = colSize;
		this.colNullCheck = colNullCheck;
		this.colDefaultVal = colDefaultVal;
		this.colComment = colComment;
	}

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}

	public String getColPKChecked() {
		return colPKChecked;
	}

	public void setColPKChecked(String colPKChecked) {
		this.colPKChecked = colPKChecked;
	}

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public String getColDataType() {
		return colDataType;
	}

	public void setColDataType(String colDataType) {
		this.colDataType = colDataType;
	}

	public int getColSize() {
		return colSize;
	}

	public void setColSize(int colSize) {
		this.colSize = colSize;
	}

	public String getColNullCheck() {
		return colNullCheck;
	}

	public void setColNullCheck(String colNullCheck) {
		this.colNullCheck = colNullCheck;
	}

	public int getColDefaultVal() {
		return colDefaultVal;
	}

	public void setColDefaultVal(int colDefaultVal) {
		this.colDefaultVal = colDefaultVal;
	}

	public String getColComment() {
		return colComment;
	}

	public void setColComment(String colComment) {
		this.colComment = colComment;
	}

	@Override
	public String toString() {
		return "SqlEditorTableVO [table_name=" + table_name + ", colPKChecked=" + colPKChecked + ", colName=" + colName
				+ ", colDataType=" + colDataType + ", colSize=" + colSize + ", colNullCheck=" + colNullCheck
				+ ", colDefaultVal=" + colDefaultVal + ", colComment=" + colComment + "]";
	}

	
	
}
