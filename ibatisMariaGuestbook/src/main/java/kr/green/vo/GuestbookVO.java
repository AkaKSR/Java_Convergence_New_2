package kr.green.vo;

import java.util.Date;

public class GuestbookVO {
	private int idx;
	private String name;
	private String password;
	private String content;
	private Date regDate;
	private String ip;

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		return "GuestbookVO [idx=" + idx + ", name=" + name + ", password=" + password + ", content=" + content
				+ ", regDate=" + regDate + ", ip=" + ip + "]";
	}

}
