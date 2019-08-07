package kr.or.ddit.sqlEdiotSequence.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.jboss.logging.FormatWith;
import org.springframework.format.annotation.DateTimeFormat;

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

/**
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
public class DetailSeqVO {
	
	SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");
	
	private Date created;
	private Date last_ddl_time;
	private String sequence_owner;
	private String sequence_name;
	private String min_value;
	private String max_value;
	private String increment_by;
	private String cycle_flag;
	private String order_flag;
	private String cache_size;
	private String last_number;
	
	
	public String getCreated() {
		return sdf.format(created);
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getLast_ddl_time() {
		return sdf.format(last_ddl_time);
	}
	public void setLast_ddl_time(Date last_ddl_time) {
		this.last_ddl_time = last_ddl_time;
	}
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
	@Override
	public String toString() {
		return "DetailSeqVO [created=" + created + ", last_ddl_time=" + last_ddl_time + ", sequence_owner="
				+ sequence_owner + ", sequence_name=" + sequence_name + ", min_value=" + min_value + ", max_value="
				+ max_value + ", increment_by=" + increment_by + ", cycle_flag=" + cycle_flag + ", order_flag="
				+ order_flag + ", cache_size=" + cache_size + ", last_number=" + last_number + "]";
	}
	

	
	
	
}
