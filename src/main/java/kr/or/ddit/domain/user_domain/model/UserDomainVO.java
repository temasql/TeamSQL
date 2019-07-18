package kr.or.ddit.domain.user_domain.model;

public class UserDomainVO {
	private int udomain_id;         // 도메인아이디
	private String user_id_fk;      // 사용자아이디
	private String udomain_name;    // 도메인이름
	private String udomain_type;	// 도메인타입
	
	public int getUdomain_id() {
		return udomain_id;
	}
	public void setUdomain_id(int udomain_id) {
		this.udomain_id = udomain_id;
	}
	public String getUser_id_fk() {
		return user_id_fk;
	}
	public void setUser_id_fk(String user_id_fk) {
		this.user_id_fk = user_id_fk;
	}
	public String getUdomain_name() {
		return udomain_name;
	}
	public void setUdomain_name(String udomain_name) {
		this.udomain_name = udomain_name;
	}
	public String getUdomain_type() {
		return udomain_type;
	}
	public void setUdomain_type(String udomain_type) {
		this.udomain_type = udomain_type;
	}
	@Override
	public String toString() {
		return "UserDomainVO [udomain_id=" + udomain_id + ", user_id_fk=" + user_id_fk + ", udomain_name="
				+ udomain_name + ", udomain_type=" + udomain_type + "]";
	}
	
	
}
