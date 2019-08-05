package kr.or.ddit.sqlEdiotSequence.model;
/**
 * 
* DetailSeqVO.java
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

import java.util.Date;

public class DetailSeqVO {

	private Date created;
	private Date last_ddl_time;
	private String min_value;
	private String max_value;
	private String increment_by;
	private String cycle_flag;
	private String order_flag;
	private String cache_size;
	private String last_number;
	private String object_type;
	private String object_name;
	private String owner;
	
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getLast_ddl_time() {
		return last_ddl_time;
	}
	public void setLast_ddl_time(Date last_ddl_time) {
		this.last_ddl_time = last_ddl_time;
	}
	public String getMin_value() {
		return min_value;
	}
	public void setMin_value(String min_value) {
		this.min_value = min_value;
	}
	public String getMax_value() {
		return max_value;
	}
	public void setMax_value(String max_value) {
		this.max_value = max_value;
	}
	public String getIncrement_by() {
		return increment_by;
	}
	public void setIncrement_by(String increment_by) {
		this.increment_by = increment_by;
	}
	public String getCycle_flag() {
		return cycle_flag;
	}
	public void setCycle_flag(String cycle_flag) {
		this.cycle_flag = cycle_flag;
	}
	public String getOrder_flag() {
		return order_flag;
	}
	public void setOrder_flag(String order_flag) {
		this.order_flag = order_flag;
	}
	public String getCache_size() {
		return cache_size;
	}
	public void setCache_size(String cache_size) {
		this.cache_size = cache_size;
	}
	public String getLast_number() {
		return last_number;
	}
	public void setLast_number(String last_number) {
		this.last_number = last_number;
	}
	
	public String getObject_type() {
		return object_type;
	}
	public void setObject_type(String object_type) {
		this.object_type = object_type;
	}
	public String getObject_name() {
		return object_name;
	}
	public void setObject_name(String object_name) {
		this.object_name = object_name;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	@Override
	public String toString() {
		return "DetailSeqVO [created=" + created + ", last_ddl_time=" + last_ddl_time + ", min_value=" + min_value
				+ ", max_value=" + max_value + ", increment_by=" + increment_by + ", cycle_flag=" + cycle_flag
				+ ", order_flag=" + order_flag + ", cache_size=" + cache_size + ", last_number=" + last_number
				+ ", object_type=" + object_type + ", object_name=" + object_name + ", owner=" + owner + "]";
	}
	
	public DetailSeqVO(String object_type, String object_name, String owner) {
		super();
		this.object_type = object_type;
		this.object_name = object_name;
		this.owner = owner;
	}
	
	
	
}
