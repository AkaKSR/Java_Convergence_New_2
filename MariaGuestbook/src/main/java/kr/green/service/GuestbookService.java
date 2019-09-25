package kr.green.service;

import java.sql.Connection;
import java.util.List;

import kr.green.dao.GuestbookDAO;
import kr.green.jdbc.JdbcUtil;
import kr.green.vo.GuestbookVO;
import kr.green.vo.Paging;

public class GuestbookService {
	// ==================================================== //
	// 싱글톤 패턴
	// 1. 자기 자신의 객체를 정적 멤버로 가진다.
	private static GuestbookService instance = new GuestbookService();

	// 2. 밖에서 객체를 생성하지 못하도록 생성자를 private으로 만든다.
	private GuestbookService() {
	}

	// 3. 밖에서 이미 만들어진 객체를 사용할 수 있도록 정적 메서드를 생성한다.
	public static GuestbookService getInstance() {
		return instance;
	}

	// ==================================================== //
	// 1. 목록보기
	public Paging<GuestbookVO> selectList(int currentPage, int pageSize, int blockSize) {
		Paging<GuestbookVO> paging = null; // 변경되는 부분
		Connection conn = null;
		GuestbookDAO dao = null;
		try {
			conn = JdbcUtil.getConnection();
			conn.setAutoCommit(false);
			dao = GuestbookDAO.getInstance();
			// ==================================================== //
			// 변경되는 부분
			// 1. 전체 레코드 개수 구하기 --- 이 값이 있어야만 페이지 계산이 가능
			int totalCount = dao.selectCount(conn);
			// 2. 페이징 객체 생성
			paging = new Paging<>(totalCount, currentPage, pageSize, blockSize);
			// 3. 1페이지 분량의 글 목록을 읽어서 paging 객체에 넣어준다.
			List<GuestbookVO> list = dao.selectList(conn, paging.getStartNo(), paging.getPageSize());
			paging.setList(list);
			// ==================================================== //
			conn.commit();
		} catch (Exception e) {
			JdbcUtil.rollback(conn);
		} finally {
			JdbcUtil.close(conn);
		}
		return paging; // 변경되는 부분
	}
	// 2. 저장하기
	public int insert(GuestbookVO vo) {
		int count = 0; // 변경되는 부분
		Connection conn = null;
		GuestbookDAO dao = null;
		try {
			conn = JdbcUtil.getConnection();
			conn.setAutoCommit(false);
			dao = GuestbookDAO.getInstance();
			// ==================================================== //
			// 변경되는 부분
			if (vo != null && vo.getName() != null && vo.getPassword() != null && vo.getContent() != null) {
				count = dao.insert(conn, vo);
			}
			// ==================================================== //
			conn.commit();
		} catch (Exception e) {
			JdbcUtil.rollback(conn);
		} finally {
			JdbcUtil.close(conn);
		}
		return count; // 변경되는 부분
	}
	// 3. 수정하기
	public int update(GuestbookVO vo) {
		int count = 0; // 변경되는 부분
		Connection conn = null;
		GuestbookDAO dao = null;
		try {
			conn = JdbcUtil.getConnection();
			conn.setAutoCommit(false);
			dao = GuestbookDAO.getInstance();
			// ==================================================== //
			// 변경되는 부분
			if (vo != null && vo.getName() != null && vo.getPassword() != null && vo.getContent() != null) {
				count = dao.update(conn, vo);
			}
			// ==================================================== //
			conn.commit();
		} catch (Exception e) {
			JdbcUtil.rollback(conn);
		} finally {
			JdbcUtil.close(conn);
		}
		return count; // 변경되는 부분
	}
	// 4. 삭제하기
	public int delete(GuestbookVO vo, int idx) {
		int count = 0; // 변경되는 부분
		Connection conn = null;
		GuestbookDAO dao = null;
		try {
			conn = JdbcUtil.getConnection();
			conn.setAutoCommit(false);
			dao = GuestbookDAO.getInstance();
			// ==================================================== //
			// 변경되는 부분
			if (vo != null && vo.getName() != null && vo.getPassword() != null && vo.getContent() != null) {
				count = dao.delete(conn, idx);
			}
			// ==================================================== //
			conn.commit();
		} catch (Exception e) {
			JdbcUtil.rollback(conn);
		} finally {
			JdbcUtil.close(conn);
		}
		return count; // 변경되는 부분
	}
	// ==================================================== //
}
