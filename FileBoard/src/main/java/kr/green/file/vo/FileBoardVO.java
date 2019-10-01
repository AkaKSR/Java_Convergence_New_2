package kr.green.file.vo;

import java.util.Date;
import java.util.List;

public class FileBoardVO {
	private int idx;
	private String name;
	private String password;
	private String subject;
	private String content;
	private Date regDate;
	private String ip;

	// 필드 추가 시작
	private int fileCount; // 첨부 파일의 개수를 저장할 필드
	private List<FileBoardFileVO> fileList; // 첨부파일 목록
	// 필드 추가 종료

	public List<FileBoardFileVO> getFileList() {
		return fileList;
	}

	public void setFileList(List<FileBoardFileVO> fileList) {
		this.fileList = fileList;
	}

	public int getFileCount() {
		return fileCount;
	}

	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
	}

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

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
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
		return "FileBoardVO [idx=" + idx + ", name=" + name + ", password=" + password + ", subject=" + subject
				+ ", content=" + content + ", regDate=" + regDate + ", ip=" + ip + ", fileCount=" + fileCount
				+ ", fileList=" + fileList + "]";
	}
}
