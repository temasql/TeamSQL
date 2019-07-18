package kr.or.ddit.ts_file.model;

public class TSFileVO {
	private int tsfile_id;          // 첨부파일아이디
	private int post_id_fk;         // 게시글아이디
	private String tsfile_path;     // 첨부파일경로
	private String tsfile_filename;	// 원본파일명

	public int getTsfile_id() {
		return tsfile_id;
	}
	public void setTsfile_id(int tsfile_id) {
		this.tsfile_id = tsfile_id;
	}
	public int getPost_id_fk() {
		return post_id_fk;
	}
	public void setPost_id_fk(int post_id_fk) {
		this.post_id_fk = post_id_fk;
	}
	public String getTsfile_path() {
		return tsfile_path;
	}
	public void setTsfile_path(String tsfile_path) {
		this.tsfile_path = tsfile_path;
	}
	public String getTsfile_filename() {
		return tsfile_filename;
	}
	public void setTsfile_filename(String tsfile_filename) {
		this.tsfile_filename = tsfile_filename;
	}
	@Override
	public String toString() {
		return "TSFileVO [tsfile_id=" + tsfile_id + ", post_id_fk=" + post_id_fk + ", tsfile_path=" + tsfile_path
				+ ", tsfile_filename=" + tsfile_filename + "]";
	}
}
