package kr.or.ddit.sqlEditorProcedure.model;

public class ProcedureDetailVO {
	private String owner;
	private String object_name;
	private String subobject_name;
	private String object_id;
	private String data_object_id;
	private String object_type;
	private String created;
	private String last_ddl_time;
	private String timestamp;
	private String status;
	private String temporary;
	private String generated;
	private String secondary;
	private String namespace;
	private String edition_name;
	
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getObject_name() {
		return object_name;
	}
	public void setObject_name(String object_name) {
		this.object_name = object_name;
	}
	public String getSubobject_name() {
		return subobject_name;
	}
	public void setSubobject_name(String subobject_name) {
		this.subobject_name = subobject_name;
	}
	public String getObject_id() {
		return object_id;
	}
	public void setObject_id(String object_id) {
		this.object_id = object_id;
	}
	public String getData_object_id() {
		return data_object_id;
	}
	public void setData_object_id(String data_object_id) {
		this.data_object_id = data_object_id;
	}
	public String getObject_type() {
		return object_type;
	}
	public void setObject_type(String object_type) {
		this.object_type = object_type;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getLast_ddl_time() {
		return last_ddl_time;
	}
	public void setLast_ddl_time(String last_ddl_time) {
		this.last_ddl_time = last_ddl_time;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTemporary() {
		return temporary;
	}
	public void setTemporary(String temporary) {
		this.temporary = temporary;
	}
	public String getGenerated() {
		return generated;
	}
	public void setGenerated(String generated) {
		this.generated = generated;
	}
	public String getSecondary() {
		return secondary;
	}
	public void setSecondary(String secondary) {
		this.secondary = secondary;
	}
	public String getNamespace() {
		return namespace;
	}
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	public String getEdition_name() {
		return edition_name;
	}
	public void setEdition_name(String edition_name) {
		this.edition_name = edition_name;
	}
	public ProcedureDetailVO(String owner, String object_name, String subobject_name, String object_id,
			String data_object_id, String object_type, String created, String last_ddl_time, String timestamp,
			String status, String temporary, String generated, String secondary, String namespace,
			String edition_name) {
		this.owner = owner;
		this.object_name = object_name;
		this.subobject_name = subobject_name;
		this.object_id = object_id;
		this.data_object_id = data_object_id;
		this.object_type = object_type;
		this.created = created;
		this.last_ddl_time = last_ddl_time;
		this.timestamp = timestamp;
		this.status = status;
		this.temporary = temporary;
		this.generated = generated;
		this.secondary = secondary;
		this.namespace = namespace;
		this.edition_name = edition_name;
	}
	@Override
	public String toString() {
		return "TriggerDetailVO [owner=" + owner + ", object_name=" + object_name + ", subobject_name=" + subobject_name
				+ ", object_id=" + object_id + ", data_object_id=" + data_object_id + ", object_type=" + object_type
				+ ", created=" + created + ", last_ddl_time=" + last_ddl_time + ", timestamp=" + timestamp + ", status="
				+ status + ", temporary=" + temporary + ", generated=" + generated + ", secondary=" + secondary
				+ ", namespace=" + namespace + ", edition_name=" + edition_name + "]";
	}
	public ProcedureDetailVO() {
		
	}
	
}
