package kr.green.board.vo;

import java.util.Date;
import java.util.List;

/*
create table freeboard(
   idx 		int primary key auto_increment,  	-- 키필드
   name 	varchar(50) not null,				-- 작성자
   password varchar(50) not null,				-- 암호
   subject  varchar(200) not null,				-- 제목
   content  text not null,						-- 내용
   regDate  timestamp default now(),			-- 작성일
   hit      int  	  default 0,				-- 조회수
   good     int  	  default 0,				-- 추천
   bad      int  	  default 0,				-- 비추천
   ip       varchar(20) not null				-- IP
);
 */
public class BoardVO {
	private int idx;
	private String name;
	private String password;
	private String subject;
	private String content;
	private Date regDate;
	private int hit;
	private int good;
	private int bad;
	private String ip;
	// ======================================================================= //
	// 댓글을 처리하기 위하여 두개의 필드를 추가
	private int commentCount; // 리스트에서 댓글의 개수 출력을 위해 추가
	private List<CommentVO> commentList; // view.jsp에서 하단에 댓글 출력을 위해 추가

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public List<CommentVO> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<CommentVO> commentList) {
		this.commentList = commentList;
	}
	// ======================================================================= //

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

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public int getGood() {
		return good;
	}

	public void setGood(int good) {
		this.good = good;
	}

	public int getBad() {
		return bad;
	}

	public void setBad(int bad) {
		this.bad = bad;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		return "BoardVO [idx=" + idx + ", name=" + name + ", password=" + password + ", subject=" + subject
				+ ", content=" + content + ", regDate=" + regDate + ", hit=" + hit + ", good=" + good + ", bad=" + bad
				+ ", ip=" + ip + ", commentCount=" + commentCount + ", commentList=" + commentList + "]";
	}

}
