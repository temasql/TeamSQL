package kr.or.ddit.table_lock.model;

public class TableLockVO {
	private String table_name;      // 테이블이름
	private String account_id_fk;   // 계정아이디
	private String user_id_fk;		// 사용자아이디
	
	
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	public String getAccount_id_fk() {
		return account_id_fk;
	}
	public void setAccount_id_fk(String account_id_fk) {
		this.account_id_fk = account_id_fk;
	}
	public String getUser_id_fk() {
		return user_id_fk;
	}
	public void setUser_id_fk(String user_id_fk) {
		this.user_id_fk = user_id_fk;
	}
	@Override
	public String toString() {
		return "TableLockVO [table_name=" + table_name + ", account_id_fk=" + account_id_fk + ", user_id_fk="
				+ user_id_fk + "]";
	}
	
	
}
