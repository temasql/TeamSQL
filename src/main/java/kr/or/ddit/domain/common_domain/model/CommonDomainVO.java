package kr.or.ddit.domain.common_domain.model;

public class CommonDomainVO {
	private int cdomain_id;       // 도메인아이디
	private String cdomain_name;  // 도메인이름
	private String cdomain_type;  // 도메인타입
	
	public int getCdomain_id() {
		return cdomain_id;
	}
	public void setCdomain_id(int cdomain_id) {
		this.cdomain_id = cdomain_id;
	}
	public String getCdomain_name() {
		return cdomain_name;
	}
	public void setCdomain_name(String cdomain_name) {
		this.cdomain_name = cdomain_name;
	}
	public String getCdomain_type() {
		return cdomain_type;
	}
	public void setCdomain_type(String cdomain_type) {
		this.cdomain_type = cdomain_type;
	}
	@Override
	public String toString() {
		return "CommonDomainVO [cdomain_id=" + cdomain_id + ", cdomain_name=" + cdomain_name + ", cdomain_type="
				+ cdomain_type + "]";
	}
	
	
}
