package kr.green.memo.vo;

import java.util.Date;

//데이블 1개당 1개의 VO를 만드는데 테이블 구조와 1대1로 대응되도록 만든다.
public class MemoVO {
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "MemoVO [idx=" + idx + ", name=" + name + ", password=" + password + ", content=" + content
				+ ", regDate=" + regDate + ", ip=" + ip + "]";
	}

}
