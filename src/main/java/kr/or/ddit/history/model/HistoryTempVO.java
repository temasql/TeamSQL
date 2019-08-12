package kr.or.ddit.history.model;

import java.util.Date;

public class HistoryTempVO {
	
	private String account_id;
	private Date dtm;
	private String name;
	private String slice_account_id;
	
	public String getAccount_id() {
		return account_id;
	}
	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}
	public Date getDtm() {
		return dtm;
	}
	public void setDtm(Date dtm) {
		this.dtm = dtm;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSlice_account_id() {
		return slice_account_id;
	}
	public void setSlice_account_id(String slice_account_id) {
		this.slice_account_id = slice_account_id;
	}
	@Override
	public String toString() {
		return "HistoryTempVO [account_id=" + account_id + ", dtm=" + dtm + ", name=" + name + ", slice_account_id="
				+ slice_account_id + "]";
	}
	public HistoryTempVO() {
		
	}
	
	
}
