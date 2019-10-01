package kr.green.memo.vo;

import java.util.List;

public class Paging<T> {
	// 입력 4개
	private int totalCount;
	private int currentPage;
	private int pageSize;
	private int blockSize;
	
	// 계산 5개
	private int stotalPage;
	private int startNo;
	private int endNo;
	private int startPage;
	private int endPage;
	
	private List<T> list;

	public Paging(int totalCount, int currentPage, int pageSize, int blockSize) {
		super();
		this.totalCount = totalCount;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.blockSize = blockSize;
		calc();
	}
	
	private void calc() {
		
	}
