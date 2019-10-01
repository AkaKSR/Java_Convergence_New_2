package kr.green.memo.service;

import java.sql.Connection;

import org.apache.log4j.Logger;

import kr.green.jdbc.JdbcUtil;
import kr.green.memo.dao.MemoDAO;
import kr.green.memo.vo.MemoVO;


public class MemoService {
	// 싱글톤 패턴으로 생성
	private static MemoService instance = new MemoService();
	private MemoService() {}
	public static MemoService getInstance() {
		return instance;
	}
	
	// Log4j 초기화
	private static Logger logger = Logger.getLogger(MemoService.class);
	// 1. 목록보기
	
	
	// 2. 저장하기
	public int insert(MemoVO vo) {
		int count = 0;
		logger.debug("MemoService insert 인수 : " + vo);
		Connection conn = null;
		MemoDAO dao = null;
		try {
			conn = JdbcUtil.getConnection();
			dao = MemoDAO.getInstance();
			conn.setAutoCommit(false);
			// ====================================== //
			if (vo != null) count = dao.insert(conn, vo);
			// ====================================== //
			conn.commit();
		} catch (Exception e) {
			JdbcUtil.rollback(conn);
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		logger.debug("MemoService insert 리턴값 : " + count);
		return count;
	}
	
	// 3. 수정하기
	public void update(MemoVO vo) {
		logger.debug("MemoService update 인수 : " + vo);
		Connection conn = null;
		MemoDAO dao = null;
		try {
			conn = JdbcUtil.getConnection();
			dao = MemoDAO.getInstance();
			conn.setAutoCommit(false);
			// ====================================== //
			MemoVO dbVO = dao.selectByIdx(conn, vo.getIdx());
			if (dbVO != null && dbVO.getPassword().equals(vo.getPassword())) {
				dao.update(conn, vo);
			}
			// ====================================== //
			conn.commit();
		} catch (Exception e) {
			JdbcUtil.rollback(conn);
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	// 4. 삭제하기
	public void delete(MemoVO vo) {
		logger.debug("MemoService update 인수 : " + vo);
		Connection conn = null;
		MemoDAO dao = null;
		try {
			conn = JdbcUtil.getConnection();
			dao = MemoDAO.getInstance();
			conn.setAutoCommit(false);
			// ====================================== //
			MemoVO dbVO = dao.selectByIdx(conn, vo.getIdx());
			if (dbVO != null && dbVO.getPassword().equals(vo.getPassword())) {
				dao.delete(conn, vo.getIdx());
			}
			// ====================================== //
			conn.commit();
		} catch (Exception e) {
			JdbcUtil.rollback(conn);
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
}
