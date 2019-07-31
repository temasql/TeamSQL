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
	private String colPKChecked;
	private String colName;
	private String colDataType;
	private int    colSize;
	private String colNullCheck;
	private int    colDefaultVal;
	private String colComment;

	public SqlEditorTableVO() {	}
	

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
		return "SqlEditorTableVO [colPKChecked=" + colPKChecked + ", colName=" + colName + ", colDataType="
				+ colDataType + ", colSize=" + colSize + ", colNullCheck=" + colNullCheck + ", colDefaultVal="
				+ colDefaultVal + ", colComment=" + colComment + "]";
	}


	
	
}
