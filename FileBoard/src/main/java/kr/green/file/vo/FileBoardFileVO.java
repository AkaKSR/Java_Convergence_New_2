package kr.green.file.vo;

public class FileBoardFileVO {
	private int idx;
	private int ref;
	private String ofile;
	private String sfile;

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public String getOfile() {
		return ofile;
	}

	public void setOfile(String ofile) {
		this.ofile = ofile;
	}

	public String getSfile() {
		return sfile;
	}

	public void setSfile(String sfile) {
		this.sfile = sfile;
	}

	@Override
	public String toString() {
		return "FileBoardFileVO [idx=" + idx + ", ref=" + ref + ", ofile=" + ofile + ", sfile=" + sfile + "]";
	}

}
